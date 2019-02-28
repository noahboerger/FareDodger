
import base.Passenger;
import base.Utils;
import base.Zone;
import check.TicketChecker;
import org.junit.Assert;
import org.junit.Test;
import state.S0;
import state.S1;
import state.S2;
import state.S3;
import strategy.AES;
import strategy.ISecurityStrategy;
import strategy.RSA;

import java.util.Date;

public class Tests {

    @Test
    public void checkRSAEncryption() {
        String encrypt = "TestWort";
        ISecurityStrategy strategy = new RSA();
        Assert.assertTrue(!strategy.encrypt(encrypt).equals(encrypt) && strategy.decrypt(strategy.encrypt(encrypt)).equals(encrypt));
    }

    @Test
    public void checkAESEncryption() {
        String encrypt = "TestWort";
        ISecurityStrategy strategy = new AES();
        Assert.assertTrue(!strategy.encrypt(encrypt).equals(encrypt) && strategy.decrypt(strategy.encrypt(encrypt)).equals(encrypt));
    }

    @Test
    public void testValidPassenger() {
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        Assert.assertTrue(ticketCheckerINNEN.checkPassenger(passenger));
    }

    @Test
    public void testPassengerWithoutTicket() {
        Passenger passenger = Utils.generateRandomPassengerWithoutTicket();
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        Assert.assertFalse(ticketCheckerINNEN.checkPassenger(passenger));
    }

    @Test
    public void testPassengerWithTicketForFalsePassenger() {
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        passenger.getTicket().getTicketChip().setPassengerName("false_name");
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        Assert.assertFalse(ticketCheckerINNEN.checkPassenger(passenger));
    }

    @Test
    public void testPassengerWithTicketForFalseDate() {
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        passenger.getTicket().getTicketChip().setValidTo(new Date(System.currentTimeMillis()));
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        Assert.assertFalse(ticketCheckerINNEN.checkPassenger(passenger));
    }

    @Test
    public void testPassengerWithTicketForFalseZone() {
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        passenger.getTicket().getTicketChip().setZone(Zone.INNENRAUM);
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.AUSSENRAUM);
        Assert.assertFalse(ticketCheckerINNEN.checkPassenger(passenger));
    }

    @Test
    public void testStates() {
        Passenger passenger = Utils.generateRandomPassengerWithoutTicket();
        Assert.assertTrue(passenger.getState() instanceof S0);
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S1);
        passenger.dodgeFare();
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S1);
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S2);
        passenger.dodgeFare();
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S2);
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S3);
        passenger.dodgeFare();
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S3);
        passenger.dodgeFare();
        passenger.dodgeFare();
        passenger.dodgeFare();
        passenger.dodgeFare();
        Assert.assertTrue(passenger.getState() instanceof S3);
    }
}
