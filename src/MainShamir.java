import algorithms.encryption.Shamir;
import java.io.IOException;

public class MainShamir {

    public static void main(String[] args)
    {
        long P = Shamir.generatePublicP();
        System.out.println("Random Integer: " + P);
        Shamir A = new Shamir(P);
        Shamir B = new Shamir(P);

        try {
            A.ShamirCalcIteration("Example/img.png", "result/encryption/Shamir/img1.png", 1);
            B.ShamirCalcIteration("result/encryption/Shamir/img1.png", "result/encryption/Shamir/img2.png", 2);
            A.ShamirCalcIteration("result/encryption/Shamir/img2.png", "result/encryption/Shamir/img3.png", 3);
            B.ShamirCalcIteration("result/encryption/Shamir/img3.png", "result/encryption/Shamir/img4.png", 4);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}