package Check;

import Base.Passenger;

public class Police implements IPoliceListener {

    public static Police instance = new Police();

    private Police() {
    }

    public static Police getInstance() {
        return instance;
    }

    public void receiveFareDodger(Passenger passenger) {
        System.out.println("POLICE: Info about " + passenger.getName() + " received");
    }

    public void jail(Passenger passenger, ArrestDuration duration) {
        System.out.println("POLICE: " + passenger.getName() + " gets jailed for one " + duration);
    }
}
