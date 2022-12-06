package algorithms.blind_signature;

import algorithms.cryptosys_public_key.PowFast;
import algorithms.digital_signature.RSADigitalSign;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import static functionalfiles.FileBytes.writeToFile;
import static functionalfiles.FileBytes.writeToFileNumStr;

public class Client {
    private BigInteger C;
    private BigInteger D;
    private BigInteger N;
    private BigInteger r;
    private final Bulletin bulletin;
    private String message;

    public Client(Bulletin bulletin) {
        this.bulletin = bulletin;
        RSADigitalSign rsa  = new RSADigitalSign();
        C = new BigInteger(String.valueOf(rsa.getC()));
        D = new BigInteger(String.valueOf(rsa.getD()));
        N = new BigInteger(String.valueOf(rsa.getN()));
        System.out.println("C = "+rsa.getC()+" D = "+rsa.getD()+" N = "+rsa.getN());
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

    public BigInteger HashOfAnswer () {
        String checksumMD5 = DigestUtils.md5Hex(message);
        BigInteger hash = new BigInteger(checksumMD5, 16);
        return hash.mod(new BigInteger(String.valueOf(N)));
    }

    public long getSignBulletin(BigInteger hash) throws IOException {
        long S = PowFast.calculate(hash.longValue(), C.longValue(), N.longValue());
        //writeIntSignToFile("blind",(int)S);
        writeToFile("result/blind_signature/sign.txt", S);
        writeToFileNumStr("result/blind_signature/nweSign.txt", S);
        return S;
    }

    public BigInteger getD() {
        return D;
    }

    public BigInteger getN() {
        return N;
    }
}
