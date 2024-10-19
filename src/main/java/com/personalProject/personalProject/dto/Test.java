package com.personalProject.personalProject.dto;

public class Test {
    private TestEnum testEnum;
    private String message;

    public Test() {
    }

    public Test(TestEnum testEnum, String message) {
        this.testEnum = testEnum;
        this.message = message;
    }

    public TestEnum getTestEnum() {
        return testEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setTestEnum(TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Test copyOf(Test tests) {
        Test newTest = new Test();
        newTest.setMessage(tests.getMessage());
        newTest.setTestEnum(tests.testEnum);
        return newTest;

    }
}
