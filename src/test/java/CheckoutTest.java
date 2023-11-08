import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest {

    @Test
    public void regularUserPurchase() {
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
        browser.get("https://www.sharelane.com/cgi-bin/get_credit_card.py?type=1");
        String card = browser.findElement(
                By.xpath("//table/tbody/tr/td/center/table/tbody/tr[2]/td[2]/span/b")).getText();

        browser.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=9");
        browser.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        browser.findElement(By.xpath("//table/tbody/tr[5]/td/table/tbody/tr[5]/td/form/input[1]")).click();
        browser.findElement(By.name("card_number")).sendKeys(card);
        browser.findElement(
                By.xpath("//table/tbody/tr[5]/td/table/tbody/tr[3]/td[2]/table/tbody/tr[7]/td[2]/input"))
                .click();
        String checkoutMessage = browser.findElement(
                By.xpath("//table/tbody/tr[5]/td/table/tbody/tr[2]/td/p/font/b")).getText();

        Assert.assertEquals(checkoutMessage, "Thank you for your order!!!");
        browser.quit();
    }
}