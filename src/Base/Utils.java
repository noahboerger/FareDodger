package Base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {

    private static Random random = new Random();
    private static String pattern = "dd.MM.yyyy";
    public static SimpleDateFormat format = new SimpleDateFormat(pattern);

    public static String dateToString(Date date) {
        return format.format(date);
    }

    public static Date stringToDate(String dateString) {
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Ticket generateValidTicket(Passenger passenger, Zone zone) {
        EncryptionType encryptionType;
        if (random.nextBoolean()) {
            encryptionType = EncryptionType.RSA;
        } else {
            encryptionType = EncryptionType.AES;
        }
        TicketChip ticketChip = new TicketChip(passenger.getName(), zone, new Date(System.currentTimeMillis() - Math.abs(random.nextInt())), new Date(System.currentTimeMillis() + Math.abs(random.nextInt())), encryptionType, Math.abs(random.nextDouble()));
        return new Ticket(ticketChip);
    }

    public static Passenger generateRandomPassengerWithValidTicket() {
        Passenger passenger = new Passenger(getRandomName(), null);
        passenger.setTicket(generateValidTicket(passenger, Zone.AUSSENRAUM));
        return passenger;
    }

    public static Passenger generateRandomPassengerWithoutTicket() {
        Passenger passenger = new Passenger(getRandomName(), null);
        return passenger;
    }

    private static String getRandomName() {
        switch (Math.abs(random.nextInt()) % 10) {
            case 0:
                return "Paul";
            case 1:
                return "Bernd";
            case 2:
                return "Brigitte";
            case 3:
                return "Janine";
            case 4:
                return "Johannes";
            case 5:
                return "Laura";
            case 6:
                return "Julian";
            case 7:
                return "Cay";
            case 8:
                return "Mathias";
            case 9:
                return "Sibille";
            default:
                return "Namenlos";
        }
    }
}
