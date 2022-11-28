package algorithms.encryption;

import functionalfiles.FileBytes;

import java.security.SecureRandom;
import java.io.IOException;

public class Vernam {
    private byte[] secretKey;

    public void encryptFile(String path, String pathNewFile) throws IOException {
        byte[] byteArray = Shamir.getBytesFromFile(path);
        secretKey = new byte[byteArray.length];
        byte[] encryptMessage = new byte[byteArray.length];
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());
        for (int i = 0; i < byteArray.length; i++) {
            secretKey[i] = (byte) sr.nextInt(Byte.MAX_VALUE);
            encryptMessage[i] = (byte) (byteArray[i] ^ secretKey[i]);
        }
        FileBytes.getFileFromBytes(pathNewFile, encryptMessage);

    }
    public void decryptFile(String path, String newPath, byte[] key) throws IOException {
        byte[] encryptMessage = FileBytes.getBytesFromFile(path);
        byte[] decryptMessage = new byte[encryptMessage.length];
        for (int i = 0; i < encryptMessage.length; i++) {
            decryptMessage[i] = (byte) (encryptMessage[i] ^ key[i]);
        }
        Shamir.getFileFromBytes(newPath, decryptMessage);
    }

    public byte[] getSecretKey() {
        return secretKey;
    }
}
