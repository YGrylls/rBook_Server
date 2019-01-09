package com.rbook.group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.GroupResDAO;
import com.rbook.entity.GroupRes;
import com.rbook.entity.User;
import com.rbook.mapperObject.GroupResNode;
import com.rbook.mapperObject.UserNode;
import com.rbook.model.GroupResInfo;

@Service
public class GroupResService {

	@Autowired
	private GroupResDAO groupResDAO;

	public List<GroupResInfo> browseGroupRes(String username, String uuid) {
		ArrayList<GroupResInfo> res = null;
		try {
			Map<GroupResNode, UserNode> queryIn = groupResDAO.getResListIn(username, uuid);
			assert (queryIn != null);
			Map<GroupResNode, UserNode> queryOut = groupResDAO.getResListOut(username, uuid);
			assert (queryOut != null);
			res = new ArrayList<GroupResInfo>();
			for (Map.Entry<GroupResNode, UserNode> o : queryIn.entrySet()) {
				GroupRes tempRes = o.getKey().toEntity();
				User tempUser = o.getValue().toEntity();
				res.add(new GroupResInfo(tempRes, tempUser, username, true));
			}
			for (Map.Entry<GroupResNode, UserNode> o : queryOut.entrySet()) {
				GroupRes tempRes = o.getKey().toEntity();
				User tempUser = o.getValue().toEntity();
				res.add(new GroupResInfo(tempRes, tempUser, username, false));
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = null;
		}
		return res;

	}

	@Transactional
	public void tryCloseGroup(String uuid) {// the uuid here is the uuid of a related groupRes
		List<Boolean> boolList = groupResDAO.getAllResStatusByResID(uuid);
		assert (boolList != null && boolList.size() >= 1);
		boolean flg = true;
		for (Boolean b : boolList) {
			flg = flg && b;
		}
		if (flg) {
			int res = groupResDAO.closeGroup(uuid);
			assert (res == 1);
		}

	}

	@Transactional
	public GroupRes acceptGroupRes(String username, String uuid, boolean ifStart) {
		GroupRes res = null;
		if (ifStart) {
			GroupResNode resNode = groupResDAO.acceptGroupResIn(username, uuid);
			if (resNode == null)
				return null;

			res = resNode.toEntity();
			tryCloseGroup(uuid);

		} else {
			GroupResNode resNode = groupResDAO.acceptGroupResOut(username, uuid);
			if (resNode == null)
				return null;

			res = resNode.toEntity();
			tryCloseGroup(uuid);
		}
		return res;

	}

}
