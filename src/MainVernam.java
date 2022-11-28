import algorithms.encryption.Vernam;

import java.io.IOException;

public class MainVernam {
    public static void main(String[] args) {
        Vernam A = new Vernam();
        Vernam B = new Vernam();

        try
        {
            A.encryptFile("Example/img.png", "result/encryption/Verman/intermediateTestPic.png");
            B.decryptFile("result/encryption/Verman/intermediateTestPic.png", "result/encryption/Verman/TestPic.png", A.getSecretKey());

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
