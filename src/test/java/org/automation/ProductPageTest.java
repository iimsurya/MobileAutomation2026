package org.automation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ProductPageTest extends AppiumTest {


    @Test
    public void scrollAndChooseProduct() {

        scrollToElementUiScrollable("Jordan 6 Rings");
    }

    public void scrollToElementUiScrollable(String text){

    String UiScrollable = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + text + "\"))";
    driver.findElement(AppiumBy.androidUIAutomator(UiScrollable)).click();
    }
}
