package app.domain.adapter;

import app.domain.model.ReferenceValue;
import app.domain.model.Parameter;
import com.example3.CovidReferenceValues1API;

public class ExternalModuleAdapter1 implements ExternalModule {
    CovidReferenceValues1API referenceValues1API = new CovidReferenceValues1API();
    int accessKey = 12345;
    String parameterId = "IgGAN"; //IgC antibodies

    public ReferenceValue getReferenceValue(Parameter param) {
        Double min = referenceValues1API.getMinReferenceValue(parameterId, accessKey);
        Double max = referenceValues1API.getMaxReferenceValue(parameterId, accessKey);
        String metric = referenceValues1API.usedMetric(parameterId, accessKey);

        return new ReferenceValue(min, max, metric);
    }
}
