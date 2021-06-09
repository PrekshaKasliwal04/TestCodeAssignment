package com.basesource.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
public class RetryFailedTestCases implements IRetryAnalyzer {
    private int retryCnt = 0;
    private int maxRetryCnt = 2;
    
    //This method will be called every time a test fails. It will return TRUE if a test fails and need to be retried as per maxRetryCnt, else it returns FALSE
    public boolean retry(ITestResult result) {
        if (retryCnt < maxRetryCnt) {
            System.out.println("Retrying " + result.getName() + " again and the retry count is " + (retryCnt+1));
            retryCnt++;
            return true;
        }
        return false;
    }
   
}