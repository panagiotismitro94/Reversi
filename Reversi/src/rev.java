import java.io.IOException;
import java.util.Scanner;
public class rev {
	public char player;
	public int turn;
	public char pc;
	public char[][] game;
	public int ending;
	public boolean end;/*
	*Defining the constructor
	*/
	rev(char p, int t, char r, char[][] g, int en, boolean e) throws IOException {
		player=p;
		turn=t;
		pc=r;
		game=g;
		ending=en;
		end=e;/*
		*Initializing the constructor
		*/
		int c=0;/*
		* Invalid moves in this turn
		*/
		int move=0;//Has the current player played?
		while(true) {
			System.out.println("Where do you want to play?");
			@SuppressWarnings("resource")
			Scanner ar=new Scanner(System.in);
			@SuppressWarnings("resource")
			Scanner br=new Scanner(System.in);
			int a=ar.nextInt();
			int b=br.nextInt();				
			System.out.println("First input is:" + a);
	        System.out.println("Second input is:" + b);
			/*
			* Where is the player going to add a pawn?
			*/
			if(((g[a][b]!='X')&&(g[a][b]!='O'))) {/*
			* Is the player's intended move valid?
			*/
				for(int i=1; i<a; i++) {
					for(int j=1; j<b; j++) {
						if(move==0) {
							if(((g[a-i][b]==r)&&(g[a-(i+1)][b]==p))||
							((g[a][b-j]==r)&&(g[a][b-(j+1)]==p))||
							((a+i+1<8)&&(g[a+i][b]==r)&&(g[a+i+1][b]==p))||
							((b+j+1<8)&&(g[a][b+j]==r)&&(g[a][b+j+1]==p))||
							((i==j)&&(a-(i+1)>-1)&&(b-(j+1)>-1)&&(g[a-i][b-j]==r)&&(g[a-(i+1)][b-(j+1)]==p))||
							((i==j)&&(a-(i+1)>-1)&&(b+j+1<8)&&(g[a-i][b+j]==r)&&(g[a-(i+1)][b+j+1]==(p)))||
							((i==j)&&(a+i+1<8)&&(b+j+1<8)&&(g[a+i][b+j]==r)&&(g[a+i+1][b+j+1]==p))||
							((i==j)&&(a+i+1<8)&&(b-(j+1)>-1)&&(g[a+i][b-j]==r)&&(g[a+i+1][b-(j+1)]==p))) {
								if((g[a-i][b]==r)&&(g[a-(i+1)][b]==p)) {//Check all directions
									for(int k=1; k<i+1; k++) {
										if(g[a-k][b]==r) g[a-k][b]=p;
										g[a][b]=p;
									}
								}
								if((g[a][b-j]==r)&&(g[a][b-(j+1)]==p)) {
									for(int q=1; q<j+1; q++) {
										if(g[a][b-q]==r) g[a][b-q]=p;
										g[a][b]=p;
									}
								}
								if((a+i+1<8)&&(g[a+i][b]==r)&&(g[a+i+1][b]==p)) {
									for(int k=a+1; k<8; k++) {
										if(g[k][b]==r) g[k][b]=p;
										g[a][b]=p;
									}
								}
								if((b+j+1<8)&&(g[a][b+j]==r)&&(g[a][b+j+1]==p)) {
									for(int q=b+1; q<8; q++) {
										if(g[a][q]==r) g[a][q]=p;
										g[a][b]=p;
									}
								}
								if((i==j)&&(a-(i+1)>-1)&&(b-(j+1)>-1)&&(g[a-i][b-j]==r)&&(g[a-(i+1)][b-(j+1)]==p)) {
									for(int z=1; ((a-z>-1)&&(b-z>-1)); z++) {
										if(g[a-z][b-z]==r) g[a-z][b-z]=p;
										g[a][b]=p;
									}
								}
								if((i==j)&&(a-(i+1)>-1)&&(b+j+1<8)&&(g[a-i][b+j]==r)&&(g[a-(i+1)][b+j+1]==p)) {
									for(int z=1; ((a-z>-1)&&(b+z<8)); z++) {
										if(g[a-z][b+z]==r) g[a-z][b+z]=p;
										g[a][b]=p;
									}
								}
								if((i==j)&&(a+i+1<8)&&(b+j+1<8)&&(g[a+i][b+j]==r)&&(g[a+i+1][b+j+1]==p)) {
									for(int z=1; ((a+z<8)&&(b+z<8)); z++) {
										if(g[a+z][b+z]==r) g[a+z][b+z]=p;
										g[a][b]=p;
									}
								}
								if((i==j)&&(a+i+1<8)&&(b-(j+1)>-1)&&(g[a+i][b-j]==r)&&(g[a+i+1][b-(j+1)]==p)) {
									for(int z=1; ((a+z<8)&&(b-z>-1)); z++) {
										if(g[a+z][b-z]==r) g[a+z][b-z]=p;
										g[a][b]=p;
									}
								}
								t=t+1;
								move=move+1;
							} else if(c==5) {
								System.out.println("Pass by " + p);
								en=en+1;//Pass. If both players have passed, the game ends
								t=t+1;//Number of turns played
							} else {
								System.out.println("Invalid move by" + p);
								c=c+1;
							}
						}
					}
				}
			}
			if((move==1)||(c==5)) break;
		}
		if(en==2) e=true;
		System.out.println("Current game status:");/*
		* Shows the gameboard
		*/
		for(int i=0; i<8; i++) {
			System.out.print("|");
			for(int j=0; j<8; j++) {
				System.out.print(game[i][j]);
				if(j==7) {
					System.out.println("|");
				} else {
					System.out.print("|");
				}
			}
		}
		return;
	}
}