package com.rbook.DAO;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.UserNode;

@Transactional
@Repository
public interface TestDao extends Neo4jRepository<UserNode, Long> {

	@Query("MERGE (n:User {username:{0}}) ON CREATE SET n.password={1}, n.ID={2}, n.totalAccount={3}, n.rankStatus={4}, n.nickname={5}")
	public void addUser(String name, String password, String identity, int total, int rank, String nickname);

	@Query("MATCH (n:User {username:{0}}),(m:User {username:{1}}) "
			+ "MERGE (n)-[r:ONE2ONE_DEBT {number:{2}, desc:{3}, date:{4}, status:{5}, proposal:{6}, uuid:{7}}]->(m) RETURN r.uuid")
	public String createDebt(String startname, String endname, int num, String desc, String date, int status,
			boolean proposal, String uuid);

	@Query("MATCH (n:User{username:{0}}) DETACH DELETE n")
	public void deleteUser(String username);
//
//	@Query("MATCH (n)-[r:ONE2ONE_DEBT]-(m) WHERE r.uuid={0} DELETE r")
//	public String deleteDebt(String uuid);
}
