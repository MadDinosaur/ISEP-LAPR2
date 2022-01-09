package app.domain.adapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.nhs.report.Report2NHS.writeUsingFileWriter;

/**
 *
 * @author Luis Moreira <1200973@isep.ipp.pt>
 */

public class ExternalModuleNhsReportAdapter implements ExternalModuleNhsReport{

    /**
     * this method will use access the external library Report2NHS, where the report will be created and sent
     * also after that, it will register the event and generate a .txt log file into the folder
     * @param string this string includes all the information which will be written into the report
     */
    @Override
    public void sendReport(String string) {
        writeUsingFileWriter(string);

        File log = new File("./NHSReport/log.txt");
        try {
            if (!log.exists()) {
                log.createNewFile();
            }

            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            String dateTime = getDateAndTime();
            out.append("******* Daily Report successfully sent at ").append(dateTime).append(" ******* ").append("\n");
            out.close();

        } catch (IOException e) {
            System.out.println("Error saving the log event.");
        }

        System.out.println("NHS Daily Report sent");
        
    }
    /**
     * method used to get current Date and Time
     * @return String with current date and time
     */
    public String getDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
