package indi.mcy.ST.lab2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadAndCheck {
	public static void main(String[] args) throws IOException {
		ReadAndCheck rx = new ReadAndCheck();
		//设置启动driver
		rx.setUp();		
		WebDriver driver = (WebDriver) new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get("http://121.193.130.195:8800");			
		//创建输出文本
		FileOutputStream fileOut = new FileOutputStream("src/outputFile/workbook.xlsx");
		Workbook wb = rx.createOutputXlsx(); 		
		String pathname = "src/namelist/nameList.xlsx";
        try {
            FileInputStream file = new FileInputStream(pathname);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row r = rowIterator.next();
                if (r.getPhysicalNumberOfCells() == 0) {
                    System.out.println("Empty Row");
                    continue;
                }
                Cell c = r.getCell(0);
                if (c.toString().trim().equals("序号")) {
                    continue;
                }
                String student_id = new DecimalFormat("#").format(r.getCell(1).getNumericCellValue());
				String student_name = r.getCell(2).toString();
				String student_git = r.getCell(3).toString();
				//匹配并输出结果
				rx.checkInfoAndOutResult(new Student(student_id, student_name, student_git), driver, wb);				
            }
        } catch (Exception e) {
            System.out.println("出现异常：" + e.toString());
        } finally {
        	driver.close();
        	try {
				wb.write(fileOut);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				wb.close();
			}
        }		
	}
	
	private void setUp() {
		String driverPath = System.getProperty("user.dir") + "/src/sources/driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
	}
	
	private  void checkInfoAndOutResult(Student student, WebDriver driver, Workbook wb) {
		this.login(student, driver);
		this.check(student, driver, wb);
	}
	private void login(Student student, WebDriver driver) {
		driver.findElement(By.cssSelector("input[name=id]")).sendKeys(student.getStudent_id());
		driver.findElement(By.cssSelector("input[name=password]"))
		.sendKeys(student.getStudent_id().substring(student.getStudent_id().length() - 6));
		driver.findElement(By.id("btn_login")).click();
	}
	
	private void logout(WebDriver driver) {
		driver.findElement(By.id("btn_logout")).click();
		driver.findElement(By.id("btn_return")).click();
	}
	
	private void check(Student student, WebDriver driver, Workbook wb) {
		String id = driver.findElement(By.id("student-id")).getText();
		String name = driver.findElement(By.id("student-name")).getText();
		String git = driver.findElement(By.id("student-git")).getText();
		Sheet currentSheet = wb.getSheetAt(0);
		int xlsxIndex = currentSheet.getLastRowNum() + 1;
		if (!(id.equals(student.getStudent_id()) && name.equals(student.getStudent_name()) && git.equals(student.getStudent_git()))) {
			Row currentRow = currentSheet.createRow(xlsxIndex);
			currentRow.createCell(0).setCellValue(student.getStudent_id());
			if (!id.equals(student.getStudent_id())) {
				currentRow.createCell(1).setCellValue("Not consistant");
				System.out.println("id is wrong");
			}
			if (!name.equals(student.getStudent_name())) {
				currentRow.createCell(2).setCellValue("Not consistant");	
				System.out.println("name is wrong");
			}
			if (!git.equals(student.getStudent_git())) {
				currentRow.createCell(3).setCellValue("Not consistant");
				System.out.println("git is wrong");

			}
		}
		this.logout(driver);
	}
	
	public XSSFWorkbook createOutputXlsx() {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("wrongInfo");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("id");
        row.createCell(2).setCellValue("name");
        row.createCell(3).setCellValue("git");
        return wb;
    }

}
