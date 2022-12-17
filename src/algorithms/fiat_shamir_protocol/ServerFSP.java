package algorithms.fiat_shamir_protocol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ServerFSP {
    private final BigInteger N;
    private int C;
    private int numIteration = 30;

    private BigInteger V_client;
    private Map<String, BigInteger> Database;

    public ServerFSP() {
        N = BigInteger.valueOf(23002229);
        Database = byBufferedReader("src/algorithms/fiat_shamir_protocol/DataBase.txt");
    }

    public static Map<String, BigInteger> byBufferedReader(String filePath) {
        HashMap<String, BigInteger> map = new HashMap<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(" ", 2);
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0];
                    String valueStr = keyValuePair[1];
                    if(valueStr.matches("\\d+")) {
                        map.putIfAbsent(key, new BigInteger(valueStr));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    //Проверка логина пользователя в базе данных
    public boolean authenticate(String login){
        if(Database.containsKey(login)) {
            V_client  = Database.get(login);
            return true;
        }
        return false;
    }

    //Генерация сервером числа в диапазоне [0;1]
    public int generateBit() {
        return C = (int) (Math.random() * 2);
    }

    //Проверка на равенство у^2 = x*v^e (%N)
    public boolean verify(BigInteger x, BigInteger y) {
        return y.modPow(BigInteger.valueOf(2), N).compareTo(x.multiply(V_client.pow(C)).mod(N)) == 0;
    }

    public int getNumIteration() {
        return numIteration;
    }
    public BigInteger getN() {
        return N;
    }
}
