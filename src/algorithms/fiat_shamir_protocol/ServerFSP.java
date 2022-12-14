package algorithms.fiat_shamir_protocol;

import algorithms.encryption.RSA;

import java.math.BigInteger;
import java.util.HashMap;

public class ServerFSP {
    private BigInteger N;
    private int NumIteration = 10;
    private int C;
    private BigInteger X;
    private BigInteger Y;
    HashMap<BigInteger, BigInteger> Database;

    public ServerFSP()
    {
        RSA rsa = new RSA();
        N = new BigInteger(String.valueOf(rsa.getN()));
        //N = BigInteger.valueOf(23002229);
        System.out.println("N = " + N);
    }

    public void fillDatabase()
    {
        Database = new HashMap<>();
    }

    public int generateBit()
    {
        return C = (int) (Math.random() * 2);
    }

    public boolean verify(BigInteger x, BigInteger y, BigInteger v)
    {
        X = x;
        Y = y;
        return Y.modPow(BigInteger.valueOf(2), N).compareTo(X.multiply(v.pow(C)).mod(N)) == 0;

    }

    public int getNumIteration() {
        return NumIteration;
    }
    public BigInteger getN()
    {
        return N;
    }
}
