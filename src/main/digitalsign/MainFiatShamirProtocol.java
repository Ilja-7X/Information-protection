package main.digitalsign;

import algorithms.fiat_shamir_protocol.ClientFSP;
import algorithms.fiat_shamir_protocol.ServerFSP;

import java.io.IOException;
import java.math.BigInteger;

public class MainFiatShamirProtocol {
    public static void main(String[] args) {
        try {
            ServerFSP server = new ServerFSP();
            ClientFSP client = new ClientFSP(server.getN());

            if(!server.authenticate(client.getLogin())) {
                throw new IOException("Authentication failed");
            }

            BigInteger x;
            BigInteger y;

            for (int i = 0; i < server.getNumIteration(); i++) {
                x = client.calculateX();
                y = client.calculateY(server.generateBit());
                if(!server.verify(x, y)) {
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
