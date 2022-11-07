import algorithms.encryption.Shamir;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        long P = Shamir.generatePublicP();
        System.out.println("Random Integer: " + P);
        Shamir A = new Shamir(P);
        Shamir B = new Shamir(P);

        try {
            List<Long> x1 = A.ShamirCalcIteration("Example/img.png", null, 1);
            List<Long> x2 = B.ShamirCalcIteration(null, x1, 2);
            List<Long> x3 = A.ShamirCalcIteration(null, x2, 3);
            B.ShamirCalcIteration("Example/testImg.png", x3, 4);
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}