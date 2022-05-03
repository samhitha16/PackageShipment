package demo18506;

import java.util.ArrayList;
import java.util.List;

import base.Display;
import base.Highway;
import base.Hub;
import base.Location;
import base.Network;
import base.Truck;

public class FNetwork extends Network{

	List<Hub> h=new ArrayList<Hub>();
	List<Highway> hw=new ArrayList<Highway>();
	List<Truck> t=new ArrayList<Truck>();
	@Override
	public void add(Hub hub) 
	{
		h.add(hub);		
	}

	@Override
	public void add(Highway hwy) {
		hw.add(hwy);	
	}

	@Override
	public void add(Truck truck) {
		t.add(truck);
	}

	@Override
	public void start() {
		for(Hub hb:h)
			hb.start();
		for(Truck tr:t)
			tr.start();		
	}

	@Override
	public void redisplay(Display disp) {
		for(Hub hb:h)
			hb.draw(disp);
		for(Truck tr:t)
			tr.draw(disp);
		for(Highway hv:hw)
			hv.draw(disp);
	}

	@Override
	protected Hub findNearestHubForLoc(Location loc) 
	{
		int min=Integer.MAX_VALUE;
		Hub near=null;
		for(Hub hb:h)
		{
			int d=loc.distSqrd(hb.getLoc());
			if(d<min)
			{
				min=d;
				near=hb;
			}
		}
		return near;
	}

}
