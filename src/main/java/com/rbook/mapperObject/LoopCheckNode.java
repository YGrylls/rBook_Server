package com.rbook.mapperObject;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.rbook.entity.LoopCheck;

@NodeEntity(label = "LoopCheck")
public class LoopCheckNode implements IDataRes {

	@Id
	@GeneratedValue
	private long graphId;

	@Property("start")
	private String start;

	@Property("end")
	private String end;

	@Property("scale")
	private int scale;

	@Override
	public LoopCheck toEntity() {
		return new LoopCheck(start, end, scale);
	}

}
