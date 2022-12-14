package main.digitalsign;

import algorithms.fiat_shamir_protocol.ClientFSP;
import algorithms.fiat_shamir_protocol.ServerFSP;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.util.Scanner;

public class MainFiatShamirProtocol {
    public static void main(String[] args) {
        ServerFSP server = new ServerFSP();
        ClientFSP client = new ClientFSP(server.getN());

        BigInteger x;
        BigInteger y;

        //System.out.println("N = " + server.getN());
        //System.out.println("S = " + client.getS());
        System.out.println("V = " + client.getV());


        for(int i = 0; i < server.getNumIteration(); i++)
        {
            x = client.calculateX();
            int c = server.generateBit();
            y = client.calculateY(c);
            System.out.println(server.verify(x, y, client.getV()));
        }
    }
}
