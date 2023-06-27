package qa_guru_allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;


public class SelenideTest {
    @Test
    public void testIssueCheck() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com/");
        $(".header-search-input").setValue("selenide/selenide").submit();
        $(linkText("selenide/selenide")).click();
        $("#issues-tab").click();
        $("#issue_" + 2336 + "_link").shouldHave(text("Authentication issue"));
    }
}
