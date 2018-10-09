package com.exilant.service;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exilant.CommonUtils.Response;
import com.exilant.dao.ExcelDataDao;
import com.exilant.domain.Module;
import com.exilant.domain.ProjectInfo;
import com.exilant.domain.TestCase;
import com.exilant.domain.TestScenario;
import com.exilant.domain.TestSuite;


@Service
public class ExcelDataServiceImpl1 implements ExcelDataService{
	
	@Autowired
	ExcelDataDao excelDataDao;

	Response response;

	@Override
	public List<Response> readExcelData(MultipartFile multipartFile) throws IOException {
	List<Response> res=new ArrayList<>();
	      try {
	             try {	          	           
	            	 Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream());
	                 Sheet worksheet = workbook.getSheetAt(0);
	                 ProjectInfo projectInf = new ProjectInfo(); 
	                 ArrayList<Module> modList=new ArrayList<Module>();
	       
	       	          for(int i=1;i<worksheet.getPhysicalNumberOfRows();i++) {     	     
	       	              DataFormatter formatter = new DataFormatter();
	       	              
	       	          if(!((formatter.formatCellValue(worksheet.getRow(i).getCell(0))).isEmpty()))
	       	          {	       	             	       	             	       	        	
	       	        	  	projectInf.setProjectId(formatter.formatCellValue(worksheet.getRow(i).getCell(0)));
	       	             	projectInf.setProjectName(formatter.formatCellValue(worksheet.getRow(i).getCell(1)));
	       	            	projectInf.setProjDescri(formatter.formatCellValue(worksheet.getRow(i).getCell(2)));
	       	          }
	       	           if(formatter.formatCellValue(worksheet.getRow(i).getCell(3))!=null)
	       	              {
	       	              Module m1=new Module();
	       	              m1.setModuleId(formatter.formatCellValue(worksheet.getRow(i).getCell(3)));
	       	              m1.setModuleName(formatter.formatCellValue(worksheet.getRow(i).getCell(4)));	       	              
	       	              modList.add(m1);
	       	               }	       	            
	       	          projectInf.setModuleList(modList);	         
	       	          Response response2=TestScenarioMethod(worksheet,i);
		      	      res.add(response2);
		      	      Response  response3=testSuiteMethod(worksheet,i);	  
		      	      res.add(response3);        
	             }	       	     
	       	       Response response1=excelDataDao.readExcelData(projectInf);  
	      	       res.add(response1);             	          
	          }catch (Exception e) {
				response.setMessage(e.getMessage());
			}
	      }catch (Exception e) {
	    	  response.setMessage(e.getMessage());
		}
		return res;	
	}
		
	
	public Response TestScenarioMethod(Sheet worksheet,int i) {     
		
		DataFormatter formatter = new DataFormatter();
		TestScenario ts1=new TestScenario();
         if(formatter.formatCellValue(worksheet.getRow(i).getCell(9))!=null)
            {  
            ts1.setTestScenId(formatter.formatCellValue(worksheet.getRow(i).getCell(9)));
            ts1.setTestScenName(formatter.formatCellValue(worksheet.getRow(i).getCell(10)));
            ts1.setTestScenDesc(formatter.formatCellValue(worksheet.getRow(i).getCell(11))); 
            if(formatter.formatCellValue(worksheet.getRow(i).getCell(12))!=null)
             {    
              TestCase t1=new TestCase();
              t1.setTestcaseId(formatter.formatCellValue(worksheet.getRow(i).getCell(12)));
              t1.setTestCaseDesc(formatter.formatCellValue(worksheet.getRow(i).getCell(13)));
              t1.setTestCaseCatgry(formatter.formatCellValue(worksheet.getRow(i).getCell(14)));
              t1.setTestCasePrity(formatter.formatCellValue(worksheet.getRow(i).getCell(15)));
              t1.setTestCaseTag(formatter.formatCellValue(worksheet.getRow(i).getCell(16)));
              t1.setTestCaseSteps(formatter.formatCellValue(worksheet.getRow(i).getCell(17)));
              t1.setTestCaseData(formatter.formatCellValue(worksheet.getRow(i).getCell(18)));
              t1.setExpectedResult(formatter.formatCellValue(worksheet.getRow(i).getCell(19)));
         
            ts1.setTestCaseList(t1);
             }
           }          
         ts1.setModuleId(formatter.formatCellValue(worksheet.getRow(i).getCell(3))); 
         response= excelDataDao.requirementData(ts1);  
         return response;
     }	
	
	
	public Response testSuiteMethod(Sheet worksheet,int i) {
		
		 TestSuite testSuite=new TestSuite();
		 ArrayList<TestCase> testCaseList=new ArrayList<TestCase>();	            	 
		 DataFormatter formatter = new DataFormatter();
		 testSuite.setModuleId(formatter.formatCellValue(worksheet.getRow(i).getCell(3)));		
		 testSuite.setTestSuiteName(formatter.formatCellValue(worksheet.getRow(i).getCell(20)));

		 if(formatter.formatCellValue(worksheet.getRow(i).getCell(3))!=null)
		 {     
           if(formatter.formatCellValue(worksheet.getRow(i).getCell(12))!=null)
            {    TestCase t1=new TestCase();
          
             t1.setTestcaseId(formatter.formatCellValue(worksheet.getRow(i).getCell(12)));
             t1.setTestCaseDesc(formatter.formatCellValue(worksheet.getRow(i).getCell(13)));
             t1.setTestCaseCatgry(formatter.formatCellValue(worksheet.getRow(i).getCell(14)));
             t1.setTestCasePrity(formatter.formatCellValue(worksheet.getRow(i).getCell(15)));
             t1.setTestCaseTag(formatter.formatCellValue(worksheet.getRow(i).getCell(16)));
             t1.setTestCaseSteps(formatter.formatCellValue(worksheet.getRow(i).getCell(17)));
             t1.setTestCaseData(formatter.formatCellValue(worksheet.getRow(i).getCell(18)));
             t1.setExpectedResult(formatter.formatCellValue(worksheet.getRow(i).getCell(19))); 
             testCaseList.add(t1);
            }
           testSuite.setTestCase(testCaseList);  
          }  
        response=excelDataDao.testSuitData(testSuite); 
		return response;
	
    }		
	   
}