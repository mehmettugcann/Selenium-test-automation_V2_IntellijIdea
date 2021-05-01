import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WrapsElement;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class loginpage {
    WebDriverWait wait;
    protected WebDriver driver;
    public static String loginUrl = "https://www.gittigidiyor.com/uye-girisi";
    public static String baseUrl = "https://www.gittigidiyor.com";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/selenium/chhromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
    }

    @Test
    public void correctLogin() throws InterruptedException {
        driver.get(loginUrl);
        driver.findElement(By.id("L-UserNameField")).sendKeys("tugcan12@hotmail.com");
        driver.findElement(By.id("L-PasswordField")).sendKeys("MTK123456");
        driver.findElement(By.id("gg-login-enter")).click();


        // driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[1]/div[2]/input")).sendKeys("Bilgisayar");
        WebElement searchArea = driver.findElement(By.xpath("//input[@placeholder='Keşfetmeye Bak']"));
        searchArea.sendKeys("Bilgisayar");
        searchArea.sendKeys(Keys.ENTER);

        WebElement pageTwo =   driver.findElement(By.xpath("//a[text()='2']"));
        JavascriptExecutor js=(JavascriptExecutor)driver;

        /*
        sayfa 2 butonunu görene kadar scroll yap, daha sonra tıkla
         */
        js.executeScript("arguments[0].scrollIntoView();",pageTwo);
        pageTwo.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2");

        ArrayList<WebElement> arrays = (ArrayList<WebElement>) driver.findElements(By.cssSelector("ul.catalog-view>li"));


        int rand = (int)(Math.random() * arrays.size()) + 1;
        arrays.get(rand).click();
        System.out.println(rand);


        WebElement price1 = driver.findElement(By.xpath("//div[@id='sp-price-lowPrice']"));

        String  price11 = price1.getText();

        WebElement addToBasket = driver.findElement(By.xpath("//button[@id='add-to-basket']"));
        wait.until(ExpectedConditions.visibilityOf(addToBasket));
        //js.executeScript("window.scrollBy(0,200)");
        js.executeScript("arguments[0].scrollIntoView();",addToBasket);
        addToBasket.click();

        //tıklanabilir durumda olana kadar bekle ve sonrasında tıkla

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Sepete Git']"))).click();

        Thread.sleep(2000);
        WebElement priceInTheBasket = driver.findElement(By.cssSelector("div.total-price>strong"));
        String priceInTheBasketS = priceInTheBasket.getText();

        Assert.assertEquals(price11,priceInTheBasketS);

        WebElement amount = driver.findElement(By.xpath("//select[@class='amount']"));

        Select slc = new Select(amount);
        slc.selectByValue("2");

        Thread.sleep(2000);
        WebElement amount2 = driver.findElement(By.xpath("(//div[@class='gg-d-16 detail-text'])[1]"));

        String amount2s = amount2.getText();



        Thread.sleep(2000);
        Assert.assertTrue(amount2s.contains("2"));
        driver.findElement(By.xpath("//a[@title='Sil']")).click();
        WebElement emptyMessage = driver.findElement(By.xpath("(//h2)[1]"));
        wait.until(ExpectedConditions.visibilityOf(emptyMessage));
        String emptyMessageActual = emptyMessage.getText();
        String emptyMessageExpected = "Sepetinizde ürün bulunmamaktadır.";

        Assert.assertEquals(emptyMessageExpected,emptyMessageActual);
        Thread.sleep(1500);
        System.out.println("Sepetinizde ürün bulunmamaktadır..");

        driver.close();























//        JavascriptExecutor js=(JavascriptExecutor)driver;
//
//        js.executeScript("arguments[0].scrollIntoView();",element);
//
//        driver.findElement(By.xpath("//*[@id=\"main-header\"]/div[3]/div/div/div/div[2]/form/div/div[2]/button/span")).click();
//
//
//        JavascriptExecutor js = ((JavascriptExecutor) driver);
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//        driver.findElement(By.xpath("//*[@id=\"best-match-right\"]/div[5]/ul/li[2]/a")).click();
//        Assert.assertEquals(driver.getCurrentUrl(), "https://www.gittigidiyor.com/arama/?k=bilgisayar&sf=2");
//
//        Random random = new Random();
//        int rand = random.nextInt(48) + 1; // 1 sayfada 48 ürün
//
//        driver.findElement(By.xpath("//div[@class='clearfix']/ul[@class='catalog-view clearfix products-container']/li[" + rand + "]/a[@class='product-link']")).click();
//        driver.findElement(By.cssSelector("[id='purchaseSoldInfoActionButtons'] [id='add-to-basket']")).click();
//        Assert.assertEquals(driver.getTitle(), "main-header");

    }
}

