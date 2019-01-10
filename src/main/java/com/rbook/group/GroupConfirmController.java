package com.rbook.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.GroupRequest;
import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.Group;
import com.rbook.entity.User;
import com.rbook.user.LoginService;

@RestController
public class GroupConfirmController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private GroupConfirmService groupConfirmService;

	@ResponseBody
	@PostMapping("/confirmGroup")
	public IfSuccessResponse confirmGroup(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// confirm a group
		Group res = groupConfirmService.personalGroupConfirm(user.getUsername(), req.getUuid());
		if (res == null) {
			return new IfSuccessResponse(1, "Failed, not in group or already confirmed", null);
		} else {
			return new IfSuccessResponse(0, "Success", res);
		}
	}

}
