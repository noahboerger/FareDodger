package state;

import base.Passenger;
import check.IPoliceListener;
import check.Police;
import command.ICommand;
import command.InformCommand;

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
        ICommand informCommand = new InformCommand(passenger);
        listener.receive(informCommand);
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
