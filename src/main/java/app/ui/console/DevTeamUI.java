package app.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI() {
        //Empty constructor
    }
    public void run()
    {
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t Tomás Cancela - 1200985@isep.ipp.pt \n");
        System.out.print("\t Luís Moreira - 1200973@isep.ipp.pt \n");
        System.out.print("\t Diogo Gaspar - 120066@isep.ipp.pt \n");
        System.out.print("\t Bárbara Pinto - 1191507@isep.ipp.pt \n");
        System.out.print("\t João Pacheco - 1200996@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
