import app.controller.CreateNewParameterController;
import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Exceptions.*;
import org.junit.Assert;
import org.junit.Test;
import app.domain.model.*;

import java.util.List;

public class ParameterTests {

    @Test(expected = InvalidNameException.class)
    public void testInvalidName(){
        String name = "HDL Cholesterol";
        String code = "12dsa";
        String description = "Something something";

        Parameter par = new Parameter(name, code, description);
    }

    @Test(expected = InvalidCodeException.class)
    public void testInvalidCode(){
        String name = "RBC";
        String code = "fehue12321";
        String description = "Red blood cells";

        Parameter par = new Parameter(name, code, description);
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testInvalidDescription(){
        String name = "RBC";
        String code = "12345";
        String description = "Red blood cells are essential to the human body's survival";

        Parameter par = new Parameter(name, code, description);
    }

    @Test(expected = InvalidNameException.class)
    public void testNullName(){
        String name = "";
        String code = "12345";
        String description = "Red blood cells";

        Parameter par = new Parameter(name, code, description);
    }

    @Test
    public void testCreateNewParameterController(){
        CreateNewParameterController controller = new CreateNewParameterController();
        Category pc = new Category("Hemogram", "13425", "Blood contents");
        controller.setCategory(pc);
        controller.createNewParameter("PLT", "12345", "Platelets");
        controller.saveParameter();
        Parameter newPar = new Parameter("PLT", "12345", "Platelets");
        List<Parameter> parameterList = null;
        parameterList.add(newPar);

        Assert.assertSame(pc.getParameterList(), parameterList);
    }
}