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
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		String username = req.getUsername();
		String counter = req.getCounterName();
		if (one2OneDebtService.ifCanAdd(username, counter)) {
			One2OneDebt debt = one2OneDebtService.addNewDebt(username, counter, req.getNum(), req.getDesc(),
					req.getDirect());
			if (debt == null) {
				return new IfSuccessResponse(2, "DB Error", null);
			}
			System.out.println("------------addPairDebt---------\n" + debt);
			return new IfSuccessResponse(0, "Success", debt);
		} else {
			return new IfSuccessResponse(1, "Can not add debt, auth refused or limit reached", null);
		}
	}

	@ResponseBody
	@PostMapping("/deletePairDebt")
	public IfSuccessResponse deletePairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}
		String username = req.getUsername();
		long[] list = req.getIdList();
		List<One2OneDebt> res = one2OneDebtService.deleteDebt(username, req.getCounter(), list);
		if (res != null) {
			return new IfSuccessResponse(0, "Success", res);
		}
		return new IfSuccessResponse(2, "Error", null);
	}

	@ResponseBody
	@PostMapping("/combinePairDebt")
	public IfSuccessResponse combinePairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
		User user = loginService.loginCheck(req.getUsername(), req.getPassword());
		if (user == null) {
			return new IfSuccessResponse(5, "Auth Failed", null);
		}

		return new IfSuccessResponse(2, "Error", null);
	}

	@ResponseBody
	@PostMapping("/browsePairDebt")
	public IfSuccessResponse certainPairDebt(@RequestBody CounterRequest req, HttpServletRequest request,
			HttpServletResponse response) {
		if (!req.checkValidate()) {
			return new IfSuccessResponse(-1, "Request invalid", null);
		}
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
