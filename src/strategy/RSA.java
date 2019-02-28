package strategy;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA implements ISecurityStrategy {

    private BigInteger n, d, e;

    private final int bitlen = 1024;

    public RSA() {
        generateKeys();
    }

    private synchronized void generateKeys() {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }

    @Override
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }

    @Override
    public synchronized String decrypt(String encryptedText) {
        return new String((new BigInteger(encryptedText)).modPow(d, n).toByteArray());
    }
}
