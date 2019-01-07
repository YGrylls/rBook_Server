package com.rbook.DAO;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.GroupResNode;

@Transactional
@Repository
public interface GroupResDAO extends Neo4jRepository<GroupResNode, Long> {

	@Query("MATCH (n:User)-[:PROPOSE_GROUP_DEBT]->(debt:GroupDebt)-[:OWE_GROUP_DEBT]->(m:User) WHERE debt.uuid IN {0} CREATE (n)-[:IN_RES]->(res:GroupRes{uuid:debt.uuid+m.username,num:debt.num,start:false,end:false,status:false})-[:OUT_RES]->(m) RETURN res")
	public List<GroupResNode> createRes(String[] idList);

}
