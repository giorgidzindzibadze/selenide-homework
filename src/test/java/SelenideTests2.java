import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.downloadsFolder;
import static com.codeborne.selenide.Selenide.*;

@Listeners({SoftAsserts.class})
public class SelenideTests2 {
    public void SelenideTests() {
        baseUrl = "http://the-internet.herokuapp.com";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        timeout = 20000;
        holdBrowserOpen = false;
        screenshots = false;
        baseUrl = "http://the-internet.herokuapp.com";
        reopenBrowserOnFail = true;
        downloadsFolder = "src/main/resources/Pictures";
        fastSetValue = true;
        assertionMode = AssertionMode.SOFT;
        fileDownload = FileDownloadMode.HTTPGET;
        reportsFolder = "src/main/resources/failedScreens";
        downloadsFolder = "src/main/resources/images";

    }

    @Test
    public void demoqaTest() {
        reportsFolder = "src/main/resources/Reports";
        SoftAssert softAssert = new SoftAssert();
        open("https://demoqa.com/books ");
        SelenideElement s = $(".rt-tbody");
        ElementsCollection matchingBooks = s.$$(".rt-tr-group")
                .filterBy(Condition.text("O'Reilly Media"))
                .filterBy(Condition.text("Javascript"));
        softAssert.assertEquals(matchingBooks.size(), 10);
        softAssert.assertEquals(          //სხვა სელექტორით ვერ მივწვდი
                matchingBooks.get(0).$("a[href='/books?book=9781449331818']").text(),
                "Learning JavaScript Design Patterns",
                "First matching book's title is not as expected"
        );


        matchingBooks.stream().forEach(book -> {
            book.$(By.className("mr-2")).click();
            Selenide.executeJavaScript("window.scrollTo(0, 400);");
            SelenideElement backTo = $("#addNewRecordButton");
            backTo.click();
            Selenide.executeJavaScript("window.scrollTo(0, 600);");
        });

        softAssert.assertAll();


    }

    @Test
    public void demoqaTest2() {
        open("https://demoqa.com/books");
        SoftAssert softAssert = new SoftAssert();

        ElementsCollection books = $(".rt-tbody").$$(".rt-tr-group")
                .filterBy(Condition.text("O'Reilly Media"))
                .filterBy(Condition.text("Javascript"));

        for (SelenideElement book : books) {
            boolean isImageNotEmpty = book.$(By.xpath(".//img[contains(@alt, 'image')]")).isImage();
            softAssert.assertTrue(isImageNotEmpty, "Image is empty for a book.");
        }
        softAssert.assertAll();
    }
}

