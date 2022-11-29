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
