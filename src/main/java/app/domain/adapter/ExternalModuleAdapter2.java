package app.domain.adapter;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;
import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

public class ExternalModuleAdapter2 implements ExternalModule {
    ExternalModule2API referenceValues2API = new ExternalModule2API();

    public ReferenceValue getReferenceValue(Parameter param) {
        String parameterId = param.getParameterCode();

        EMRefValue refValue = referenceValues2API.getReferenceFor(parameterId);
        Double minValue = refValue.getMinValue();
        Double maxValue = refValue.getMaxValue();
        String metric = refValue.getMetric();

        return new ReferenceValue(minValue, maxValue, metric);
    }
}
