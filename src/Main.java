public class Main
{

	private static boolean isPrime(long n)
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
		Ulam u = new Ulam();
		Sequence sg = new Sequence();
		GOLGen gg = new GOLGen();
		
//		int n = 9;
//		int sequenceLength = (int)(Math.pow(4,n));
//		int[][] trav = h.hilbert2n(n);
		int n = 500;
		int sequenceLength = (int)Math.pow(n<<1,2);
		int[][] trav = u.getSpiral(n);
		
		double epsilon = 2.5;//note that we use X^(1/epsilon) as our limit for checking
		long[] S = sg.nPseudoPrimeXEpsDet(sequenceLength,epsilon);
		gg.GOLGolly(trav,"ulam" + n + "PseudoPrimeEps" + epsilon + ".rle",S);
	}
}
