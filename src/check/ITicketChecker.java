package check;

import base.Passenger;

public interface ITicketChecker {
    boolean checkPassenger(Passenger passenger);

    public String controlStatus();
}
