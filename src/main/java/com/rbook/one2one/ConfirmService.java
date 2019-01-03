package com.rbook.one2one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.One2OneDebtDAO;
import com.rbook.entity.One2OneDebt;

@Service
public class ConfirmService {

	@Autowired
	private One2OneDebtDAO debtDao;

	@Transactional
	public One2OneDebt accept(String username, String countername, String id, int status) {
		One2OneDebt res = null;
		One2OneDebt debt = debtDao.getDebt(id).toEntity();
		System.out.println("--------debt\n" + debt + "\n--------");
		if (!(debt.getStart().equals(username) && debt.getEnd().equals(countername)
				|| debt.getStart().equals(countername) && debt.getEnd().equals(username))) {
			return null;
		} else if (debt.getStart().equals(username) == debt.isProposal()) {
			return null;
		}

		switch (status) {
		case 1: {
			res = debtDao.setStatus(id, 0).toEntity();
			break;
		}
		case 2: {
			debtDao.deleteDebt(id);
			res = debt;
			break;
		}
		case 4: {
			res = debtDao.setStatus(id, 0).toEntity();
			debtDao.confirmCombineDelete(username, countername);
			break;
		}
		default: {
			res = null;
		}
		}
		return res;
	}

	@Transactional
	public One2OneDebt refuse(String username, String countername, String id, int status) {
		One2OneDebt res = null;
		One2OneDebt debt = debtDao.getDebt(id).toEntity();
		if (!(debt.getStart().equals(username) && debt.getEnd().equals(countername)
				|| debt.getStart().equals(countername) && debt.getEnd().equals(username))) {
			return null;
		} else if (debt.getStart().equals(username) == debt.isProposal()) {
			return null;
		}

		switch (status) {
		case 1: {
			debtDao.deleteDebt(id);
			res = debt;
			break;
		}
		case 2: {
			res = debtDao.setStatus(id, 0).toEntity();
			break;
		}
		case 4: {
			debtDao.deleteDebt(id);
			debtDao.confirmCombineCancel(username, countername);
			res = debt;
			break;
		}
		default: {
			res = null;
		}
		}
		return res;

	}

}
