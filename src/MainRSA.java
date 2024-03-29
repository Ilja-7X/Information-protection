import algorithms.encryption.RSA;

import java.io.IOException;

public class MainRSA {
    public static void main(String[] args) {

        RSA A = new RSA();
        RSA B = new RSA();
        try
        {
            A.sendMessage("Example/img.png","Result/encryption/RSA/intermediateTestPic.png", B.getD(), B.getN());
            B.receiveMessage("Result/encryption/RSA/intermediateTestPic.png", "Result/encryption/RSA/newTestPic.png");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
