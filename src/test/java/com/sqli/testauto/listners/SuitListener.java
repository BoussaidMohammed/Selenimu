package com.sqli.testauto.listners;

import com.sqli.testauto.tests.BaseTest;
import com.sqli.testauto.utils.RetryAnalyzer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SuitListener implements ITestListener, IAnnotationTransformer {



    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("screenshot");
        String filename = System.getProperty("user.dir")+ File.separator+"screenshots" + File.separator
                +result.getMethod().getMethodName();
        File f1 = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f1, new File(filename + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
