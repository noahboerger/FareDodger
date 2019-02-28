import base.Passenger;
import base.Utils;
import base.Zone;
import check.TicketChecker;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Observer Pattern als viertes Pattern zusätzlich hinzugefügt
    public static void main(String[] args) {
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        TicketChecker ticketCheckerAUSSEN = new TicketChecker("Kontrollöres Aussenraum", Zone.AUSSENRAUM);
        TicketChecker ticketCheckerXXL = new TicketChecker("Kontrollöres XXL", Zone.XXL);

        List<Passenger> testPassengerList = new ArrayList<>();
        testPassengerList.add(Utils.generateRandomPassengerWithoutTicket());

        Passenger innenraumPassenger = Utils.generateRandomPassengerWithValidTicket();
        innenraumPassenger.getTicket().getTicketChip().setZone(Zone.INNENRAUM);

        Passenger aussenraumPassenger = Utils.generateRandomPassengerWithValidTicket();
        aussenraumPassenger.getTicket().getTicketChip().setZone(Zone.AUSSENRAUM);

        Passenger xxlPassenger = Utils.generateRandomPassengerWithValidTicket();
        xxlPassenger.getTicket().getTicketChip().setZone(Zone.XXL);


        testPassengerList.add(innenraumPassenger);
        testPassengerList.add(aussenraumPassenger);
        testPassengerList.add(xxlPassenger);


        for (int i = 0; i < 3; i++) {
            for (Passenger passenger : testPassengerList) {
                ticketCheckerINNEN.checkPassenger(passenger);
                ticketCheckerAUSSEN.checkPassenger(passenger);
                ticketCheckerXXL.checkPassenger(passenger);
            }
        }
        System.out.println();
        System.out.println(ticketCheckerINNEN.controlStatus());
        System.out.println(ticketCheckerAUSSEN.controlStatus());
        System.out.println(ticketCheckerXXL.controlStatus());

        System.out.println();
        ticketCheckerINNEN.checkPassenger(testPassengerList.get(0));
    }
}
