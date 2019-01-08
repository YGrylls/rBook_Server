package com.rbook.mapperObject;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.rbook.entity.GroupRes;

@NodeEntity(label = "GroupRes")
public class GroupResNode implements IDataRes {

	@Id
	@GeneratedValue
	private long graphId;

	@Property("uuid")
	private String uuid;

	@Property("num")
	private int num;

	@Property("start")
	private boolean start;

	@Property("end")
	private boolean end;

	@Property("status")
	private boolean status;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GroupResNode [graphId=" + graphId + ", uuid=" + uuid + ", num=" + num + ", start=" + start + ", end="
				+ end + ", status=" + status + "]";
	}

	@Override
	public GroupRes toEntity() {
		return new GroupRes(uuid, num, start, end, status);

	}

}
