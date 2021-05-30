package app.ui.console;

import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MainMenuUI {

    public MainMenuUI()
    {
    }

    public void run() throws IOException
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Know the Development Team",new DevTeamUI()));
        options.add(new MenuItem("Test RegisterTest",new RegisterTestUI()));
<<<<<<< HEAD
        options.add(new MenuItem("Test create report (deletar depois)", new CreateReportUI()));
=======
        options.add(new MenuItem("Test sample", new SampleUI()));
>>>>>>> a7a4604c1d8eba006f07739495ecf0cd0a3801bc
        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu\n\nLogin as ADMIN\nUsername: admin@lei.sem2.pt  Password: 123456\n\n");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }


}

