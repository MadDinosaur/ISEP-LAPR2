package app.controller;

import app.domain.model.Company;
import app.domain.store.ClientStore;
import app.domain.store.TestStore;

import java.util.*;

public class OverviewTestsController {

    private Company company;
    private ClientStore clientStore = company.getClientStore();
    private TestStore testStore = company.getTestStore();
    private List<Date> dateInterval;

    /**
     * Code inspired from the following website: https://www.baeldung.com/java-between-dates
     * @param begginingDate Beggining date
     * @param endingDate Ending date
     */
    public void getDatesBetweenDateInterval(Date begginingDate, Date endingDate) {
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
        this.dateInterval = datesInRange;
    }

    public int getNumberOfClients() {
        return this.clientStore.getNumberOfClients();
    }

    public int getNumberOfTestsWaitingForResults() {
        return this.testStore.getNumberOfTestsWaitingForResults();
    }

    public int getNumberOfTestsWaitingForReport() {
        return this.testStore.getNumberOfTestsWaitingForReport();
    }


}
