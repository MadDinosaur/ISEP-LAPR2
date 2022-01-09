package app.domain.adapter;

import app.domain.model.Parameter;
import app.domain.model.ReferenceValue;

public interface ExternalModule {
    ReferenceValue getReferenceValue(Parameter param);
}
