
import java.io.PrintWriter;
import java.io.File;
public class Hilbert
{
	public Hilbert()
	{
	
	}

	
	private String getTurtleHilbert(int order)
	{
		String A = "LBFRAFARFBL";
		String B = "RAFLBFBLFAR";
		String turtle = A;
		
		for(int o = 1; o < order; o++)
		{
			StringBuilder newTurtle = new StringBuilder();
			for(int i = 0; i < turtle.length(); i++)
			{
				if(turtle.charAt(i) == 'A')
				{
					//sub a
					newTurtle.append(A);
				}
				else if(turtle.charAt(i) == 'B')
				{
					//sub b
					newTurtle.append(B);
				}
				else
				{
					//no sub
					newTurtle.append(turtle.charAt(i));
				}
			}
			turtle = newTurtle.toString();
		}
		turtle = turtle.replace("A","");
		turtle = turtle.replace("B","");
		return turtle;
	}
	
	int[][] hilbert2n(int n)
	{
		if(n > 15)
		{
			throw new NumberFormatException("Hilbert curve dimension too big. hilbert2n creates enough curve for a 2^n x 2^n image.");
		}
		int dim = 1 << n;
		int c[][] = new int[(int)Math.pow(dim,2)][2];
		c[0][0] = 0;
		c[0][1] = 0;
		String turtle = getTurtleHilbert(n);
		int direction = 0;//down
		int idx = 1;
		
		for(int i = 0; i < turtle.length(); i++)
		{
			switch (turtle.charAt(i))
			{
				case 'F':
					switch(direction)
					{
						case 0:
							//down
							c[idx][0] = c[idx - 1][0];
							c[idx][1] = c[idx - 1][1] + 1;
							break;
						case 1:
							//left
							c[idx][0] = c[idx - 1][0] - 1;
							c[idx][1] = c[idx - 1][1];
							break;
						case 2:
							//up
							c[idx][0] = c[idx - 1][0];
							c[idx][1] = c[idx - 1][1] - 1;
							break;
						case 3:
							//right
							c[idx][0] = c[idx - 1][0] + 1;
							c[idx][1] = c[idx - 1][1];
					}
					idx++;
					break;
				case 'R':
					direction = (direction + 1) % 4;
					break;
				case 'L':
					direction = (direction - 1) % 4;
					if(direction < 0)
						direction = 4 + direction;
			}
		}
		
		return c;
	}
	
	
}
