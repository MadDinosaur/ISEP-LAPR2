package app.domain.adapter;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public interface ExternalModuleNhsReport {
    /**
     * this method will generate NHSReport.txt and send it
     * @param string this string includes all the information which will be written into the report
     */
    void sendReport(String string);
    String getDateAndTime();

}
