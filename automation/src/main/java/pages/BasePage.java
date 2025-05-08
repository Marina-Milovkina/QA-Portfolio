package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver; // модификатор доступа - тип переменной (драйвер браузера) - имя переменной

    public BasePage(WebDriver driver) {  // конструктор класса BasePage
        this.driver = driver; // переменной класса присвоить значение, которое пришло в конструкторе
    }

    /**
     * Перейти по url
     * @param url ссылка в виде строки
     */
    public void openUrl(String url) {
        driver.get(url);  // команда WebDriver, которая открывает указанный url в браузере
    }

    /**
     * Найти элемент на странице
     * @param locator путь до элемента, тип - By
     * @return element найденный элемент
     */
    public WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    /**
     * Получить текст из элемента
     * @param locator путь до элементов, тип - By
     */
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Внести значение в поле
     * @param locator путь до элемента, тип - By
     * @param text текст
     */
    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    /**
     * Кликнуть на элемент
     * @param locator путь до элемента, тип - By
     */
    public void click(By locator) {
        findElement(locator).click();
    }

}
