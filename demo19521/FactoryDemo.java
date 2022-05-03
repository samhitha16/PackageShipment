package demo18506;

import base.Factory;
import base.Highway;
import base.Hub;
import base.Location;
import base.Network;
import base.Truck;

public class FactoryDemo extends Factory{

	@Override
	public Network createNetwork() {
		
		return new FNetwork();
	}

	@Override
	public Highway createHighway() {
		return new FHighway();
	}

	@Override
	public Hub createHub(Location location) {
		return new FHub(location);
		
	}

	@Override
	public Truck createTruck() {
		return new FTruck();
	}

}
