package app.mappers.dto;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class TestTypeDtoTest extends TestCase {
    TestTypeDto testTypeDto = new TestTypeDto("12345");

    @Test
    public void testGetCode() {
        Assert.assertEquals(testTypeDto.getCode(),"12345");
    }
}