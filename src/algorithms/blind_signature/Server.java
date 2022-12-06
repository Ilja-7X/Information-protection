package algorithms.blind_signature;

import algorithms.cryptosys_public_key.PowFast;

import java.math.BigInteger;

public class Server {
    public boolean checkSignature(BigInteger hash, long S, BigInteger D, BigInteger N) {
        long w = PowFast.calculate(S, D.longValue(), N.longValue());

        System.out.println("message hash = "+hash+" w hash = "+w);

        return w == hash.longValue();
    }
}
