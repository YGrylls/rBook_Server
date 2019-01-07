package com.rbook.group;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.GroupDAO;
import com.rbook.DAO.GroupResDAO;
import com.rbook.entity.Group;
import com.rbook.mapperObject.GroupDebtNode;
import com.rbook.mapperObject.GroupNode;
import com.rbook.mapperObject.GroupResNode;

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
	public boolean groupConfirm(Group group, LocalDate time) {

		Boolean ifAllConfirmed = groupDAO.checkIfAllConfirmed(group.getUuid());
		if (ifAllConfirmed == null || ifAllConfirmed == false) {
			return false;
		}
		List<GroupDebtNode> debtList = groupDAO.confirmGroup(group.getUuid(), time.toString());// time consistent
		assert (debtList != null);
		ArrayList<String> idList = new ArrayList<String>();
		for (GroupDebtNode n : debtList) {
			idList.add(n.toEntity().getUuid());
		}
		List<GroupResNode> resList = groupResDAO.createRes((String[]) idList.toArray());
		assert (resList != null);

		return true;
	}
}
