# Supplementary Specification (FURPS+)

## Functionality
_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
| **Authentication** |Authentication |All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits. |
| **Persistence** | Object serialization | The application should use object serialization to ensure data persistance between two runs of the application. |
| **Security** | Access control|Only the specialist doctor is allowed to access all client data. 
| **Localisation** | English language | The application must support the English language only. |


## Usability 
_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
| **Accessibility** | User manual | The application must delivered with a user manual.
| **Consistency** | Graphical interface | The application must have a graphical interface which must be simple, intuitive and consistent. 


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|**Availability**| System failure |The system should not fail more than 5 days in one year. 
|**Recoverability**| Data loss | Whenever the system fails, there should be no data loss.


## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|**Response Time** | User response time |Any interface between a user and the system shall have a maximum response time of 3 seconds. 
|**Start-up Time** | Start-up time |The system should start up in less than 10 seconds. 



## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|**Testability** |Unit tests and coverage report |Unit tests must be implemented for all methods except methods that implement Input/Output operations. 
|**Adaptability** |Other tests support|The system should be developed having in mind the need to easily support other kinds of clinical tests (e.g. urine). 
|**Configurability**|Ordering algorithm | The ordering algorithm to be used by the application must be defined through a configuration file.|


## +

### Design Constraints
_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|**Best Practices** |CamelCase coding standard | The implementation must adopt recognized coding standards, in this case CamelCase.
|**Best Practices** |OO software analysis and design |The implementation must adopt OO standards.


### Implementation Constraints
_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|**Implementation Languages** | Java code base | The application must be developed in Java language using the IntelliJ IDE or Netbeans. 
|**Implementation Languages** | JavaFX 11 graphical interface |The application graphical interface is to be developed in JavaFX 11. 
|**Standards Compliance** | Javadoc documentation | The Java code must use Javadoc to generate useful documentation. 
|**Standards Compliance** | JUnit 4 testing framework | The unit tests should be implemented using the JUnit 4 framework. 
|**Standards Compliance** | JaCoCo test coverage | The JaCoCo plugin should be used to generate the test coverage report. 
|**Platform Support** | Java Virtual Machine | The application should run on all platforms for which there exists a Java Virtual Machine. |
|**Resource Limits** |RAM |The application must run in a machine with 8GB of RAM. 




### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


| **_Subcategory_** | **_Function_** | **_Description/Example_** |
|:------------------------|:-----------------|:--------------------------------------------|
|**External Systems** |Barcode API |The system must interface with an external API to automatically generate barcodes identifying each sample. 
|**External Systems** |NHS Report API | The system is required to generate daily automatic reports with all the information demanded by the NHS and should send them to the NHS using their API. 
|**External Systems** |Test validation module |The application uses na external module that is responsible for doing na automatic validation using test reference values. 
|**External Systems** |SMS and e-mail |The application must be able to notify clients by SMS and e-mail. 



### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

None.