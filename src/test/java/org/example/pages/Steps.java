package org.example.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {
    @Step("Открыть главную страницу github.com")
    public void openMainPage() {
        open("https://github.com/");
    }
    @Step("Нажать на поисковую строку")
    public void openAdvancedSearch() {
        $(".header-search-button").click();
    }
    @Step("Ввести в поисковую строку запрос \"{repository}\" и нажимаем enter")
    public void searchForRepository(String repository) {
        $("#query-builder-test").setValue(repository).pressEnter();
    }
    @Step("Перейти в репозиторий \"{repository}\", кликнув по названию")
    public void clickOnRepositoryLink(String repository) {
        $(linkText(repository)).click();
    }
    @Step("Перейти на вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }
    @Step("Перейти в Issue c #{issue}, кликнув по названию")
    public void openIssuePage(int issue) {
        $("#issue_" + issue + "_link").click();
    }
    @Step("Проверить, что Issue с #{issueName} отображается заголовок \"{issueName}\"")
    public void issueNameShouldBeCorrect(String issueName) {
        $(".gh-header-show")
                .shouldHave(text(issueName));
    }
}
