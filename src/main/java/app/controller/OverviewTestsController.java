package app.controller;

import app.domain.adapter.BiggestContiguousSubSequenceAlgorithm;
import app.domain.model.Company;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OverviewTestsController {

    private Company company;
    private BiggestContiguousSubSequenceAlgorithm algorithm;

    public OverviewTestsController() {
        this.company = App.getInstance().getCompany();
    }

    public static List<Date> getDatesBetweenDateInterval(LocalDate beginningDate, LocalDate endingDate) {
        List<Date> dateLst = new ArrayList<>();
        List<LocalDate> localDateLst = beginningDate.datesUntil(endingDate).collect(Collectors.toList());
        for (LocalDate localDate : localDateLst) {
            Date date = new Date(localDate.getYear() - 1900, localDate.getMonth().getValue() - 1, localDate.getDayOfMonth());
            dateLst.add(date);
        }
        return dateLst;
    }


    public int getTotalNumberOfClients() {
        return this.company.getClientStore().getTotalNumberOfClients();
    }

    public int getTotalNumberOfValidatedTests() {
        return this.company.getTestStore().getTotalNumberOfValidatedTests();
    }

    public int getNumberOfTestsWaitingForResultsInDate(Date date) {
        return this.company.getTestStore().getNumberOfTestsWaitingForResultsInDate(date);
    }

    public int getNumberOfTestsWaitingForReportInDate(Date date) {
        return this.company.getTestStore().getNumberOfTestsWaitingForReportInDate(date);
    }

    public int getNumberOfTestsValidatedInDate(Date date) {
        return this.company.getTestStore().getNumberOfTestsValidatedInDate(date);
    }

    public int[] getDifferenceOfNewAndValidatedTests(List<Date> dateInterval) {
        return this.company.getTestStore().getDifferenceOfNewAndValidatedTestsForEachHalfAnHour(dateInterval);
    }

    public int[] getBiggestContiguousSubSequence(int[] sequence, String code) {
        this.algorithm = company.getBiggestContinuousSumAlgorithm(code);
        return algorithm.getBiggestContiguousSubSequence(sequence);
    }
}
