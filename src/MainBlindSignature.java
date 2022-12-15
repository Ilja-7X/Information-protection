import java.math.BigInteger;

import algorithms.blind_signature.Bulletin;
import algorithms.blind_signature.Client;
import algorithms.blind_signature.Commission;
import algorithms.blind_signature.Server;


public class MainBlindSignature {
    public static void main(String[] args) {
            BigInteger hash;
            long ClientSignature;
            Bulletin bulletin = Commission.askQuestion("Is the earth round?");

            for(int i = 0; i < 4; i++) {
                    Client client = new Client(bulletin);
                    Server server = new Server();

                    System.out.println("Answer = " + client.answerQuestion());
                    client.HashOfAnswer(server.getN());

                    client.generateBlindingFactor(server.getN());
                    System.out.println("r = " + client.getR() + " m = " + client.getM() + " c = " + server.getC() + " d = " + server.getD() + " n = " + server.getN());

                    BigInteger t = client.applyMultiplier(server.getC(), server.getN());
                    System.out.println("t = " + t);
                    BigInteger t2 = server.getSign(t);
                    System.out.println("t` = " + t2);
                    BigInteger num = client.extractSignature(t2, server.getN());
                    System.out.println("num = " + num);
                    System.out.println("HashName = " + client.getHashName());
                    System.out.println(server.checkRepeatability(client.getHashName(), num) ? "Вы уже голосовали!" : "Ваш ответ принят!");
            }
    }
}
