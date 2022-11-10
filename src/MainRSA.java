import algorithms.encryption.RSA;

import java.io.IOException;
import java.util.List;

public class MainRSA {
    public static void main(String[] args) {

        RSA A = new RSA();
        RSA B = new RSA();
        try
        {
            List<Long> massage= A.sendMessage("Example/testPic.png", B.getD(), B.getN());
            B.receiveMessage(massage, "Example/newTestPic.png");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }



    }
}
