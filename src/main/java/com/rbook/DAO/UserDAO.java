package com.rbook.DAO;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.LoopCheckNode;
import com.rbook.mapperObject.UserNode;

@Transactional
@Repository
public interface UserDAO extends Neo4jRepository<UserNode, Long> {

	@Query("MATCH (n:User) WHERE n.username={0} OR n.ID={1} RETURN count(n) ")
	public int checkExistList(String name, String identity);

	@Query("MATCH (n:User) WHERE n.username={0} RETURN count(n)")
	public int checkUser(String name);

	@Query("MERGE (n:User {username:{0}}) ON CREATE SET n.password={1}, n.ID={2}, n.totalAccount={3}, n.rankStatus={4}, n.nickname={5}")
	public void addUser(String name, String password, String identity, int total, int rank, String nickname);

	@Query("MATCH (n:User {username:{0}, password:{1}}) RETURN n")
	public List<UserNode> loginCheck(String name, String password);

	@Query("MATCH (n:User) SET n.totalAccount=0 RETURN n")
	public List<UserNode> removeOldTotalAccount();

	@Query("MATCH (a:User)-[r:ONE2ONE_DEBT]->(b:User) WHERE r.status IN [0,2,3] SET a.totalAccount=a.totalAccount+r.number, b.totalAccount=b.totalAccount-r.number")
	public void updateAllTotalAccount();

	@Query("MATCH (l:LoopCheck) DETACH DELETE l")
	public void removeOldLoop();

	@Query("MATCH path=(u:User{username:{0}})-[:ONE2ONE_DEBT]-(s:User)-[:ONE2ONE_DEBT*1..3 {status:0}]-(e:User)-[:ONE2ONE_DEBT]-(m:User{username:{0}}) WHERE SIZE(apoc.coll.toSet(NODES(path))) = LENGTH(path) WITH DISTINCT s.username AS startu, e.username AS endu, LENGTH(path) AS scales MATCH (t:User{username:{0}}) CREATE (t)-[:IN_LOOP]->(l:LoopCheck {start:startu,end:endu,scale:scales})")
	public void addNewLoop(String username);

	@Query("MATCH (l:LoopCheck) WHERE l.start>l.end DETACH DELETE l")
	public void deleteDuplicateLoop();

	@Query("MATCH (u:User{username:{0}})-[:IN_LOOP]->(l:LoopCheck) RETURN l")
	public List<LoopCheckNode> getLoopCheck(String username);

	@Query("MATCH (u:User {username:{0}}) SET u.rankStatus={1}")
	public void setRank(String username, long rank);

}
