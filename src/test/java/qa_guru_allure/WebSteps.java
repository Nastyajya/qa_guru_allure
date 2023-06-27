package qa_guru_allure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную cтраницу")
    public WebSteps openMainPage() {
        open("https://github.com/");
        return this;
    }

    @Step("Ищем репозиторий {repo}")
    public WebSteps searchForRepository(String repo) {
        $(".header-search-input").setValue(repo).submit();
        return this;
    }

    @Step("Кликаем по линку {repo}")
    public WebSteps clickOnRepository(String repo) {
        $(linkText(repo)).click();
        return this;
    }

    @Step("Переходим на вкладку issue")
    public WebSteps clickIssueTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверяем что yомер {number} имеет название {name} ")
    public WebSteps checkIssue(int number, String name) {
        $("#issue_" + number + "_link").shouldHave(text(name));
        return this;
    }
}
