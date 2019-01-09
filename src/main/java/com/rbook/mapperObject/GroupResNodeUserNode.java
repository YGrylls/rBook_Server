package com.rbook.mapperObject;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class GroupResNodeUserNode {
	private GroupResNode res;
	private UserNode user;

	public GroupResNode getRes() {
		return res;
	}

	public void setRes(GroupResNode res) {
		this.res = res;
	}

	public UserNode getUser() {
		return user;
	}

	public void setUser(UserNode user) {
		this.user = user;
	}

}
