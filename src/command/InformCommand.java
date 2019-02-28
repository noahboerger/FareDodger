package command;

import base.Passenger;

public class InformCommand implements ICommand{

    private Passenger passenger;

    public InformCommand(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public void execute() {
        System.out.println("POLICE: Info about " + passenger.getName() + " received");
    }
}
