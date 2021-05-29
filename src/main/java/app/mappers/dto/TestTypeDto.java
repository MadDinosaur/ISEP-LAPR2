package app.mappers.dto;

import app.domain.model.Category;
import app.domain.model.CollectionMethod;

import java.util.ArrayList;
import java.util.List;


public class TestTypeDto {
    private String code;


    public TestTypeDto(String code) {
        this.code = code;

    }
    public String getCode() {
        return code;
    }

}
