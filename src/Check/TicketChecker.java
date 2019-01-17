package Check;

import Base.Passenger;
import Base.Ticket;
import Base.TicketChip;
import Base.Zone;

import java.util.Date;

public class TicketChecker implements ITicketChecker {

    private String name;
    private int checkedTickets;
    private int fareDodger;

    private Zone checkZone;

    public TicketChecker(String name, Zone checkZone) {
        this.name = name;
        checkedTickets = 0;
        fareDodger = 0;
        this.checkZone = checkZone;

    }

    public void checkPassenger(Passenger passenger) {
        if (passenger.getTicket() == null) {
            int fee = passenger.dodgeFare();
            System.out.println("TICKETCHECKER: " + passenger.getName() + " has no ticket! Fee: " + fee + "€ \n");
            fareDodger++;
        } else {
            if (checkTicket(passenger)) {
                System.out.println("TICKETCHECKER: " + passenger.getName() + " has a valid ticket! \n");
            } else {
                int fee = passenger.dodgeFare();
                System.out.println("TICKETCHECKER: " + passenger.getName() + " has no valid ticket! Fee: " + fee + "€ \n");
                fareDodger++;
            }
        }
        checkedTickets++;
    }

    private boolean checkTicket(Passenger passenger) {
        Ticket ticket = passenger.getTicket();
        TicketChip ticketChip = ticket.getTicketChip();
        Date now = new Date(System.currentTimeMillis());
        if (!passenger.getName().equals(ticketChip.getPassengerName())) {
            System.out.println("TICKETCHECKER: Ticket not valid for this passenger");
            return false;
        } else if (!ticketChip.getZone().validIn(checkZone)) {
            System.out.println("TICKETCHECKER: Ticket not valid in this zone");
            return false;
        } else if (ticketChip.getValidFrom().after(now) || ticketChip.getValidTo().before(now)) {
            System.out.println("TICKETCHECKER: Ticket is no longer or not yet valid");
            return false;
        } else {
            return true;
        }
    }

    public String controlStatus() {
        return "TICKETCHECKER: [" + name + "] Checked " + checkedTickets + " passengers, " + fareDodger + " without valid ticket";
    }
}
