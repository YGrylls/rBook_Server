package com.rbook.one2one;

import java.util.List;

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
public class One2OneDebtController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private One2OneDebtService one2OneDebtService;

	@ResponseBody
	@PostMapping("/addPairDebt")
	public IfSuccessResponse addPairDebt(@RequestBody One2OneDebtRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/deletePairDebt")
	public IfSuccessResponse deletePairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("-------------delete-------------\n" + req);

		return new IfSuccessResponse(-1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/combinePairDebt")
	public IfSuccessResponse combinePairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("-------------combine-------------\n" + req);

		return new IfSuccessResponse(-1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/browsePairDebt")
	public IfSuccessResponse certainPairDebt(@RequestBody CounterRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("----------req----------\n" + req + "\n-----------");
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		List<One2OneDebt> list = one2OneDebtService.browsePairDebt(user.getUsername(), req.getCounter());
		if (list != null) {
			System.out.println("------------browsePairDebt---------\n" + list);
			return new IfSuccessResponse(0, "Sueccess", list);
		} else {
			return new IfSuccessResponse(1, "Fetch Error", null);
		}
	}

}
