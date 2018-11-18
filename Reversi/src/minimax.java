public class minimax {
	public char pc;
	public int turn;
	public char player;
	public char[][] game;
	public int ending;
	public boolean end;/*
	*Defining the constructor
	*/
	minimax(char p, int t, char r, char[][] g, int en, boolean e) {
		pc=p;
		turn=t;
		player=r;
		game=g;
		ending=en;
		end=e;/*
		*Initializing the constructor
		*/
		int move=0;//Has the current player played?
		while(true) {
			int cs[][]=new int[64][4];/*
			 *	1st column: i 2nd column: j
			 *	3rd column: number of pawns changing colour(main evaluation)
			 *	4th column: multiplier(secondary evaluation)
			 *  game[i][j]=game[cs[i][0]][cs[i][1]]
			 */
			for(int i=0; i<64; i++) {
				cs[i][0]=i/8;
				cs[i][1]=i%8;
			}
			for(int i=0; i<64; i++) {
				int a=cs[i][0];//i
				int b=cs[i][1];//j
				cs[i][3]=1;//Multiplier
				if(((a==0)&&(b==0))||((a==0)&&(b==7))||((a==7)&&(b==0))||((a==7)&&(b==7))) {
					cs[i][3]=cs[i][3]+2;
				} else if((a==0)||(b==0)||(a==7)||(b==7)) {
					cs[i][3]=cs[i][3]+1;
				} else {
					cs[i][3]=cs[i][3]+0;
				}
			}
			int max=0;
			for(int i=0; i<64; i++) {
				cs[i][2]=0;//Evaluation
				int a=cs[i][0];
				int b=cs[i][1];
				if((g[a][b]!='X')&&(g[a][b]!='O')) {/*
				* g[a][b] contains neither X nor O
				*/
					for(int j=0; i<8; i++) {
						for(int k=0; i<8; i++) {
							if(move==0) {
								if(((g[a-j][b]==r)&&(g[a-(j+1)][b]==p))||
									((g[a][b-k]==r)&&(g[a][b-(k+1)]==p))||
									((a+j+1<8)&&(g[a+j][b]==r)&&(g[a+j+1][b]==p))||
									((b+k+1<8)&&(g[a][b+k]==r)&&(g[a][b+k+1]==p))||
									((j==k)&&(a-(j+1)>-1)&&(b-(k+1)>-1)&&(g[a-j][b-k]==r)&&(g[a-(j+1)][b-(k+1)]==p))||
									((k==j)&&(a-(j+1)>-1)&&(b+k+1<8)&&(g[a-j][b+k]==r)&&(g[a-(j+1)][b+k+1]==p))||
									((k==j)&&(a+j+1<8)&&(b+k+1<8)&&(g[a+j][b+k]==r)&&(g[a+j+1][b+k+1]==p))||
									((k==j)&&(a+j+1<8)&&(b-(k+1)>-1)&&(g[a+j][b-k]==r)&&(g[a+j+1][b-(k+1)]==p))) {
									if((g[a-j][b]==r)&&(g[a-(j+1)][b]==p)) {//Check all directions
										for(int w=1; w<j+1; w++) {
											if(g[a-w][b]==r) {
												cs[i][2]++;
												if(((a-w==0)&&(b==0))||((a-w==0)&&(b==7))) {
													cs[i][3]=cs[i][3]+2;
												} else if((a-w==0)||(b==0)||(b==7)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									if((g[a][b-k]==r)&&(g[a][b-(k+1)]==p)) {
										for(int w=1; w<k+1; w++) {
											if(g[a][b-w]==r) {
												cs[i][2]++;										
												if(((a==0)&&(b-w==0))||((a==7)&&(b-w==0))) {
													cs[i][3]=cs[i][3]+2;
												} else if((a==0)||(b-w==0)||(a==7)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									if((a+j+1<8)&&(g[a+j][b]==r)&&(g[a+j+1][b]==p)) {
										for(int w=a+1; w<8; w++) {
											if(g[w][b]==r) {
												cs[i][2]++;
												if(((w==7)&&(b==0))||((w==7)&&(b==7))) {
													cs[i][3]=cs[i][3]+2;
												} else if((b==0)||(b==7)||(w==7)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}		
									if((b+k+1<8)&&(g[a][b+k]==r)&&(g[a][b+k+1]==p)) {
										for(int w=b+1; w<8; w++) {
											if(g[a][w]==r) {
												cs[i][2]++;
												if((((a==0)&&(w==7))||((a==7)&&(w==7)))) {
													cs[i][3]=cs[i][3]+2;
												} else if((a==0)||(w==7)||(a==7)) {
												cs[i][3]=cs[i][3]+1;
												} else {
												cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									if((j==k)&&(a-(j+1)>-1)&&(b-(k+1)>-1)&&(g[a-j][b-k]==r)&&(g[a-(j+1)][b-(k+1)]==p)) {
										for(int w=1; ((a-w>-1)&&(b-w>-1)); w++) {
											if(g[a-w][b-w]==r) {
												cs[i][2]++;
												if(((a-w==0)&&(b-w==0))) {
													cs[i][3]=cs[i][3]+2;
												} else if((a-w==0)||(b-w==0)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									if((k==j)&&(a-(j+1)>-1)&&(b+k+1<8)&&(g[a-j][b+k]==r)&&(g[a-(j+1)][b+k+1]==p)) {
										for(int w=1; ((a-w>-1)&&(b+w<8)); w++) {
											if(g[a-w][b+w]==r) {
												cs[i][2]++;
												if((a-w==0)&&(b+w==7)) {
													cs[i][3]=cs[i][3]+2;
												} else if((a-w==0)||(b+w==7)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									if((k==j)&&(a+j+1<8)&&(b+k+1<8)&&(g[a+j][b+k]==r)&&(g[a+j+1][b+k+1]==p)) {
										for(int w=1; ((a+w<8)&&(b+w<8)); w++) {
											if(g[a+w][b+w]==r) {
												cs[i][2]++;
												if(((a+w==7)&&(b+w==7))) {
													cs[i][3]=cs[i][3]+2;
												} else if((b+w==7)||(a+w==7)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									if(((k==j)&&(a+j+1<8)&&(b-(k+1)>-1)&&(g[a+j][b-k]==r)&&(g[a+j+1][b-(k+1)]==p))) {
										for(int w=1; ((a+w<8)&&(b-w>-1)); w++) {
											if(g[a+w][b-w]==r) {
												cs[i][2]++;
												if(((a+w==7)&&(b-w==0))) {
													cs[i][3]=cs[i][3]+2;
												} else if((b-w==0)||(a+w==7)) {
													cs[i][3]=cs[i][3]+1;
												} else {
													cs[i][3]=cs[i][3]+0;
												}
											}
										}
									}
									cs[i][2]=cs[i][2]*cs[i][3];
								}
							}
						}	
					}
				}
			}				
			for(int i=0; i<64; i++) {
				if(cs[i][2]>max) max=cs[i][2];
			}
			for(int i=0; i<64; i++) {
				if((cs[i][2]==max)&&(move==0)) {
					int a=cs[i][0];
					int b=cs[i][1];
					for(int j=0; i<8; i++) {
						for(int k=0; i<8; i++) {
							if((g[a-j][b]==r)&&(g[a-(j+1)][b]==p)) {
								for(int w=1; w<j+1; w++) {
									if(g[a-w][b]==r) g[a-w][b]=p;
									g[a][b]=p;
								}
							}
							if((g[a][b-k]==r)&&(g[a][b-(k+1)]==p)) {
								for(int w=1; w<k+1; w++) {
									if(g[a][b-w]==r) g[a][b-w]=p;
									g[a][b]=p;
								}
							}
							if((a+j+1<8)&&(g[a+j][b]==r)&&(g[a+j+1][b]==p)) {
								for(int w=a+1; w<8; w++) {
									if(g[w][b]==r) g[w][b]=p;
									g[a][b]=p;
								}
							}
							if((b+k+1<8)&&(g[a][b+k]==r)&&(g[a][b+k+1]==p)) {
								for(int q=b+1; q<8; q++) {
									if(g[a][q]==r) g[a][q]=p;
									g[a][b]=p;
								}
							}
							if((k==j)&&(a-(j+1)>-1)&&(b-(k+1)>-1)&&(g[a-j][b-k]==r)&&(g[a-(j+1)][b-(k+1)]==p)) {
								for(int z=1; ((a-z>-1)&&(b-z>-1)); z++) {
									if(g[a-z][b-z]==r) g[a-z][b-z]=p;
									g[a][b]=p;
								}
							}
							if((k==j)&&(a-(j+1)>-1)&&(b+k+1<8)&&(g[a-j][b+k]==r)&&(g[a-(j+1)][b+k+1]==p)) {
								for(int z=1; ((a-z>-1)&&(b+z<8)); z++) {
									if(g[a-z][b+z]==r) g[a-z][b+z]=p;
									g[a][b]=p;
								}
							}
							if((k==j)&&(a+j+1<8)&&(b+k+1<8)&&(g[a+j][b+k]==r)&&(g[a+j+1][b+k+1]==p)) {
								for(int z=1; ((a+z<8)&&(b+z<8)); z++) {
									if(g[a+z][b+z]==r) g[a+z][b+z]=p;
									g[a][b]=p;
								}
							}
							if((k==j)&&(a+j+1<8)&&(b-(k+1)>-1)&&(g[a+i][b-j]==r)&&(g[a+j+1][b-(k+1)]==p)) {
								for(int z=1; ((a+z<8)&&(b-z>-1)); z++) {
									if(g[a+z][b-z]==r) g[a+z][b-z]=p;
									g[a][b]=p;
								}
							}
						}
					}
					move=move+1;
				}
			}		
			if(max==0) {
				System.out.println("Pass by " + p);
				en=en+1;//Pass. If both players have passed, the game ends
			}
			if(move==0) move=move+1;
			t=t+1;//Number of turns played
			if(move==1) break;
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