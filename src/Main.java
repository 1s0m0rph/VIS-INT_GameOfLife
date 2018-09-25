public class Main
{

	static boolean isPrime(long n)
	{
		long lim = (long)Math.ceil(Math.sqrt(n));
		for(long c = 2; c <= lim; c++)
		{
			if(n % c == 0)
				return false;
		}
		return true;
	}
	
	static long[] nPrimes(int n)
	{
		long r[] = new long[n];
		int idx = 1;
		r[0] = 2;
		for(long c = 3; idx < n; c += 2)
		{
			if(isPrime(c))
				r[idx++] = c;
		}
		return r;
	}
	
	public static void main(String[] args)
	{
		Hilbert h = new Hilbert();
		h.GOL(8,"hilbertPrimes8.cells");
	}
}
