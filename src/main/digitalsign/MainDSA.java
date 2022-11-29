package main.digitalsign;

import algorithms.digital_signature.DSA;

public class MainDSA {
    public static void main(String[] args) {
        try {
            DSA.generateParameters();
            DSA A = new DSA();
            A.signFile("Example/img.png","Result/digitalsign/ElGamalSign");
            DSA B = new DSA();
            B.checkSign("Example/img.png", "result/digitalsign/ElGamalSign/checkKey-img.png.txt", A.getY());
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}