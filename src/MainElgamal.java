import algorithms.encryption.Elgamal;

public class MainElgamal {
    public static void main(String[] args) {
        Elgamal.setInitialValues();

        Elgamal elA = new Elgamal();
        Elgamal elB = new Elgamal();

        elA.calculateSendMassage("Example/img.png","result/encryption/Elgamal/intermediateImg.png", elB.getD());
        elB.calculateReceiveMassage("result/encryption/Elgamal/intermediateImg.png", "result/encryption/Elgamal/newImg.png", elA.getR());
    }
}
