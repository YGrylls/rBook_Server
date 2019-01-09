package com.rbook.group;

import java.util.List;

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
import com.rbook.model.GroupInfo;
import com.rbook.user.LoginService;

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
		List<GroupInfo> list = groupService.browseGroup(user.getUsername());
		if (list != null) {
			return new IfSuccessResponse(0, "Success", list);

		} else
			return new IfSuccessResponse(1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/browseCertainGroup")
	public IfSuccessResponse browseCertainGroup(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		GroupInfo res = groupService.browseCertainGroup(user.getUsername(), req.getUuid());
		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);

		} else
			return new IfSuccessResponse(1, "Error", null);
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
		GroupInfo info = groupService.createGroup(user.getUsername(), req.getGroupName());
		if (info == null) {
			return new IfSuccessResponse(1, "Create Failed", null);
		} else {
			return new IfSuccessResponse(0, "Success", null);
		}
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

		GroupInfo info = groupService.addGroup(user.getUsername(), req.getUuid());

		if (info == null) {
			return new IfSuccessResponse(1, "Group may not exist, be full or already confirmed", null);
		} else {
			return new IfSuccessResponse(0, "Success", null);
		}
	}

	@ResponseBody
	@PostMapping("/browseGroupMember")
	public IfSuccessResponse browseGroupMember(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}

		// return a list of user
		List<User> res = groupService.browseGroupMember(req.getUuid(), user.getUsername());
		if (res == null) {
			return new IfSuccessResponse(1, "Failed, not in group or group not exist", null);
		} else {
			return new IfSuccessResponse(0, "Success", res);
		}
	}

	@ResponseBody
	@PostMapping("/browseGroupMemberExceptSelf")
	public IfSuccessResponse browseGroupMemberExceptSelf(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}

		// return a list of user
		List<User> res = groupService.browseGroupMemberExceptSelf(req.getUuid(), user.getUsername());
		if (res == null) {
			return new IfSuccessResponse(1, "Failed, not in group or group not exist", null);
		} else {
			return new IfSuccessResponse(0, "Success", res);
		}
	}

}
