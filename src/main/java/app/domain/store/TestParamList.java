package app.domain.store;

import app.domain.adapter.ExternalModule;
import app.domain.model.Exceptions.UnregisteredParameterException;
import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestParamList {
    List<TestParameter> testParameters = new ArrayList<>();

    TestType testType;

    public TestParamList(TestType testType) {
        this.testType = testType;
    }

    public TestParameter getTestParameter(String paramCode) {
        for(TestParameter testParam: testParameters)
            if(testParam.getParameter().getParameterCode().equalsIgnoreCase(paramCode))
                return testParam;

        throw new UnregisteredParameterException();
    }

    public List<Parameter> getParameters() {
        List<Parameter> params = new ArrayList<>();

        for(TestParameter testParam: testParameters)
            params.add(testParam.getParameter());

        return params;
    }

    public TestParameter createTestParam(Parameter param) {
        TestParameter testParam = new TestParameter(param);
        if (testParameters.add(testParam))
            return testParam;
        return null;
    }

    /**
     * Returns the reference value for each parameter of the test
     * @param testParam TestParameter
     * @return ReferenceValue
     */
    public ReferenceValue getReferenceValue(TestParameter testParam) {
        return testParam.getReferenceValue();
    }

    public TestParameterResult createTestParameterResult(String paramCode, String result, String metric) {
        TestParameter testParam = getTestParameter(paramCode);
        ExternalModule module = testType.getExternalModule();
        ReferenceValue refValue = module.getReferenceValue(testParam.getParameter());
        return testParam.createTestParameterResult(result, metric, refValue);
    }

    public boolean addTestParameterResult(Parameter param, TestParameterResult result) {
        TestParameter testParam = getTestParameter(param.getParameterCode());
        return testParam.addTestParameterResult(result);
    }

    /**
     * Returns a list with the test parameters results
     * @return List<TestParameterResult>
     */
    public List<TestParameterResult> getTestParametersResults() {
        List<TestParameterResult> listTestParameterResults = new ArrayList<>();
        for (TestParameter tp : testParameters) {
            listTestParameterResults.add(tp.getResult());
        }
        return listTestParameterResults;
    }

    public List<TestParameter> getTestParameters() {
        return testParameters;
    }

    public int size() {
        return testParameters.size();
    }

}
