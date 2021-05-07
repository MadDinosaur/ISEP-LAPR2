# US8 - Register a new clinical analysis laboratory

## 1. Engineering Requirements

### 1.1 User Story Description

As an **administrator**, I want to **register a new clinical analysis laboratory** stating which
kind of test(s) it operates.

### 1.2. Specifications and Clarifications

**From the Specifications Document:**
> _"All Many Labs clinical analysis laboratories perform clinical blood tests, and a subset of
    these laboratories also performs Covid-19 tests."_

**From the client clarifications:**
* **Q:** What information is associated with a clinical Analysis laboratory (e.g. designation, localization...)? What rules are applicable to such data?
    * **A:** Each Clinical Analysis Laboratory is characterized by the following attributes:
        * Laboratory ID;
        * Name;
        * Address;
        * Phone Number;
        * TIN number.

        The Chemical Laboratory is characterized by the following attributes:
        * Name;
        * Address;
        * Phone Number;
        * TIN number.

        Many Labs company has only one chemical lab.

* **Q:** Which type/format has LaboratoryID, address, name, phone number, and TIN number on Clinical Analysis Laboratory?
    * **A:** Each Clinical Analysis Laboratory is characterized by the following attributes:
         * Laboratory ID: five alphanumeric characters;
         * Name: A string with no more than 20 characters;
         * Address: A string with no more than 30 characters;
         * Phone Number: 11-digit number;
         * TIN number: 10-digit number;
         * Type of tests performed by the lab.
         
         All information is required.
         
### 1.3. Acceptance Criteria

* _**AC1**_ Laboratory's name must contain 20 characters or fewer.
* _**AC2**_ Laboratory's address should not have more than 30 characters.
* _**AC3**_ Laboratory's phone number must be an 11-digit number.
* _**AC4**_ Laboratory's TIN must be a 10-digit number.
* _**AC5**_ Laboratory ID should contain exactly 5 alphanumeric characters.

### 1.4. Found out Dependencies

There is a dependency to US9, before creating a new clinical analysis laboratory, the program needs to know what type of tests there are.

### 1.5. Input and Output data
**Input data:**
- **Typed data:** Clinical Analysis Laboratory Name, Lab Address, Phone Number, Tax Identification Number, Laboratory ID
- **Selected data:** Types of Test

**Output data:**
- Success of the operation.

### 1.6. System Squence Diagram

![US8-SSD](US8_SSD.svg)

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US8-DM](US8_DM.svg)

### 2.2. Other Remarks

*Use this section to capture some additional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).*

## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for...| Answer      | Justification (with patterns)  |
|:-------------  |:------------------------------------------ |:------------|:------------------------------ |
| Step 1  		 |register a new clinical analysis laboratory?|Administrator|                                |
| Step 2  		 |request information about the new laboratory?|newLabUI    |                                |
| Step 3  		 |give the new laboratory information?        |Administrator|                                |
| Step 4  		 |request type of tests performed by the lab? |newLabUI     |                                |
| Step 5  		 |select test types performed by the lab?	  |Administrator|                                |
| Step 6  		 |							                  |             |                                |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * ClinicalAnalysisLaboratory
 * TestType

Other software classes (i.e. Pure Fabrication) identified: 
 * newLabUI  
 * newLabController

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.* 

![US8-SD](US8_SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![US8-CD](US8_CD.svg)

# 4. Tests 
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.* 

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*

# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*