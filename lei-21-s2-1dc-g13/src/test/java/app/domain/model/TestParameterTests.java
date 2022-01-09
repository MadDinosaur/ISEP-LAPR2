package app.domain.model;

import org.junit.Test;

public class TestParameterTests {

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter(){
        Parameter param = null;
        TestParameter testParam = new TestParameter(param);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreationTestParameterResultNullRefValue(){
        Parameter param = new Parameter("WBC", "12345", "Description");
        String value = "11.23";
        String metric = "mg";
        ReferenceValue refValue = null;
        TestParameter testParam = new TestParameter(param);
        testParam.createTestParameterResult(value, metric, refValue);
    }
}
