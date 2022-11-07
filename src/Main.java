
import algorithms.cryptosys_public_key.PowFast;
import algorithms.encryption.Shamir;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        long P = Shamir.generatePublicP();
        System.out.println("Random Integer value : " + P);
        Shamir A = new Shamir(P);
        Shamir B = new Shamir(P);
        /*byte[] buffer = Shamir.getBytesFromFile("Example/image.png");
        Shamir.getFileFromBytes("Example/10.png", buffer);

        System.out.println();*/

        try {
            List<Long> x1 = A.ShamirCalcIteration("Example/img.png", null, 1);
            List<Long> x2 = B.ShamirCalcIteration(null, x1, 2);
            List<Long> x3 = A.ShamirCalcIteration(null, x2, 3);
            List<Long> x4 = B.ShamirCalcIteration("Example/testImg.png", x3, 4);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        //System.out.print("p");
    }
}