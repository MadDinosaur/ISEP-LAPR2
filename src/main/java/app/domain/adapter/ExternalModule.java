package app.domain.adapter;

import app.domain.model.ReferenceValue;
import app.domain.model.Parameter;

public interface ExternalModule {
    ReferenceValue getReferenceValue(Parameter param);
}
