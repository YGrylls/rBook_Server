package com.rbook.one2one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.One2OneDebt;
import com.rbook.entity.User;
import com.rbook.login.LoginService;

@RestController
public class ConfirmController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private One2OneDebtService one2OneDebtService;

	@Autowired
	private ConfirmService confirmService;

	@ResponseBody
	@PostMapping("/AcceptDebt")
	public IfSuccessResponse acceptPairDebt(@RequestBody ConfirmRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		One2OneDebt res = confirmService.accept(user.getUsername(), req.getCounter(), req.getId(), req.getStatus());
		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);
		}
		return new IfSuccessResponse(2, "Error", null);
	}

	@ResponseBody
	@PostMapping("/RefuseDebt")
	public IfSuccessResponse refusePairDebt(@RequestBody ConfirmRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		One2OneDebt res = confirmService.refuse(user.getUsername(), req.getCounter(), req.getId(), req.getStatus());
		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);
		}
		return new IfSuccessResponse(2, "Error", null);
	}

}
