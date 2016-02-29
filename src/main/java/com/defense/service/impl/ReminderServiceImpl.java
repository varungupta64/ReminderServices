package com.defense.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.defense.entity.ReminderExcelEntity;
import com.defense.exceptions.ServiceException;
import com.defense.service.ReminderService;
import com.defense.utils.UtilityClass;

@Service("reminderService")
public class ReminderServiceImpl implements ReminderService {

	@Override
	public XSSFWorkbook initializeWorkBook() throws ServiceException {
		Properties prop = new Properties();
		InputStream input = null;
		FileInputStream file = null;
		XSSFWorkbook workbook = null;

		try {
			input = getClass().getClassLoader().getResourceAsStream("path.properties");
			prop.load(input);

			file = new FileInputStream(prop.getProperty("filePath"));

			// Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			// add logger
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			// add logger
			throw new ServiceException(e.getMessage());
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(file);
		}
		return workbook;
	}

	@Override
	public List<ReminderExcelEntity> fetchData(XSSFWorkbook workbook) throws ServiceException {
		List<ReminderExcelEntity> reminderExcelEntityList = new ArrayList<ReminderExcelEntity>();
		int numberOfSheets = workbook.getNumberOfSheets();

		for (int index = 0; index < numberOfSheets; index++) {
			XSSFSheet spreadsheet = workbook.getSheetAt(index);
			Iterator<Row> rowIterator = spreadsheet.iterator();
			XSSFRow row = null;
			ReminderExcelEntity reminderExcelEntity = null;

			// skipping first line i.e. heading
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				reminderExcelEntity = new ReminderExcelEntity();
				row = (XSSFRow) rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int i = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (null != cell) {
						switch (i) {
						case 0:
							reminderExcelEntity.setsNo((int) cell.getNumericCellValue());
							break;
						case 1:
							reminderExcelEntity.setRecievedOn(cell.getDateCellValue());
							break;
						case 2:
							reminderExcelEntity.setLetterNo(cell.getStringCellValue());
							break;
						case 3:
							reminderExcelEntity.setSubject(cell.getStringCellValue());
							break;
						case 4:
							reminderExcelEntity.setFrom(cell.getStringCellValue());
							break;
						case 5:
							reminderExcelEntity.setSection(cell.getStringCellValue());
							break;
						case 6:
							reminderExcelEntity.setDateOfAction(cell.getDateCellValue());
							break;
						}
					}
					i++;
				}
				reminderExcelEntityList.add(reminderExcelEntity);
			}
		}		
		return reminderExcelEntityList;
	}
	
	@Override
	public List<ReminderExcelEntity> checkForAction(List<ReminderExcelEntity> reminderExcelEntityList,Date currentDate) throws ServiceException {
		List<ReminderExcelEntity> resultExcelEntityList = new ArrayList<ReminderExcelEntity>();
		for(ReminderExcelEntity reminderExcelEntity:reminderExcelEntityList){
			if(UtilityClass.isSuitableCandidate(currentDate,reminderExcelEntity.getDateOfAction())){
				resultExcelEntityList.add(reminderExcelEntity);
			}
		}
		return resultExcelEntityList;
	}		
}
