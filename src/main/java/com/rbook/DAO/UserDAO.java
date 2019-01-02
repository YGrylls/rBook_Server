package com.rbook.DAO;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserDAO extends Neo4jRepository<UserNode, Long> {

	@Query("MATCH (n:User) WHERE n.name={0} OR n.ID={1} RETURN count(n) ")
	public int checkExistList(String name, String identity);

	@Query("MATCH (n:User) WHERE n.name={0} RETURN count(n)")
	public int checkUser(String name);

	@Query("MERGE (n:User {username:{0}}) ON CREATE SET n.password={1}, n.ID={2}, n.totalAccount={3}, n.rankStatus={4}, n.nickname={5})")
	public void addUser(String name, String password, String identity, int total, int rank, String nickname);

	@Query("MATCH (n:User {username:{0}, password:{1}}) RETURN n")
	public List<UserNode> loginCheck(String name, String password);

}
