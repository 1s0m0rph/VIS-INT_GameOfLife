import java.io.File;
import java.io.PrintWriter;

public class GOLGen
{
	public GOLGen(){}
	
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
	
	private String replaceStringIndex(String a, int idx, char with)
	{
		return a.substring(0,idx) + with + a.substring(idx+1);
	}
	
	void GOL(int[][] traversal, String fileName)
	{
		//n is dimension
		int lim = traversal.length;
		int dim = (int)Math.sqrt(traversal.length);
		String[] rep = new String[dim];
		for(int i = 0; i < dim; i++)
		{
			StringBuilder temp = new StringBuilder();
			for(int j = 0; j < dim; j++)
			{
				temp.append("-");
			}
			rep[i] = temp.toString();
		}
		
		for(int d = 0; d < lim; d++)
		{
			if(isPrime(d) && d != 1 && d != 0)
			{
				rep[traversal[d][0]] = replaceStringIndex(rep[traversal[d][0]],traversal[d][1],'o');
			}
		}
		
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(new File(fileName));
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			System.exit(1);
		}
		
		for(String s : rep)
		{
			pw.println(s);
		}
		pw.close();
	}
	
	private String compressString(String s)
	{
		int currentCharCount = 1;
		char currentChar = s.charAt(0);
		StringBuilder ret = new StringBuilder();
		for(int i = 1; i < s.length(); i++)
		{
			if(s.charAt(i) != currentChar)
			{
				if(currentCharCount != 1)
				{
					ret.append(currentCharCount).append(Character.toString(currentChar));
				}
				else
				{
					ret.append(currentChar);
				}
				currentChar = s.charAt(i);
				currentCharCount = 1;
			}
			else
			{
				currentCharCount++;
			}
		}
		if(currentCharCount != 1)
		{
			ret.append(currentCharCount).append(Character.toString(currentChar));
		}
		else
		{
			ret.append(currentChar);
		}
		return ret.toString();
	}
	
	void GOLGolly(int[][] traversal, String fileName, int bias)
	{
		//n is dimension
		int lim = traversal.length;
		int dim = (int)Math.sqrt(traversal.length);
		String[] rep = new String[dim];
		for(int i = 0; i < dim; i++)
		{
			StringBuilder temp = new StringBuilder();
			for(int j = 0; j < dim; j++)
			{
				temp.append("b");
			}
			rep[i] = temp.toString();
		}
		
		for(int d = 0; d < lim; d++)
		{
			if(isPrime(d+bias) && (d + bias) != 0 && (d + bias) != 1)
			{
				rep[traversal[d][1]] = replaceStringIndex(rep[traversal[d][1]],traversal[d][0],'o');
			}
		}
		
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(new File(fileName));
		}
		catch(Exception e)
		{
			System.out.println("File not found");
			System.exit(1);
		}
		
		
		pw.println("#CXRLE Pos=0,0\n" +
				"x = 2, y = 2, rule = B3/S23");
		for(String s : rep)
		{
			pw.print(compressString(s) + "$");
//			pw.print(s + "$");
		}
		pw.print("!");
		pw.close();
	}
}
