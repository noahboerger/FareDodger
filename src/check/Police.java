package check;

import base.Passenger;
import command.ICommand;

public class Police implements IPoliceListener {

    public static Police instance = new Police();

    private Police() {
    }

    public static Police getInstance() {
        return instance;
    }

    public void receive(ICommand command) {
        command.execute();
    }
}
