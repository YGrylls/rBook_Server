package com.rbook.DAO;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.GroupResNode;
import com.rbook.mapperObject.UserNode;

@Transactional
@Repository
public interface GroupResDAO extends Neo4jRepository<GroupResNode, Long> {

	/*
	 * @Query("MATCH (n:User)-[:PROPOSE_GROUP_DEBT]->(debt:GroupDebt)-[:OWE_GROUP_DEBT]->(m:User) "
	 * + "WHERE debt.uuid IN {0} " +
	 * "CREATE (n)-[:IN_RES]->(res:GroupRes{uuid:debt.uuid+m.username,num:debt.num,start:false,end:false,status:false})-[:OUT_RES]->(m) "
	 * + "RETURN res.uuid") public List<String> createRes(String[] idList);
	 */

	@Query("MATCH (s:User {username:{0}}),(e:User{username:{1}}) CREATE (s)-[:IN_RES]->(res:GroupRes{uuid:{2},num:{3},start:false,end:false,status:false})-[:OUT_RES]->(e)")
	public GroupResNode createRes(String start, String end, String uuid, int num);

	@Query("OPTIONAL MATCH (r:GroupRes), (g:Group {uuid:{1}}) WHERE r.uuid IN {0} CREATE (g)-[l:HAS_RES]->(r) RETURN count(l)")
	public int linkResToGroup(String[] idList, String guid);

	@Query("MATCH (u:User {username:{0}})-[:IN_RES]->(r: GroupRes)<-[:HAS_RES]-(g:Group {uuid:{1}}) WITH r AS res MATCH (res)-[:OUT_RES]->(c: User) RETURN res, c")
	public Map<GroupResNode, UserNode> getResListIn(String username, String uuid);

	@Query("MATCH (u:User {username:{0}})<-[:OUT_RES]-(r: GroupRes)<-[:HAS_RES]-(g:Group {uuid:{1}}) WITH r AS res MATCH (res)<-[:IN_RES]-(c: User) RETURN res, c")
	public Map<GroupResNode, UserNode> getResListOut(String username, String uuid);

	@Query("MATCH (u:User {username:{0}})-[:IN_RES]->(res: GroupRes {uuid:{1}}) SET res.start=true, res.status=(res.start AND res.end) RETURN res")
	public GroupResNode acceptGroupResIn(String username, String uuid);

	@Query("MATCH (u:User {username:{0}})<-[:OUT_RES]-(res: GroupRes {uuid:{1}}) SET res.end=true, res.status=(res.start AND res.end) RETURN res")
	public GroupResNode acceptGroupResOut(String username, String uuid);

	@Query("MATCH (r:GroupRes{uuid:{0}})<-[:HAS_RES]-(g:Group)-[:HAS_RES]->(m:GroupRes) RETURN m.status")
	public List<Boolean> getAllResStatusByResID(String uuid);

	@Query("MATCH (r:GroupRes{uuid:{0}})<-[:HAS_RES]-(g:Group) SET g.status=2 RETURN count(g)")
	public int closeGroup(String uuid);

}
