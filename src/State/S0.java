package State;

import Base.Passenger;
import Check.IPoliceListener;

import java.util.List;

public class S0 implements IState {


    public S0() {
    }

    @Override
    public int dodgeFare(Passenger passenger) {
        passenger.setState(new S1());
        return 60;
    }
}
