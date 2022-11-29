package algorithms.digital_signature;

import java.math.BigInteger;

import algorithms.cryptosys_public_key.*;
import org.apache.commons.codec.digest.DigestUtils;
import java.security.SecureRandom;
import java.io.IOException;
import java.io.FileInputStream;
import algorithms.cryptosys_public_key.PowFast;
import java.security.SignatureException;

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
        System.out.println("P" + P);
        System.out.println("G" + G);
        X = srand.nextInt(((int)P-2)+1);
        Y = PowFast.calculate(G, X, P);
    }

    public void signFile(String pathFile) throws IOException {
        long k, k_1;
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
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
        System.out.println("r = " + r);
        BigInteger u = hash.subtract(BigInteger.valueOf(X*r)).mod(BigInteger.valueOf(P-1));
        S = u.multiply(BigInteger.valueOf(k_1)).mod(BigInteger.valueOf(P-1));
        System.out.println("S = " + S);
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
