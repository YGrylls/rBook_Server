package com.rbook.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rbook.common.BrowseRequest;
import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.LoopCheck;
import com.rbook.entity.User;

@Controller
public class UserInfoController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserInfoService userInfoService;

	@ResponseBody
	@PostMapping("/browseUserInfo")
	public IfSuccessResponse browseUserInfo(@RequestBody BrowseRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		List<LoopCheck> res = userInfoService.browseLoopCheck(user.getUsername());
		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);
		} else {
			return new IfSuccessResponse(2, "Failed", null);
		}

	}

}
