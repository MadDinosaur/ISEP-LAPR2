# US 9 - Define a new type of test and its collecting methods 

## 1. Requirements Engineering

### 1.1. User Story Description

*As an administrator, I want to specify a new type of test and its collecting methods.*

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>Such tests rely on measuring one or more parameters that can be grouped/organized by categories.
 
**From the client clarifications:**

In the US9 what do you mean by the collecting methods and  what collecting methods  are available??
>When the administrator (US9) specifies a new type of test, the administrator also specifies the method to collect a sample. The administrator introduces a brief description for each collecting method.

Should the collection method be associated to the test type or to the sample type?
>With the test type.

### 1.3. Acceptance Criteria

*There were no acceptance criteria found.*

### 1.4. Found out Dependencies

*There is a dependency to "US10 - Specifying a new parameter and categorizing it" since any type of test requires different parameters to be measured, which are grouped into categories.*

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a designation;
    * a description;
  
* Selected data:
    * a category, or more
  
**Output Data:**

* (In)Success of the operation.


### 1.6. System Sequence Diagram (SSD)

![US9_SSD](US9_SSD.svg)


### 1.7 Other Relevant Remarks

>The administrator introduces a brief description for each collecting method.


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US9_DM](US9_DM.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1 		 |	starting a new test type?| Administrator             |                              |
| Step 2  		 |	instantiating a new test type?	| Company					| Creator Pattern            |                              |
| Step 3         |  requesting the data required? | CreateTestTypeUI | |
| Step 4         |  typing the data required? | Administrator
| Step 5  		 |	setting the designation?						 | TestType            |                              |
| Step 6  		 |	setting the collection method?						 | CollectionMethod             |                              |
| Step 7  		 |	adding categories to the test type?					 | TestType             |                              |
| Step 8  		 |	validating the data globally?						 | Company            |                              |              
| Step 9         |  saving the created test type? | Company | |
| Step 10        |  informing operation success? | CreateTestTypeUI | |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * TestType
 * Category
 * Company
 * CollectionMethod
 * Administrator

Other software classes (i.e. Pure Fabrication) identified: 
 * CreateTestTypeUI  
 * CreateTestTypeController

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.* 

![US9_SD](US9_SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![US9_CD](US9_CD.svg)

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





