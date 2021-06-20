package app.ui.console;

import app.controller.App;
import app.domain.model.Company;
import app.ui.gui.ImportCSVFileUI;
import app.ui.gui.MakeRegressionUI;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateNhsReportUI implements Runnable{

    Scanner sc = new Scanner(System.in);
    private String typeOfPoints;
    private int numberOfPoints;
    private String initialDate;
    private String finalDate;
    private String regressionModel;
    private String independentVariable;
    private Company company = App.getInstance().getCompany();



    @Override
    public void run(){

        /*System.out.println("\n");
        System.out.println("######## Nhs Report Creation ########");
        System.out.println("Please choose an option for the historical points");
        String awnser =sc.nextLine();
        //option 1  = days
        //option 2 = weeks
        if (awnser.equals("1")){
            typeOfPoints = "days";
        }else{
            typeOfPoints = "weeks";
        }
        System.out.println("Please write the numeber historical points");
        numberOfPoints =Integer.parseInt(sc.nextLine());
        System.out.println("Please select the initial date");
        initialDate = sc.nextLine();
        System.out.println("Please select the final date");
        finalDate = sc.nextLine();
        System.out.println("Please choose an option for regression model");
        awnser =sc.nextLine();
        //option 1  = simple
        //option 2 = multi
        if (awnser.equals("1")){
            regressionModel = "simple";
        }else{
            regressionModel = "multi";
        }
        if(regressionModel.equals("simple")){
            System.out.println("Please choose an option for the independent variable");
            awnser =sc.nextLine();
            //option 1  = day tests
            //option 2 = mean age
            if (awnser.equals("1")){
                regressionModel = "day tests";
            }else{
                regressionModel = "mean age";
            }
        }*/

    }
}
