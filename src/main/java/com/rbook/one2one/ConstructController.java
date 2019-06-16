package com.rbook.one2one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.ConstructRequest;
import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.One2OneDebt;
import com.rbook.entity.User;
import com.rbook.user.LoginService;

@RestController
public class ConstructController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private ConstructService constructService;

	@PostMapping("/One2OneConstruct")
	@ResponseBody
	public IfSuccessResponse construct(@RequestBody ConstructRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		One2OneDebt debt = constructService.constructPair(user.getUsername(), req.getCounterName(), req.getDesc());
		if (debt == null) {
			return new IfSuccessResponse(1, "User not exists or you two already paired", null);
		} else {
			// System.out.println("------------ConstructPair---------\n" + debt);
			return new IfSuccessResponse(0, "Success", debt);
		}

	}

}
