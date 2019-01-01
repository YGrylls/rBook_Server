package com.rbook.DAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface One2OneDebtDAO extends Neo4jRepository<One2OneDebtRel, Long> {

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m :User {username:{1}} RETURN r)")
	public List<One2OneDebtRel> findDebts(String username, String counterUsername);

	@Query("MATCH (n:User {username:{0}}),(m:User {username:{1}}) CREATE (n)<-[r:ONE2ONE_DEBT {num:{2}, desc:{3},date:{4}, status:{5}}]-(m) RETURN r")
	public One2OneDebtRel createDebt(String username, String counterName, int num, String desc, LocalDate date,
			int status);

	@Query("MATCH (n:User {username:{0}})-[r:ONE2ONE_DEBT]-(m: User) RETURN r")
	public List<One2OneDebtRel> findAllDebts(String username);

}
