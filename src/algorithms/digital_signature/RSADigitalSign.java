package algorithms.digital_signature;

import algorithms.cryptosys_public_key.PowFast;
import algorithms.encryption.RSA;

import functionalfiles.FileBytes;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.math.BigInteger;

public class RSADigitalSign {
    private long C;
    private long D;
    private long N;
    public RSADigitalSign() {
        RSA subscriber = new RSA();
        C = subscriber.getC();
        D = subscriber.getD();
        N = subscriber.getN();
    }
    public void signFileMD5(String pathExistingFile, String pathDirectory) throws IOException {
        File file = new File(pathExistingFile);
        String nameFile = file.getName();
        String newPath = pathDirectory + "/" + "checkKey-"+ nameFile + ".txt";
        String checksum = DigestUtils.md5Hex(new FileInputStream(file));
        BigInteger hash = new BigInteger(checksum, 16);
        hash = hash.mod(new BigInteger(String.valueOf(N)));

        long S = PowFast.calculate(hash.longValue(), C, N);
        FileBytes.writeToFile(newPath, S);
    }
    public void checkSign(String pathFile, String pathSignFile, long D, long N) throws IOException {
        String checksum = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksum, 16);
        hash = hash.mod(new BigInteger(String.valueOf(N)));
        System.out.println("Hash = " + hash.longValue());


        long S = FileBytes.readToFile(pathSignFile);

        long w = PowFast.calculate(S, D, N);
        System.out.println("Сalculated value = " + w);

        if(w != hash.longValue()){
            System.out.println("Note: digital signature RSA is invalid");
        }
        else
            System.out.println("Note: digital signature RSA is valid");
    }


    public long getN() {
        return N;
    }
    public long getD() {
        return D;
    }

    public long getC() { return C; }
}