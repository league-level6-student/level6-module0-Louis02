package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double a = 10;
    	int b = 15;
    	int expected = 150;
        //when
    	double actual = payroll.calculatePaycheck(a, b);
        //then
    	assertEquals(expected,actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int a = 10;
    	
    	double expected = 5.75;
    	
        //when
    	double actual = payroll.calculateMileageReimbursement(a);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String a = "bob";
    	double b = 10;
    	String expected = "Hello bob, We are pleased to offer you an hourly wage of 10.0";
        //when
    	String actual = payroll.createOfferLetter(a, b);
        //then
    	assertEquals(expected,actual);
    }

}