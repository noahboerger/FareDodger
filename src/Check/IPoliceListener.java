package Check;

import Base.Passenger;

public interface IPoliceListener {
    void receiveFareDodger(Passenger passenger);

    public void jail(Passenger passenger, ArrestDuration duration);
}
