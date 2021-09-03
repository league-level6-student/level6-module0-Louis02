package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {
    @Mock
	CellPhone cell;
    @Mock
    Car car;
    
    DeliveryDriver deliveryDriver;
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver("John", car, cell);
    	 
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expected= true;
    	when(cell.browseCatMemes()).thenReturn(true);
        //when
    	boolean actual = deliveryDriver.wasteTime();
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expected= true;
    	when(car.fillTank(1)).thenReturn(true);
        //when
    	boolean actual = deliveryDriver.refuel(1);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expected= true;
    	when(cell.call("9")).thenReturn(true);
        //when
    	boolean actual = deliveryDriver.contactCustomer("9");
        //then
    	assertEquals(expected, actual);
    }

}