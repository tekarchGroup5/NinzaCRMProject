package seleniumautomationframeworkjul24;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReadWrite {
	
	public static void readExcel() throws IOException {
		String path = System.getProperty("user.dir")+"/src/main/resources/ExcelDemo.xlsx";
		
		XSSFWorkbook workbook = new XSSFWorkbook(path);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row;
		XSSFCell cell;
		
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				cell = row.getCell(j);
				System.out.print(cell+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir")+"/src/main/resources/ExcelDemo2.xlsx";
		XSSFWorkbook studentData =  new XSSFWorkbook();
		XSSFSheet sheet = studentData.createSheet("Students");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("SlNo");
		row.createCell(1).setCellValue("Name");
		
		row = sheet.createRow(1);
		
		row.createCell(0).setCellValue("1");
		row.createCell(1).setCellValue("Mithun");
		
		FileOutputStream fr = new FileOutputStream(path);
		studentData.write(fr);
		studentData.close();
		
			
	}

}
