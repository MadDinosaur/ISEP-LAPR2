package app.mappers;

import app.domain.model.Parameter;
import app.domain.model.TestParameter;
import app.domain.model.TestType;
import app.mappers.dto.ParamDTO;
import app.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

public class ParamMapper {

    public ParamDTO toDto(Parameter parameter){

        return new ParamDTO(parameter.getParameterCode(),parameter.getParameterName(),parameter.getParameterDescription());
    }

    public List<ParamDTO> toDTO(List<Parameter> params) {
        List<ParamDTO> paramDTOList = new ArrayList<>();

        for(Parameter param : params) {
            System.out.println("chega aqui2");
            String code = param.getParameterCode();
            String description = param.getParameterDescription();
            String name = param.getParameterName();

            paramDTOList.add(new ParamDTO(code, name, description));
        }

        return paramDTOList;
    }
}
