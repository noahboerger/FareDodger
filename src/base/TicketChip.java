package base;

import strategy.AES;
import strategy.ISecurityStrategy;
import strategy.RSA;

import java.util.Date;

public class TicketChip {
    private String passengerName;
    private String zone;
    private String dateValidFromString;
    private String dateValidToString;
    private ISecurityStrategy crypter;
    private String cost;

    public TicketChip(String passengerName, Zone zone, Date validFrom, Date validTo, EncryptionType encryptionType, double cost) {
        if (encryptionType.equals(EncryptionType.RSA)) {
            crypter = new RSA();
        } else {
            crypter = new AES();
        }

        this.passengerName = crypter.encrypt(passengerName);
        this.zone = crypter.encrypt(zone.toString());
        this.dateValidFromString = crypter.encrypt(Utils.dateToString(validFrom));
        this.dateValidToString = crypter.encrypt(Utils.dateToString(validTo));
        this.cost = crypter.encrypt(Double.toString(cost));

    }


    public String getPassengerName() {
        return crypter.decrypt(passengerName);
    }

    public Zone getZone() {
        return Zone.fromString(crypter.decrypt(zone));
    }

    public Date getValidFrom() {
        return Utils.stringToDate(crypter.decrypt(dateValidFromString));
    }

    public Date getValidTo() {
        return Utils.stringToDate(crypter.decrypt(dateValidToString));
    }

    public double getCost() {
        try {
            return Double.parseDouble(crypter.decrypt(cost));
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = crypter.encrypt(passengerName);
    }

    public void setZone(Zone zone) {
        this.zone = crypter.encrypt(zone.toString());
    }

    public void setValidFrom(Date validFrom) {
        this.dateValidFromString = crypter.encrypt(Utils.dateToString(validFrom));
    }

    public void setValidTo(Date validTo) {
        this.dateValidToString = crypter.encrypt(Utils.dateToString(validTo));
    }

    public void setCost(double cost) {
        this.cost = crypter.encrypt(Double.toString(cost));
    }
}
