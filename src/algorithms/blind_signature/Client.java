package algorithms.blind_signature;

import algorithms.cryptosys_public_key.Euclid;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.Scanner;

public class Client {
    private long r;
    private final Bulletin bulletin;
    private String message;

    public Client(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public void generateBlindingFactor(long N)
    {
        SecureRandom sr = new SecureRandom();
        long[] EuclidResult;
            do {
                this.r = sr.nextInt((int)N-3)+2;
                EuclidResult =  Euclid.calculate(r, N);
            } while(EuclidResult[0] != 1);
            System.out.println("r = " + r);
    }

    public String answerQuestion() {
        System.out.println(bulletin.getFullQuestion());
        for (int i = 0; i < bulletin.getPossibleAnswer().length; i++) {
            System.out.println(i+1+"."+bulletin.getPossibleAnswer()[i]+" ");
        }
        Scanner in = new Scanner(System.in);
        System.out.print("-> ");
        int choice = in.nextInt();
        message = bulletin.getFullQuestion()+" - "+bulletin.getPossibleAnswer()[choice - 1];
        return message;
    }

    public BigInteger HashOfAnswer (BigInteger N) {
        String checksumMD5 = DigestUtils.md5Hex(message);
        BigInteger hash = new BigInteger(checksumMD5, 16);
        return hash.mod(new BigInteger(String.valueOf(N)));
    }
}
