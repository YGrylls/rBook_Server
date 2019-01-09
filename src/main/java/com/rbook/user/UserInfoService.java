package com.rbook.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbook.DAO.UserDAO;
import com.rbook.entity.LoopCheck;
import com.rbook.mapperObject.LoopCheckNode;

@Service
public class UserInfoService {

	@Autowired
	private UserDAO userDAO;

	public List<LoopCheck> browseLoopCheck(String username) {

		ArrayList<LoopCheck> res = new ArrayList<LoopCheck>();
		List<LoopCheckNode> queryRes = userDAO.getLoopCheck(username);
		for (LoopCheckNode n : queryRes) {
			res.add(n.toEntity());
		}
		return res;
	}

}
