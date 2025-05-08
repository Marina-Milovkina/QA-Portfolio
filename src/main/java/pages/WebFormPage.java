package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.Objects;

import static org.openqa.selenium.Keys.*;

public class WebFormPage extends BasePage {
    public WebFormPage(WebDriver driver){
        super(driver);
    }

    // локаторы
    private static final By PAGE_NAME = By.xpath ("//h1[@class='display-6']");

    private static final String TEXT_INPUT_XPATH = "//input[@type='text']";

    private static final String PASSWORD_INPUT_XPATH = "//input[@type='password']";

    private static final String TEXTAREA_XPATH = "//textarea[@class='form-control']";

    private static final String DROPDOWN_XPATH = "//select[@class='form-select']";

    private static final String DEFAULT_CHECKBOX_XPATH = "//input[@id='my-check-2']";

    private static final String CHECKED_CHECKBOX_XPATH = "//input[@id='my-check-1']";

    private static final String DEFAULT_RADIO_XPATH = "//input[@id='my-radio-2']";

    private static final String DATALIST_XPATH = "//input[@name='my-datalist']";

    private static final String SUBMIT_XPATH = "//button[@type='submit']";

    private static final String SELECT_DATE_ELEMENT = "//input[@name='my-date']";

    private String dayInDatePicker(int number){
        return String.format("//td[@class='day' and text()='%s']", number); // используется метод для динамического формирования строки
    }

    private String monthInDatePicker(String month){
        return String.format("//div[contains(@class, 'datepicker') and contains(@style, 'display: block')]//span[contains(@class, 'month') and text()='%s']", month); // используется метод для динамического формирования строки
    }

    private String yearInDatePicker(String year){
        return String.format("//div[contains(@class, 'datepicker') and contains(@style, 'display: block')]//span[contains(@class, 'year') and text()='%s']", year); // используется метод для динамического формирования строки
    }

    private static final String DATEPICKER_SWITCH = "//th[@class='datepicker-switch']";

    private static final String INPUT_COLOR = "//input[@type='color']";

    private static final String INPUT_RANGE = "//input[@type='range']";

    // методы
    public void openWebFormPage(){
        openUrl("https://www.selenium.dev/selenium/web/web-form.html");
    }

    public String getPageName() {
        return getText(PAGE_NAME);
    }

    // ввести фио, пароль и название компании
    public void inputForm(String name, String password, String company){
        sendKeys(By.xpath(TEXT_INPUT_XPATH), name);
        sendKeys(By.xpath(PASSWORD_INPUT_XPATH), password);
        sendKeys(By.xpath(TEXTAREA_XPATH), company);
    }

    // Проверка, что введенные значения отобразились
    public String getNameFromTable() {
        return driver.findElement(By.xpath(TEXT_INPUT_XPATH)).getAttribute("value");
    }

    public String getPasswordFromTable() {
        return driver.findElement(By.xpath(PASSWORD_INPUT_XPATH)).getAttribute("value");
    }

    public String getCompanyFromTable() {
        return driver.findElement(By.xpath(TEXTAREA_XPATH)).getAttribute("value");
    }

    // метод открытия списка Dropdown (select)
    public void selectFromDropdown(String visibleText) {
        WebElement dropdown = driver.findElement(By.xpath(DROPDOWN_XPATH)); // или By.xpath(...)
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

   // метод, выбирающий значение в Datalist
    public void DatalistInput(String city){
        sendKeys(By.xpath(DATALIST_XPATH), city);
    }

    // проверка получения значения из datalist input
    public String getDatalistInputValue() {
        return findElement(By.xpath(DATALIST_XPATH)).getAttribute("value");
    }

    //Установить оба чек-бокса в положение true
    public boolean getDefaultCheckBoxState() {
        return findElement(By.xpath(DEFAULT_CHECKBOX_XPATH)).isSelected();
    }

    public boolean getCheckedCheckBoxState() {
        return findElement(By.xpath(CHECKED_CHECKBOX_XPATH)).isSelected();
    }

    public void setDefaultCheckBox(boolean state) {
        if (!getDefaultCheckBoxState() == state) {
            click(By.xpath(DEFAULT_CHECKBOX_XPATH));
        }
    }
    public void clickDefaultRadio(){
        findElement(By.xpath(DEFAULT_RADIO_XPATH)).click();
    }
    public boolean isDefaultRadioSelected() {
        return findElement(By.xpath(DEFAULT_RADIO_XPATH)).isSelected(); // метод String.format позволяет подставить в плейсхолдер константы переменную checkBoxName
    }

    public void clickSubmit(){
        findElement(By.xpath(SUBMIT_XPATH)).click();
    }

    public String getFormTitleText() {
        return findElement(By.tagName("h1")).getText();
    }

    public void clickDatePicker(){
        click((By.xpath(SELECT_DATE_ELEMENT)));
    }

    public void setDate() {
        click(By.xpath(DATEPICKER_SWITCH));
        click(By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']"));
        click(By.xpath(yearInDatePicker("2024")));
        click(By.xpath(monthInDatePicker("Sep")));
        click(By.xpath(dayInDatePicker(23)));
    }

    public String getSelectedDate() {
        return findElement(By.xpath(SELECT_DATE_ELEMENT)).getAttribute("value");
    }

    public void inputColor (String color){
        sendKeys(By.xpath(INPUT_COLOR), color);
    }

    public String getSelectedColor() {
        return  findElement(By.xpath(INPUT_COLOR)).getAttribute("value");
    }

    public void setInputRange() {
        WebElement slider = findElement(By.xpath(INPUT_RANGE));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", slider, "10");
    }

    public String getInputRange() {
        return findElement(By.xpath(INPUT_RANGE)).getAttribute("value");
    }

}
