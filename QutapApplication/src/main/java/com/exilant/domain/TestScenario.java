package com.exilant.domain;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="testScenario")
public class TestScenario {
	
	private String moduleId; 
	@Id
	private String testScenId;
	private String testScenName;
	private String testScenDesc;
	private TestCase testCaseList;
	public String getTestScenId() {
		return testScenId;
	}
	public void setTestScenId(String testScenId) {
		this.testScenId = testScenId;
	}
	public String getTestScenName() {
		return testScenName;
	}
	public void setTestScenName(String testScenName) {
		this.testScenName = testScenName;
	}
	public String getTestScenDesc() {
		return testScenDesc;
	}
	public void setTestScenDesc(String testScenDesc) {
		this.testScenDesc = testScenDesc;
	}
	public TestCase getTestCaseList() {
		return testCaseList;
	}
	public void setTestCaseList(TestCase testCaseList) {
		this.testCaseList = testCaseList;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	
	
	
}
