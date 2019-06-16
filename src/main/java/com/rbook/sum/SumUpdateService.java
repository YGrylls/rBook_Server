package com.rbook.sum;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.One2OneDebtDAO;
import com.rbook.DAO.UserDAO;
import com.rbook.mapperObject.One2OneDebtRel;
import com.rbook.mapperObject.UserNode;

@Service
@Transactional
public class SumUpdateService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private One2OneDebtDAO debtDAO;

	public void update() {
		List<UserNode> list = userDAO.removeOldTotalAccount();
		assert (list != null);
		userDAO.removeOldLoop();
		userDAO.updateAllTotalAccount();
		for (UserNode u : list) {
			updateRankStatus(u.getUsername());
			updateLoopCheck(u.getUsername());
		}
	}

	public void updateRankStatus(String username) {
		LocalDate time = LocalDate.now();
		List<One2OneDebtRel> debtList = debtDAO.findAllDebts(username);
		double temp = 0;
		for (One2OneDebtRel r : debtList) {
			if (r.getStatus() == 1 || r.getStatus() == 4)
				continue;
			LocalDate debtTime = r.getDate();
			if (debtTime.isAfter(time))
				continue;
			int x = 1;
			if (r.getStart().getUsername() != username) {
				x = -1;
			}
			long t = debtTime.until(time, ChronoUnit.DAYS);
			temp += x * Math.sqrt(t) * r.getNumber() / 100001;
		}
		int n = 1;
		if (temp < 0)
			n = -1;
		long res = (long) (Math.atan(temp * 0.1) * 50 / 1.571 + 50);
		userDAO.setRank(username, res);

	}

	public void updateLoopCheck(String username) {
		userDAO.addNewLoop(username);
		userDAO.deleteDuplicateLoop();
	}

}
