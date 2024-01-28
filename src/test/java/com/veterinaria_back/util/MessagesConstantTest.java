package com.veterinaria_back.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MessagesConstantTest {

    @Test
    public void testMessgeConstant() {
        MessagesConstant messagesConstant = new MessagesConstant();

      assertEquals(messagesConstant.SUCCESS_MESSAGE, "Operación exitosa");
      assertEquals(messagesConstant.SUCCESS_CODE, 0);
    }

}