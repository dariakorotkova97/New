package ru.avito;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sun.xml.internal.bind.WhiteSpaceProcessor.trim;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class FirstTest {
    private WebDriver driver;
    @Before
    public void beforeTest() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
     public void test() throws Exception{
        List<Integer> maxitems= new ArrayList<Integer>();
        String maxitem;
        WebDriverWait wait = new WebDriverWait(driver, 2);
        driver.get("https://www.avito.ru/rossiya");

        WebElement search;
        search = wait.until(visibilityOfElementLocated(By.xpath(".//option[text()='Кошки']")));
        search.click();

        search = wait.until(visibilityOfElementLocated(By.xpath(".//div[@class='search-form__submit']")));
        search.click();
        wait.until(stalenessOf(search));

        search = driver.findElement(By.xpath(".//span[contains(@class,'pseudo-link')][1]"));
        search.click();

        List <WebElement> count = driver.findElements(By.xpath(".//div[@class='catalog-counts__more']/*/li/span"));

        for (int i=0; i<count.size(); i++){
            String st= count.get(i).getAttribute("innerHTML");
           int item = Integer.parseInt(st.replaceAll("\\s+",""));
           maxitems.add(item);
        }
        int cats = maxitems.indexOf(Collections.max(maxitems));
        //search = driver.findElement(By.xpath(".//span[contains(text(),\""+trim(count.get(cats).getAttribute("innerHTML"))+"\")]"));
        search = driver.findElement(By.xpath("(.//div[@class='catalog-counts__more']/*/li)["+Integer.toString(cats+1)+"]"));
        System.out.println(search.getAttribute("innerHTML"));

        search = wait.until(visibilityOfElementLocated(By.xpath("(.//a[@class='item-description-title-link'])[1]")));
        search.click();
        wait.until(stalenessOf(search));

        search = driver.findElement(By.xpath(".//div[@class='item-params']/span"));
        System.out.println(search.getAttribute("innerHTML"));

    }


    @After
    public void afterTest() throws Exception{
        driver.quit();
    }
}
