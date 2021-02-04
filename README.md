**Repository Details**

This Repository is created to Automate the test cases related to Westpac KiwiSaver Retirement Calculator

---

## Tools/Pre-requisites required to run this project: 

1. Latest version of IntelliJ installed
2. java version "1.8.0_281" installed
3. GitBash installed

---

## How to Execute the Test Cases

1. Clone the repository from

   a. navigate to the url: https://github.com/SusmithaKrishna/test-automation-suite
   
   b. click on code -> ensure HTTPS is selected -> copy the HTTPS URL: https://github.com/SusmithaKrishna/test-automation-suite.git
   
   c. clone the code to your local using "git clone https://github.com/SusmithaKrishna/test-automation-suite.git"=
        
2. Open the Gradle Project on your System using intelliJ

   a. open intellij community edition
   
   b. click on files
   
   c. select build.gradle file of test-automation-suite
   
   d. open as Project
   
3. Go to src->test->java->runners->cucumberRunner

4. Right click on the file and click on Run 'CucumberRunner'

5. Once the test run is completed, to view the cucumber reports navigate to build -> reports -> cucumber_reports -> cucumber-results-test-results.html -> right click -> Open in -> Browser -> select the browser that you want to see the reports in
