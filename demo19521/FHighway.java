package demo18506;

import java.util.ArrayList;
import java.util.List;

import base.Highway;
import base.Truck;

public class FHighway extends Highway
{
	List<Truck> t;
	FHighway()
	{
		t=new ArrayList<>();
	}
	public boolean hasCapacity() 
	{
		if(t.size()==getCapacity())
			return false;
		else
			return true;
	}

	@Override
	public boolean add(Truck truck) 
	{
		if(t.size()==getCapacity())
			return false;
		else
		{
			t.add(truck);
			return true;
		}
	}

	@Override
	public void remove(Truck truck) 
	{
		t.remove(truck);
	}

}
