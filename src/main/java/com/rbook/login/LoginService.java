package com.rbook.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbook.DAO.UserDAO;
import com.rbook.entity.User;
import com.rbook.mapperObject.UserNode;
import com.rbook.util.Encoder;

@Service
public class LoginService {

	@Autowired
	private UserDAO userdao;

	public User loginCheck(String username, String password) {

		String encodedPW = Encoder.encryptBasedDes(password);
		UserNode user = null;
		try {
			List<UserNode> resList = userdao.loginCheck(username, encodedPW);
			for (UserNode o : resList) {
				System.out.println(o);
			}
			if (!resList.isEmpty()) {
				user = resList.get(0);
				return user.toEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
