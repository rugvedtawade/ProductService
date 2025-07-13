package com.scaler.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void Test_WhenTwoIntegersAreAdded_RunSuccessfully() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act
        int result = calculator.add(1,3);

        //Assert
        assertEquals(4,result);
    }

    @Test
    public void Test_DivideByZero_ResultsInArithmeticException() {
        //Arrange
        Calculator calculator = new Calculator();

        //Act and Assert
        assertThrows(ArithmeticException.class,()->calculator.divide(5,0));
    }

}
