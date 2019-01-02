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
	public One2OneDebt accept(String username, String countername, long id, int status) {
		One2OneDebt res = null;
		long[] idList = { id };
		int check = debtDao.checkStatus(username, countername, idList, status);
		if (check != 0)
			return null;
		switch (status) {
		case 1: {
			res = debtDao.setStatus(id, 0).toEntity();
		}
		case 2: {
			debtDao.deleteDebt(id);
			res = new One2OneDebt(id, -1, -1, null, null, null, null, false);
		}
		case 4: {
			res = debtDao.setStatus(id, 0).toEntity();
			debtDao.confirmCombineDelete(username, countername);
		}
		default: {
			res = null;
		}
		}
		return res;
	}

	@Transactional
	public One2OneDebt refuse(String username, String countername, long id, int status) {
		One2OneDebt res = null;
		long[] idList = { id };
		int check = debtDao.checkStatus(username, countername, idList, status);
		if (check != 0)
			return null;

		switch (status) {
		case 1: {
			debtDao.deleteDebt(id);
			res = new One2OneDebt(id, -1, -1, null, null, null, null, false);
		}
		case 2: {
			res = debtDao.setStatus(id, 0).toEntity();
		}
		case 4: {
			debtDao.deleteDebt(id);
			debtDao.confirmCombineCancel(username, countername);
			res = new One2OneDebt(id, -1, -1, null, null, null, null, false);
		}
		default: {
			res = null;
		}
		}
		return res;

	}

}
