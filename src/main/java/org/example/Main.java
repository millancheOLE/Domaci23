package org.example;

/*
Napraviti aplikaciju pomocu Selenium-a koji ce otvoriti sajt kupujemprodajem.com, izlistati sve kategorije (Stvari) sa leve
strane i njihove linkove (kao spoken tekst “kategorija: link”), kliknuti iz te liste na Bicikli (bez hardkodovanja, posto imate listu,
iskoristiti element iz nje da se klikne), kliknuti na Električni (mozete hardcodovati). Ostati na toj strani kao kraj zadatka.
Uspavati program na 5 sekundi kako bi se video rezultat i posle toga browser zatvoriti.

Obratiti paznju na "ad" koji kaze da se registrujete. Uzeti dugme x i kliknuti ga pre svega da ne bi ste imali problem da ne mozete da kliknete na kategoriju.

Za 5+ nakon klika na kategoriju bicikli, treba izlistati sve kategorije koje pisu (Mountainbike, Gradski itd).
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\milla\\IdeaProjects\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.kupujemprodajem.com/");

        driver.manage().window().maximize();

        WebElement closeAD = driver.findElement(By.xpath("//*[@id=\"bodyTag\"]/div[9]/div/div[2]"));
        closeAD.click();

        List<WebElement> webElementsNameList = driver.findElements(By.xpath("//*[@id=\"category-tree-content-goods\"]/a"));

        for (int i = 0; i < webElementsNameList.size(); i++) {
            System.out.println(webElementsNameList.get(i).getText() + ", link: " + webElementsNameList.get(i).getAttribute("href"));
        }

        for (int i = 0; i < webElementsNameList.size(); i++) {
            if (webElementsNameList.get(i).getText().equals("Bicikli")) {
                driver.navigate().to(webElementsNameList.get(i).getAttribute("href"));
                break;
            }
        }

        List<WebElement> bicikliSubcategory = driver.findElements(By.xpath("//*[@id=\"realCategoriesHolder\"]/div/div/div/div/h2/a/span"));

        for (int i = 0; i < bicikliSubcategory.size(); i++) {
            System.out.println(bicikliSubcategory.get(i).getText());
        }

        WebElement elektricni = driver.findElement(By.xpath("//*[@id=\"groupBox1360\"]/div[1]/h2/a/span"));
        elektricni.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
    }
}