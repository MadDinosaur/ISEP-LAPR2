package app.domain.store;

import app.domain.model.*;
import app.domain.model.Exceptions.InvalidTestCodeException;
import app.domain.model.Exceptions.InvalidTestException;
import app.domain.model.Exceptions.TestDoesntExistException;
import app.domain.model.Exceptions.UnregisteredBarcodeException;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore implements Serializable {
    private final List<Test> tests = new ArrayList<>();


    public boolean addTest(Test test) {
        validateTest(test);
        return this.tests.add(test);
    }

    public boolean validateTest(Test test1) {
        for (Test test2 : tests) {
            if (test1.equals(test2)) {
                throw new InvalidTestException();
            }
        }
        return true;
    }

    public List<Test> getRegisteredTests() {
        List<Test> readyForCollection = new ArrayList<>();
        for (Test test : tests)
            if (test.isRegistered())
                readyForCollection.add(test);

        return readyForCollection;
    }

    public List<Test> getTestsWithCollectedSamples() {
        List<Test> readyForAnalysis = new ArrayList<>();
        for (Test test : tests)
            if (test.hasCollectedSamples())
                readyForAnalysis.add(test);

        return readyForAnalysis;
    }

    /**
     * Returns a list of tests which are on the SAMPLES_ANALYZED state
     *
     * @return List<Test>
     */
    public List<Test> getTestsListReadyForReport() {
        List<Test> readyForDiagnosis = new ArrayList<>();
        for (Test test : tests)
            if (test.hasAnalyzedSamples())
                readyForDiagnosis.add(test);

        return readyForDiagnosis;
    }

    public List<Test> getListTestsWithReport() {
        List<Test> readyForValidation = new ArrayList<>();
        for (Test test : tests)
            if (test.hasDiagnosis())
                readyForValidation.add(test);

        return readyForValidation;
    }

    public List<Test> getTestsValidated() {
        List<Test> validated = new ArrayList<>();
        for (Test test : tests)
            if (test.isValidated())
                validated.add(test);

        return validated;
    }

    public List<Test> getListPositiveCovid() {
        List<Test> positiveCovid = new ArrayList<>();
        for (Test test : tests) {
            if (test.getTestType().getCode().equals("Covid")) {
                if (test.isValidated()) {
                    List<TestParameterResult> testParameterResultList = test.getTestParamResults();
                    for (TestParameterResult parameterResult : testParameterResultList) {
                        if (parameterResult.getValue() > 1.4) {
                            positiveCovid.add(test);
                        }
                    }


                }
            }
        }
        return positiveCovid;
    }

    public int getNumberOfPositiveCasesPerDay(String dayOfTest) {
        List<Test> listOfPositiveTests = getListPositiveCovid();
        int numberOfCases = 0;
        for (Test test : listOfPositiveTests) {
            String date = test.getDateTimeValidation();
            if(date != null) {
                String[] dateComponents = date.split(" ");
                date = dateComponents[0];
                if (dayOfTest.equalsIgnoreCase(date)) {
                    numberOfCases++;
                }
            }
        }
        return numberOfCases;
    }

    public double getNumberOfTestsPerformed(String dayOfTest) {
        double numberOfTestsMade = 0;
        for (Test test : tests) {
            String date = test.getDateTimeValidation();
            if(date != null) {
                String[] dateComponents = date.split(" ");
                date = dateComponents[0];
                if (dayOfTest.equalsIgnoreCase(date)) {
                    numberOfTestsMade++;
                }
            }
        }
        return numberOfTestsMade;
    }

    public double getAverageAgeOfClient(String dayOfTest) {
        double sumAge = 0;
        int numberOfClient = 0;
        for (Test test : tests) {
            String date = test.getDateTimeValidation();
            if(date != null) {
                String[] dateComponents = date.split(" ");
                date = dateComponents[0];
                if (dayOfTest.equalsIgnoreCase(date)) {
                    sumAge += test.getClient().getAgeInYears();
                    numberOfClient++;
                }
            }
        }
        if (numberOfClient != 0) {

            return sumAge / numberOfClient;
        } else {
            return 0;
        }
    }


    public Test getTestBySampleBarcode(String barcode) {
        for (Test test : tests)
            if (test.getSampleList().existsSample(barcode))
                return test;

        throw new UnregisteredBarcodeException();
    }

    public List<Parameter> getTestParameters(Test test) {
        return test.getTestParamList().getParameters();
    }

    public TestParameterResult createTestParameterResult(Test test, String paramCode, String result, String metric) {
        return test.createTestParameterResult(paramCode, result, metric);
    }

    /**
     * Returns a test by its code
     *
     * @param testCode Test's code
     * @return Test
     */
    public Test getTestByCode(String testCode) {
        for (Test t : tests) {
            if (t.getTestCode().equals(testCode)) {
                return t;
            }
        }
        throw new InvalidTestCodeException();
    }

    /**
     * Saves the report in the given test
     * @param test   Test
     * @param report Report
     */
    public void saveReport(Test test, Report report) {
        test.addReport(report);
    }

    public void validateTest(String nhsCode) {
        for (Test test : tests) {
            if (test.getNhsCode().equals(nhsCode)) {
                test.validateTest();
            }
        }
    }

    public boolean validadeTestCode(String code) {
        for (Test test : tests) {
            if (test.getNhsCode().equals(code)) {
                return false;
            }
        }
        return true;
    }

    public Test findTestThroughNhsCode(String nhsCode) {
        for (Test test : tests) {
            if (test.getNhsCode().equals(nhsCode)) {
                return test;
            }
        }
        throw new TestDoesntExistException();
    }

    public List<Test> getTestsByClient(Client client) {
        List<Test> clientTests = new ArrayList<>();
        for (Test test : tests)
            if (test.getClient().equals(client))
                clientTests.add(test);
        return clientTests;
    }

    public List<Test> getValidatedTestsByClient(Client client) {
        List<Test> clientTests = new ArrayList<>();
        for (Test test : tests)
            if (test.getClient().equals(client) && test.isValidated())
                clientTests.add(test);
        return clientTests;
    }


    /**
     * Getter for the total number of validated tests
     * @return total number of validated tests
     */
    public int getTotalNumberOfValidatedTests() {
        return getTestsValidated().size();
    }


    /**
     * Getter for the number of tests waiting for results in a specified date
     * @param date date
     * @return number of tests waiting for results
     */
    public int getNumberOfTestsWaitingForResultsInDate(Date date) {
            int numberOfTests = 0;
            for (Test test : getTests()) {
                try {
                    String[] dateAndTimeOfRegister = test.getDateTimeRegister().split(" ");
                    String[] dayMonthYear = dateAndTimeOfRegister[0].split("/");
                    int day = Integer.parseInt(dayMonthYear[0]);
                    int month = Integer.parseInt(dayMonthYear[1]);
                    int year = Integer.parseInt(dayMonthYear[2]);
                    Date dateOfRegister = new Date(year - 1900, month - 1, day);
                    String[] dateAndTimeOfResults = test.getDateTimeResults().split(" ");
                    String[] diaMesAno = dateAndTimeOfResults[0].split("/");
                    int dia = Integer.parseInt(diaMesAno[0]);
                    int mes = Integer.parseInt(diaMesAno[1]);
                    int ano = Integer.parseInt(diaMesAno[2]);
                    Date dateOfResults = new Date(ano - 1900, mes - 1, dia);
                    if (!dateOfRegister.after(date) && !dateOfRegister.before(date) && dateOfResults.after(date)) {
                        numberOfTests++;
                    }
                } catch (NullPointerException ignored) {

                }
            }
        return numberOfTests;
    }

    /**
     * Getter for the number of tests waiting for report in a specified date
     * @param date date
     * @return number of tests waiting for report
     */
    public int getNumberOfTestsWaitingForReportInDate(Date date) {
            int numberOfTests = 0;
            for (Test test : getTests()) {
                try {
                    String[] dateAndTimeOfResults = test.getDateTimeResults().split(" ");
                    String[] dayMonthYear = dateAndTimeOfResults[0].split("/");
                    int day = Integer.parseInt(dayMonthYear[0]);
                    int month = Integer.parseInt(dayMonthYear[1]);
                    int year = Integer.parseInt(dayMonthYear[2]);
                    Date dateOfResults = new Date(year - 1900, month - 1, day);
                    String[] dateAndTimeOfReport = test.getDateTimeReport().split(" ");
                    String[] diaMesAno = dateAndTimeOfReport[0].split("/");
                    int dia = Integer.parseInt(diaMesAno[0]);
                    int mes = Integer.parseInt(diaMesAno[1]);
                    int ano = Integer.parseInt(diaMesAno[2]);
                    Date dateOfReport = new Date(ano - 1900, mes - 1, dia);
                    if (!dateOfResults.after(date) && !dateOfResults.before(date) && dateOfReport.after(date)) {
                        numberOfTests++;
                    }
                } catch (NullPointerException ignored) {
                }
            }
        return numberOfTests;
    }

    /**
     * Getter for the number of validated tests in a specified date
     * @param date date
     * @return number of validated tests
     */
    public int getNumberOfTestsValidatedInDate(Date date) {
            int numberOfTests = 0;
            for (Test test : getTests()) {
                try {
                    String[] dateAndTimeOfValidation = test.getDateTimeValidation().split(" ");
                    String[] dayMonthYear = dateAndTimeOfValidation[0].split("/");
                    int day = Integer.parseInt(dayMonthYear[0]);
                    int month = Integer.parseInt(dayMonthYear[1]);
                    int year = Integer.parseInt(dayMonthYear[2]);
                    Date dateOfValidation = new Date(year - 1900, month - 1, day);
                    if (!dateOfValidation.after(date) && !dateOfValidation.before(date)) {
                        numberOfTests++;
                    }
                } catch (NullPointerException ignored) {
                }
            }
        return numberOfTests;
    }


    /**
     * Sorts each test for each half an hour of a day
     * @param hours hours
     * @param minutes minutes
     * @param size size
     * @param i i
     * @return array of integers
     */
    public int[] sortTestsForEachHalfAnHour(int hours, int minutes, int size, int i, int[] numberOfTestsForEachHalfAnHour) {
        if (hours == 8 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[i]++;
        } else if (hours == 8 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[1 + i]++;
        } else if (hours == 9 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[2 + i]++;
        } else if (hours == 9 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[3 + i]++;
        } else if (hours == 10 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[4 + i]++;
        } else if (hours == 10 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[5 + i]++;
        } else if (hours == 11 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[6 + i]++;
        } else if (hours == 11 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[7 + i]++;
        } else if (hours == 12 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[8 + i]++;
        } else if (hours == 12 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[9 + i]++;
        } else if (hours == 13 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[10 + i]++;
        } else if (hours == 13 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[11 + i]++;
        } else if (hours == 14 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[12 + i]++;
        } else if (hours == 14 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[13 + i]++;
        } else if (hours == 15 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[14 + i]++;
        } else if (hours == 15 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[15 + i]++;
        } else if (hours == 16 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[16 + i]++;
        } else if (hours == 17 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[17 + i]++;
        } else if (hours == 17 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[18 + i]++;
        } else if (hours == 18 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[19 + i]++;
        } else if (hours == 18 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[20 + i]++;
        } else if (hours == 19 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[21 + i]++;
        } else if (hours == 19 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[22 + i]++;
        } else if (hours == 20 && minutes > 30) {
            numberOfTestsForEachHalfAnHour[23 + i]++;
        } else if (hours == 20 && minutes < 30) {
            numberOfTestsForEachHalfAnHour[24 + i]++;
        }
        return numberOfTestsForEachHalfAnHour;
    }

    /**
     * Getter for the number of new tests for each half an hour
     * @param dateInterval date interval
     * @return array of numbers
     */
    public int[] getNumberOfNewTestsForEachHalfAnHour(List<Date> dateInterval) {
        int size = 24 * dateInterval.size();
        int[] numberOfTestsForEachHalfAnHour = new int[size];
        int i = 1;
        for (Date date : dateInterval) {
            for (Test test : getTests()) {
                String[] dateAndTimeOfRegister = test.getDateTimeRegister().split(" ");
                String[] hoursMinutes = dateAndTimeOfRegister[1].split(":");
                int hours = Integer.parseInt(hoursMinutes[0]);
                int minutes = Integer.parseInt(hoursMinutes[1]);
                String[] dayMonthYear = dateAndTimeOfRegister[0].split("/");
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);
                Date dateOfRegister = new Date(year - 1900, month - 1, day);
                if (!dateOfRegister.after(date) && !dateOfRegister.before(date)) {
                    numberOfTestsForEachHalfAnHour = sortTestsForEachHalfAnHour(hours, minutes, size, i, numberOfTestsForEachHalfAnHour);
                }
            }
            i = i + 24;
        }
        return numberOfTestsForEachHalfAnHour;
    }

    /**
     * Getter for the validated tests for each half an hour
     * @param dateInterval date interval
     * @return array of numbers
     */
    public int[] getNumberOfValidatedTestsForEachHalfAnHour(List<Date> dateInterval) {
        int size = 24 * dateInterval.size();
        int[] numberOfTestsForEachHalfAnHour = new int[24 * dateInterval.size()];
        int i = 1;
        for (Date date : dateInterval) {
            for (Test test : getTests()) {
                try {
                    String[] dateAndTimeOfValidation = test.getDateTimeValidation().split(" ");
                    String[] hoursMinutes = dateAndTimeOfValidation[1].split(":");
                    int hours = Integer.parseInt(hoursMinutes[0]);
                    int minutes = Integer.parseInt(hoursMinutes[1]);
                    String[] dayMonthYear = dateAndTimeOfValidation[0].split("/");
                    int day = Integer.parseInt(dayMonthYear[0]);
                    int month = Integer.parseInt(dayMonthYear[1]);
                    int year = Integer.parseInt(dayMonthYear[2]);
                    Date dateOfValidation = new Date(year - 1900, month - 1, day);
                    if (dateOfValidation.equals(date)) {
                        numberOfTestsForEachHalfAnHour = sortTestsForEachHalfAnHour(hours, minutes, size, i, numberOfTestsForEachHalfAnHour);
                    }
                } catch (NullPointerException ignored) {
                }
            }
            i = i + 24;
        }
        return numberOfTestsForEachHalfAnHour;
    }

    /**
     * Getter for the difference of new and validated tests for each half an hour
     * @param dateInterval date interval
     * @return array of numbers
     */
    public int[] getDifferenceOfNewAndValidatedTestsForEachHalfAnHour(List<Date> dateInterval) {
        int[] numberOfNewTests = getNumberOfNewTestsForEachHalfAnHour(dateInterval);
        int[] numberOfValidatedTests = getNumberOfValidatedTestsForEachHalfAnHour(dateInterval);
        int[] difference = new int[numberOfNewTests.length];
        for (int i = 0; i < difference.length; i++) {
            difference[i] = numberOfNewTests[i] - numberOfValidatedTests[i];
        }
        return difference;
    }

    /**
     * Creates a test from a CSV file
     * @param clinicalAnalysisLaboratory clinical analysis laboratory
     * @param client client
     * @param testCode test code
     * @param nhsCode nhs code
     * @param testTypeChosen the chosen test type
     * @param listOfChosenCategories the chosen list of categories
     * @param testParamList test param list
     * @param dateTimeRegister date and time of register
     * @param dateTimeResults date and time of results
     * @param dateTimeReport date and time of report
     * @param dateTimeValidation date and time of validation
     * @return a new Test
     */
    public Test createTestFromCSV(ClinicalAnalysisLaboratory clinicalAnalysisLaboratory, Client client, String testCode, String nhsCode, TestType testTypeChosen, List<Category> listOfChosenCategories, TestParamList testParamList, String dateTimeRegister, String dateTimeResults, String dateTimeReport, String dateTimeValidation) {
        Test test = new Test(clinicalAnalysisLaboratory, client, testCode, nhsCode, testTypeChosen, listOfChosenCategories, testParamList, dateTimeRegister, dateTimeResults, dateTimeReport, dateTimeValidation);
        return test;
    }

    public List<Test> getTests() {
        return tests;
    }
}
