package com.sqli.testauto.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private int retryCount = 0;



    @Override
    public boolean retry(ITestResult iTestResult) {
        count++;
        return count <= retryCount;
    }
}
