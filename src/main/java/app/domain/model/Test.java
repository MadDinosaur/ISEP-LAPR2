package app.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    private String code;

    private String nhsCode;

    private Date createdAt;

    private TestParamStore testParamStore;

    private Report report;

    public void addReport(Report report) {
        this.report = report;
    }
}
