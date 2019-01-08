package com.rbook.group;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.GroupDebtDAO;
import com.rbook.entity.GroupDebt;
import com.rbook.entity.User;
import com.rbook.mapperObject.GroupDebtNode;
import com.rbook.mapperObject.UserNode;
import com.rbook.model.GroupDebtInfo;
import com.rbook.util.UID;

@Service
public class GroupDebtService {

	@Autowired
	private GroupDebtDAO groupDebtDAO;

	public boolean canPropose(String username, String uuid, String[] targetList) {
		try {
			for (String n : targetList) { // check if self is the target
				if (n.equals(username))
					return false;
			}
			int personal = groupDebtDAO.checkPersonalStatus(username, uuid);
			if (personal != 1)
				return false;
			int debtNum = groupDebtDAO.getDebtNum(uuid);
			if (debtNum >= 100)
				return false;
			int target = groupDebtDAO.checkTargetInGroup(targetList, uuid);
			if (target != targetList.length)
				return false;

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
	public boolean deleteGroupDebt(String username, String guid, String[] deleteList) {
		if (!canDelete(username, guid, deleteList)) {
			return false;
		}
		groupDebtDAO.deleteGroupDebt(deleteList);
		return true;

	}

	public boolean canDelete(String username, String uuid, String[] targetList) {
		try {
			int personal = groupDebtDAO.checkPersonalStatus(username, uuid);
			if (personal != 1)
				return false;
			int target = groupDebtDAO.checkDeleteTarget(username, targetList, uuid);
			if (target != targetList.length)
				return false;
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public List<GroupDebtInfo> browseGroupDebt(String username, String uuid) {

		ArrayList<GroupDebtInfo> resList = null;
		try {
			Map<GroupDebtNode, UserNode> queryRes = groupDebtDAO.findGroupDebt(uuid);
			assert (queryRes != null);
			resList = new ArrayList<GroupDebtInfo>();
			for (Map.Entry<GroupDebtNode, UserNode> g : queryRes.entrySet()) {
				GroupDebt debt = g.getKey().toEntity();
				User proposer = g.getValue().toEntity();
				resList.add(new GroupDebtInfo(debt, proposer, username));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resList = null;
		}
		return resList;

	}

	public List<User> browseDebtMember(String uuid) {
		ArrayList<User> resList = null;
		try {
			List<UserNode> queryRes = groupDebtDAO.findGroupDebtMember(uuid);
			assert (queryRes != null && queryRes.size() > 0);
			resList = new ArrayList<User>();
			for (UserNode n : queryRes) {
				resList.add(n.toEntity());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resList = null;
		}
		return resList;

	}

}
