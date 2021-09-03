package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

	MyDonutShop myDonutShop;
	@Mock
	PaymentService  paymentService;
	@Mock
	BakeryService bakeryService;
	@Mock
	DeliveryService deliveryService;
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
		
	}

	@Test
	void itShouldTakeDeliveryOrder() throws Exception {
		// given
		Order order = new Order("John", "9", 2, 2, "1234567890", true);

		// when
		when(paymentService.charge(order)).thenReturn(true);
		when(bakeryService.getDonutsRemaining()).thenReturn(2);
		myDonutShop.openForTheDay();
		myDonutShop.takeOrder(order);
		// then
		verify(deliveryService,times(1)).scheduleDelivery(order);
	}

	@Test
	void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
		// given
		Order order = new Order("John", "9", 5, 2, "1234567890", true);
		when(paymentService.charge(order)).thenReturn(true);
		when(bakeryService.getDonutsRemaining()).thenReturn(0);
		
		// when
		myDonutShop.openForTheDay();
		// then
		Throwable exception  = assertThrows(IllegalArgumentException.class,()->myDonutShop.takeOrder(order));
		assertEquals(exception.getMessage(), "Insufficient donuts remaining");
	}

	@Test
	void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException() {
		// given
		Order order = new Order("John", "9", 5, 2, "1234567890", true);
		// when
		when(paymentService.charge(order)).thenReturn(true);
		
		myDonutShop.openForTheDay();
		Throwable exception  = assertThrows(IllegalArgumentException.class,()->myDonutShop.takeOrder(order));
		assertEquals(exception.getMessage(), "Insufficient donuts remaining");
		// then
	}

}