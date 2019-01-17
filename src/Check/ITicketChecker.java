package Check;

import Base.Passenger;

public interface ITicketChecker {
    void checkPassenger(Passenger passenger);

    public String controlStatus();
}
