package strategy;

public interface ISecurityStrategy {
    String encrypt(String message);

    String decrypt(String encryptedText);
}
