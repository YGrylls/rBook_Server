package com.rbook.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.BrowseRequest;
import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.User;
import com.rbook.login.LoginService;

@RestController
public class GroupController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private GroupService groupService;

	@ResponseBody
	@PostMapping("/browseGroup")
	public IfSuccessResponse browseGroup(@RequestBody BrowseRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// create group
		return null;
	}

	@ResponseBody
	@PostMapping("/browseGroupDetail")
	public IfSuccessResponse browseGroupDetail(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// create group
		return null;
	}

	@ResponseBody
	@PostMapping("/createGroup")
	public IfSuccessResponse createGroup(@RequestBody CreateGroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// create group
		return null;
	}

	@ResponseBody
	@PostMapping("/addGroup")
	public IfSuccessResponse addGroup(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// add into a group
		return null;
	}

}
