package org.example;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.pages.Steps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AlureTest {

    @Test
    @Owner("Dinis")
    @Feature("Issue page")
    @Story("Issues")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка корректности имени Issue на странице Issue с помощью чистого Selenide")
    public void githubRepositoryShouldHaveUssuesTitle() {
        open("https://github.com");
        $(".header-search-button").click();
        $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();
        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $("#issue_" + 87 + "_link").click();
        $(".gh-header-show")
                .shouldHave(text("Issue for HW qa.guru"));
    }


    @Test
    @Owner("Dinis")
    @Feature("Issue page")
    @Story("Issues")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка корректности имени Issue на странице Issue с помощью lambda-функций")
    public void githubRepositoryShouldHaveUssuesTitleStep() {
        step("Открываем главную страницу", () -> open("https://github.com"));

        step("Ищем репозиторий roshenkoam/allure-example", () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue("eroshenkoam/allure-example").pressEnter();
            $(linkText("eroshenkoam/allure-example")).click();
        });

        step("Открываем Issue №87", () -> {
            $("#issues-tab").click();
            $("#issue_" + 87 + "_link").click();
        });

        step("Проверяем заголовок Issue", () -> {
            $(".gh-header-show")
                    .shouldHave(text("Issue for HW qa.guru"));
        });
    }

    @Test
    @Owner("Dinis")
    @Feature("Issue page")
    @Story("Issues")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка корректности имени Issue на странице Issue с помощью аннотации @Step")
    void checkIssueNameUsingStepAnnotationTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Steps steps = new Steps();

        steps.openMainPage();
        steps.openAdvancedSearch();
        steps.searchForRepository("eroshenkoam/allure-example");
        steps.clickOnRepositoryLink("eroshenkoam/allure-example");
        steps.openIssuesTab();
        steps.openIssuePage(87);
        steps.issueNameShouldBeCorrect("Issue for HW qa.guru");
    }
}
