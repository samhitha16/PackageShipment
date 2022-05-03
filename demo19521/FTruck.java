package demo19521;

import base.Highway;
import base.Hub;
import base.Network;
import base.Truck;

public class FTruck extends Truck{

	private Hub last=null;
	private Highway current=null;
	private Hub destiny=null;
	private String truckName;
	static int v=19521;
	private long currentTime=0;
	FTruck()
	{	
		truckName="Truck-"+v;
		v++;
	}
	
	public String getTruckName() {
		return truckName;
	}
	
	@Override
	public Hub getLastHub() {
		return last;
	}

	@Override
	public void enter(Highway hwy) {
			hwy.add(this);
			current=hwy;
	}

	@Override
	protected void update(int deltaT) {
		currentTime+=deltaT;
		if(destiny==null) {
			getClosestDestHub();
			//System.out.println("Destiny "+destiny.getLoc().getX()+" "+destiny.getLoc().getY());
		}
		if(currentTime<getStartTime()) {}
		else if(last==null) {
			getNearestHub();
			last.add(this);
			this.setLoc(last.getLoc());
			//System.out.println(this.getName()+" moved to"+last.getLoc().getX()+" "+last.getLoc().getY());
			//System.out.println(this.getName()+" must move to"+destiny.getLoc().getX()+" "+destiny.getLoc().getY());
		}
		/*else if(getLoc()==destiny.getLoc())
		{
			this.setLoc(this.getDest());
		}
		else if(getLoc()==getDest()) {
			System.out.println(this.getName()+" reached destination");
		}*/
		else if(current!=null){ //Traveling on highway until next hub
				if(current.getEnd().add(this))
				{
					last=current.getEnd();
					current.remove(this);
					this.setLoc(last.getLoc());
					current=null;
				}
		}
		
		
	}
	
	private void getNearestHub()
	{
		last=Network.getNearestHub(getSource());	
	}
	
	private void getClosestDestHub()
	{
		destiny=Network.getNearestHub(getDest());
	}

}
