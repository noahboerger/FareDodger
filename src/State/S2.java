package State;

import Base.Passenger;
import Check.IPoliceListener;
import Check.Police;

import java.util.List;

public class S2 implements IState {

    private IPoliceListener listener;
    private int dodgeFareCounter;

    public S2() {
        this.listener = Police.getInstance();
        dodgeFareCounter = 0;
    }

    @Override
    public int dodgeFare(Passenger passenger) {
        dodgeFareCounter++;
        listener.receiveFareDodger(passenger);
        switch (dodgeFareCounter) {
            case 1:
                return 240;
            case 2:
                return 360;
            case 3:
                passenger.setState(new S3());
                return 480;
            default:
                return 0;
        }
    }
}
