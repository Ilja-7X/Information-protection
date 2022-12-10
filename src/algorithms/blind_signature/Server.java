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
        System.out.println("C = "+rsa.getC()+" D = "+rsa.getD()+" N = "+rsa.getN());
    }

    

    public long getSignBulletin(BigInteger hash) throws IOException {
        long S = PowFast.calculate(hash.longValue(), C.longValue(), N.longValue());
        //writeIntSignToFile("blind",(int)S);
        writeToFileNumStr("result/blind_signature/sign.txt", S);
        return S;
    }

    public boolean checkSignature(BigInteger hash, long S) {
        long w = PowFast.calculate(S, D.longValue(), N.longValue());

        System.out.println("message hash = "+hash+" w hash = "+w);

        return w == hash.longValue();
    }

    public BigInteger getD() {
        return D;
    }

    public BigInteger getN() {
        return N;
    }
}
