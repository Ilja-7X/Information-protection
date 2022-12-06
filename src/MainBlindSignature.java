import java.io.IOException;
import java.math.BigInteger;

import algorithms.blind_signature.Bulletin;
import algorithms.blind_signature.Client;
import algorithms.blind_signature.Commission;
import algorithms.blind_signature.Server;


public class MainBlindSignature {
    public static void main(String[] args) {
        try{
            BigInteger hash;
            long ClientSignature;
            Bulletin bulletin = Commission.askQuestion("Is the earth round?");

            Client Valera = new Client(bulletin);
            System.out.println("Answer = "+Valera.answerQuestion());
            hash = Valera.HashOfAnswer();
            ClientSignature = Valera.getSignBulletin(hash);
            System.out.println("Client signature = "+ClientSignature);

            Server server = new Server();
            System.out.println(server.checkSignature(hash, ClientSignature, Valera.getD(), Valera.getN()));
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
