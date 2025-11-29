package com.ditutala.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Math")
public class MathController {

    //http:localhost:8080/math/sum/5/4
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable ("numberOne") String numberOne,
            @PathVariable ("numberTwo") String numberTwo
    ) throws Exception {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Please enter a number");
        return convertDouble(numberOne ) + convertDouble( numberTwo);
    }
    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty() ) return false;
        String number = strNumber.replace(",", ".");
        return  (number.matches("[+-]?[0-9]*\\.?[0-9]*"));

    }

    private Double convertDouble(String strNumber)  throws Exception{
        if (strNumber == null || strNumber.isEmpty() ) throw new UnsupportedOperationException("Please enter a number");
        String number = strNumber.replace(",", ".");
        return Double.parseDouble(number);
    }

    //http:localhost:8080/math/Subtraction/5/4
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable ("numberOne") String numberOne,
            @PathVariable ("numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Please enter a number");
        return convertDouble(numberOne) - convertDouble(numberTwo);
    }

    //http:localhost:8080/math/Subtraction/5/4
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable ("numberOne") String numberOne,
            @PathVariable ("numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Please enter a number");
        return convertDouble(numberOne) * convertDouble(numberTwo);
    }

    //http:localhost:8080/math/division/5/4
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable ("numberOne") String numberOne,
            @PathVariable ("numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Please enter a number");
        return convertDouble(numberOne) / convertDouble(numberTwo);
    }

    //http:localhost:8080/math/mean/5/4
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable ("numberOne") String numberOne,
            @PathVariable ("numberTwo") String numberTwo
    ) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Please enter a number");
        return sum(numberOne,numberTwo)/2;
    }

    //http:localhost:8080/math/sqrt/5
    @RequestMapping("/sqrt/{numberOne}")
    public Double sqrtNumbers(
            @PathVariable ("numberOne") String numberOne
    ) throws Exception {
        if (!isNumeric(numberOne)) throw new UnsupportedOperationException("Please enter a number");
        return Math.sqrt(convertDouble(numberOne));
    }
}
