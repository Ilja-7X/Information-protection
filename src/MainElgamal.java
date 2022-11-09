import algorithms.encryption.Elgamal;

import java.util.List;

public class MainElgamal {
    public static void main(String[] args) {
        Elgamal.setInitialValues();

        Elgamal elA = new Elgamal();
        Elgamal elB = new Elgamal();

        List<Long> massage= elA.calculateSendMassage("Example/testPic.png", elB.getD());
        elB.calculateReceiveMassage(elA.getR(), massage, "Example/newTestPic.png");
    }
}
