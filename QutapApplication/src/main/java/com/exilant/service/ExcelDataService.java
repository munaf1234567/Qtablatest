package com.exilant.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.exilant.CommonUtils.Response;

public interface ExcelDataService {

	List<Response> readExcelData(MultipartFile multipartFile) throws IOException;

}
