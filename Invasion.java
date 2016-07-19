import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
public class Invasion
{
	public static void main(String[] args)
	{
		if(args.length != 1){System.exit(0);}
		int[][] map = new int[0][0];
		try{map = readMap(args[0]);}catch(IOException e){e.printStackTrace();}
		//map = initEdge(map);
		int[] max = bestSquare(map);
		int score = map[max[0]][max[1]];
		score = score * score;
		System.out.println(printFinal(map, max));
		System.out.println("Score of: " + score);
	}

	public static int[][] readMap(String file) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		int[][] map;
		String line = br.readLine();
		int n = Integer.parseInt(line);
		map = new int[n][n];
		line = br.readLine();
		char[] row;
		for(int i = 0; i < n; i++)
		{
			row = line.toCharArray();
			for(int j = 0; j < n; j++)
			{
				if(row[j] == '-')
				{
					map[i][j] = 1;
				}
				else
				{
					map[i][j] = 0;
				}
			}
			line = br.readLine();
		}
		return map;
	}
	public static int[] bestSquare(int[][] map)
	{
		//the biggest square that can fit in space map[x][y] is:
		// 1+min(map[x][y+1], map[x+1][y], map[x+1][y+1])
		//Fills map with largest squares, returns best fit
		int[] best = new int[3];
		int n = map.length;
		for(int i = n-2; i >= 0; i--)
		{
			for(int j = n-2; j >= 0; j--)
			{
				if(map[i][j] == 1){map[i][j] = 1+minAdj(map,i,j);}
				if(map[i][j] >= map[best[0]][best[1]]){best[0] = i; best[1] = j;}	
			}	
		}
		return best;
	}

	public static int minAdj(int[][] map, int i, int j)
	{
		return Math.min(Math.min(map[i][j+1],map[i+1][j]),map[i+1][j+1]);
	}

	public static String printMap(int[][] map)
	{
		String str = "";
		int n = map.length;
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				str=str+map[i][j];
				//System.out.print(map[i][j]);
			}	
			str=str+"\n";
			//System.out.print("\n");
		}
		return str;
	}
	public static String printFinal(int[][] map, int[] coord)
	{
		String str = "";
		int n = map.length;
		int score = map[coord[0]][coord[1]];
		for(int i = coord[0]; i < coord[0]+score; i++)
		{
			for(int j = coord[1]; j < coord[1]+score; j++)
			{
				map[i][j]=-1;
			}	
		}
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(map[i][j] == 0){str = str+"X";}
				else if(map[i][j] == -1){str = str+"O";}
				else{str=str+'-';}
				//System.out.print(map[i][j]);
			}	
			str=str+"\n";
			//System.out.print("\n");
		}
		return str;
	}
}