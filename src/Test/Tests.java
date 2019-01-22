package Test;

import Base.Passenger;
import Base.Utils;
import Base.Zone;
import Check.TicketChecker;
import Strategy.AES;
import Strategy.ISecurityStrategy;
import Strategy.RSA;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class Tests {

    @Test
    public void checkRSAEncryption() {
        String encrypt = "TestWort";
        ISecurityStrategy strategy = new RSA();
        if (!strategy.encrypt(encrypt).equals(encrypt) && strategy.decrypt(strategy.encrypt(encrypt)).equals(encrypt)) {
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void checkAESEncryption() {
        String encrypt = "TestWort";
        ISecurityStrategy strategy = new AES();
        if (!strategy.encrypt(encrypt).equals(encrypt) && strategy.decrypt(strategy.encrypt(encrypt)).equals(encrypt)) {
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testValidPassenger() {
        boolean check = false;
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        check = ticketCheckerINNEN.checkPassenger(passenger);
        Assert.assertTrue(check);
    }

    @Test
    public void testPassengerWithoutTicket() {
        boolean check = false;
        Passenger passenger = Utils.generateRandomPassengerWithoutTicket();
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        check = !ticketCheckerINNEN.checkPassenger(passenger);
        Assert.assertTrue(check);
    }

    @Test
    public void testPassengerWithTicketForFalsePassenger() {
        boolean check = false;
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        passenger.getTicket().getTicketChip().setPassengerName("false_name");
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        check = !ticketCheckerINNEN.checkPassenger(passenger);
        Assert.assertTrue(check);
    }

    @Test
    public void testPassengerWithTicketForFalseDate() {
        boolean check = false;
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        passenger.getTicket().getTicketChip().setValidTo(new Date(System.currentTimeMillis()));
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.INNENRAUM);
        check = !ticketCheckerINNEN.checkPassenger(passenger);
        Assert.assertTrue(check);
    }

    @Test
    public void testPassengerWithTicketForFalseZone() {
        boolean check = false;
        Passenger passenger = Utils.generateRandomPassengerWithValidTicket();
        passenger.getTicket().getTicketChip().setZone(Zone.INNENRAUM);
        TicketChecker ticketCheckerINNEN = new TicketChecker("Kontrollöres Innenraum", Zone.AUSSENRAUM);
        check = !ticketCheckerINNEN.checkPassenger(passenger);
        Assert.assertTrue(check);
    }
}
