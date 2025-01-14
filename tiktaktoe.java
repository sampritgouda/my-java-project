package tiktaktoe;

import java.util.Random;
import java.util.Scanner;

class tiktak
{
	static char[][] board;
	public tiktak()
	{

		board=new char[3][3];
		initboard();
	}


	void initboard()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				board[i][j]=' ';
			}
		}
	}





	static void displayboard()
	{
		System.out.println("-------------");
		for(int i=0;i<3;i++)
		{
			System.out.print("| ");
			for(int j=0;j<3;j++)
			{
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}



	static boolean rowcheck()
	{
		for(int i=0;i<3;i++)
		{
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
			{
				return true;
			}
		}
		return false;
	}







	static boolean colcheck()
	{
		for(int i=0;i<3;i++)
		{
			if(board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]==board[2][i])
			{
				return true;
			}
		}
		return false;
	}




	static boolean diagcheck()
	{
		if((board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) || (board[0][2]!=' ' && board[1][1]==board[0][2] && board[1][1]==board[2][0]))
		{
			return true;
		}
		return false;
	}



	static void placemark(int row,int col,char mark)
	{
		board[row][col]=mark;
	}


	static boolean checkdraw()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
		return true;
	}

}





abstract class player
{
	String name;
	char mark;


	void giveinfo(String name,char mark)
	{
		this.name=name;
		this.mark=mark;
	}


	boolean isvalid(int row,int col)
	{
		if(row<=2 && row>=0 && col<=2 && col>=0)
		{
			if(tiktak.board[row][col]==' ')
			{
				return true;
			}
		}
		return false;
	}

	abstract void makemove();


}




class humanplayer extends player
{
	void makemove()
	{
		int row,col;
		Scanner scan=new Scanner(System.in);
		do {
			System.out.println("enter row and column");
			row=scan.nextInt();
			col=scan.nextInt();

			if(!isvalid(row,col))
			{
				System.out.println("invalid choice");
			}
		}while(!isvalid(row,col));
		tiktak.placemark(row, col, mark);

	}
}





class aiplayer extends player
{
	void makemove()
	{
		int row,col;
		Random r=new Random();
		do {
			System.out.println("enter row and column");
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isvalid(row,col));
		tiktak.placemark(row, col, mark);

	}
}

public class tiktaktoe {

	public static void main(String[] args) {
		char c,c1,c2;
		String s1,s2;
		Scanner scan=new Scanner(System.in);
		while(true)
		{
			tiktak t=new tiktak();
			System.out.println("enter 'y' to play with computer OR press any key to play with friend");
			c=scan.next().charAt(0);
			if(c=='y')
			{
				System.out.println("enter 1st player name");
				s1=scan.next();
				System.out.println("enter your mark other than 'X'");
				c1=scan.next().charAt(0);
				player p1=new humanplayer();
				player p2=new aiplayer();
				p1.giveinfo(s1, c1);
				p2.giveinfo("AI", 'X');
				player cp;
				cp=p1;
				while(true)
				{
					System.out.println(cp.name+"  turn");
					cp.makemove();
					tiktak.displayboard();

					if(tiktak.rowcheck() || tiktak.colcheck() || tiktak.diagcheck())
					{
						System.out.println(cp.name+"  won the match.");
						break;
					}
					else if(tiktak.checkdraw())
					{
						System.out.println("match drawn");
						break;
					}
					else
					{
						if(cp==p1)
						{
							cp=p2;
						}
						else
						{
							cp=p1;
						}
					}

				}



			}
			else
			{
				System.out.println("enter 1st player name");
				s1=scan.next();
				System.out.println("enter 1st player mark");
				c1=scan.next().charAt(0);
				System.out.println("enter 2nd player name");
				s2=scan.next();
				System.out.println("enter 2nd player mark");
				c2=scan.next().charAt(0);

				player p1=new humanplayer();
				player p2=new humanplayer();
				p1.giveinfo(s1, c1);
				p2.giveinfo(s2, c2);
				player cp;
				cp=p1;
				while(true)
				{
					System.out.println(cp.name+"  turn");
					cp.makemove();
					tiktak.displayboard();

					if(tiktak.rowcheck() || tiktak.colcheck() || tiktak.diagcheck())
					{
						System.out.println(cp.name+"  won the match.");
						break;
					}
					else if(tiktak.checkdraw())
					{
						System.out.println("match drawn");
						break;
					}
					else
					{
						if(cp==p1)
						{
							cp=p2;
						}
						else
						{
							cp=p1;
						}
					}

				}
			}
			System.out.println("press 'y'  do you want to exit from game  OR press any other key to continue ");
			c=scan.next().charAt(0);
			if(c=='y')
			{
				break;
			}
		}


	}

}
