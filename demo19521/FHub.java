package demo18506;

import java.util.ArrayList;
import java.util.List;

import base.Highway;
import base.Hub;
import base.Location;
import base.Network;
import base.Truck;

public class FHub extends Hub{

	List<Truck> t;
	
	public FHub(Location loc) {
		super(loc);
		t=new ArrayList<Truck>();
	}

	@Override
	public boolean add(Truck truck) {
		if(getCapacity()==t.size())
			return false;
		else {
			t.add(truck);
			return true;
		}
	}

	@Override
	protected void remove(Truck truck) {
		t.remove(truck);
	}

	@Override
	public Highway getNextHighway(Hub last, Hub dest) {
		/*ArrayList<Hub> reachableHubs=new ArrayList<>();
		ArrayList<Highway> hv=last.getHighways();
		for(Highway h1:hv)
		{
			if(h1.getEnd()==dest)
				return h1;
			else
				reachableHubs.add(h1.getEnd());
		}*/
		ArrayList<Highway> p=new ArrayList<Highway>();
		obtain(last, dest, p,new ArrayList<Hub>());
		//System.out.print("Path b/w"+last.getLoc().getX()+" "+last.getLoc().getY());
		//System.out.println(" and "+dest.getLoc().getX()+" "+dest.getLoc().getY());
		return p.get(0);
	}
	
	private boolean obtain(Hub last,Hub dest,ArrayList<Highway> path,ArrayList<Hub> visited)
	{
		if(last==dest)
			return true;
		else if(visited.contains(last))
			return false;
		else
		{
			ArrayList<Highway> hw=last.getHighways();
			for(int i=0;i<hw.size();i++)
			{
				visited.add(last);
				if(obtain(hw.get(i).getEnd(),dest,path,visited))
				{
					path.add(hw.get(i));
					return true;
				}
			}
			return false;
		}
	}
	@Override
	protected void processQ(int deltaT) {
		for(Truck t1:t)
		{
			/*if(t1.getDest()==t1.getLoc())
				t.remove(t1);
			else if(getLoc()==t1.getLoc())
				t.remove(t1);*/
			Hub nd=Network.getNearestHub(t1.getDest());
			if(nd.getLoc().getX()==this.getLoc().getX() && nd.getLoc().getY()==this.getLoc().getY())
			{
				t.remove(t1);
				t1.setLoc(t1.getDest());
				//System.out.println(t1.getName()+"Towards destiny");
			}
			else
			{
				Highway next=getNextHighway(this, Network.getNearestHub(t1.getDest()));
				if(next.hasCapacity())
				{
					t1.enter(next);
					t.remove(t1);
					//System.out.println(t1.getName()+" moving on highway toward"+next.getEnd().getLoc().getX()+" "+next.getEnd().getLoc().getY());
				}
			}
		}
		
	}

}
