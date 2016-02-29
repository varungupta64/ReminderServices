package com.defense.service;

import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.defense.entity.ReminderExcelEntity;

import com.defense.exceptions.ServiceException;

public interface ReminderService {
	public XSSFWorkbook initializeWorkBook() throws ServiceException;
	public List<ReminderExcelEntity> checkForAction(List<ReminderExcelEntity> reminderExcelEntityList,Date currentDate) throws ServiceException;
	public List<ReminderExcelEntity> fetchData(XSSFWorkbook workbook) throws ServiceException;
}
