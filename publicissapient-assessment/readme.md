## PublicisSapient | Senior Quality Engineer | Assessment

### Introduction
Dear <candidate>,
this project is a very basic Cucumber-Selenium-Maven framework, which we will use in this assignment for automating the steps of three different scenarios. Feel free to change, adapt and add whatever you need.

Have fun!

### Objective
The objective of this assignment is not a full-fledged and fully functional test automation framework. We're more interested in the approach you would pick and in particular we would be interested in ...

 - How would the step definition look like?
 - How would you structure the (page) objects?
 - How would your selectors look like? 

### Preconditions to run the project

In case you want to run the project, you need to have the following software installed:

 - Java 8 SDK
 - Chrome
 - Maven
 
You also need to download the chromedriver. The path to the driver can be set in config.properties inside the project.

### How to run the project

In case you would like to run the project you can do this either via the Cucumber plugin of your IDE or via maven: mvn clean test

### Feature File

The feature file, that should get automated in this assignment can be found in *src/test/resources/features/searchResults.feature*

### Test Object
The URL to the website is www.publicissapient.com/. This is also already configured in the config.properties file.