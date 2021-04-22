# Use Case Diagram (UCD)

**In the scope of this project, there is a direct relationship of _1 to 1_ between Use Cases (UC) and User Stories (US).**

![Use Case Diagram](UCD.svg)


# Use Cases / User Stories
| UC/US  | Name | Actor | Description                   
|:----|:------------------------------------------------------------------------|:---|:----|
| US1 | [Register test](US1.md)| Receptionist | Register in the application the test to be performed to a client. |
| US2 | [Register sample](US2.md)| Medical Lab Technician | Record the sample in the system, associating the samples with the client/test, and identifying each sample with a barcode. |
| US3 | [Register client](US3.md)| Receptionist | Register a new client in the application (required client’s citizen card number, NHS number, birth date, sex, TIF, phone number, e-mail and name. |
| US4 | [Register analysis results](US4.md)| Clinical Chemistry Technologist | Record the results of the chemical analysis on the samples. |
| US5 | [Generate diagnosis](US5.md) | Specialist Doctor | The results of all chemical analysis are automatically validated using test reference values. The doctor makes a diagnosis with that information and writes a report to deliver to the client. |
| US6 | [Choose sorting method](US6.md)| Administrator | Display the list of clients sorted by name or by TIF. |
| US7 | [Specify a new employee](US7.md) | Administrator | Register a new employee in the application. |
| US8 | [Specify a new clinical analysis laboratory](US8.md) | Administrator | Register a new clinical analysis laboratory in the application and state what kind of tests it operates. |
| US9 | [Specify new test](US9.md) | Administrator | Specify a new type of test and its collecting methods. |
| US10 | [Specify new test parameter](US10.md) | Administrator | Specify a new test parameter and categorize it. |
| US11 | [Specify a new parameter category](US11.md) | Administrator | Specify a new test parameter category. |
| US12 | [Generate NHS report](US12.md) | System Clock | NHS requires automatic information about the number of COVID-19 tests performed, and to report the number of positive cases per day, week, month and year, generate forecasts and generate daily reports. |
| US13 | [Validate the clinical analysis](US13.md) | Laboratory Coordinator | Display the chemical test/result and associated diagnosis for validation. |
| US14 | [View results](US14.md) | Client  | Notify by SMS and e-mail when results are available. Display the results. |
| US15 | [Check results](US15.md) | Medical Lab Technician, Clinical Chemistry Technologist, Specialist Doctor, Laboratory Coordinator | Display the result information to all actors. |
| US16 | [View statistics for number of tests waiting for result](US16.md) | Lab Coordinator | Compute the time interval, in a week, when the company was less effective in responding. |
| US17 | [Access all client data](US17.md) | Specialist Doctor | Only the specialist doctor is allowed to access all client data. |
