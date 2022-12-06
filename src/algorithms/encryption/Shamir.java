package algorithms.encryption;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import algorithms.cryptosys_public_key.*;
import functionalfiles.FileBytes;

import java.util.List;
import java.util.ArrayList;


public class Shamir {
    private static long P_;
    private long C_;
    private long D_;

    public static long generatePublicP() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            sr.setSeed(System.currentTimeMillis());

            do {
                P_ = sr.nextInt(1000000000 - 2) + 1;
            } while (!Prime.isPrime(P_));
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Exception thrown : " + e);
        }
        catch (NoSuchProviderException e)
        {
            System.out.println("Exception thrown : " + e);
        }
        //System.out.println("Random Integer value : " + P);
        return P_;
    }

    public Shamir(long P) {
        P_ = P;
        SecureRandom sr = new SecureRandom();
        sr.setSeed(System.currentTimeMillis());
        long[] EuclidResult;
        do {
            do {
                C_ = sr.nextInt(1000000000 - 2) + 1;
                EuclidResult = Euclid.calculate(C_,P-1);
            } while(EuclidResult[0] != 1);
            D_ = EuclidResult[2] + (P-1);
        } while(C_*D_%(P-1) != 1 );
        System.out.println("Random P: " + P_);
        System.out.println("Random C: " + C_);
        System.out.println("Random D: " + D_);
    }

    public long getC() {
        return C_;
    }

    public long getD() {
        return D_;
    }

    public static long getP() {
        return P_;
    }

    public static byte[] getBytesFromFile(String path)
    {
        byte[] buffer = null;
        try(FileInputStream fin=new FileInputStream(path))
        {
            buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return buffer;
    }

    public static void getFileFromBytes(String path, byte[] buffer)
    {
        try(FileOutputStream fos=new FileOutputStream(path))
        {
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void ShamirCalcIteration(String path, String newPath, int numOfIteration) throws IOException {
        byte[] arrayBytes;
        byte[] buffer;
        List<Long> E;

        switch (numOfIteration) {
            case 1:
                byte[] byteArray = getBytesFromFile(path);
                List<Long> x1 = new ArrayList<>();

                for (byte a : byteArray) {
                    //System.out.print(a + " ");
                    x1.add(PowFast.calculate(a, getC(), getP()));
                }
                arrayBytes = FileBytes.longToBytes(x1);
                FileBytes.getFileFromBytes(newPath, arrayBytes);
            case 2:
                buffer = FileBytes.getBytesFromFile(path);
                E = FileBytes.bytesToLong(buffer);
                List<Long> x2 = new ArrayList<>();
                for (Long a : E) {
                    x2.add(PowFast.calculate(a, getC(), getP()));
                }
                arrayBytes = FileBytes.longToBytes(x2);
                FileBytes.getFileFromBytes(newPath, arrayBytes);
            case 3:
                buffer = FileBytes.getBytesFromFile(path);
                E = FileBytes.bytesToLong(buffer);
                List<Long> x3 = new ArrayList<>();
                for (Long a : E) {
                    x3.add(PowFast.calculate(a, getD(), getP()));
                }
                arrayBytes = FileBytes.longToBytes(x3);
                FileBytes.getFileFromBytes(newPath, arrayBytes);
            case 4:
                buffer = FileBytes.getBytesFromFile(path);
                E = FileBytes.bytesToLong(buffer);
                byte[] outFile = new byte[E.size()];
                for (int i = 0; i < E.size(); i++) {
                    outFile[i] = (byte) PowFast.calculate(E.get(i), getD(), getP());
                }
                getFileFromBytes(newPath, outFile);
        }
    }
}
