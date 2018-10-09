package com.exilant.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exilant.CommonUtils.Response;
import com.exilant.service.ExcelDataService;


@RestController
@RequestMapping("/Qutap")
public class ExcelDataController {
	
org.slf4j.Logger log= LoggerFactory.getLogger(ExcelDataController.class);
	
	@Autowired
	ExcelDataService excelDataService1;
	
	
	@PostMapping("/excel")
	public List<Response> readExcelData(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest req) throws IOException { 
		log.info("url of the application"+req.getRequestURL().toString());
		System.out.println("multipart"+multipartFile.isEmpty());
		List<Response> response=excelDataService1.readExcelData(multipartFile);
		for(Response x:response) {
			x.setUrl(req.getRequestURL().toString());
		}
		return response;
		
	}

	
}
