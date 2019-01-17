package State;

import Base.Passenger;
import Check.IPoliceListener;
import Check.Police;

import java.util.List;

public class S1 implements IState {

    private IPoliceListener listener;
    private int dodgeFareCounter;

    public S1() {
        this.listener = Police.getInstance();
        dodgeFareCounter = 0;
    }

    @Override
    public int dodgeFare(Passenger passenger) {
        dodgeFareCounter++;
        listener.receiveFareDodger(passenger);
        switch (dodgeFareCounter) {
            case 1:
                return 80;
            case 2:
                return 120;
            case 3:
                passenger.setState(new S2());
                return 200;
            default:
                return 0;
        }
    }
}
