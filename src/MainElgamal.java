import algorithms.encryption.Elgamal;

import java.util.List;

public class MainElgamal {
    public static void main(String[] args) {
        Elgamal el = new Elgamal();
        System.out.println("P = " + el.getP());
        System.out.println("G = " + el.getG());
        List<Long> massage= el.calculateSendMassage("Example/testPic.png", 454444785);
        for( Long i: massage)
        {
            System.out.print(i + " ");
        }
    }
}
