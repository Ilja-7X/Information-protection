package algorithms.blind_signature;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.Scanner;

public class Client {
    private BigInteger r;
    private final Bulletin bulletin;
    private String message;

    public Client(Bulletin bulletin) {
        this.bulletin = bulletin;
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
