package com.hr.airline.config;

import com.hr.airline.services.VerifyId;

public class GenerateRandomValue {

    VerifyId verifyId = new VerifyId();
    public String generateRandomValue(int length) {
         int value;
         String result;
         do{
             value = (int) (Math.random() * 10000);
             result = String.format("%0" + length + "d", value);
         }while(!verifyId.verify(result));
         return result;
    }
}
