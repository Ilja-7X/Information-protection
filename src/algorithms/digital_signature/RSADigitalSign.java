package algorithms.digital_signature;

import algorithms.cryptosys_public_key.PowFast;
import algorithms.encryption.RSA;
import algorithms.encryption.Shamir;
import functionalfiles.FileBytes;
import algorithms.cryptosys_public_key.*;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.math.BigInteger;


public class RSADigitalSign {
    private long C;
    private long D;
    private long N;
    public RSADigitalSign() {
        RSA subscriber = new RSA();
        C = subscriber.getC();
        D = subscriber.getD();
        N = subscriber.getN();
    }
    public void signFileMD5(String pathExistingFile, String pathNewFile) throws IOException {
        String checksum = DigestUtils.md5Hex(new FileInputStream(pathExistingFile));
        BigInteger hash = new BigInteger(checksum, 16);
        hash = hash.mod(new BigInteger(String.valueOf(N)));
        List<Long> E = new ArrayList<>();

        long S = PowFast.calculate(hash.longValue(), C, N);
    }
    public long getN() {
        return N;
    }
    public long getD() {
        return D;
    }
}