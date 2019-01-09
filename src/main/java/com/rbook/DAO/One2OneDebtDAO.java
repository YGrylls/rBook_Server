package com.rbook.DAO;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.One2OneDebtRel;

@Transactional
@Repository
public interface One2OneDebtDAO extends Neo4jRepository<One2OneDebtRel, Long> {

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m :User {username:{1}}) RETURN (n)-[r]-(m)")
	public List<One2OneDebtRel> findDebts(String username, String counterUsername);

	@Query("MATCH (n:User {username:{0}}),(m:User {username:{1}}) CREATE (n)-[r:ONE2ONE_DEBT {number:{2}, desc:{3}, date:{4}, status:{5}, proposal:{6}, uuid:{7}}]->(m) RETURN (n)-[r]->(m)")
	public One2OneDebtRel createDebt(String startname, String endname, int num, String desc, String date, int status,
			boolean proposal, String uuid);

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m: User) RETURN (n)-[r]-(m)")
	public List<One2OneDebtRel> findAllDebts(String username);

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT {status: 0}]-(m :User {username:{1}}) RETURN count(r)")
	public int findAcceptedDebts(String username, String countername);

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m:User {username:{1}}) WHERE r.status IN [3,4] RETURN count(r)")
	public int checkCombine(String username, String countername);

	@Query("MATCH (n :User {username:{0}})-[r:ONE2ONE_DEBT {status:{3}}]-(m: User {username:{1}}) WHERE r.uuid IN {2} RETURN count(r)")
	public int checkStatus(String username, String countername, String[] id, int status);

	@Query("MATCH (n :User {username:{0}})-[r:ONE2ONE_DEBT]->(m :User) WHERE r.uuid IN {1} SET r.status={2}, r.proposal={3}  RETURN (n)-[r]-(m)")
	public List<One2OneDebtRel> setStateStart(String username, String[] id, int status, boolean proposal);

	@Query("MATCH (n :User {username:{0}})<-[r:ONE2ONE_DEBT]-(m :User) WHERE r.uuid IN {1} SET r.status={2}, r.proposal={3}  RETURN (n)-[r]-(m)")
	public List<One2OneDebtRel> setStateEnd(String username, String[] id, int status, boolean proposal);

	@Query("MATCH (n :User {username:{0}})-[r:ONE2ONE_DEBT {status:3}]-(m:User {username: {1}}) DELETE r")
	public void confirmCombineDelete(String username, String countername);

	@Query("MATCH (n :User {username:{0}})-[r:ONE2ONE_DEBT {status:3}]-(m:User {username: {1}}) SET r.status=0 RETURN count(r)")
	public int confirmCombineCancel(String username, String countername);

	@Query("MATCH (n)-[r: ONE2ONE_DEBT]-(m) WHERE r.uuid={0} SET r.status={1} RETURN (n)-[r]-(m)")
	public One2OneDebtRel setStatus(String id, int status);

	@Query("MATCH (n)-[r:ONE2ONE_DEBT]-(m) WHERE r.uuid={0} RETURN (n)-[r]-(m)")
	public One2OneDebtRel getDebt(String id);

	@Query("MATCH (n)-[r:ONE2ONE_DEBT]-(m) WHERE r.uuid={0} DELETE r")
	public void deleteDebt(String id);

}
