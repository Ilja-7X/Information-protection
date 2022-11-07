package algorithms.cryptosys_public_key;

public class Prime {
    public static boolean isPrime(long n)
    {
        for(int i = 2; i <=n/2; i++)
        {
            if(n % i == 0)
                return false;
        }
        return true;
    }

    /*public static boolean testFerma(long p, int k)
    {
        if(p==2)
            return true;
        if(p&1)
            return false;
    }*/

}
