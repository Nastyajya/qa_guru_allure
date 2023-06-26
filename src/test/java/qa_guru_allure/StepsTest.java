package qa_guru_allure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class StepsTest {
    private static final String REPOSITORY = "Selenide/";
    private static final String ISSUE = "issue";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываеи главную мтраницу", () -> {
        open("https://github.com/");
        });

        step("Ищем репозиторий" + REPOSITORY, () -> {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
        $(byTagAndText("a", REPOSITORY));
        });
        step("Проверяем наличие текста issue", () -> {
        $(withText(ISSUE)).should(exist);
        });
    }

    @Test
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryText(REPOSITORY);
        steps.shouldSeeIssue(ISSUE);

    }
}