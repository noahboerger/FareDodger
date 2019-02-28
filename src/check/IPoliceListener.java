package check;

import command.ICommand;

public interface IPoliceListener {
    void receive(ICommand command);
}
