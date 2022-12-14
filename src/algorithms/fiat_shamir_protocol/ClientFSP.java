package algorithms.fiat_shamir_protocol;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class ClientFSP {
    private BigInteger S;
    private BigInteger V;
    private BigInteger R;
    private BigInteger N;

    private String Login;

    public ClientFSP(BigInteger N)
    {
        this.N = N;
        //this.S = nextRandomBigInteger(this.N.subtract(BigInteger.valueOf(1)));
        this.Login = entryLogin();
        this.S = entryPassword();
        //this.V = S.pow(2).mod(this.N);
        this.V = S.modPow(BigInteger.valueOf(2), this.N);
    }

    private String entryLogin()
    {
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        return new String(scanner.nextLine());
    }

    private BigInteger entryPassword()
    {
            System.out.println("Enter the password: ");
            Scanner scanner = new Scanner(System.in);
            String password;
            password = scanner.nextLine();
            //System.out.println(password);
            String checkSumMD5 = DigestUtils.md5Hex(password);
            BigInteger hash = new BigInteger(checkSumMD5, 16);
            //System.out.println("Hash: " + hash);
            //System.out.println("Hash2 = " + hash.mod(N));
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
        //R = BigInteger.valueOf(21955553);
        BigInteger X = R.modPow(BigInteger.valueOf(2), N);
        return X;
    }

    public BigInteger calculateY(int bit)
    {
        BigInteger y = R.multiply(S.pow(bit)).mod(N);
        return y;
    }

    public BigInteger getV()
    {
        return V;
    }

    public BigInteger getR()
    {
        return R;
    }
    public BigInteger getS()
    {
        return S;
    }

    public String getLogin() {
        return Login;
    }
}
