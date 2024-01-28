package com.veterinaria_back.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomExceptionTest {

    @Test
    public void testMessage() {

        CustomException customException = new CustomException("Ok");

        assertEquals(customException.getMessage(), "Ok");
        assertNotEquals(customException.getMessage(), "error");
    }

    @Test
    public void testMessageCause() {
        Throwable cause = new Exception("Ok");
        CustomException customException = new CustomException("Error message", cause);

        assertEquals("Error message", customException.getMessage());
        assertEquals(cause, customException.getCause());
    }

    @Test
    public void testCause() {
        Throwable cause = new Exception("Ok");
        CustomException CustomException = new CustomException(cause);
        CustomException differentCustomException = new CustomException();

        assertEquals(CustomException.getCause(), cause);
        assertNotEquals(CustomException,differentCustomException);

    }

    @Test
    public void testMesageCauseEnableSuppressionWritableStackTrace() {
        Throwable cause = new Exception("Ok");
        CustomException customException = new CustomException("Error message", cause, true, false);
        CustomException differentCustomException = new CustomException();

        assertEquals(customException.getCause(), cause);
        assertNotEquals(customException,differentCustomException);
        assertEquals(customException.getMessage(), "Error message");
        assertNotEquals(customException.getMessage(), "error");

    }
}