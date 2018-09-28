public class Ulam
{
	public Ulam()
	{
	}
	
	int[][] getSpiral(int a)
	{
		int n = (int)Math.pow((a << 1) + 1,2);
		int sp[][] = new int[n][2];
		int start = (int)(Math.sqrt(n) / 2);
		sp[0][0] = start;
		sp[0][1] = start;
		int dir = 0;//0 is right, 1 is up, 2 is left, 3 is down
		int step = 1, stDist = 1;
		for(int i = 1; i < n; i++)
		{
			switch(dir)
			{
				case 0:
					//right
					sp[i][0] = sp[i-1][0] + 1;
					sp[i][1] = sp[i-1][1];
					break;
				case 1:
					//up
					sp[i][0] = sp[i-1][0];
					sp[i][1] = sp[i-1][1] - 1;
					break;
				case 2:
					//left
					sp[i][0] = sp[i-1][0] - 1;
					sp[i][1] = sp[i-1][1];
					break;
				case 3:
					sp[i][0] = sp[i-1][0];
					sp[i][1] = sp[i-1][1] + 1;
					//down
			}
			if(dir == 0 || dir == 2)
			{
				if(stDist == step)
				{
					dir = (dir + 1) % 4;
					stDist = 1;
				}
				else
					stDist++;
			}
			else
			{
				if(stDist == step)
				{
					dir = (dir + 1) % 4;
					step++;
					stDist = 1;
				}
				else
					stDist++;
			}
		}
		return sp;
	}
}
