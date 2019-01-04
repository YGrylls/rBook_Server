package com.rbook.mapperObject;

import java.time.LocalDate;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.rbook.entity.GroupDebt;

@NodeEntity(label = "GroupDebt")
public class GroupDebtNode implements IDataRes {

	@Id
	@GeneratedValue
	private long graphId;

	@Property("uuid")
	private String uuid;
	@Property("num")
	private int num;
	@Property("desc")
	private String desc;
	@Property("time")
	private LocalDate time;

	@Override
	public GroupDebt toEntity() {
		return new GroupDebt(uuid, desc, num, time);
	}

}
