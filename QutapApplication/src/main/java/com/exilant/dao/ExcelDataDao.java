package com.exilant.dao;

import javax.servlet.http.HttpServletRequest;

import com.exilant.CommonUtils.Response;
import com.exilant.domain.ProjectInfo;
import com.exilant.domain.Requirement;
import com.exilant.domain.TestScenario;
import com.exilant.domain.TestSuite;


public interface ExcelDataDao {

	Response readExcelData(ProjectInfo projectInfo);
	Response requirementData(TestScenario ts1);
	Response testSuitData(TestSuite testSuite);
	
}
