package app.domain.adapter;

import app.domain.model.ReferenceValue;
import app.domain.model.Parameter;
import com.example1.ExternalModule3API;

public class ExternalModuleAdapter3 implements ExternalModule {
    ExternalModule3API referenceValues3API = new ExternalModule3API();
    int accessKey = 12345;

    public ReferenceValue getReferenceValue(Parameter param) {
        String parameterId = param.getParameterCode();

        String metric = referenceValues3API.usedMetric(parameterId, accessKey);
        Double minValue = referenceValues3API.getMinReferenceValue(parameterId, accessKey);
        Double maxValue = referenceValues3API.getMaxReferenceValue(parameterId, accessKey);

        return new ReferenceValue(minValue, maxValue, metric);
    }
}
