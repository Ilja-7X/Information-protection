import algorithms.encryption.Vernam;

import java.io.IOException;

public class MainVernam {
    public static void main(String[] args) {
        Vernam A = new Vernam();
        Vernam B = new Vernam();

        try
        {
            byte[] buffer = A.encryptFile("Example/img.png");
            B.decryptFile("Example/newImgPic.png", buffer, A.getSecretKey());

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
