package base;

import state.IState;
import state.S0;

public class Passenger {
    private IState state;
    private Ticket ticket;
    private String name;

    public Passenger(String name, Ticket ticket) {
        this.name = name;
        this.ticket = ticket;
        state = new S0();
    }

    public int dodgeFare() {
        return state.dodgeFare(this);

    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getName() {
        return name;
    }
}
