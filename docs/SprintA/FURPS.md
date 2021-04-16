# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
| |Authentication |All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits. |
|Persistence | Object Serialization | The application should use object serialization to ensure data persistance between two runs of the application. |
| Security | Access Control|Only the specialist doctor is allowed to access all client data. 


## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
| Aesthetics | Graphical interface | The application must have a graphical interface which must be simple, intuitive and consistent. 


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|Availibility| |The system should not fail more than 5 days in one year. 
|Recoverability| | Whenever the system fails, there should be no data loss. 




## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|Response time | |Any interface between a user and the system shall have a maximum response time of 3 seconds. 
|Start-up time | |The system should start up in less than 10 seconds. 



## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|Testability |Unit tests and coverage report |Unit tests must be implemented for all methods except methods that implement Input/Output operations. 
|Adaptability |Other tests support|The system should be developed having in mind the need to easily support other kinds of tests (e.g. urine). 
|Compatibility |Plataforms | The application should run on all platforms for which there exists a Java Virtual Machine. 




## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|Implementation languages | English language | The application must support the English language only. 
|Platform support | Java code base | The application must be developed in Java language using the IntelliJ IDE or Netbeans. 
|Platform support | JavaFX 11 graphical interface |The application graphical interface is to be developed in JavaFX 11. 
|Platform support | Javadoc documentation | The Java code must use Javadoc to generate useful documentation. 
|Platform support | JUnit 4 testing framework | The unit tests should be implemented using the JUnit 4 framework. 
|Platform support | JaCoCo test coverage plugin | The JaCoCo plugin should be used to generate the test coverage report. 
|Standards-compliance |CamelCase coding standard | The implementation must adopt recognized coding standards, in this case CamelCase. 
|Standards-compliance |OO software analysis and design |The implementation must adopt OO standards. 
| |Brute-force algorithm |The application must implement a brute-force algorithm (an algorithm which examines each subsequence) to determine the testing process efficiency (number of tests waiting for result). The time complexity analysis should be documented in the user manual. 
| |Ordering algorithm |The application must allow ordering the clients by TIF and by name. At least two sorting algorithms should be evaluated, implemented and documented in the application user manual. 
| |Linear and multiple linear regression algorithms | The NHS requires that both simple linear and multiple linear regression algorithms should be evaluated to select the best model for Covid-19 cases predictions. 
|Resource limits |RAM |The application must run in a machine with 8GB of RAM. 




### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|External systems |Barcode API |The system must interface with an external API to automatically generate barcodes identifying each sample. 
|External systems |NHS Report API | The system is required to generate daily automatic reports with all the information demanded by the NHS and should send them to the NHS using their API. 
|External systems |Test validation module |The application uses na external module that is responsible for doing na automatic validation using test reference values. 
|External systems |SMS and e-mail |The application must be able to notify clients by SMS and e-mail. 



### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
