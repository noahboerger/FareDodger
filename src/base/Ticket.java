package base;

public class Ticket {
    private TicketChip ticketChip;

    public Ticket(TicketChip ticketChip) {
        this.ticketChip = ticketChip;
    }

    public TicketChip getTicketChip() {
        return ticketChip;
    }
}
