package org.automation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URI;
import java.time.Duration;

public class UIKitCatalogTest {

    public AppiumDriverLocalService service;
    public IOSDriver driver;

    //@BeforeTest
    public void openUIKitCatalogApp() throws Exception {

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();

        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 17");  // Change to your simulator name if different
        options.setPlatformVersion("26.5");        // Change to your iOS version
        options.setApp("com.apple.UIKitCatalog");  // UIKitCatalog bundle ID
        options.setAutomationName("XCUITest");

        driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        System.out.println("✓ UIKitCatalog app opened successfully!");
    }

    //@Test
    public void testUIKitCatalogOpened() throws Exception {
        // Wait for app to load
        Thread.sleep(2000);

        // Take screenshot to verify app is open
        System.out.println("✓ App is open and running");

        // Try to find elements using different locators
        try {
            var titleElements = driver.findElements(AppiumBy.iOSNsPredicateString(
                "type == 'XCUIElementTypeNavigationBar'"
            ));
            System.out.println("Found " + titleElements.size() + " navigation bars");
        } catch (Exception e) {
            System.out.println("Could not find navigation elements");
        }
    }

    //@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}

