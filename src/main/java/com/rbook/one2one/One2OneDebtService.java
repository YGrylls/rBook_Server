package com.rbook.one2one;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbook.DAO.One2OneDebtDAO;
import com.rbook.DAO.One2OneDebtRel;
import com.rbook.entity.One2OneDebt;

@Service
public class One2OneDebtService {

	@Autowired
	private One2OneDebtDAO one2OneDebtDAO;

	public List<One2OneDebt> browsePairDebt(String username, String counter) {
		List<One2OneDebtRel> list = null;
		ArrayList<One2OneDebt> res = null;

		try {
			list = one2OneDebtDAO.findDebts(username, counter);
			res = new ArrayList<One2OneDebt>();
			for (One2OneDebtRel o : list) {
				res.add(o.toEntity());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}

}
