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
import com.rbook.entity.Group;
import com.rbook.entity.User;
import com.rbook.mapperObject.FindGroup;
import com.rbook.mapperObject.GroupNode;
import com.rbook.mapperObject.UserNode;
import com.rbook.model.GroupInfo;
import com.rbook.util.UID;

@Service
public class GroupService {

	@Autowired
	private GroupDAO groupDAO;

	@Transactional
	public GroupInfo createGroup(String username, String groupName) {
		Group group = groupDAO.createGroup(UID.generate(), groupName, 0, LocalDate.now().toString()).toEntity();
		assert (group != null);
		GroupInfo add = addGroup(username, group.getUuid());
		assert (add != null);
		return add;
	}

	public FindGroup findGroupMem(String guid) {
		FindGroup group = null;
		try {
			group = groupDAO.findGroupMem(guid);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return group;
	}

	@Transactional
	public GroupInfo addGroup(String username, String guid) {
		GroupInfo res = null;
		FindGroup find = findGroupMem(guid);
		Group group = find.getGroup().toEntity();
		int mem = find.getMember();
		if (mem >= 0 && mem < 20 && group != null) {
			if (group.getStatus() != 0) {
				return null;
			}
			int count = groupDAO.addGroup(username, group.getUuid(), false);
			assert (count == 1);
			res = new GroupInfo(group, mem, false);

		} else {
			return null;
		}
		return res;
	}

	public List<GroupInfo> browseGroup(String username) {
		ArrayList<GroupInfo> list = new ArrayList<GroupInfo>();
		try {
			ArrayList<String> uuidList = new ArrayList<String>();
			Map<String, GroupInfo> resList = new HashMap<String, GroupInfo>();
			Map<GroupNode, Boolean> memMap = groupDAO.checkAllGroup(username);
			for (Map.Entry<GroupNode, Boolean> g : memMap.entrySet()) {
				uuidList.add(g.getKey().getName());
				resList.put(g.getKey().getName(), new GroupInfo(g.getKey().toEntity(), -1, g.getValue()));
			}
			Map<GroupNode, Integer> memMapI = groupDAO.findMultiGroupMem((String[]) uuidList.toArray());
			for (Map.Entry<GroupNode, Integer> g : memMapI.entrySet()) {
				resList.get(g.getKey().getName()).setMember(g.getValue());
			}
			for (GroupInfo o : resList.values()) {
				list.add(o);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<User> browseGroupMember(String uuid, String username) {
		ArrayList<User> resList = null;
		boolean ifIn = false;
		try {
			resList = new ArrayList<User>();

			List<UserNode> queryRes = groupDAO.getGroupMember(uuid);
			if (queryRes == null)
				return null;
			for (UserNode un : queryRes) {
				User user = un.toEntity();
				if (user.getUsername().equals(username))
					ifIn = true;
				resList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if (ifIn)
			return resList;
		else
			return null;

	}

}
