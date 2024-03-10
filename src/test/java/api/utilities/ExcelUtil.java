package api.utilities;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet  sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
//	private static String TEST_DATA_SHEET_PATH="./src/test/resources/testDataDriven/UserData.xlsx";//. means go from the root of project to src
//	private static Workbook book;
//	private static Sheet sheet;
//
//	public static Object[][] getTestData(String sheetName) {
//
//		Object data[][] = null;//create 2d obj array which is pointing initially null
//
//		try {
//			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);//make connection with this TEST_DATA_SHEET_PATH excel sheet,
//			//give exception with try catch bcoz what if tomorrow file is not present 
//			book = WorkbookFactory.create(ip);//WorkbookFactory class comes from apache poi,create method,it will interact with work book and reach at excel sheet
//			sheet = book.getSheet(sheetName);//create global ref  of Workbook and make it static so i dont need to create obj,getSheet method give sheet name to them
//
//			//Initialize static array with new keyword Object[represent rows=4 here][represent column/cellj=6 here]
//			//no.of row and column  is different in every excel sheet to just catch last row num and column num, dont give hard core number
//			//getRow means pass row no. so in excel sheet its 1st row but for Java it will be 0 index so goto to 0 index and give me column count
//			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//here i have just initialize 2D obj array
//
//			//create 2 for lop:outer and inner
//			for (int i = 0; i < sheet.getLastRowNum(); i++) {//outer loop represent no.of rows(i),00,01,02..
//				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {//inner loop represent no.of columns(J)
//
//					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();//start fill data in 2D array()
////i=0 pint to first row which contains first,last name...
////but i want to capture data from 2nd row so write i+1(means 2nd row),j=0(means first column),toString will covert excel string to java string
//				}
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (InvalidFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return data;
//
//	}
//	
	//constr.
	public ExcelUtil(String path)
	{
		this.path = path;
	}
	
	public int getRowCount(String sheetName)  throws IOException {
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
public int getCellCount(String sheetName,int rownum)  throws IOException {
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
public String getCellData(String sheetName,int rownum,int column)  throws IOException {
	
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	sheet=workbook.getSheet(sheetName);
	row=sheet.getRow(rownum);
	cell=row.getCell(column);
	
	DataFormatter formatter=new DataFormatter();
	String data;
	
	try{
	data=formatter.formatCellValue(cell);
	}
	catch(Exception e)
	{
	data="";
	}
	workbook.close();
	fi.close();
	return data;
}
public void setCellData(String sheetName,int rownum,int column,String data)  throws IOException {
	
	File xlfile=new File(path);
	if(!xlfile.exists()) {//not exist then create new file
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
	}
	
	
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	
	if(workbook.getSheetIndex(sheetName)==-1) //if sheet not exists then create new sheet
		workbook.createSheet();
		sheet=workbook.getSheet(sheetName);
	
	if(sheet.getRow(rownum)==null) //if row not exists then create new row
		sheet.createRow(rownum);
		row=sheet.getRow(rownum);
	
		cell=row.createCell(column);
	cell.setCellValue(data);
	
	fo=new FileOutputStream(path);
	workbook.write(fo);
	workbook.close();
	fi.close();
	fo.close();
	
}
public void fillGreenColor(String sheetName,int rownum,int column)  throws IOException {
	
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	sheet=workbook.getSheet(sheetName);
	
	row=sheet.getRow(rownum);
	cell=row.getCell(column);
	
	style=workbook.createCellStyle();

	style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	cell.setCellStyle(style);
	workbook.write(fo);
	workbook.close();
	fi.close();
	fo.close();
	
}

public void fillRedColor(String sheetName,int rownum,int column)  throws IOException {
	
	fi=new FileInputStream(path);
	workbook=new XSSFWorkbook(fi);
	sheet=workbook.getSheet(sheetName);
	
	row=sheet.getRow(rownum);
	cell=row.getCell(column);
	
	style=workbook.createCellStyle();

	style.setFillBackgroundColor(IndexedColors.RED.getIndex());
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	
	cell.setCellStyle(style);
	workbook.write(fo);
	workbook.close();
	fi.close();
	fo.close();
	
}
//
//





}
