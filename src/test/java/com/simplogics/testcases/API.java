package com.simplogics.testcases;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import com.aventstack.extentreports.Status;
import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;
public class API extends BaseData {
	//@Test(priority = 2)
public void Api() throws InterruptedException, MalformedURLException, IOException {
		

		// test = extent.createTest("AddSubAccountToInvoice");
		if (!(TestUtil.isTestRunnable("Api", excel))) {

			throw new SkipException(
					"Skipping the test " + "Api".toUpperCase() + "as the Run mode is NO");
		}
		WebElement table = driver
				.findElement(By.xpath(OR.getProperty("Table_XPATH")));
		List<WebElement> allRows = table.findElements(By.cssSelector(OR.getProperty("AllRows_CSS")));
		System.out.println(allRows.size());
		for (int i = 0; i < allRows.size(); i++) {
			 //SoftAssert softAssertion= new SoftAssert();
			WebElement r = allRows.get(i);
			r.click();
			Thread.sleep(5000);
			test = extent.createTest(r.getText());
			System.out.println(r.getText());
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(5000);
			click("Tryit_CSS");
			Thread.sleep(5000);
			Actions builder = new Actions(driver);
			WebElement element = driver.findElement(By.cssSelector(OR.getProperty("Scroll_CSS")));
			builder.moveToElement(element).clickAndHold().perform();
			builder.moveToElement(element).clickAndHold().perform();
			click("SendButton_CSS");
			Thread.sleep(5000);
			builder.moveToElement(element).clickAndHold().perform();
			builder.moveToElement(element).clickAndHold().perform();

			String result = driver.findElement(By.cssSelector(OR.getProperty("Data_CSS"))).getText();
			if (result.contains("true")&&(result.contains("200 OK"))) {
				test.log(Status.PASS, "Testcase Success :" + r.getText());
				test.log(Status.PASS, "HTTP Response =" + result);
				verifyEquals("true", "true");
				
			} 
			else if(result.contains("false")&&(result.contains("200 OK"))) {
				
				//assert (false);  
				test.log(Status.FAIL, "Testcase Failed :" + r.getText());
				test.log(Status.FAIL, "HTTP Failed Response =" + result);
				verifyEquals("true", "false");

				
			}
			else if(result.contains("400 Bad Request")){
				test.log(Status.FAIL, "Testcase Failed :" + r.getText());
				test.log(Status.FAIL, "HTTP API Failed Response =" + result);
				verifyEquals("200 OK", "400 Bad Request");
				
			}
			else if(result.contains("500 Internal Server Error")) {
				test.log(Status.FAIL, "Testcase Failed :" + r.getText());
				test.log(Status.FAIL, "HTTP API Failed Response =" + result);
				verifyEquals("200 OK", "500 Internal Server Error");
								
			}
			
			Thread.sleep(5000);
			WebElement element2 = driver.findElement(By.cssSelector(OR.getProperty("mousemove_CSS")));
			builder.moveToElement(element2).release().perform();
			builder.moveToElement(element2).release().perform();
			driver.findElement(By.cssSelector(OR.getProperty("close_CSS"))).click();
			Thread.sleep(5000);
			
			
		}
		
	}
}

