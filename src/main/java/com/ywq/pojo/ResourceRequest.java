package com.ywq.pojo;

public class ResourceRequest {

	private String name;
	private String resourceId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public ResourceRequest(String name, String resourceId) {
		super();
		this.name = name;
		this.resourceId = resourceId;
	}
	public ResourceRequest() {
		
	}
	@Override
	public String toString() {
		return "ResourceRequest [name=" + name + ", resourceId=" + resourceId + "]";
	}
	
		
}
