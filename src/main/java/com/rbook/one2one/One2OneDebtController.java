package com.rbook.one2one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.IfSuccessResponse;

@RestController
public class One2OneDebtController {

	@ResponseBody
	@PostMapping("/addPairDebt")
	public IfSuccessResponse addPairDebt(@RequestBody One2OneDebtRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/deletePairDebt")
	public IfSuccessResponse addPairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/combinePairDebt")
	public IfSuccessResponse combinePairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Error", null);
	}

}
