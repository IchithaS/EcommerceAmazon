package AmazonApp.AmazonApp.suites;
import java.io.IOException;

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
		
		try {
			LP.Page(uname, pass);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
