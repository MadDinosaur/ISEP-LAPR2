package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class MedLabTechUI implements Runnable{
    public MedLabTechUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Collected Samples",new SampleUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nMedical Lab Technician Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
