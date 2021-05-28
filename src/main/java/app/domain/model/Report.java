package app.domain.model;

import app.domain.model.Exceptions.InvalidTextReportException;

public class Report {

    private String textDiagnosis;
    private String textReport;


    public Report(String textDiagnosis, String textReport) {
        this.textDiagnosis = textDiagnosis;
        validateReport(textReport);
        this.textReport = textReport;

    }

    private boolean validateReport(String textReport) {
        String[] listaDePalavras = textReport.split(" ");
        if (listaDePalavras.length > 400) {
            throw new InvalidTextReportException();
        } else {
            return true;
        }
    }

}
