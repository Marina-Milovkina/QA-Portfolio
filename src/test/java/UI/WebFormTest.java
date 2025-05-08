package UI;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.WebFormPage;

import static org.testng.Assert.*;

public class WebFormTest extends BaseTest {

    private WebFormPage webFormPage;

    @BeforeClass
    public void beforeClass(){
        System.out.println("Setting up before class in WebFormTest.");
        webFormPage = new WebFormPage(driver); // помещаем в переменную новый объект и передаем ему драйвер
    }

    @Test(description = "Перейти на страницу")
    public void step_01(){
        webFormPage.openWebFormPage();
        Assert.assertEquals(webFormPage.getPageName(),"Web form");
    }

    @Test(description = "Ввести фио, пароль и название компании")
    public void step_02(){
        webFormPage.inputForm("Миловкина Марина Валентиновна", "Password", "Liga");
        assertEquals(webFormPage.getNameFromTable(), "Миловкина Марина Валентиновна");
        assertEquals(webFormPage.getPasswordFromTable(), "Password");
        assertEquals(webFormPage.getCompanyFromTable(), "Liga");
    }

    @Test(description = "В поле «Dropdown select» выбрать значение «two»")
    public void step_03(){
        webFormPage.selectFromDropdown("Two");
    }



    @Test(description = "В Datalist выбрать значение «Seattle»")
    public void step_04(){
        webFormPage.DatalistInput("Seattle"); // ввести значение в datalist
        assertEquals(webFormPage.getDatalistInputValue(),"Seattle");
    }

    @Test(description = "Установить оба чекбокса в состояние TRUE")
    public void step_05(){
        webFormPage.setDefaultCheckBox(true);
        assertTrue(webFormPage.getDefaultCheckBoxState());
        assertTrue(webFormPage.getCheckedCheckBoxState());
    }

    @Test(description = "Выбрать radioButton")
    public void step_06(){
        webFormPage.clickDefaultRadio();
        assertTrue(webFormPage.isDefaultRadioSelected(), "Radio button должен отображаться на странице");
    }

    @Test(description = "Ввести дату 09.23.2024")
    public void step_07(){
        webFormPage.clickDatePicker();
        webFormPage.setDate();
        assertEquals(webFormPage.getSelectedDate(), "09/23/2024");
    }

    @Test(description = "Выбрать зеленый цвет в color picker")
    public void step_08(){
        webFormPage.inputColor("#00ff00");
        assertEquals(webFormPage.getSelectedColor(), "#00ff00");
    }

    @Test(description = "Перевести Example range в максимальное состояние")
    public void step_09() {
        webFormPage.setInputRange();
        assertEquals(webFormPage.getInputRange(), "10");
    }

    @Test(description = "Нажать на Submit")
    public void step_10(){
        webFormPage.clickSubmit();
        assertEquals( webFormPage.getFormTitleText(), "Form submitted");
    }
}

