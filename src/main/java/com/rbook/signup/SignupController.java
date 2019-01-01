package com.rbook.signup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.IfSuccessResponse;

@RestController
public class SignupController {

	@Autowired
	private SignupService signupService;

	@ResponseBody
	@PostMapping("/signup")
	public IfSuccessResponse signUp(@RequestBody SignupRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("---------signup start---------");
		if (req.signInCheck()) {
			String username = req.getUsername();
			String password = req.getPassword();
			String identity = req.getIdentity();
			String nickname = req.getNickname();
			if (signupService.ifExist(username, identity)) {
				return new IfSuccessResponse(1, "User or Identity exists", null);
			}
			if (signupService.createNewUser(username, password, identity, nickname)) {

				System.out.println("---------signup success---------");

				return new IfSuccessResponse(0, "Success", null);
			} else {
				return new IfSuccessResponse(2, "DB Error", null);
			}

		} else {
			return new IfSuccessResponse(-1, "Input invalidate", null);
		}
	}
}
