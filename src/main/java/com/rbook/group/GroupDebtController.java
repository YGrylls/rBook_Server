package com.rbook.group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.GroupDebt;
import com.rbook.entity.User;
import com.rbook.login.LoginService;

@RestController
public class GroupDebtController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private GroupDebtService groupDebtService;

	@ResponseBody
	@PostMapping("/proposeGroupDebt")
	public IfSuccessResponse proposeGroupDebt(@RequestBody ProposeGroupDebtRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// propose a new group debt

		GroupDebt res = groupDebtService.proposeGroupDebt(user.getUsername(), req.getGuid(), req.getDesc(),
				req.getNum(), req.getTargetList());

		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);
		} else {
			return new IfSuccessResponse(1, "Propose Failed, check your request and target users", null);
		}

	}

	@ResponseBody
	@PostMapping("/deleteGroupDebt")
	public IfSuccessResponse deleteGroupDebt(@RequestBody DeleteGroupDebtRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// delete a group debt
		return null;
	}

	@ResponseBody
	@PostMapping("/browseDebtMember")
	public IfSuccessResponse browseDebtMember(@RequestBody GroupRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		// delete a group debt
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
}
