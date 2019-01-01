package com.rbook.DAO;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface One2OneDebtDAO extends Neo4jRepository<One2OneDebtRel, Long> {

}
