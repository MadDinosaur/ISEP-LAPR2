package app.domain.model;

import app.domain.model.Exceptions.InvalidTextReportException;

import java.util.ArrayList;

public class Report {

    private String textReport;
    private String textDiagnosis;

    public Report(String textReport, String textDiagnosis) {
        validateReport(textReport);
        this.textReport = textReport;
        this.textDiagnosis = textDiagnosis;
    }

    public boolean validateReport(String textReport) {
        String[] listaDePalavras = textReport.split(" ");
        if (listaDePalavras.length > 400) {
            throw new InvalidTextReportException();
        } else {
            return true;
        }
    }

}
