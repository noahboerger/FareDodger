package State;

import Base.Passenger;
import Check.ArrestDuration;
import Check.IPoliceListener;
import Check.Police;

import java.util.List;

public class S3 implements IState {

    private IPoliceListener listener;
    private int dodgeFareCounter;

    public S3() {
        listener = Police.getInstance();
        dodgeFareCounter = 0;
    }

    @Override
    public int dodgeFare(Passenger passenger) {
        dodgeFareCounter++;
        listener.receiveFareDodger(passenger);
        switch (dodgeFareCounter) {
            case 1:
listener.jail(passenger, ArrestDuration.WEEK);
                return 500;
            case 2:
                listener.jail(passenger, ArrestDuration.MONTH);
                return 1000;
            case 3:
                listener.jail(passenger, ArrestDuration.YEAR);
                return 5000;
            default:
                return 0;
        }
    }
}
