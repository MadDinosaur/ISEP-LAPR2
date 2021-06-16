package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{

    /**
     * //Empty constructor
     */
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new client",new RegisterClientUI()));
        options.add(new MenuItem("Register a new employee", new CreateEmployeeUI()));
        options.add(new MenuItem("Register a new clinical analysis laboratory", new RegisterNewLabUI()));
        options.add(new MenuItem("Create a new test type", new CreateTestTypeUI()));
        options.add(new MenuItem("Create new parameter", new CreateNewParameterUI()));
        options.add(new MenuItem("Register a new test", new RegisterTestUI()));
        options.add(new MenuItem("Register samples", new SampleUI()));
        options.add(new MenuItem("Register a test result", new RecordTestResultUI()));
        options.add(new MenuItem("Register a new report", new CreateReportUI()));
        options.add(new MenuItem("Validate test", new ValidateTestUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
