public class Sequence
{
	public Sequence(){}
	
	
	boolean isPrime(long n)
	{
		long lim = (long)Math.sqrt(n) + 1;
		for(long i = 2; i < lim; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	boolean isPseudoPrime(long n, double epsilon)
	{
		long lim = (long)Math.pow(n,1/epsilon) + 1;
		for(long i = 2; i < lim; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	long[] nPrimes(int n)
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
	
	long[] nPseudoPrimeGrowthRand(int n)
	{
		long r[] = new long[n];
		//how many pprimes do I have so far?
		//if it's less than n / ln n, generate a new random number < n, add that to the seq
		r[0] = 0;
		r[1] = 1;
		r[2] = 2;
		int numInSeq = 3;
		long i = 2;
		while(true)
		{
			long tv = (long)Math.round(i / Math.log(i));
			if(numInSeq < tv)
			{
				// generate a new pprime < i
				//prev pprime is r[nis-1]
				long upper = (i - 1) >> 1;
				long lower = ((r[numInSeq-1]-1)>>1) + 1;
				long np = (long)(Math.random() * (upper - lower)) + lower;
				r[numInSeq] = (np << 1) + 1;
				numInSeq++;
				if(numInSeq == n)
					break;
			}
			i++;
		}
		return r;
	}
	
	long[] nPseudoPrimeXEpsDet(int n, double epsilon)
	{
		long r[] = new long[n];
		int idx = 1;
		r[0] = 2;
		for(long c = 3; idx < n; c += 2)
		{
			if(isPseudoPrime(c,epsilon))
				r[idx++] = c;
		}
		return r;
	}
	
	long[] nSquares(int n)
	{
		long[] s = new long[n];
		for(int i = 0; i < n; i++)
		{
			s[i] = (long)Math.pow((long)i,2);
		}
		return s;
	}
	
	long[] nRecaman(int n)
	{
		long r[] = new long[n];
		long seen[] = new long[n];
		//0th bit of seen[0] is i=0
		//way longer than it needs to be but that makes things easier. also asymptotically equiv
		for(int i = 0; i < n; i++)
		{
			r[i] = -1;
		}
		r[0] = 0;
		seen[0] = 1;
		
		for(int i = 1; i < n; i++)
		{
			r[i] = r[i-1] - i;
			if(r[i] < 0)
			{
				r[i] = r[i-1] + i;
				long bit = r[i] & 0x3F;
				int pos = (int)(r[i] >> 6);
				seen[pos] |= 1 << bit;
			}
			else
			{
				long bit = r[i] & 0x3F;//n % 64
				int pos = (int)(r[i] >> 6);//n / 64
				if(((seen[(int)pos] >> bit) & 1) != 0)
				{
					r[i] = r[i-1] + i;
					bit = r[i] & 0x3F;
					pos = (int)(r[i] >> 6);
					seen[pos] |= 1 << bit;
				}
				else
				{
					seen[pos] |= 1 << bit;
				}
			}
		}
		return r;
	}
	
	/*
	nkNat(n,3,1) = 1,1,1,2,2,2,3,3,3...
	 */
	long[] nkNat(int n, int k, int start)
	{
		long s[] = new long[n];
		int current = start;
		for(int i = 0; i < n; i+=k)
		{
			for(int j = 0; j < k && (j + i) < n; j++)
			{
				s[i+j] = current;
			}
			current++;
		}
		return s;
	}
	
	/*
	returns n of every kth natural number, starting at start
	i.e. {start,start+k,start+2k,...,start+k(n-1)}
	
	nNat(n,1,1) = natural numbers (1-indexed)
	nNat(n,1,0) = natural numbers (0-indexed)
	nNat(
	 */
	long[] nNaturals(int n, int k, int start)
	{
		long[] s = new long[n];
		long current = start;
		for(int i = 0; i < n; i++)
		{
			s[i] = current;
			current += k;
		}
		return s;
	}
	
	long[] nNaturals(int n)
	{
		return nNaturals(n,1,0);
	}
	
	long[] triangleTNK(int n)
	{
		long s[] = new long[n];
		int countTo = 1;
		int i = 0;
		while(i < n)
		{
			for(int j = 1; j <= countTo && i < n; j++)
			{
				s[i++] = j;
			}
			countTo++;
		}
		return s;
	}
}
