package qa_guru_allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class StepsTest {
    private static final String REPOSITORY = "selenide/selenide";
    private static final int ISSUE = 2336;
    private static final String ISSUENAME = "Authentication issue";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").setValue("selenide/selenide").submit();
            $(linkText("selenide/selenide")).click();
        });
        step("Переходим на вкладку issue", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем что issue с номером" + ISSUE + "отображает название" + ISSUENAME, () -> {
            $("#issue_" + ISSUE + "_link").shouldHave(text(ISSUENAME));
        });
    }

    @Test
    void testAnnotatedSteps() {
        WebSteps webSteps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        webSteps
                .openMainPage()
                .searchForRepository(REPOSITORY)
                .clickOnRepository(REPOSITORY)
                .clickIssueTab()
                .checkIssue(ISSUE, ISSUENAME);
    }
}