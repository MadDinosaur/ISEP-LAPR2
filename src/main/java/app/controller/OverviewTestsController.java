package app.controller;

import app.domain.adapter.BiggestContiguousSumAlgorithm;
import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;

import java.util.*;

public class OverviewTestsController {

    private Company company;
    private ClientStore clientStore = company.getClientStore();
    private TestStore testStore = company.getTestStore();
    private BiggestContiguousSumAlgorithm algorithm;

    /**
     * Code inspired from the following website: https://www.baeldung.com/java-between-dates
     * @param begginingDate Beggining date
     * @param endingDate Ending date
     */
    public List<Date> getDatesBetweenDateInterval(Date begginingDate, Date endingDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(begginingDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endingDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    public int getTotalNumberOfClients() {
        return this.clientStore.getTotalNumberOfClients();
    }

    public int getTotalNumberOfValidatedTests() {
        return this.testStore.getTotalNumberOfValidatedTests();
    }

    public List<Integer> getNumberOfTestsWaitingForResultsInDateInterval(List<Date> dateInterval) {
        return this.testStore.getNumberOfTestsWaitingForResultsInDateInterval(dateInterval);
    }

    public List<Integer> getNumberOfTestsWaitingForReportInDateInterval(List<Date> dateInterval) {
        return this.testStore.getNumberOfTestsWaitingForReportInDateInterval(dateInterval);
    }

    public List<Integer> getNumberOfTestsValidatedInDateInterval(List<Date> dateInterval) {
        return this.testStore.getNumberOfTestsValidatedInDateInterval(dateInterval);
    }

    public int[] getDifferenceOfNewAndValidatedTests(List<Date> dateInterval) {
        return this.testStore.getDifferenceOfNewAndValidatedTestsForEachHalfAnHour(dateInterval);
    }

    public int[] getBiggestContiguousSubSequence(int[] sequence, String code) {
        this.algorithm = company.getBiggestContinuousSumAlgorithm(code);
        return algorithm.getBiggestContiguousSubSequence(sequence);
    }
}
