package AmazonApp.AmazonApp.suites;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
public class BaseTest {

	 //public static WebDriver driver;
	public ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest test;
	XSSFWorkbook wbook;
	XSSFSheet sheet;

	public void setdriver(WebDriver driver) {
		this.driver.set(driver);
	}

	public WebDriver getDriver() {
		return this.driver.get();
	}

	@BeforeMethod
	@Parameters(value = "browser")
	public void setup(String browser) throws IOException {
		
		
		if (browser.equalsIgnoreCase("edge")) {
			//For Reports
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
			extent.attachReporter(htmlReporter);
			test = extent.createTest("Execution of ecommerce app", "Sample description");
			test.info("Amazon website");
			
			//exceldata
			FileInputStream fis = new FileInputStream("exceldata.xlsx");	
			wbook = new XSSFWorkbook(fis);		
			sheet = wbook.getSheetAt(0);
		//Browser intialization.
			System.setProperty("webdriver.edge.driver", "C:\\seleniumdrivers\\edge\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
			setdriver(new EdgeDriver(options));
			
			//System.setProperty("webdriver.chrome.driver", "C:\\seleniumdrivers\\Chrome\\chromedriver_new.exe");
			//ChromeOptions options = new ChromeOptions();
			//options.addArguments("--ignore-certificate-errors");
			//options.addArguments("--ignore-ssl-errors");
			//setdriver(new ChromeDriver(options));
			}
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "C:\\seleniumdrivers\\FireFox\\geckodriver.exe");
			// Initialize browser
			setdriver(new FirefoxDriver());
		}
	}

	@AfterMethod
	public void teardown() 
	{
	//getDriver().close();
	getDriver().quit();
	 extent.flush();
	}  
	



}
