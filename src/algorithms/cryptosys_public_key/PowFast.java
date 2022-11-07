package algorithms.cryptosys_public_key;

public class PowFast {
    public static long calculate( long a, long x, long p ) {
        long y = 1;
        if(x < 0) {
            System.out.println("PowFast: Negative numbers X");
            return -2;
        }else if (p <= 0) {
            System.out.println("PowFast: Negative numbers P");
            return -2;
        }
        else {
            while (x != 0) {
                if ((x & 1) == 1) {
                    y = (y * a) % p;
                }
                a = (a * a) % p;
                x >>= 1;
            }
            return y;
        }
    }
}
