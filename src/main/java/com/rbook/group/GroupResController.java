package com.rbook.group;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.AcceptGroupResRequest;
import com.rbook.common.GroupRequest;
import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.GroupRes;
import com.rbook.entity.User;
import com.rbook.model.GroupResInfo;
import com.rbook.user.LoginService;

@RestController
public class GroupResController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private GroupResService groupResService;

	@ResponseBody
	@PostMapping("/browseRes")
	public IfSuccessResponse browseRes(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// get a res list that is related to self

		List<GroupResInfo> res = groupResService.browseGroupRes(user.getUsername(), req.getUuid());

		if (res != null) {
			Collections.sort(res);
			return new IfSuccessResponse(0, "Success", res);
		} else {
			return new IfSuccessResponse(2, "Failed", null);
		}
	}

	@ResponseBody
	@PostMapping("/acceptRes")
	public IfSuccessResponse acceptRes(@RequestBody AcceptGroupResRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// confirm a res
		GroupRes res = groupResService.acceptGroupRes(user.getUsername(), req.getUuid(), req.isIfStart());
		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);
		} else {
			return new IfSuccessResponse(2, "Failed, are you in this result or right position?", null);
		}
	}

}
