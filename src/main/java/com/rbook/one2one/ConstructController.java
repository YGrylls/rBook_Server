package com.rbook.one2one;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbook.common.IfSuccessResponse;

@RestController
public class ConstructController {

	@PostMapping("/One2OneConstruct")
	@ResponseBody
	public IfSuccessResponse construct(@RequestBody ConstructRequest req, HttpServletRequest request,
			HttpServletResponse response) {

		return new IfSuccessResponse(-1, "Fail", null);

	}

}
