package app.mappers;

import app.domain.model.Parameter;
import app.domain.model.TestParameter;
import app.mappers.dto.ParamDTO;

import java.util.ArrayList;
import java.util.List;

public class ParamMapper {

    public List<ParamDTO> toDTO(List<Parameter> params) {
        List<ParamDTO> paramDTOList = new ArrayList<>();

        for(Parameter param : params) {
            String code = param.getParameterCode();
            String description = param.getParameterDescription();
            String name = param.getParameterCode();

            paramDTOList.add(new ParamDTO(code, name, description));
        }

        return paramDTOList;
    }
}
