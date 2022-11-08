package algorithms.cryptosys_public_key;

import java.security.SecureRandom;


public class DiffieHellman {
    public long P;
    public long G;

    public DiffieHellman(){
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());
        long Q;
        int P;
        do {
            Q = sr.nextInt(1000000000 - 2) + 1;
            P = 2 * (int)Q + 1;
        } while(!Prime.isPrime(Q) || !Prime.isPrime(P));

        do {
            G = sr.nextInt(P - 3) + 2;
        } while(PowFast.calculate(G,Q,P) == 1);
        this.P = P;
    }
    public long getP()
    {
        return P;
    }

    public long getG()
    {
        return G;
    }
}
