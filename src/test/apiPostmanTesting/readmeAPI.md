# **PlaceLab API Testing**

## **_PlaceLab API Testing Collection_**

### **_Introduction_**

The "PlaceLab API Testing" collection is a set of API requests designed to test the functionality of the PlaceLab API. This documentation provides an overview of the collection and focuses on the "Road Analysis Report - Smoke Test" folder.

The collection will be stored in package name: apiPostmanTesting

## **_Running API Tests in POSTMAN_**

In order to run exported PlaceLab API Testing collection you need to use exported PlaceLab Environment.
Environment contains secure information and is exported only to use under this project from authorized AtlanBH personnel.

PlaceLab Environment file wil be saved in package named: apiPostmanTesting

## **_Road Analyisis - Smoke Test_**

### **_Road Analysis - Smoke Test Folder_**

The "Road Analysis - Smoke Test" folder contains API requests related to road analysis functionality. These requests are designed to perform a smoke test that contains login to PlaceLab and testing on the road analysis feature of the PlaceLab API. Below are the details of the API requests in this folder.



### **_Request 1: POST Generate Auth Token_**
**`POST`**
**`Generate Auth Token`**
**_`/api/v2/sessions`_**

#### **_Description:_** Generates an authentication token to access the PlaceLab API.

* Request Method: POST
* End point: /api/v2/sessions
* Headers:
application/x-www-form-urlencoded**
* Body:
Provides email and password (from PlaceLab environment) in the JSON format.
* Response:
* Status 200: OK - Returns the authentication token.
* Status 400: Bad Request - Indicates an invalid request or missing parameters.
* Auth token:
Received authentication token is saved as "accesstoken" into PlaceLab Environment trough tests
* Request Headers:
* Content-Type
* application/x-www-form-urlencoded

Body:
urlencoded
* email
* password


### **_Request 2: POST Create Road Analysis report_**

**`POST`**
**`Create Road Analysis Report`**
**`/api/v2/reports/road_analysis?name=Road%20Analysis%20Report%20313`**

#### **_Description: Creates a Road Analysis Report for a specific location provided in uploaded file._**

* Request Method: POST
* Endpoint: /api/reports/road_analysis
* Headers:
* Authorization: Authorization token (Provided from PlaceLab environment)
* Body:
Body is in form-data format and provides file from local machine (Located in resources folder) with requested information
* Response:
* Report ID: Created report ID is stored in PlaceLab Environment trough tests an "reportID" variable.
* Status 200: OK - Returns a success message indicating the road analysis report creation.
* Status 400: Bad Request - Indicates an invalid request or missing parameters.
* Status 401: Unauthorized - Indicates authentication failure.
* Request Headers:
Authorization
* Query Params:
* name
* Road%20Analysis%20Report%20199

* Body:
form-data
* file:
QAmpFinalTask/src/test/resources/roads_sample_NewYork.txt



### **_Request 3: GET Check Road Analysis report_**

**`GET`**
**`Check Road Analysis Report`**
**`api/v2/reports//status`**

#### **_Description: Retrieves the status of a Road Analysis Report for a specific location._**

* Request Method: GET
* Endpoint: /api/v2/reports/{reportId}/status
* Headers:
* Authorization: Authorization token (Provided from PlaceLab environment)
* Path Parameters:
* reportId: The unique identifier of the road analysis report (Provided from PlaceLab environment)
* Test: Provided test in this request handles the possible errors and retrys the request (with set timeout of 30 seconds because of time needed to create report) until status of request is "FINISHED"
* Response:
* Status 200: OK - Returns the status of the Road Analysis Report

* Status 400: Bad Request - Indicates an invalid request or missing parameters.
* Status 401: Unauthorized - Indicates authentication failure.
* Status 404: Not Found - Indicates that the road analysis report with the specified ID was not found.
* Request Headers:
Authorization


### **_Request 4: GET Download Road analysis report_**

`GET`
`Download Road Analysis Report`
`api/v2/reports//download`

#### **_Description: Downloads a Road Analysis Report for a specific location._**

* Request Method: GET
* Endpoint: /api/v2/reports/{reportId}/download
* Headers:
* Authorization: Authorization token (Provided from PlaceLab environment)
* Path Parameters:
* reportId: The unique identifier of the road analysis report. (Provided from PlaceLab environment)
* Tests: In this request test handles all possible errors
* Response - Possible responses and errors for requested APIs are:
* 200: OK
* 400: Bad request
* 401: Unauthorized
* 403: ForbiddenThis error can happen if user authentication failed.
* 404: Not found resource
* 409: Conflict (Conflict error can happen if a user is trying to download a result file for a report that has not finished processing yet.)
* 422: Unprocessable entity (Unprocessable entity error signalizes that the user has made a mistake with the params needed for the action. The mistake can be an erroneous param name, an erroneous param type or a wrong number of params.)
* 501: Not implemented
* 520: Unknown errorEndFragment
* Request Headers
Authorization

`Example`

Request:

cURL
curl --location 'api/v2/reports//download' \
--header 'Authorization;'

`Response`

`Body`
`Headers (14)`

RECORD_ID|STATUS_DRIVING|STATUS_PEDESTRIAN
34567|Valid|No Data

## **_Conclusion_**

The "Road Analysis - Smoke Test" folder within the "PlaceLab API Testing" collection focuses on testing the road analysis functionality of the PlaceLab API. By executing the requests in this folder and reviewing the responses, you can ensure the proper functioning of the road analysis feature.