import com.codeborne.selenide.*;
import com.codeborne.selenide.testng.SoftAsserts;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
@Listeners({SoftAsserts.class})
public class SelenideTests {
    public SelenideTests() {
        baseUrl = "http://the-internet.herokuapp.com";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
            timeout=20000;
            holdBrowserOpen=false;
            screenshots=false;
            baseUrl = "http://the-internet.herokuapp.com";
            reopenBrowserOnFail = true;
            downloadsFolder="src/main/resources/Pictures";
            fastSetValue=true;
            assertionMode=AssertionMode.SOFT;
            fileDownload= FileDownloadMode.HTTPGET;
            reportsFolder="src/main/resources/failedScreens";
        downloadsFolder="src/main/resources/images";

    }
    @Test
    public void checkboxSelenide(){
        open("http://the-internet.herokuapp.com/checkboxes");
        $(byXpath("//*[@id=\"checkboxes\"]/input[1]")).click();
        $(byXpath("//*[@id=\"checkboxes\"]/input[1]")).shouldHave(Condition.attribute("type", "checkbox"));
        $(byXpath("//*[@id=\"checkboxes\"]/input[2]")).shouldHave(Condition.attribute("type", "checkbox"));
    }

    @Test
    public void dropdown(){
        open("http://the-internet.herokuapp.com/dropdown ");
        $("#dropdown").shouldHave(Condition.text("Please select an option"));
        $(byXpath("//*[@id=\"dropdown\"]/option[3]")).click();
        $("#dropdown").shouldHave(Condition.text("Option 2"));
    }
    @Test
    public void registration() {
        open("https://demoqa.com/text-box");
//        ვეცადე განსხვავებული სელექტორები გამომეყენებინა
        $(byXpath("//input[@type='text' and contains(@placeholder, 'Full Name')]")).setValue("Giorgi Dzindzibadze");
        $(byXpath("//input[@type='email' and contains(@class, 'mr-sm-2 form-control')]")).setValue("giorgidzindzibadze7@gmail.com");
        $("#currentAddress").setValue("Tbilisi, varketi III, 2 micro");
        Selenide.executeJavaScript("window.scrollTo(0, 200);");
        ElementsCollection elements = $$(".form-control");
        elements.get(3).setValue("Tbilisi, varketi III, 2 micro");
        $("#submit").click();
        Selenide.executeJavaScript("window.scrollTo(0, 400);");
        ElementsCollection elements1 = $$(".mb-1");
        elements1.shouldHave(CollectionCondition.exactTexts(
                "Name:Giorgi Dzindzibadze",
                "Email:giorgidzindzibadze7@gmail.com",
                "Current Address :Tbilisi, varketi III, 2 micro",
                "Permananet Address :Tbilisi, varketi III, 2 micro"
        ));


    }
}
