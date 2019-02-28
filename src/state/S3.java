package state;

import base.Passenger;
import base.ArrestDuration;
import check.IPoliceListener;
import check.Police;
import command.ArrestCommand;
import command.ICommand;
import command.InformCommand;

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
        ICommand informCommand = new InformCommand(passenger);
        listener.receive(informCommand);
        ICommand arrestCommand;
        switch (dodgeFareCounter) {
            case 1:
                arrestCommand = new ArrestCommand(passenger, ArrestDuration.WEEK);
                listener.receive(arrestCommand);
                return 500;
            case 2:
                arrestCommand = new ArrestCommand(passenger, ArrestDuration.MONTH);
                listener.receive(arrestCommand);
                return 1000;
            case 3:
                arrestCommand = new ArrestCommand(passenger, ArrestDuration.YEAR);
                listener.receive(arrestCommand);
                return 5000;
            default:
                arrestCommand = new ArrestCommand(passenger, ArrestDuration.YEAR);
                listener.receive(arrestCommand);
                return 5000;
        }
    }
}
