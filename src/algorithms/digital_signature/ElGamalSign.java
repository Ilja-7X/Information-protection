package algorithms.digital_signature;

import java.io.File;
import java.math.BigInteger;

import algorithms.cryptosys_public_key.*;
import functionalfiles.FileBytes;
import org.apache.commons.codec.digest.DigestUtils;
import java.security.SecureRandom;
import java.io.IOException;
import java.io.FileInputStream;
import algorithms.cryptosys_public_key.PowFast;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

public class ElGamalSign {
    private static long P;
    private static long G;

    private long X;
    private long Y;
    private long r;
    private BigInteger S;

    public static void generateParameters() {
        DiffieHellman diffieHellman =  new DiffieHellman();
        setP(diffieHellman.getP());
        setG(diffieHellman.getG());
    }

    public ElGamalSign() {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());
        X = srand.nextInt(((int)P-2)+1);
        Y = PowFast.calculate(G, X, P);
    }

    public void signFile(String pathFile, String pathDirectory) throws IOException {
        long k, k_1;

        File file = new File(pathFile);
        String nameFile = file.getName();
        String newPath = pathDirectory + "/" + "checkKey-"+ nameFile + ".txt";

        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(file));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(P)));

        long[] EuclidResult;
        do {
            do {
                k = srand.nextInt((int)P-3)+2;
                EuclidResult =  Euclid.calculate(k, P-1);
            } while(EuclidResult[0] != 1);
            k_1 = EuclidResult[2] + (P-1);
        } while(k*k_1%(P-1) != 1);

        r = PowFast.calculate(G, k, P);
        BigInteger u = hash.subtract(BigInteger.valueOf(X*r)).mod(BigInteger.valueOf(P-1));
        S = u.multiply(BigInteger.valueOf(k_1)).mod(BigInteger.valueOf(P-1));
        List<Long> buffer = new ArrayList<>();
        buffer.add(r);
        buffer.add(S.longValue());
        FileBytes.writeLongToFile(newPath, buffer);
    }

    public void checkSign(String pathFile, String pathSignFile, long Y_open) throws IOException, SignatureException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(P)));

        List<Long> buffer = FileBytes.readLongToFile(pathSignFile);

        long r_open = buffer.get(0);
        long S = buffer.get(1);

        BigInteger Y_R = new BigInteger(String.valueOf(BigInteger.valueOf(Y_open)));
        Y_R = Y_R.modPow(BigInteger.valueOf( r_open), BigInteger.valueOf(P));

        BigInteger R_S = new BigInteger(String.valueOf(r_open));
        R_S = R_S.modPow(BigInteger.valueOf(S) , BigInteger.valueOf(P));

        BigInteger check = Y_R.multiply(R_S).mod(BigInteger.valueOf(P));

        long message = PowFast.calculate(G, hash.longValue(), P);
        System.out.println("message = "+message+" check = "+check);
        if (check.longValue() != message) {
            System.out.println("Note: digital signature ElGamal is invalid");
        }
        else
            System.out.println("Note: digital signature ElGamal is valid");
    }

    public static void setP(long p) {
        P = p;
    }

    public static void setG(long g) {
        G = g;
    }

    public long getY() {
        return Y;
    }

    public long getR() {
        return r;
    }

    public BigInteger getS() {
        return S;
    }
}
