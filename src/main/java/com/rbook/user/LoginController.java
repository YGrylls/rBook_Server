package com.rbook.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.IfSuccessResponse;
import com.rbook.entity.User;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@ResponseBody
	@PostMapping("/login")
	public IfSuccessResponse logIn(@RequestBody LoginRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (req.signInCheck()) {
			User user = loginService.loginCheck(req.getUsername(), req.getPassword());
			if (user == null) {
				return new IfSuccessResponse(1, "Username or Password Wrong", null);
			}
			return new IfSuccessResponse(0, "Success", user);
		} else {
			return new IfSuccessResponse(-1, "Input invalidate", null);
		}
	}

}
