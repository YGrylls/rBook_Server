package com.rbook.one2one;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.One2OneDebtDAO;
import com.rbook.DAO.One2OneDebtRel;
import com.rbook.DAO.UserDAO;
import com.rbook.entity.One2OneDebt;

@Service
public class ConstructService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private One2OneDebtDAO one2OneDebtDAO;

	@Transactional
	public One2OneDebt constructPair(String username, String counterName, String desc) {

		One2OneDebtRel debt = null;

		int check = userDAO.checkUser(counterName);
		if (check == 0) {
			return null;
		}

		List<One2OneDebtRel> debtList = one2OneDebtDAO.findDebts(username, counterName);
		System.out.println("----------debtList-----\n" + debtList + "\n-------");
		if (debtList.size() != 0) {
			return null;
		}
		debt = one2OneDebtDAO.createDebt(username, counterName, 0, desc, LocalDate.now().toString(), 1, true);
		System.out.println("----------NewDebt-----\n" + debt + "\n-------");
		if (debt != null) {
			return debt.toEntity();

		} else
			return null;

	}

}
