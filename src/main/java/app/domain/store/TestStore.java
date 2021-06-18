package app.domain.store;

import app.domain.model.*;
import app.domain.model.Exceptions.InvalidTestCodeException;
import app.domain.model.Exceptions.InvalidTestException;
import app.domain.model.Exceptions.TestDoesntExistException;
import app.domain.model.Exceptions.UnregisteredBarcodeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStore {
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
                if(test.isValidated()) {
                    List<TestParameterResult> testParameterResultList = test.getTestParamResults();
                    for (TestParameterResult parameterResult : testParameterResultList) {
                        if (parameterResult.getValue() != null) {
                            if (parameterResult.getValue() > 1.4) {
                                positiveCovid.add(test);
                            }
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
            String date = test.getDateTimeRegister();
            String[] dateComponents = date.split(" ");
            date = dateComponents[0];
            if (dayOfTest.equalsIgnoreCase(date)) {
                numberOfCases++;
            }
        }
        return numberOfCases;
    }

    public double getNumberOfTestsPerformed(String dayOfTest) {
        double numberOfTestsMade = 0;
        for (Test test : tests) {
            String date = test.getDateTimeRegister();
            String[] dateComponents = date.split(" ");
            date = dateComponents[0];
            if (dayOfTest.equalsIgnoreCase(date)) {
                numberOfTestsMade++;
            }
        }
        return numberOfTestsMade;
    }

    public double getAverageAgeOfClient(String dayOfTest) {
        double sumAge = 0;
        int numberOfClient = 0;
        for (Test test : tests) {
            String date = test.getDateTimeRegister();
            String[] dateComponents = date.split(" ");
            date = dateComponents[0];
            if (dayOfTest.equalsIgnoreCase(date)) {
                sumAge = test.getClient().getAgeInYears();
                numberOfClient++;
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
     *
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


    public int getTotalNumberOfValidatedTests() {
        return getTestsValidated().size();
    }


    public List<Integer> getNumberOfTestsWaitingForResultsInDateInterval(List<Date> dateInterval) {
        List<Integer> listOfNumberOfTestsInEachDay = new ArrayList<>();
        for (Date date : dateInterval) {
            int numberOfTests = 0;
            for (Test test : getRegisteredTests()) {
                String[] dateAndTimeOfRegister = test.getDateTimeRegister().split(" ");
                String[] dayMonthYear = dateAndTimeOfRegister[0].split("/");
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);
                Date dateOfRegister = new Date(year, month, day);
                if (!dateOfRegister.after(date)) {
                    numberOfTests++;
                }
            }
            listOfNumberOfTestsInEachDay.add(numberOfTests);
        }
        return listOfNumberOfTestsInEachDay;
    }

    public List<Integer> getNumberOfTestsWaitingForReportInDateInterval(List<Date> dateInterval) {
        List<Integer> listOfNumberOfTestsInEachDay = new ArrayList<>();
        for (Date date : dateInterval) {
            int numberOfTests = 0;
            for (Test test : getTestsListReadyForReport()) {
                String[] dateAndTimeOfResults = test.getDateTimeResults().split(" ");
                String[] dayMonthYear = dateAndTimeOfResults[0].split("/");
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);
                Date dateOfResults = new Date(year, month, day);
                if (!dateOfResults.after(date)) {
                    numberOfTests++;
                }
            }
            listOfNumberOfTestsInEachDay.add(numberOfTests);
        }
        return listOfNumberOfTestsInEachDay;
    }

    public List<Integer> getNumberOfTestsValidatedInDateInterval(List<Date> dateInterval) {
        List<Integer> listOfNumberOfTestsInEachDay = new ArrayList<>();
        for (Date date : dateInterval) {
            int numberOfTests = 0;
            for (Test test : getTestsValidated()) {
                String[] dateAndTimeOfValidation = test.getDateTimeValidation().split(" ");
                String[] dayMonthYear = dateAndTimeOfValidation[0].split("/");
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);
                Date dateOfValidation = new Date(year, month, day);
                if (!dateOfValidation.after(date)) {
                    numberOfTests++;
                }
            }
            listOfNumberOfTestsInEachDay.add(numberOfTests);
        }
        return listOfNumberOfTestsInEachDay;
    }


    public int[] sortTestsForEachHalfAnHour(int hours, int minutes, int size, int i) {
        int[] numberOfTestsForEachHalfAnHour = new int[size];
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

    public int[] getNumberOfNewTestsForEachHalfAnHour(List<Date> dateInterval) {
        int size = 24 * dateInterval.size();
        int[] numberOfTestsForEachHalfAnHour = new int[size];
        int i = 1;
        for (Date date : dateInterval) {
            for (Test test : getRegisteredTests()) {
                String[] dateAndTimeOfRegister = test.getDateTimeRegister().split(" ");
                String[] hoursMinutes = dateAndTimeOfRegister[1].split(":");
                int hours = Integer.parseInt(hoursMinutes[0]);
                int minutes = Integer.parseInt(hoursMinutes[1]);
                String[] dayMonthYear = dateAndTimeOfRegister[0].split("/");
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);
                Date dateOfRegister = new Date(year, month, day);
                if (dateOfRegister.equals(date)) {
                    numberOfTestsForEachHalfAnHour = sortTestsForEachHalfAnHour(hours, minutes, size, i);
                }
            }
            i = i + 24;
        }
        return numberOfTestsForEachHalfAnHour;
    }

    public int[] getNumberOfValidatedTestsForEachHalfAnHour(List<Date> dateInterval) {
        int size = 24 * dateInterval.size();
        int[] numberOfTestsForEachHalfAnHour = new int[24 * dateInterval.size()];
        int i = 1;
        for (Date date : dateInterval) {
            for (Test test : getTestsValidated()) {
                String[] dateAndTimeOfValidation = test.getDateTimeValidation().split(" ");
                String[] hoursMinutes = dateAndTimeOfValidation[1].split(":");
                int hours = Integer.parseInt(hoursMinutes[0]);
                int minutes = Integer.parseInt(hoursMinutes[1]);
                String[] dayMonthYear = dateAndTimeOfValidation[0].split("/");
                int day = Integer.parseInt(dayMonthYear[0]);
                int month = Integer.parseInt(dayMonthYear[1]);
                int year = Integer.parseInt(dayMonthYear[2]);
                Date dateOfValidation = new Date(year, month, day);
                if (dateOfValidation.equals(date)) {
                    numberOfTestsForEachHalfAnHour = sortTestsForEachHalfAnHour(hours, minutes, size, i);
                }
            }
            i = i + 24;
        }
        return numberOfTestsForEachHalfAnHour;
    }

    public int[] getDifferenceOfNewAndValidatedTestsForEachHalfAnHour(List<Date> dateInterval) {
        int[] numberOfNewTests = getNumberOfNewTestsForEachHalfAnHour(dateInterval);
        int[] numberOfValidatedTests = getNumberOfValidatedTestsForEachHalfAnHour(dateInterval);
        int[] difference = new int[numberOfNewTests.length];
        for (int i = 0; i < difference.length; i++) {
            difference[i] = numberOfNewTests[i] - numberOfValidatedTests[i];
        }
        return difference;
    }

}
