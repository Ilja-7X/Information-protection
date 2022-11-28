package main.digitalsign;

import algorithms.digital_signature.RSADigitalSign;

import java.io.IOException;

public class MainRSADigitalSign {
    public static void main(String[] args) {
        try {
            RSADigitalSign A = new RSADigitalSign();
            A.signFileMD5("Example/img.png", "Result/digitalsign/RSA");
            RSADigitalSign B = new RSADigitalSign();
            B.checkSign("Example/img.png", "Result/digitalsign/RSA/checkKey-img.png.txt", A.getD(), A.getN());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
