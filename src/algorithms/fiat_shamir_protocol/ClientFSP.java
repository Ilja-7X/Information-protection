package algorithms.fiat_shamir_protocol;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class ClientFSP {
    private final BigInteger S;
    private final BigInteger V;
    private BigInteger R;
    private final BigInteger N;

    private final String Login;

    public ClientFSP(BigInteger N)
    {
        this.N = N;
        this.Login = entryLogin();
        this.S = entryPassword();
        this.V = S.modPow(BigInteger.valueOf(2), this.N);
    }

    private String entryLogin()
    {
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private BigInteger entryPassword()
    {
            System.out.println("Enter the password: ");
            Scanner scanner = new Scanner(System.in);
            String password;
            password = scanner.nextLine();
            String checkSumMD5 = DigestUtils.md5Hex(password);
            BigInteger hash = new BigInteger(checkSumMD5, 16);
            return hash.mod(N);
    }

    public BigInteger nextRandomBigInteger(BigInteger upperLimit){
        Random randomSource = new Random();
        BigInteger randomNumber;
        do {
            randomNumber = new BigInteger(upperLimit.bitLength(), randomSource);
        } while (randomNumber.compareTo(upperLimit) >= 0);
        return randomNumber;
    }
    public BigInteger calculateX()
    {
        R = nextRandomBigInteger(N.subtract(BigInteger.valueOf(1)));
        return R.modPow(BigInteger.valueOf(2), N);
    }

    public BigInteger calculateY(int bit)
    {
        return R.multiply(S.pow(bit)).mod(N);
    }

    public String getLogin() {
        return Login;
    }
}
