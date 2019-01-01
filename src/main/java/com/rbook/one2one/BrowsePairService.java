package com.rbook.one2one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbook.DAO.One2OneDebtDAO;
import com.rbook.DAO.One2OneDebtRel;
import com.rbook.entity.One2OneDebt;
import com.rbook.entity.Pair;
import com.rbook.entity.User;

@Service
public class BrowsePairService {

	@Autowired
	private One2OneDebtDAO one2OneDAO;

	public Map<String, Pair> browsePair(String username) {
		HashMap<String, Pair> map = null;
		try {
			List<One2OneDebtRel> resList = one2OneDAO.findAllDebts(username);

			map = new HashMap<String, Pair>();
			for (One2OneDebtRel o : resList) {
				One2OneDebt debt = o.toEntity();
				User counter = null;
				boolean out = true;
				boolean unread = false;
				if (debt.getStatus() != 0) {
					unread = true;
				}
				if (debt.getStart().getUsername().equals(username)) {
					counter = debt.getEnd();
				} else {
					counter = debt.getStart();
					out = false;
				}
				if (map.containsKey(counter.getUsername())) {
					map.get(counter.getUsername()).update(debt.getNum(), out, debt.getDate(), unread);
				} else {
					map.put(counter.getUsername(), new Pair(counter.getUsername(), debt.getNum(), unread,
							debt.getDate(), counter.getNickname()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;

	}

}
