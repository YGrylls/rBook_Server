package com.rbook.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.GroupDebtNode;

@Transactional
@Repository
public interface GroupDebtDAO {

	@Query("MATCH (d:GroupDebt)<-[:HAS_GROUP_DEBT]-(g:Group {uuid:{0}}) RETURN count(r)")
	public int getDebtNum(String uuid);

	@Query("MATCH (u:User {username:{0}})-[r:IN_GROUP {ifConfirmed:false}]->(g:Group {uuid:{1}}) RETURN count(r)")
	public int checkPersonalStatus(String username, String uuid);

	@Query("MATCH (u:User)-[r:IN_GROUP]->(g:Group {uuid:{1}}) WHERE u.username IN {0} RETURN count(r)")
	public int checkTargetInGroup(String[] usernames, String uuid);

	@Query("MATCH (p:User{username:{0}})-->(g:Group{uuid:{1}}) CREATE (p)-[r:PROPOSE_GROUP_DEBT]->(d:GroupDebt {uuid:{2}, desc:{3}, num:{4},time:{5}} )<-[i:HAS_GROUP_DEBT]-(g) RETURN d")
	public GroupDebtNode createGroupDebt(String username, String guid, String uuid, String desc, int num, String time);

	@Query("MATCH (d:GroupDebt {uuid:{0}}), (u:User) WHERE u.username IN {1} CREATE (d)-[r:OWE_GROUP_DEBT]->(u) RETURN count(r)")
	public int linkGroupDebt(String uuid, String[] username);
}
