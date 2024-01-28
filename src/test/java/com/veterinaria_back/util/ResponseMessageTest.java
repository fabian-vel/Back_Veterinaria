package com.veterinaria_back.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMessageTest {

   @Test
    public void tetsResponseMessage() {

       ResponseMessage responseMessage = new ResponseMessage(200,"Ok","Data");

       assertEquals(200,responseMessage.getCode());
       assertEquals("Ok", responseMessage.getMessage());
       assertEquals("Data", responseMessage.getData());

       responseMessage.setCode(400);
       responseMessage.setMessage("Error");
       responseMessage.setData(null);

       assertEquals(400,responseMessage.getCode());
       assertEquals("Error", responseMessage.getMessage());
       assertNull(responseMessage.getData());

       ResponseMessage responseMessage1 = new ResponseMessage();

       assertNull(responseMessage1.getMessage());
       assertNull(responseMessage1.getMessage());
       assertNull(responseMessage1.getData());
   }
}