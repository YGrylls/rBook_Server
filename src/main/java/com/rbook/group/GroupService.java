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
import com.rbook.mapperObject.GroupNodeBoolean;
import com.rbook.mapperObject.GroupNodeInteger;
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
		System.out.println("--------groupcreate------\n" + group + "\n---------\n");
		int count = groupDAO.addGroup(username, group.getUuid(), false);
		assert (count == 1);
		GroupInfo res = new GroupInfo(group, 1, false);
		return res;
	}

	public GroupNodeInteger findGroupMem(String guid) {
		GroupNodeInteger group = null;
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
		GroupNodeInteger find = findGroupMem(guid);
		if (find == null)
			return null;
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
			List<GroupNodeBoolean> memMap = groupDAO.checkAllGroup(username);
			System.out.println("------memMap----\n" + memMap + "\n------\n");
			for (GroupNodeBoolean g : memMap) {
				uuidList.add(g.getGroup().getUuid());
				resList.put(g.getGroup().getName(), new GroupInfo(g.getGroup().toEntity(), -1, g.isConfirm()));
			}
			List<GroupNodeInteger> memMapI = groupDAO.findMultiGroupMem(uuidList.toArray(new String[uuidList.size()]));
			for (GroupNodeInteger g : memMapI) {
				resList.get(g.getGroup().getName()).setMember(g.getMember());
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

	public GroupInfo browseCertainGroup(String username, String guid) {
		try {
			GroupNodeInteger queryRes = groupDAO.findGroupMem(guid);
			if (queryRes == null)
				return null;
			Boolean confirm = groupDAO.getPersonalStatus(username, guid);
			if (confirm == null)
				return null;

			return new GroupInfo(queryRes.getGroup().toEntity(), queryRes.getMember(), confirm);
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

	public List<User> browseGroupMemberExceptSelf(String uuid, String username) {
		ArrayList<User> resList = null;
		boolean ifIn = false;
		try {
			resList = new ArrayList<User>();

			List<UserNode> queryRes = groupDAO.getGroupMember(uuid);
			if (queryRes == null)
				return null;
			for (UserNode un : queryRes) {
				User user = un.toEntity();
				if (user.getUsername().equals(username)) {
					ifIn = true;
					continue;
				}
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
