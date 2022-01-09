package app.mappers.dto;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ParamDTOTest extends TestCase {
    ParamDTO paramDTO = new ParamDTO("12345","nameTest","descriptionTest");

    @Test
    public void testGetCode() {
        Assert.assertEquals(paramDTO.getCode(),"12345");
    }

    @Test
    public void testTestGetName() {
        Assert.assertEquals(paramDTO.getName(),"nameTest");
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals(paramDTO.getDescription(),"descriptionTest");
    }
}