import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest {

    @Test
    public void regularUserLoginAndLogout() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=12345&first_name=David&last_name="
                + "&email=test%40test.com&password1=11111&password2=1111");
        String email = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b"))
                .getText();
        browser.get("https://www.sharelane.com/cgi-bin/main.py");
        browser.findElement(By.name("email")).sendKeys(email);
        browser.findElement(By.name("password")).sendKeys("1111");
        browser.findElement(By.cssSelector("[value=Login]")).click();
        browser.get("https://www.sharelane.com/cgi-bin/log_out.py");
        browser.get("https://www.sharelane.com/cgi-bin/main.py");
        boolean buttonVisible = browser.findElement(By.cssSelector("[value=Login]")).isDisplayed();

        Assert.assertTrue(buttonVisible);
        browser.quit();
    }
}
