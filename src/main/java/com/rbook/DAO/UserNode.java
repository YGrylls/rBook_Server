package com.rbook.DAO;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.rbook.entity.User;

@NodeEntity(label = "User")
public class UserNode implements IDataRes {
	public UserNode() {

	}

	@Override
	public String toString() {
		return "UserNode [username=" + username + ", password=" + password + ", ID=" + ID + ", totalAccount="
				+ totalAccount + ", rankStatus=" + rankStatus + "]";
	}

	@Id
	@GeneratedValue
	private long graphId;
	@Property("username")
	private String username;
	@Property("password")
	private String password;
	@Property("ID")
	private String ID;

	@Property("nickname")
	private String nickname;

	@Property("totalAccount")
	private int totalAccount;
	@Property("rankStatus")
	private int rankStatus;

	@Override
	public User toEntity() {
		// TODO Auto-generated method stub
		User user = new User(this.username, this.totalAccount, this.rankStatus, this.nickname);

		return user;

	}

	public String getPassword() {
		return password;
	}

}
