package main.digitalsign;

import algorithms.digital_signature.ElGamalSign;

public class MainElGamalSign {
    public static void main(String[] args) {
        try {
            ElGamalSign.generateParameters();
            ElGamalSign A = new ElGamalSign();
            A.signFile("Example/img.png","Result/digitalsign/ElGamalSign");
            ElGamalSign B = new ElGamalSign();
            B.checkSign("Example/img.png", "result/digitalsign/ElGamalSign/checkKey-img.png.txt", A.getY());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
