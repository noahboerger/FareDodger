package check;

import base.Passenger;
import base.Ticket;
import base.TicketChip;
import base.Zone;

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

    public boolean checkPassenger(Passenger passenger) {
        checkedTickets++;
        if (passenger.getTicket() == null) {
            int fee = passenger.dodgeFare();
            System.out.println("TICKETCHECKER: " + passenger.getName() + " has no ticket! Fee: " + fee + "€ \n");
            fareDodger++;
            return false;
        } else {
            if (checkTicket(passenger)) {
                System.out.println("TICKETCHECKER: " + passenger.getName() + " has a valid ticket! \n");
                return true;
            } else {
                int fee = passenger.dodgeFare();
                System.out.println("TICKETCHECKER: " + passenger.getName() + " has no valid ticket! Fee: " + fee + "€ \n");
                fareDodger++;
                return false;
            }
        }
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
