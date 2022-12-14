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
        //RSA rsa = new RSA();
        //N = new BigInteger(String.valueOf(rsa.getN()));
        N = BigInteger.valueOf(23002229);
        //System.out.println("N = " + N);
    }

    public void fillDatabase()
    {
        Database = new HashMap<>();
    }

    public int generateBit()
    {
        //return (int) (Math.random() * 2);
        return C = 1;
    }

    public boolean verify(BigInteger x, BigInteger y, BigInteger v)
    {
        X = x;
        Y = y;
        //BigInteger num1 = Y.modPow(BigInteger.valueOf(2), N);
        BigInteger num1 = Y.modPow(BigInteger.valueOf(2), N);
        //BigInteger num2 = X.multiply(v.modPow(BigInteger.valueOf(C), N));
        BigInteger num12 = X.multiply(v.pow(C));
        System.out.println("Middle = " + num12);
        BigInteger num2 = num12.mod(N);
        System.out.println(X + "*" + v + "^" + C + " = " + num2);

        System.out.println(num1 +" & " + num2);
        return num1.compareTo(num2) == 0;
        //return Y.modPow(BigInteger.valueOf(2), N).compareTo(X.multiply(v.modPow(BigInteger.valueOf(C), N)).mod(N)) == 0;
        //return Y.mod(BigInteger.valueOf(2)).compareTo(X.multiply(v.modPow(BigInteger.valueOf(C), N)).mod(N)) == 0;

    }

    public int getNumIteration() {
        return NumIteration;
    }
    public BigInteger getN()
    {
        return N;
    }
}
