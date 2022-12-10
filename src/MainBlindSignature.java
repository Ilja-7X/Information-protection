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

            Client client = new Client(bulletin);
            Server server = new Server();

            //BigInteger N = server.getN();

            //client.generateBlindingFactor(N.longValue());
            client.generateBlindingFactor(server.getN().longValue());

            System.out.println("Answer = "+client.answerQuestion());
            hash = client.HashOfAnswer(server.getN());
            ClientSignature = server.getSignBulletin(hash);
            System.out.println("Client signature = "+ClientSignature);

            System.out.println(server.checkSignature(hash, ClientSignature));
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
