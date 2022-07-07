package AmazonApp.AmazonApp.suites;
import org.testng.annotations.Test;

import AmazonApp.AmazonApp.pages.*;

public class LoginTest extends BaseTest  {
	
	/*LoginPage LP=new LoginPage(getDriver());
		LoginPage.
	
	*/
	
	@Test
	public void LP() throws InterruptedException
	{
		LoginPage LP=new LoginPage(getDriver(),test);
		String uname = sheet.getRow(1).getCell(0).getStringCellValue();
		String pass = sheet.getRow(1).getCell(1).getStringCellValue();
		
		LP.Page(uname, pass);
	}

}
