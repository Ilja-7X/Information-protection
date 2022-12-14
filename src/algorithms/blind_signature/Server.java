package algorithms.blind_signature;

import algorithms.cryptosys_public_key.PowFast;
import algorithms.digital_signature.RSADigitalSign;

import java.io.IOException;
import java.math.BigInteger;

import static functionalfiles.FileBytes.writeToFileNumStr;

public class Server {
    private BigInteger C;
    private BigInteger D;
    private BigInteger N;

    public Server(){
        RSADigitalSign rsa  = new RSADigitalSign();
        C = new BigInteger(String.valueOf(rsa.getC()));
        D = new BigInteger(String.valueOf(rsa.getD()));
        N = new BigInteger(String.valueOf(rsa.getN()));
        /*C = new BigInteger("37");
        D = new BigInteger("13");
        N = new BigInteger("77");*/
        System.out.println("C = "+rsa.getC()+" D = "+rsa.getD()+" N = "+rsa.getN());
    }

    public BigInteger getSign(BigInteger t)
    {
        return t.modPow(D,N);
    }

    public long getSignBulletin(BigInteger hash) throws IOException {
        long S = PowFast.calculate(hash.longValue(), C.longValue(), N.longValue());
        //writeIntSignToFile("blind",(int)S);
        writeToFileNumStr("result/blind_signature/sign.txt", S);
        return S;
    }

    public BigInteger getD() {
        return D;
    }

    public BigInteger getC() {
        return C;
    }

    public BigInteger getN() {
        return N;
    }
}
