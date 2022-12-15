package algorithms.blind_signature;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

import java.util.function.BiFunction;

public class Client {
    private BigInteger r;
    private BigInteger m;
    //private BigInteger r = new BigInteger("12");
    //private BigInteger m = new BigInteger("31");
    private final Bulletin bulletin;
    private BigInteger hashName;
    private String message;

    public Client(Bulletin bulletin) {
        this.bulletin = bulletin;
        hashName = calculateHashName();
    }

    private String entryLogin()
    {
        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private BigInteger calculateHashName(){
        String name = entryLogin();
        String checkSumMD5 = DigestUtils.md5Hex(name);
        return new BigInteger(checkSumMD5, 16);
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

    public BigInteger nextRandomBigInteger(BigInteger upperLimit){
        Random randomSource = new Random();
        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(upperLimit.bitLength(), randomSource);
        } while (randomNumber.compareTo(upperLimit) >= 0);
        return randomNumber;
    }
    public void generateBlindingFactor(BigInteger N)
    {
        BigInteger result;
        do {
            result = nextRandomBigInteger(N);
        } while (result.gcd(N).longValue() != 1);
        r = result;
    }


    public void HashOfAnswer (BigInteger N) {
        String checksumMD5 = DigestUtils.md5Hex(message);
        BigInteger hash = new BigInteger(checksumMD5, 16);
        m = hash.mod(new BigInteger(String.valueOf(N)));
    }

    public BigInteger applyMultiplier(BigInteger c, BigInteger n) {
        return m.multiply(r.modPow(c, n)).mod(n);
    }

    public BigInteger extractSignature(BigInteger t, BigInteger N)
    {
        return (t.multiply(r.modPow(BigInteger.ONE.negate(), N))).mod(N);
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getM() {
        return m;
    }

    public BigInteger getHashName() {
        return hashName;
    }
}
