package algorithms.cryptosys_public_key;

public class Euclid {
    public static long[] calculate(long a, long b) {
        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
        long[] U = new long[]{a, 1, 0};
        long[] V = new long[]{b, 0, 1};
        long[] T = new long[3];
        long q;
        while (V[0] != 0) {
            q = U[0]/V[0];
            T[0] = U[0] % V[0];
            T[1] = U[1] - q * V[1];
            T[2] = U[2] - q * V[2];
            for (int i = 0; i < 3; i++) {
                U[i] = V[i];
                V[i] = T[i];
            }
        }
        return U;
    }
}
