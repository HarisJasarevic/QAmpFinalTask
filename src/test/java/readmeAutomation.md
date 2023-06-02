## **PLACELAB DEMO APPLICATION - ROAD ANALYSIS - SMOKE TEST**

### **_DESCRIPTION:_**

**_This project is a test automation framework developed using Java and Selenium WebDriver. It includes test cases for logging into a web application PlaceLab demo, creating and deleting Road analysis Reports._**

### **Table of Contents**

* Prerequisites
* Setup
* Usage
* Test Cases
* Contributing
* License

### **_Prerequisites_**

To run this project, you need to have the following software installed on your machine:

* IntelliJ IDEA software (latest version)
* Java Development Kit (JDK 19.0.2 version)
* Maven (latest version)
* Web browser (Chrome or Edge)

### **_Setup_**

* Clone the repository to your local machine.
* Open the project in your preferred Java IDE.
* Configure the project dependencies and build path.
* Update the WebDriverSetup class to specify the correct paths for the WebDriver binaries.
* Update the GlobalValues class to set the appropriate URLs for the web application.
* Set up the test data and test environment as needed.
* In RoadAnalysisPage class, in code line 42,43 and 44 are set paths to sample files located in resources directory. For this test we'll use set path in line 43. On your local machine path must be set as Absolute path to this sample file in directory you cloned the project.

### **_Usage_**

To run the test cases:

1. Open a terminal or command prompt.
2. Navigate to the project directory.
3. Run the following command to execute the tests:

`bash`
`Copy code`

`mvn clean verify -Demail="haris.jasarevic@hotmail.com" -Dpassword="Tijana0306!?!?"`

IMPORTANT NOTE: As project is private and under supervision of ATLANTBH, this information are only shared in this private repository and can be used only from ATLANTBH authorized personell.
4. The tests will be executed using the specified browser (default is Chrome).
5. After the test execution is completed, the test results will be displayed in the terminal or command prompt.

### **_Test Cases_**

The project includes the following test cases:

**1. LoginTestWithValidCredentials:** This test case validates that a user can successfully log in to the web application using valid credentials.

**2. RoadAnalysisTest:** This test case performs end-to-end testing for the road analysis functionality. It includes the following steps:

* Logging in to the application.
* Navigating to the Road Analysis Page.
* Populating the Road Analysis Form and creating a report.
* Verifying that the report is created and displayed in the queries page.
* Deleting the report.
* Logging out of the application.

### **_Contributing_**

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please create a pull request or submit an issue on the project repository.

Added contributors to this project are:
* medzida.mustafic@atlantbh.com
* nejla.maksumic@atlanbh.com
* tarik.basic@atlantbh.com
* bilal.cehic@atlantbh.com
* gorjan.kalauzovic@atlantbh.com

### **_License_**

This project is licensed under the ATLANBH License. You are free to modify and distribute the code as per the terms of the license.