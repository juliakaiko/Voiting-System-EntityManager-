package com.step.springmvcapp.entity.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPassportNumberValidator 
                implements ConstraintValidator<ValidPassportNumber, String> {
    @Override
    public void initialize(ValidPassportNumber constraint) {}

    @Override
    public boolean isValid(String passportNumber, ConstraintValidatorContext context) {
        if (passportNumber == null)
            return false;

        return doPassportNumberFormatCheck(passportNumber);
    }

    private boolean doPassportNumberFormatCheck(String passportNumber) {
        if (passportNumber.isBlank() || passportNumber.isEmpty()){
            return false;
        }
       
        boolean isDigit=false;
        String cleanStr = passportNumber.trim();

        char symb [] = {'0','1','2','3','4','5','6','7','8','9'};
        int sympolPos = 0;       
        for (int j = 0; j<cleanStr.length(); j++){
            if (sympolPos == -1){
                break;
            }
            char ch =  cleanStr.charAt(j);

            for (int i=0; i<symb.length; i++){
                if (ch==symb[i]){
                    sympolPos=i;
                    break;
                }
                else
                   sympolPos=-1; 
            }  
        }
        if (sympolPos == -1) isDigit=false;
        else isDigit=true;
                
        return isDigit;        
    }
}
