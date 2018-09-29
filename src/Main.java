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
//		Hilbert h = new Hilbert();
//		for(int i = 1; i < 10; i++)
//			h.GOLGolly(i,"hilbertPrimes" + i + ".rle");
		GOLGen gg = new GOLGen();
		Ulam u = new Ulam();
		Hilbert h = new Hilbert();
//		for(int[] c : trav)
//			System.out.println("(" + c[0] + "," + c[1] + ")");
		int bias = 42;
//		for(int a = 100; a <= 1200; a+=100)
//		{
//			System.out.println(a);
////			int[][] trav = u.getSpiral(a);
//			gg.GOLGolly(trav, "ulamprimes" + a + ".rle",);
//		}
		int[][] trav = h.hilbert2n(6);
		gg.GOLGolly(trav,"hilbert6bias42.rle",bias);
	}
}
