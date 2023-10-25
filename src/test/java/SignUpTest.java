import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void zipCode4Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("1111");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        String error = browser.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error, "Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void zipCodeEmptyField() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        String error = browser.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error, "Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void zipCodeOnlyLetters() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("qwerty");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        String error = browser.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error, "Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void zipCodeOnlySymbols() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("-+-\"[]");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        String error = browser.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error, "Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void zipCode5Digits() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        boolean registerVisible = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(registerVisible);
        browser.quit();
    }

    @Test
    public void zipCodeMoreThan5() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("123456");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        String error = browser.findElement(By.cssSelector("[class=error_message]")).getText();
        Assert.assertEquals(error, "Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void positiveSignUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        browser.findElement(By.name("first_name")).sendKeys("David");
        browser.findElement(By.name("email")).sendKeys("test@test.com");
        browser.findElement(By.name("password1")).sendKeys("12345678");
        browser.findElement(By.name("password2")).sendKeys("12345678");

        browser.findElement(By.cssSelector("[value=Register]")).click();

        String signUp = browser.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        Assert.assertEquals(signUp, "Account is created!");
        browser.quit();
    }

    @Test
    public void negativeSignUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        browser.findElement(By.name("first_name")).sendKeys("David");
        browser.findElement(By.name("last_name")).sendKeys("123-03");
        browser.findElement(By.name("email")).sendKeys("test@test.com");
        browser.findElement(By.name("password1")).sendKeys("12345678");
        browser.findElement(By.name("password2")).sendKeys("12345");

        browser.findElement(By.cssSelector("[value=Register]")).click();

        String signUp = browser.findElement(By.cssSelector("[class=confirmation_message]")).getText();
        Assert.assertEquals(signUp, "Account is created!");
        browser.quit();
    }
}
