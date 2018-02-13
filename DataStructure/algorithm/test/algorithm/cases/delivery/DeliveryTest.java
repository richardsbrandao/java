package algorithm.cases.delivery;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.cases.delivery.Delivery;

public class DeliveryTest {
	
	@Test
	public void one_full_delivery() {
		Integer[] packagesWeight = new Integer[]{100};
		int count = new Delivery(packagesWeight, 100).count();
		assertEquals(1, count);
	}
	
	@Test
	public void one_full_delivery_with_two_packages() {
		Integer[] packagesWeight = new Integer[]{60,40};
		int count = new Delivery(packagesWeight, 100).count();
		assertEquals(1, count);
	}

	@Test
	public void three_packages_with_few_weight() {
		Integer[] packagesWeight = new Integer[]{60,30,50};
		int count = new Delivery(packagesWeight, 100).count();
		assertEquals(2, count);
	}

	@Test
	public void two_packages_with_two_deliveries() {
		Integer[] packagesWeight = new Integer[]{70,20,30,50};
		int count = new Delivery(packagesWeight, 100).count();
		assertEquals(2, count);
	}

	@Test
	public void mix_case_one() {
		Integer[] packagesWeight = new Integer[]{100,60,30,10,50,70,40,20,80,90};
		int count = new Delivery(packagesWeight, 100).count();
		assertEquals(6, count);
	}
	
	@Test
	public void mix_case_two() {
		Integer[] packagesWeight = new Integer[]{100,60,90};
		int count = new Delivery(packagesWeight, 100).count();
		assertEquals(3, count);
	}
	
}
