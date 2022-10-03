package Q2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class Q2 {

	public static void main(String[] args) throws InterruptedException {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("Q2Report.html");

		ExtentReports extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    
	    ExtentTest testQ2 = extent.createTest("Customer - Deposit & Withdrawal", "Deposit & Withdrawal");
	    
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\nicong\\Downloads\\geckodriver-v0.31.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		testQ2.log(Status.INFO, "Testing start");
		
		driver.manage().deleteAllCookies();
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
		driver.manage().window().maximize();
		testQ2.pass("Navigated to XYZ Bank Homepage");
		
		NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor)driver );
		ngDriver.waitForAngularRequestsToFinish();
		
		//Customer Login
		driver.findElement(By.cssSelector("div.center:nth-child(1) > button:nth-child(1)")).click();
		Thread.sleep(1000);
		testQ2.pass("Click on Customer Login button");
		driver.findElement(By.cssSelector("#userSelect")).click();
		Thread.sleep(1000);
		testQ2.pass("Successfully selected Hermoine Granger");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/div/select/option[2]")).click();
		Thread.sleep(1000);
		testQ2.pass("Successfully login");
		driver.findElement(By.cssSelector("button.btn:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#accountSelect")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/select/option[3]")).click();
		testQ2.pass("Successfully selected 1003");
		
		var balance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();		
		if (balance.equals("0")) 
		{
			System.out.println("Balance is 0");
			testQ2.pass("Verified the initial balance is 0");
		}
		else
		{
			System.out.println("Balance is not 0");
			testQ2.pass("Verified the initial balance is not 0");
		}
		
		//Perform Transaction -- start
		var Balance = 0;
		driver.findElement(By.cssSelector("button.btn:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"50000"});		
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Deposit 50000");
		Balance = Balance + 50000;
		Thread.sleep(1000);
		testQ2.pass("Successfully Deposit 50000");
		
		driver.findElement(By.cssSelector("button.btn-lg:nth-child(3)")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"3000"});
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Withdraw 3000");
		Thread.sleep(1000);
		Balance = Balance - 3000;		
		testQ2.pass("Successfully Withdraw 3000");
		
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"2000"});
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Withdraw 2000");
		Thread.sleep(1000);
		Balance = Balance - 2000;
		testQ2.pass("Successfully Withdraw 1500");
		
		driver.findElement(By.cssSelector("button.btn:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"5000"});
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Deposit 5000");
		Thread.sleep(1000);
		Balance = Balance + 5000;
		testQ2.pass("Successfully Deposit 5000");
		
		driver.findElement(By.cssSelector("button.btn-lg:nth-child(3)")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"10000"});
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Withdraw 10000");
		Thread.sleep(1000);
		Balance = Balance - 10000;
		testQ2.pass("Successfully Withdraw 10000");
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"15000"});
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Withdraw 15000");
		Thread.sleep(1000);
		Balance = Balance - 15000;
		testQ2.pass("Successfully Withdraw 15000");
		
		driver.findElement(By.cssSelector("button.btn:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[4]/div/form/div/input")).sendKeys(new String[]{"1500"});
		driver.findElement(By.cssSelector(".btn-default")).click();
		System.out.println("Deposit 1500");
		Thread.sleep(1000);
		Balance = Balance + 1500;
		testQ2.pass("Successfully Deposit 1500");
		
		System.out.println("Balance is " + Balance);
		
		String AccBalance = String.valueOf(Balance);		
		var currentBalance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
		
		if (currentBalance.equals(AccBalance))
		{
			System.out.println("Current balance is tallies with Balance section in the Customer Homepage");
			testQ2.pass("Current balance is tallies with Balance section in the Customer Homepage");
		}
		else
		{
			System.out.println("Current balance is not tallies with Balance section in the Customer Homepage");
			testQ2.pass("Current balance is not tallies with Balance section in the Customer Homepage");
		}
		
		testQ2.info("Test Completed");
		extent.flush();
		
		//Perform Transaction -- end
		driver.findElement(By.cssSelector(".home")).click();
		driver.close();
			

	}

}
