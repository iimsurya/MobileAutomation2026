package org.automation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;
import java.time.Duration;

public class AppiumTest {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    @Test
    public void openDevice() throws Exception {

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();

        service.start();

        UiAutomator2Options option = new UiAutomator2Options();
        option.setDeviceName("Pixel 8");
        option.setApp("/Users/suryav/eclipse-workspace/MobileAutomation2026/src/test/java/Apps/General-Store.apk");

        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Your automation code here

        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Argentina']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Surya");
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.className("android.widget.Button")).click();

        System.out.println(driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")).getText());

        driver.quit();
        service.stop();
    }

}
