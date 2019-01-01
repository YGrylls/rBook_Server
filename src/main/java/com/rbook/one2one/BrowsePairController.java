package com.rbook.one2one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rbook.common.IfSuccessResponse;

public class BrowsePairController {

	@ResponseBody
	@PostMapping("/browsePairDebt")
	public IfSuccessResponse addPairDebt(HttpServletRequest request, HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Error", null);
	}

	@ResponseBody
	@PostMapping("/certainPairDebt")
	public IfSuccessResponse addPairDebt(@RequestBody One2OneSelectRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Error", null);
	}

}
