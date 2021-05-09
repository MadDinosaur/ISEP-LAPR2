# US 10 -  Specify a new employee

## 1. Requirements Engineering

### 1.1. User Story Description

As an administrator, I want to specify a new parameter and categorize it.

### 1.2. Customer Specifications and Clarifications

####From the specifications document

* "Blood tests are frequently characterized by measuring several parameters which for presentation/reporting purposes are organized by categories. For example, parameters such as the number of Red Blood cells (RBC), White Blood Cells (WBC) and Platelets (PLT) are usually presented under the blood count (Hemogram) category,"
* "Covid tests are characterized by measuring a single parameter stating whether it is a positive or a negative result."
* "Regardless, such tests rely on measuring one or more parameters that can be grouped/organized by categories."

####From the client clarifications

* **Question**: "Which information will the client provide for the addition of a new parameter and its categorization?" **Answer**: "Each parameter is associated with one category. Each parameter has a Code, a Short Name and a Description. The Code are five alphanumeric characters. The Short Name is a String with no more than 8 characters. The Description is a String with no more than 20 characters."

* **Question**: "Does the client want to specify more than one new parameter at a time?" **Answer**: "Yes."

* **Question**: "What are the acceptance criteria for a new parameter category?" **Answer**: "In my previous posts you can find other requirements that must be met in order to mark US11 as complete."

* **Question**: "When a new parameter is specified, should there always be a category it falls under prior to its specification and subsequent categorization?" **Answer**: "Each parameter is associated with one category."

### 1.3. Acceptance Criteria

* AC1: The code introduced should have 5 alphanumerical charaters.
* AC2: The name for the new parameter should be a string with no more than 8 characters.
* AC3: The description should be a string with less than 20 characters.


### 1.4. Found out Dependencies

* User story 10 is dependent on User story 11 considering the fact that the categorization of a new parameter requires the existance of a parameter category to include it in.

### 1.5 Input and Output Data

* **Typed data:** New parameter name; Code; Short description.
* **Selected data** Parameter category.

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
| Step 1: Start a new parameter |	... instantiating a new Parameter? | Category | Creator: aggregates objects of Parameter class |
| Step 2: Shows list of parameter categories and asks to choose the one the new parameter falls under | ... getting the list of parameter categories available? | Company | IE: knows all the Category objects |
| Step 3: selects the parameter's category | N/A |  |  |
| Step 4: Requests the data for the new parameter  | N/A |  |  |
| Step 5: Types in requested data | N/A |  |  |
| Step 6: Shows the data and asks for confirmation | N/A |  |  |
| Step 7: Confirms typed data | ... validating and saving the typed data? | Category | IE: knows its own parameters |
| Step 8: informs the operation's success | ... informing operations success? | CreateNewParameterUI | PF: responsible for user interaction |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* **Company**
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



# 6. Integration and Demo

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*



# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





