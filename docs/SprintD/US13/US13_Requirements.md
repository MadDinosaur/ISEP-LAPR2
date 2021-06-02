# US 13 - Consulting historical tests performed by a particular client

## 1. Requirements Engineering

### 1.1. User Story Description

As a clinical chemistry technologist, I intend to consult the historical tests performed by a particular client and to be able to check tests details/results.

### 1.2. Customer Specifications and Clarifications

####From the specifications document

* "To facilitate the access to the results, the application must allow ordering the clients by TIF and by name. The ordering algorithm to be used by the application must be defined through a configuration file. It is intended that the choice of the ordering algorithm is based on the algorithm complexity (mainly the execution time). Therefore, at least two sorting algorithms should be evaluated and documented in the application user manual (in the annexes) that must be delivered with the application."

####From the client clarifications


### 1.3. Acceptance Criteria

* AC1: The application must allow ordering the clients by TIN and by name to help the clinical chemistry technologist choose the target client. The ordering algorithm to be used by the application must be defined through a configuration file. At least two sorting algorithms should be available.

### 1.4. Found out Dependencies



### 1.5 Input and Output Data



### 1.6. System Sequence Diagram (SSD)

![US10-SSD](US10_SSD.svg)

### 1.7 Other Relevant Remarks

* **Special requirements**: None;
* **Data and/or technology variations**: None
* **Frequency**: This US will happen mostly in the application's setup but being possible to happen further along the line if needed.

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US10-DM](US10_DM.svg)

## 3. Design - User Story Realization

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:---------------------|:------------|:---------------------------- |
| Step 1: Start parameter specification | N/A |  |  |
| Step 2: Shows list of parameter categories and asks to choose the one the new parameter falls under | ... getting the list of parameter categories available? | CategoryStore | High cohesion: knows all the parameter categories |
| Step 3: selects the parameter's category | ... setting the category? | CreateNewParameterController | Controller: saves instance of category |
| Step 4: Requests the data for the new parameter  | ... requesting the parameter data? | CreateNewParameterUI | Pure Fabrication: responsible for user interaction |
| Step 5: Types in requested data | ... saving the typed data? |  |  |
| Step 6: Shows the data and asks for confirmation | N/A |  |  |
| Step 7: Confirms typed data | ... validating and saving the new parameter? | Category | IE: knows its own parameters |
| Step 8: informs the operation's success | N/A |  |  |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* **CategoryStore**
* **Category**
* **Parameter**

Other software classes (i.e. Pure Fabrication) identified:
* CreateNewParameterUI
* CreateNewParameterController

## 3.2. Sequence Diagram (SD)

![US10-SD](US10_SD.svg)

## 3.3. Class Diagram (CD)

![US10-CD](US10_CD.svg)

# 4. Tests

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values.

	@Test(expected = InvalidNameException.class)
    public void testNullName(){
        String name = "";
        String code = "12345";
        String description = "Red blood cells";

        Parameter par = new Parameter(name, code, description);
    }

# 5. Construction (Implementation)

## Class CreateNewParameterController

    public void createNewParameter(String shortName, String code, String description) {
    this.par = this.pc.createNewParameter(shortName, code, description);
    }


# 6. Integration and Demo




# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





