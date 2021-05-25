package app.domain.model;

import app.domain.adapter.ExternalModule;
import app.domain.model.Exceptions.UnregisteredParameterException;

import java.util.ArrayList;
import java.util.List;

public class TestParamStore {
    List<TestParameter> testParameters = new ArrayList<>();

    TestType testType;

    public TestParamStore(TestType testType) {
        this.testType = testType;
    }

    private TestParameter getTestParameter(String paramCode) {
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

    public boolean createTestParameterResult(String paramCode, String result, String metric) {
        TestParameter testParam = getTestParameter(paramCode);
        ExternalModule module = testType.getExternalModule();
        ReferenceValue refValue = module.getReferenceValue(testParam.getParameter());
        return testParam.createTestParameterResult(result, metric, refValue);
    }
}
