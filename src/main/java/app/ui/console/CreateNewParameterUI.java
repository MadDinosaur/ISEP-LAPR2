package app.ui.console;

import app.controller.CreateNewParameterController;
import app.domain.model.Category;

import java.util.ArrayList;

/**
 *
 * @author Diogo Gaspar <1200966@isep.ipp.pt>
 */
public class CreateNewParameterUI {

    private CreateNewParameterController controller;

    public void createNewParameter(String shortName, String code, String description){
        controller.createParameter(shortName, code, description);
    }

    public void getCategoryList(){
        System.out.print(controller.getCategoryList());
    }

    public void setParameterCategory(){

    }
}
