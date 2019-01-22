package Check;

import Base.Passenger;

public interface ITicketChecker {
    boolean checkPassenger(Passenger passenger);

    public String controlStatus();
}
