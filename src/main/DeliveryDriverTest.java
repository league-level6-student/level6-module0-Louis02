package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

	DeliveryDriver deliveryDriver;

	@Mock
	Car car;

	@Mock
	CellPhone cell;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		deliveryDriver = new DeliveryDriver("Joe", car, cell);

	}

	@Test
	void itShouldWasteTime() {
		// given
		boolean expectedTimeWasted = true;

		when(cell.browseCatMemes()).thenReturn(true);
		boolean ddWasteTime = deliveryDriver.wasteTime();
		assertEquals(expectedTimeWasted, ddWasteTime);
		// when

		// then

	}

	@Test
	void itShouldRefuel() {
		// given

		// when

		// then
	}

	@Test
	void itShouldContactCustomer() {
		// given

		// when

		// then
	}

}