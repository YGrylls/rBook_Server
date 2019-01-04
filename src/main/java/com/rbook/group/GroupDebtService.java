package com.rbook.group;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.GroupDAO;
import com.rbook.DAO.GroupDebtDAO;
import com.rbook.entity.GroupDebt;
import com.rbook.mapperObject.GroupDebtNode;
import com.rbook.util.UID;

@Service
public class GroupDebtService {

	@Autowired
	private GroupDAO groupDAO;

	@Autowired
	private GroupDebtDAO groupDebtDAO;

	public boolean canPropose(String username, String uuid, String[] targetList) {
		try {
			int personal = groupDebtDAO.checkPersonalStatus(username, uuid);
			if (personal != 1)
				return false;
			int debtNum = groupDebtDAO.getDebtNum(uuid);
			if (debtNum >= 100)
				return false;
			int target = groupDebtDAO.checkTargetInGroup(targetList, uuid);
			if (target != targetList.length)
				return false;

			for (String n : targetList) {
				if (n.equals(username))
					return false;
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public GroupDebt proposeGroupDebt(String username, String guid, String desc, int num, String[] targetList) {
		if (!canPropose(username, guid, targetList))
			return null;
		GroupDebtNode newNode = groupDebtDAO.createGroupDebt(username, guid, UID.generate(), desc, num,
				LocalTime.now().toString());
		assert (newNode != null);
		GroupDebt res = newNode.toEntity();
		int count = groupDebtDAO.linkGroupDebt(newNode.toEntity().getUuid(), targetList);
		assert (count == targetList.length);
		return res;

	}

	@Transactional
	public boolean deleteGroupDebt(String username, String[] deleteList) {
		return false;
	}

}
