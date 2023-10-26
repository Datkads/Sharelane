import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTest {

    @Test
    public void discountZero() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=12345&first_name=David&last_name="
                + "&email=test%40test.com&password1=11111&password2=1111");
        String email = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        browser.get("https://www.sharelane.com/cgi-bin/main.py");
        browser.findElement(By.name("email")).sendKeys(email);
        browser.findElement(By.name("password")).sendKeys("1111");
        browser.findElement(By.cssSelector("[value=Login]")).click();
        browser.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=9");
        browser.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");

        browser.findElement(By.name("q")).clear();
        browser.findElement(By.name("q")).sendKeys("1");
        browser.findElement(By.cssSelector("[value=Update]")).click();
        String discountPercent = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        String discount$ = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        String total$ = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        Assert.assertEquals(discountPercent, "0", "Значение скидки некорректное");
        Assert.assertEquals(discount$, "0.0");
        Assert.assertEquals(total$, "10.00");

        browser.findElement(By.name("q")).clear();
        browser.findElement(By.name("q")).sendKeys("19");
        browser.findElement(By.cssSelector("[value=Update]")).click();
        discountPercent = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        discount$ = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[6]")).getText();
        total$ = browser.findElement(
                By.xpath("//table/tbody/tr[6]/td/table/tbody/tr[2]/td[7]")).getText();
        Assert.assertEquals(discountPercent, "0", "Значение скидки некорректное");
        Assert.assertEquals(discount$, "0.0");
        Assert.assertEquals(total$, "190.00");
        browser.quit();
    }

    @Test
    public void discount2() {

    }
}
