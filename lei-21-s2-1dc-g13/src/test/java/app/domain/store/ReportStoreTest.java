package app.domain.store;

import app.mappers.dto.ReportDTO;
import org.junit.Test;

public class ReportStoreTest {

    private ReportStore reportStore = new ReportStore();

    @Test
    public void createReport() {
        ReportDTO reportDTO = new ReportDTO("ya", "ya");
        reportStore.createReport(reportDTO);
    }
}
