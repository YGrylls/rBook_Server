package com.rbook.mapperObject;

import java.time.LocalDate;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.rbook.entity.Group;

@NodeEntity(label = "Group")
public class GroupNode implements IDataRes {

	@Id
	@GeneratedValue
	private long graphId;

	@Property("uuid")
	private String uuid;

	@Property("name")
	private String name;

	@Property("status")
	private int status;

	@Property("confirmTime")
	private LocalDate confirmTime;

	public long getGraphId() {
		return graphId;
	}

	public void setGraphId(long graphId) {
		this.graphId = graphId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(LocalDate confirmTime) {
		this.confirmTime = confirmTime;
	}

	@Override
	public Group toEntity() {
		return new Group(uuid, name, status, confirmTime);
	}

}
