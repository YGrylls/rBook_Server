package com.rbook.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.DAO.UserDAO;
import com.rbook.util.Encoder;

@Service
public class SignupService {

	@Autowired
	private UserDAO userdao;

	public Boolean ifExist(String name, String identity) {
		int res = userdao.checkExistList(name, identity);
		return (res != 0);
	}

	@Transactional
	public Boolean createNewUser(String username, String password, String identity, String nickname) {
		try {
			String encodedPW = Encoder.encryptBasedDes(password);
			userdao.addUser(username, encodedPW, identity, 0, 5, nickname);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}
}
