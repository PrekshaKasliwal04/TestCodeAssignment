This automation framework is for BBLog application

Project Introduction: It is a Maven-based project with Selenium TestNG Framework is written in JAVA language.
                      Page Object Model design pattern has been used to create an Object Repository for web elements with a focus on to achieve Code Reusability and Code Maintainability.

Pre - requisites:
1. IDE
2. Java SDK 1.8
3. Maven 3.3 or newer

Downloading and setting the project:
1. Please clone or download the project from https://github.com/PrekshaKasliwal04/BBlog_Test_Assignment/tree/master/FrontEnd_Test_Assignment_BBlog
2. Install project as a new Maven project File > Import > Maven > Existing Maven Projects

Executing project:
IDE: 
   1. Using pom.xml file: Right-click and Run as Maven Install or Maven test. 
   2. Running testng.xml file: This file is under the suites folder and can be run using a right-click option.

Execution Results:
    Test Results: Test reports can be found at " target\surefire-reports".
    
Framework Choice: 
1. The combination of Selenium and Java is preferred as an automation framework because of being highly flexible due to its compatibility with a wide range of browsers and scripting languages. 
2. Java provides us different libraries that can be used directly in the project. TestNG is used to handle the automated test suite execution and report generation.

Key Notes: 
1. Framework is designed to retry failed test cases twice. 
2. Browser, environment and TestNG suite parameters can be passed from pom.xml. 
3. Properties file is used to maintain object repository. (All locator values are mentioned inside it along with environment.properties to make it easy to amend values without actually changing the class files.) 
4. Reusable and common operational methods are written to optimize code inside package com.basesource.actions
