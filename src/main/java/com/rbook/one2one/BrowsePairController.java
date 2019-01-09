package com.rbook.one2one;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
import com.rbook.model.Pair;
import com.rbook.user.LoginService;

@RestController
public class BrowsePairController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private BrowsePairService browsePairService;

	@ResponseBody
	@PostMapping("/browsePair")
	public IfSuccessResponse browsePairDebt(@RequestBody BrowseRequest req, HttpServletRequest request,
			HttpServletResponse response) { // need be sorted
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		Map<String, Pair> map = browsePairService.browsePair(user.getUsername());
		if (map != null) {
			List<Pair> res = null;
			Collection<Pair> value = map.values();
			res = new ArrayList<Pair>(value);
			Collections.sort(res);
			System.out.println("------------browsePair---------\n" + res);
			return new IfSuccessResponse(0, "Success", res);
		} else {
			return new IfSuccessResponse(1, "Fetch Error", null);
		}
	}

}
