package com.exilant.domain;

import org.springframework.data.annotation.Id;

public class Module {
	@Id
	private String moduleId;
	private String moduleName;
//	private Requirement requirement;
//	public Requirement getRequirement() {
//		return requirement;
//	}
//	public void setRequirement(Requirement requirement) {
//		this.requirement = requirement;
//	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
			
}
