package com.rbook.DAO;

import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rbook.mapperObject.FindGroup;
import com.rbook.mapperObject.GroupNode;

@Transactional
@Repository
public interface GroupDAO extends Neo4jRepository<GroupNode, Long> {

	@Query("MERGE (g:Group {uuid:{0}}) ON CREATE SET g.uuid={0}, g.name={1}, g.status={2}, g.date={3} RETURN g ")
	public GroupNode createGroup(String uuid, String name, int status, String date);

	@Query("MATCH (g:Group {uuid:{0}})<-[r:IN_GROUP]-(i:User) RETURN g AS group, count(r) AS member")
	public FindGroup findGroupMem(String uuid);

	@Query("MATCH (g:Group)<-[r:IN_GROUP]-(i:User) WHERE g.uuid IN {0} RETURN g AS group, count(r) AS member")
	public Map<GroupNode, Integer> findMultiGroupMem(String[] uuid);

	@Query("MATCH (u:User {username:{0}})-[r:IN_GROUP]->(g:Group) RETURN g AS group, r.ifConfirmed AS confirm")
	public Map<GroupNode, Boolean> checkAllGroup(String username);

	@Query("MATCH (u:User {username: {0}}), (g: Group {uuid:{1}}) CREATE (u)-[r:IN_GROUP {ifConfirmed:{3}}]->(g) RETURN count(r) ")
	public int addGroup(String username, String guid, boolean confirmed);
}
