package algorithms.encryption;

import algorithms.cryptosys_public_key.DiffieHellman;
import algorithms.cryptosys_public_key.PowFast;
import algorithms.encryption.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Elgamal {
    private long P;
    private long G;

    public Elgamal()
    {
        DiffieHellman Df = new DiffieHellman();
        P = Df.getP();
        G = Df.getG();
    }

    public void calculateReceiveMassage()
    {

    }

    public List<Long> calculateSendMassage(String path, long publicKeyD)
    {
        List<Long> E = new ArrayList<>();

        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());
        long K = sr.nextInt(1000000000 - 2) + 1;

        byte[] byteArray = Shamir.getBytesFromFile(path);
        long R = PowFast.calculate(G,K,P);

        for (byte a : byteArray) {
            //System.out.print(a + " ");
            E.add(PowFast.calculate(a, publicKeyD, P));
        }
        return E;

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
