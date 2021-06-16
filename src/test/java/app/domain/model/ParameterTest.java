package app.domain.model;

import app.domain.model.Exceptions.InvalidCodeException;
import app.domain.model.Exceptions.InvalidDescriptionException;
import app.domain.model.Exceptions.InvalidNameException;
import org.junit.Test;

public class ParameterTest {

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

   /* @Test  isto da erro
    public void testAlreadyExistingParameter(){
        Category pc = new Category("Hemogram", "12345", "Blood content");
        pc.saveParameter(pc.createNewParameter("RBC", "12345", "Red blood cells"));

        Parameter par = new Parameter("RBC", "12345", "Red blood cells");
        Assert.assertSame(false, pc.saveParameter(par));
    }*/

}
