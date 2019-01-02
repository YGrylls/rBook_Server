package com.rbook.one2one;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public boolean ifCanAdd(String username, String counter) {
		int num = 0;
		try {
			num = one2OneDebtDAO.findAcceptedDebts(username, counter);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return (num >= 1 && num < 100);
	}

	@Transactional
	public One2OneDebt addNewDebt(String username, String counter, int num, String desc, boolean ifUserOut) {
		String starter;
		String ender;
		boolean proposal;
		One2OneDebt debt = null;
		if (ifUserOut) {
			starter = username;
			ender = counter;
			proposal = true;
		} else {
			starter = counter;
			ender = username;
			proposal = false;
		}
		One2OneDebtRel rel = one2OneDebtDAO.createDebt(starter, ender, num, desc, LocalDate.now(), 1, proposal);
		debt = rel.toEntity();
		return debt;
	}

	@Transactional
	public List<One2OneDebt> deleteDebt(String username, String counter, long[] list) {

		ArrayList<One2OneDebt> res = null;

		if (one2OneDebtDAO.checkStatus(username, counter, list, 0) != list.length)
			return null;
		List<One2OneDebtRel> tempStart = one2OneDebtDAO.setStateStart(username, list, 2, true);
		List<One2OneDebtRel> tempEnd = one2OneDebtDAO.setStateEnd(username, list, 2, false);
		assert (tempStart.size() + tempEnd.size() == list.length);
		res = new ArrayList<One2OneDebt>();
		for (One2OneDebtRel o : tempStart) {
			res.add(o.toEntity());
		}
		for (One2OneDebtRel o : tempEnd) {
			res.add(o.toEntity());
		}

		return res;
	}

	public boolean canCombine(String username, String counter) {
		int check = one2OneDebtDAO.checkCombine(username, counter);

		return check != 0;
	}

	@Transactional
	public One2OneDebt combineDebt(String username, String counter, long[] list, String desc) {

		One2OneDebt debt = null;
		String startname = username;
		String endname = counter;
		boolean proposal = true;
		if (one2OneDebtDAO.checkStatus(username, counter, list, 0) != list.length)
			return null;
		List<One2OneDebtRel> tempStart = one2OneDebtDAO.setStateStart(username, list, 3, true);
		List<One2OneDebtRel> tempEnd = one2OneDebtDAO.setStateEnd(username, list, 3, false);
		assert (tempStart.size() + tempEnd.size() == list.length);
		int total = 0;
		for (One2OneDebtRel o : tempStart) {

			total = total + o.getNumber();

		}
		for (One2OneDebtRel o : tempEnd) {

			total = total - o.getNumber();

		}
		if (total < 0) {
			startname = counter;
			endname = username;
			total = 0 - total;
			proposal = false;
		}
		debt = one2OneDebtDAO.createDebt(startname, endname, total, desc, LocalDate.now(), 4, proposal).toEntity();
		return debt;
	}

}
