package algorithms.encryption;

import algorithms.cryptosys_public_key.PowFast;
import algorithms.cryptosys_public_key.Prime;
import algorithms.cryptosys_public_key.Euclid;
import functionalfiles.FileBytes;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class RSA {
    public static final int MAX_VALUE = 10000;
    private long P;
    private long Q;

    private long N;
    private long phi;

    private long C;
    private long D;

    public RSA () {
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());
        do {
            this.P = sr.nextInt(MAX_VALUE-3)+2;
        } while(!Prime.isPrime(P));


        do {
            this.Q = sr.nextInt(MAX_VALUE-3)+2;
        } while(!Prime.isPrime(Q));

        this.N = P * Q;
        this.phi = (P-1)*(Q-1);
        long[] EuclidResult;
        do {
            do {
                this.D = sr.nextInt((int)phi-3)+2;
                EuclidResult =  Euclid.calculate(D, phi);
            } while(EuclidResult[0] != 1);
            this.C = EuclidResult[2] + phi;
        } while(D*C%(phi) != 1);
    }

    public void sendMessage(String pathExistingFile, String pathNewFile, long D, long N) throws IOException {
        byte[] byteArray = FileBytes.getBytesFromFile(pathExistingFile);
        List<Long> E = new ArrayList<>();
        for (byte b : byteArray) {
            E.add(PowFast.calculate(b, D, N));
        }
        byte[] arrayBytes = FileBytes.longToBytes(E);
        FileBytes.getFileFromBytes(pathNewFile, arrayBytes);
    }

    public void receiveMessage(String path, String newPath) throws IOException {
        byte[] buffer = FileBytes.getBytesFromFile(path);
        List<Long> E = FileBytes.bytesToLong(buffer);
        byte [] result = new byte[E.size()];
        for (int i = 0; i < E.size(); i++) {
            result[i] = (byte) PowFast.calculate(E.get(i), getC(), getN());
        }
        FileBytes.getFileFromBytes(newPath, result);
    }
    public long getN() {
        return N;
    }
    public long getC() {
        return C;
    }
    public long getD() {
        return D;
    }
}
