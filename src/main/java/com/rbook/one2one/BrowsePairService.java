package com.rbook.one2one;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbook.DAO.One2OneDebtDAO;
import com.rbook.entity.User;
import com.rbook.mapperObject.One2OneDebtRel;
import com.rbook.model.Pair;

@Service
public class BrowsePairService {

	@Autowired
	private One2OneDebtDAO one2OneDAO;

	public Map<String, Pair> browsePair(String username) {
		HashMap<String, Pair> map = null;
		try {
			List<One2OneDebtRel> resList = one2OneDAO.findAllDebts(username);
			System.out.println("-------list---\n" + resList + "\n----------");
			map = new HashMap<String, Pair>();
			for (One2OneDebtRel o : resList) {
				One2OneDebtRel debt = o;
				User counter = null;
				boolean out = true;
				boolean unread = false;
				int x = 1;
				if (debt.getStatus() != 0) {
					unread = true;
				}
				if (debt.getStart().getUsername().equals(username)) {
					counter = debt.getEnd().toEntity();
				} else {
					counter = debt.getStart().toEntity();
					out = false;
				}
				if (map.containsKey(counter.getUsername())) {
					System.out.println("-------map update---\n");
					if (unread)
						x = 0;
					map.get(counter.getUsername()).update(debt.getNumber() * x, out, debt.getDate(), unread);
				} else {
					if (unread)
						x = 0;
					if (!out)
						x = -x;
					map.put(counter.getUsername(), new Pair(counter.getUsername(), debt.getNumber() * x, unread,
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
