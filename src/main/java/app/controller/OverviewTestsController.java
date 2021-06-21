package app.controller;

import app.domain.adapter.BiggestContiguousSubSequenceAlgorithm;
import app.domain.model.Company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OverviewTestsController {

    /**
     * Company
     */
    private Company company;

    /**
     * The algorithm to be used
     */
    private BiggestContiguousSubSequenceAlgorithm algorithm;

    /**
     * Constructor in order to get the company
     */
    public OverviewTestsController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Gets all the dates between two dates, the beginning date and the ending date
     * @param beginningDate beginning date
     * @param endingDate ending date
     * @return list of all the dates between those two dates
     */
    public static List<Date> getDatesBetweenDateInterval(LocalDate beginningDate, LocalDate endingDate) {
        List<Date> dateLst = new ArrayList<>();
        List<LocalDate> localDateLst = beginningDate.datesUntil(endingDate).collect(Collectors.toList());
        for (LocalDate localDate : localDateLst) {
            Date date = new Date(localDate.getYear() - 1900, localDate.getMonth().getValue() - 1, localDate.getDayOfMonth());
            dateLst.add(date);
        }
        return dateLst;
    }


    /**
     * Getter for the total number of clients in the system
     * @return total number of clients
     */
    public int getTotalNumberOfClients() {
        return this.company.getClientStore().getTotalNumberOfClients();
    }


    /**
     * Getter for the total number of validated tests in the system
     * @return total number of validated tests
     */
    public int getTotalNumberOfValidatedTests() {
        return this.company.getTestStore().getTotalNumberOfValidatedTests();
    }

    /**
     * Getter for the tests waiting for results in a specified date
     * @param date date
     * @return tests waiting for results in a specified date
     */
    public int getNumberOfTestsWaitingForResultsInDate(Date date) {
        return this.company.getTestStore().getNumberOfTestsWaitingForResultsInDate(date);
    }

    /**
     * Getter for the tests waiting for report in a specified date
     * @param date date
     * @return tests waiting for report in a specified date
     */
    public int getNumberOfTestsWaitingForReportInDate(Date date) {
        return this.company.getTestStore().getNumberOfTestsWaitingForReportInDate(date);
    }

    /**
     * Getter for the number of validated tests in a specified date
     * @param date date
     * @return validated tests in a specified date
     */
    public int getNumberOfTestsValidatedInDate(Date date) {
        return this.company.getTestStore().getNumberOfTestsValidatedInDate(date);
    }

    /**
     * Getter for the difference between new and validated tests
     * @param dateInterval date interval
     * @return an array of numbers
     */
    public int[] getDifferenceOfNewAndValidatedTests(List<Date> dateInterval) {
        return this.company.getTestStore().getDifferenceOfNewAndValidatedTestsForEachHalfAnHour(dateInterval);
    }

    /**
     * Getter for the biggest contiguous sub sequence of an initial sequence
     * @param sequence sequence
     * @param code code
     * @return an array of numbers
     */
    public int[] getBiggestContiguousSubSequence(int[] sequence, String code) {
        this.algorithm = company.getBiggestContinuousSumAlgorithm(code);
        return algorithm.getBiggestContiguousSubSequence(sequence);
    }
}
