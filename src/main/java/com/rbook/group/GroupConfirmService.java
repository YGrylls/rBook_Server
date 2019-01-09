package com.rbook.group;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.GroupDAO;
import com.rbook.DAO.GroupResDAO;
import com.rbook.entity.Group;
import com.rbook.entity.GroupDebt;
import com.rbook.mapperObject.ConfirmFindDebt;
import com.rbook.mapperObject.GroupNode;
import com.rbook.mapperObject.GroupResNode;
import com.rbook.model.GroupDebtPair;
import com.rbook.util.UID;

@Service
public class GroupConfirmService {

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private GroupResDAO groupResDAO;

	@Transactional
	public Group personalGroupConfirm(String username, String uuid) {
		LocalDate time = LocalDate.now();
		GroupNode queryResP = groupDAO.setPersonalGroupStatus(username, false, uuid, true);
		if (queryResP == null) {
			return null;
		} else {
			Group group = queryResP.toEntity();
			if (groupConfirm(group, time)) {
				group.setStatus(1);
				group.setConfirmTime(time);
			}
			return group;
		}
	}

	@Transactional
	public boolean groupConfirm(Group group, LocalDate time) {// to fix

		Boolean ifAllConfirmed = groupDAO.checkIfAllConfirmed(group.getUuid());
		if (ifAllConfirmed == null || ifAllConfirmed == false) {
			return false;
		}
		List<ConfirmFindDebt> debtList = groupDAO.confirmGroup(group.getUuid(), time.toString());// time consistent
		assert (debtList != null);
		if (debtList.size() == 0) {
			int count = groupDAO.confirmEmptyGroup(group.getUuid(), time.toString());
			assert (count == 1);
			return true;
		}
		ArrayList<String> idList = new ArrayList<String>();
		HashMap<GroupDebtPair, Integer> resMap = new HashMap<GroupDebtPair, Integer>();
		for (ConfirmFindDebt n : debtList) {
			String start = n.getStart();
			String end = n.getEnd();
			GroupDebt debt = n.getGroupDebt().toEntity();
			GroupDebtPair pair = new GroupDebtPair(start, end);
			boolean flg = pair.changePosition();
			if (resMap.containsKey(pair)) {
				int oldValue = resMap.get(pair);
				int newValue = 0;
				if (flg) {
					newValue = oldValue - debt.getNum();
				} else {
					newValue = oldValue + debt.getNum();
				}
				resMap.put(pair, newValue);

			} else {
				if (flg) {
					resMap.put(pair, debt.getNum());
				} else {
					resMap.put(pair, -debt.getNum());
				}
			}
		}
		for (Map.Entry<GroupDebtPair, Integer> e : resMap.entrySet()) {
			int num = e.getValue();
			String uuid = UID.generate();
			GroupResNode node = null;
			if (num < 0) {
				node = groupResDAO.createRes(e.getKey().getEnd(), e.getKey().getStart(), uuid, -num);
			} else {
				node = groupResDAO.createRes(e.getKey().getStart(), e.getKey().getEnd(), uuid, num);
			}
			assert (node != null);
			idList.add(uuid);
		}

		int linkRes = groupResDAO.linkResToGroup(idList.toArray(new String[idList.size()]), group.getUuid());
		assert (linkRes == idList.size());

		return true;
	}
}
