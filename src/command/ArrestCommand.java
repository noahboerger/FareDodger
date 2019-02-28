package command;

import base.Passenger;
import base.ArrestDuration;

public class ArrestCommand implements ICommand {

    private Passenger passenger;
    private ArrestDuration duration;

    public ArrestCommand(Passenger passenger, ArrestDuration duration) {
        this.passenger = passenger;
        this.duration = duration;
    }

    @Override
    public void execute() {
        System.out.println("POLICE: " + passenger.getName() + " gets jailed for one " + duration);
    }
}
