package es.lobosquadrugby;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotOnFailureExtension
        implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(
            ExtensionContext context,
            Throwable throwable
    ) throws Throwable {

        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof UnetePageTest) {
            UnetePageTest test = (UnetePageTest) testInstance;
            WebDriver driver = test.getDriver();

            if (driver instanceof TakesScreenshot) {
                try {
                    byte[] screenshot =
                            ((TakesScreenshot) driver)
                                    .getScreenshotAs(OutputType.BYTES);

                    Allure.addAttachment(
                            "Screenshot da falha",
                            "image/png",
                            new ByteArrayInputStream(screenshot),
                            ".png"
                    );
                } catch (Exception e) {
                    System.err.println(
                            "Não foi possível capturar o screenshot: "
                                    + e.getMessage()
                    );
                }
            }
        }

        throw throwable;
    }
}