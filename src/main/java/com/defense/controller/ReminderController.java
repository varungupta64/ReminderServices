package com.defense.controller;

import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.defense.entity.ReminderExcelEntity;
import com.defense.exceptions.ServiceException;
import com.defense.service.ReminderService;

@RestController
public class ReminderController {
	
	@Autowired
	private ReminderService reminderService;
	
	private XSSFWorkbook workbook;
	
	private Date currentDate;
	
	List<ReminderExcelEntity> reminderExcelEntityList;
	
	List<ReminderExcelEntity> resultExcelEntityList;

	@RequestMapping(value="/hello/{name}",method=RequestMethod.GET)
	public String sayHello(@PathVariable String name){
		return "Hello "+name+" to spring boot";
	}
	
	@RequestMapping(value="/checkForAction",method=RequestMethod.GET)
	public @ResponseBody List<ReminderExcelEntity> checkForAction(){				
		try {
			if(null == workbook){
				workbook = reminderService.initializeWorkBook();
			}
			if(null == currentDate){
				currentDate = new Date();
				currentDate.setHours(0);
				currentDate.setMinutes(0);
				currentDate.setSeconds(0);
			}
			if(null == reminderExcelEntityList){
				reminderExcelEntityList = reminderService.fetchData(workbook);		
			}
			if(null == resultExcelEntityList){
				resultExcelEntityList = reminderService.checkForAction(reminderExcelEntityList,currentDate);
			}			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			//throw custom controller exception here
			e.printStackTrace();
		}
		return resultExcelEntityList;
	}
	
	@RequestMapping(value="/refresh",method=RequestMethod.GET)
	public void refresh(){				
		try {			
				workbook = reminderService.initializeWorkBook();			
			
				currentDate = new Date();
				currentDate.setHours(0);
				currentDate.setMinutes(0);
				currentDate.setSeconds(0);
			
				reminderExcelEntityList = reminderService.fetchData(workbook);					
			
				resultExcelEntityList = reminderService.checkForAction(reminderExcelEntityList,currentDate);				
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			//throw custom controller exception here
			e.printStackTrace();
		}		
	}
}
