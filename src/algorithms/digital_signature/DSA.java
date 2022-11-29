package algorithms.digital_signature;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.io.IOException;

import functionalfiles.FileBytes;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.FileInputStream;
import java.security.SignatureException;
import algorithms.cryptosys_public_key.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DSA {
    private static BigInteger P;
    private static BigInteger Q;
    private static BigInteger A;

    private BigInteger x;
    private BigInteger y;

    private BigInteger r;
    private BigInteger s;

    public static void generateParameters() {
        BigInteger b, temp_P;
        do {
            do {
                Q = getRandomBigInteger(16).add(BigInteger.valueOf(4));
            } while(!Q.isProbablePrime(100));
            do {
                P = getRandomBigInteger(16);
            } while(!P.isProbablePrime(100));

            b = P.subtract(BigInteger.valueOf(1)).divide(Q);
            temp_P = b.multiply(Q).add(BigInteger.valueOf(1));
        } while(!temp_P.equals(P));

        do {
            A = getRandomBigInteger(16);
        } while(!A.modPow(Q,P).equals(BigInteger.ONE) || A.equals(BigInteger.ONE));
        //System.out.println("p = "+b+" * "+Q+" + 1 = "+P+"\na = "+A);
    }

    public DSA() {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());
        do {
            x = new BigInteger(String.valueOf(srand.nextInt(Q.intValue())+1)) ;
            y = A.modPow(x, P);
        } while(y.compareTo(BigInteger.valueOf(1)) <= 0);
        //System.out.println("X = "+x+" Y = "+y);
    }

    public void signFile(String pathFile, String pathDirectory) throws IOException {
        SecureRandom srand = new SecureRandom();
        srand.setSeed(System.currentTimeMillis());

        File file = new File(pathFile);
        String nameFile = file.getName();
        String newPath = pathDirectory + "/" + "checkKey-"+ nameFile + ".txt";

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(file));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(Q)));
        long k;
        do {
            k = srand.nextInt(Q.intValue()+2)-1;
            r = A.modPow(BigInteger.valueOf(k), P).mod(Q);
            s = x.multiply(r).add(hash.multiply(BigInteger.valueOf(k))).mod(Q);
        } while(r.equals(BigInteger.ZERO) || s.equals(BigInteger.ZERO));
        //System.out.println(" k = "+k+" r = "+r+" s = "+s);

        List<Long> buffer = new ArrayList<>();
        buffer.add(r.longValue());
        buffer.add(s.longValue());
        FileBytes.writeLongToFile(newPath, buffer);
    }

    public void checkSign(String pathFile, String pathSignFile, BigInteger y_open) throws IOException, SignatureException {
        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(pathFile));
        BigInteger hash = new BigInteger(checksumMD5, 16);
        hash = hash.mod(new BigInteger(String.valueOf(Q)));

        List<Long> buffer = FileBytes.readLongToFile(pathSignFile);

        BigInteger r_sign = BigInteger.valueOf(buffer.get(0));
        BigInteger s_sign = BigInteger.valueOf(buffer.get(1));

        if ( r_sign.compareTo(Q) > 0 || s_sign.compareTo(Q) > 0) {
            throw new SignatureException("digital signature DSA is invalid");
        }
        long[] EuclidResult;
        BigInteger h_1;

        do {
            EuclidResult =  Euclid.calculate(hash.longValue(), Q.longValue());
            h_1 =  new BigInteger(String.valueOf(EuclidResult[2] + Q.longValue()));
        } while(!hash.multiply(h_1).mod(Q).equals(BigInteger.ONE) );

        BigInteger u1 = s_sign.multiply(h_1).mod(Q);
        BigInteger u2 = r_sign.negate().multiply(h_1).mod(Q);

        BigInteger v = (A.pow(u1.intValue()).multiply(y_open.pow(u2.intValue())).mod(P)).mod(Q);

        System.out.println("r = "+r_sign+" v = "+v);
        if (v.compareTo(r_sign) != 0) {
            System.out.println("Note: digital signature DSA is invalid");
        } else {
            System.out.println("Note: digital signature DSA is valid");
        }
    }
    public static BigInteger getRandomBigInteger(int numBits) {
        BigInteger number = new BigInteger(numBits, new Random());
        return number.setBit(0);
    }
    public BigInteger getY() {
        return y;
    }
}
