# US 1 -  View results of the tests

## 1. Requirements Engineering

### 1.1. User Story Description

As a client, I want to access the application to view the results of the tests I have performed.

### 1.2. Customer Specifications and Clarifications 

**From the Specifications Document:**

* Once the laboratory coordinator confirms that everything was done correctly, the client receives a notification alerting that the results are already available in the central application and informing that he/she must access the application to view those results.

**From the client clarifications:**
* **Q: In US01 which date should be used to arrange the tests in order?The date the test is done or the validation date?**
  
  **A:** The test registration date.

### 1.3. Acceptance Criteria

* **AC1:** The client tests must be shown ordered from the most recent to the oldest one.
* **AC2:** The test results are shown only after the client has selected a test.

### 1.4. Found out Dependencies

* **US15:** Validate the work done by the CCT and SD.
* **US3:** Register a client.

### 1.5 Input and Output Data

**Input Data**
* **Typed data:** none
* **Selected data:** test

**Output Data**
* Test results

### 1.6. System Sequence Diagram (SSD)

![US1_SSD](US1_SSD.svg)


### 1.7 Other Relevant Remarks

* **Special requirements:** Graphical user interface.
* **Data and/or technology variations:** None.
* **Frequency:** This US will happen several times.

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt
![US2_DM](US2_DM.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
|...|
*Note: IE - Information Expert*

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * ...

Other software classes identified: 
 * ShowTestResultUI (applying the Pure Fabrication pattern)  
 * ShowTestResultController (applying the Pure Fabrication pattern)

## 3.2. Sequence Diagram (SD)

![US1_SD](US1_SD.svg)

## 3.3. Class Diagram (CD)

![US1_CD](US1_CD.svg)

# 4. Tests 

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*




