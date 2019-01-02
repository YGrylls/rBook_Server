package com.rbook.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface One2OneDebtDAO extends Neo4jRepository<One2OneDebtRel, Long> {

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m :User {username:{1}}) RETURN r")
	public List<One2OneDebtRel> findDebts(String username, String counterUsername);

	@Query("MATCH (n:User {username:{0}}),(m:User {username:{1}}) CREATE (n)-[r:ONE2ONE_DEBT {num:{2}, desc:{3},date:{4}, status:{5}, proposal:{6}}]->(m) RETURN r")
	public One2OneDebtRel createDebt(String startname, String endname, int num, String desc, LocalDate date, int status,
			boolean proposal);

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m: User) RETURN r")
	public List<One2OneDebtRel> findAllDebts(String username);

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT {status=0}]-(m :User {username:{1}}) RETURN count(r)")
	public int findAcceptedDebts(String username, String countername);

	@Query("MATCH (n :User {username:{0}})-[r:ONE2ONE_DEBT {status={3}}]-(m: User {username:{1}}) WHERE id(r) IN {2} RETURN count(r)")
	public int ifCanChange(String username, String countername, long[] id, int status);

	@Query("MATCH (n :User {username:{0}})-[r:ONE2ONE_DEBT]->(m :User) WHERE id(r) IN {1} SET r.status={2}, r.proposal={3}  RETURN r")
	public List<One2OneDebtRel> setStateStart(String username, long[] id, int status, boolean proposal);

	@Query("MATCH (n :User {username:{0}})<-[r:ONE2ONE_DEBT]-(m :User) WHERE id(r) IN {1} SET r.status={2}, r.proposal={3}  RETURN r")
	public List<One2OneDebtRel> setStateEnd(String username, long[] id, int status, boolean proposal);
}
