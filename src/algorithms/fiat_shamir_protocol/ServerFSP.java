package algorithms.fiat_shamir_protocol;

import algorithms.encryption.RSA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.BiFunction;

public class ServerFSP {
    private BigInteger N;
    private int NumIteration = 10;
    private int C;
    private BigInteger X;
    private BigInteger Y;

    private BigInteger V_client;
    Map<String, BigInteger> Database;

    public ServerFSP() {
        //RSA rsa = new RSA();
        //N = new BigInteger(String.valueOf(rsa.getN()));
        N = BigInteger.valueOf(23002229);
        System.out.println("N = " + N);
        Database = byBufferedReader("src/algorithms/fiat_shamir_protocol/DataBase.txt");
        printLoginPassword();
    }

    public static void test(){
        String str = new String("235445");
        System.out.println("Flag: " + str.matches("\\d+"));
        //BigInteger check = new BigInteger(" 123456789");
        //System.out.println("Test = " + check);
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

    public void printLoginPassword()
    {
        for (Map.Entry<String, BigInteger> entry : Database.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    public boolean authenticate(String login){
        if(Database.containsKey(login))
        {
            V_client  = Database.get(login);
            return true;
        }
        return false;
    }


    public int generateBit()
    {
        return C = (int) (Math.random() * 2);
    }

    public boolean verify(BigInteger x, BigInteger y)
    {
        X = x;
        Y = y;
        return Y.modPow(BigInteger.valueOf(2), N).compareTo(X.multiply(V_client.pow(C)).mod(N)) == 0;

    }

    public int getNumIteration() {
        return NumIteration;
    }
    public BigInteger getN()
    {
        return N;
    }
}
