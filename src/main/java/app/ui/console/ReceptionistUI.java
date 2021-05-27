package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class ReceptionistUI implements Runnable{
    public ReceptionistUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a new client",new RegisterClientUI()));
        options.add(new MenuItem("Register a new test to a Client",new RegisterTestUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
