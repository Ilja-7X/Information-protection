package main.digitalsign;

import algorithms.fiat_shamir_protocol.ClientFSP;
import algorithms.fiat_shamir_protocol.ServerFSP;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class MainFiatShamirProtocol {
    public static void main(String[] args) {
        try {
            ServerFSP server = new ServerFSP();

            ServerFSP.test();

            ClientFSP client = new ClientFSP(server.getN());
            if(!server.authenticate(client.getLogin()))
            {
                throw new IOException("Authentication failed");
            }

            BigInteger x;
            BigInteger y;

            //System.out.println("N = " + server.getN());
            //System.out.println("S = " + client.getS());
            System.out.println("V = " + client.getV());


            for (int i = 0; i < server.getNumIteration(); i++) {
                x = client.calculateX();
                int c = server.generateBit();
                y = client.calculateY(c);
                if(!server.verify(x, y))
                {
                    throw new IOException("Authentication failed");
                }
            }
            System.out.println("Authentication was successful");
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
