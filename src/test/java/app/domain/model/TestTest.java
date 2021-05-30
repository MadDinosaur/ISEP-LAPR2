package app.domain.model;

import junit.framework.TestCase;

public class TestTest{

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Test test = new Test(null, null,null,null);
    }

}