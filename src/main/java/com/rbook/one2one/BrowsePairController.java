package com.rbook.one2one;

import java.util.Map;

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
import com.rbook.model.Pair;

@RestController
public class BrowsePairController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private BrowsePairService browsePairService;

	@ResponseBody
	@PostMapping("/browsePair")
	public IfSuccessResponse browsePairDebt(@RequestBody BrowseRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		Map<String, Pair> map = browsePairService.browsePair(user.getUsername());
		if (map != null) {
			System.out.println("------------browsePair---------\n" + map);
			return new IfSuccessResponse(0, "Success", map);
		} else {
			return new IfSuccessResponse(1, "Fetch Error", null);
		}
	}

}
