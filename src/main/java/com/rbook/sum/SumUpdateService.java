package com.rbook.sum;

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
		List<One2OneDebtRel> debtList = debtDAO.findAllDebts(username);

	}

	public void updateLoopCheck(String username) {
		userDAO.addNewLoop(username);
	}

}
