import java.io.IOException;//Παναγιώτης Μητρόπουλος/Panagiotis Mitropoulos 3130133
public class Reversi {
	public static void main(String[] args) throws IOException {
		char player, pc;
		char first, second;
		int turn=0;
		while(true) {
			System.out.println("Select your player name");
			player = (char) System.in.read();
			if((player=='X')||(player=='O')) break;
		}
		if(player=='X') {
			pc='O';
		} else {
			pc='X';
		}
		System.out.println("Player is " + player + ", who plays versus " + pc);
		if(player=='X') {/*
		* X=Black O=White
		*/
			first=player;
			second=pc;
		} else {
			first=pc;
			second=player;
		}
		System.out.println("X begins");
		char game[][]=new char[8][8];
		game[3][3]='O';
		game[3][4]='X';
		game[4][3]='X';
		game[4][4]='O';
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
		boolean end=false;
		int ending=0;
		while(end==false) {
			if(first==player) {
				rev r = new rev(player, turn, pc, game, ending, end);//Player's turn	
				minimax m = new minimax(pc, r.turn, player, r.game, r.ending, r.end);//PC's turn
				ending=0;
				r = new rev(player, m.turn, pc, m.game, m.ending, m.end);//Player's turn
				m = new minimax(pc, r.turn, player, r.game, r.ending, r.end);//PC's turn
				ending=0;
			} else {
				minimax m = new minimax(pc, turn, player, game, ending, end);
				rev r = new rev(player, m.turn, pc, m.game, m.ending, m.end);
				ending=0;
				m = new minimax(pc, r.turn, player, r.game, r.ending, r.end);
				r = new rev(player, m.turn, pc, m.game, m.ending, m.end);
				ending=0;
			}
		}
		int cx=0;
		int co=0;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(game[i][j]==first) cx=cx+1;
				if(game[i][j]==second) co=co+1;
			}
		}
		System.out.print("The game ended after " + turn + " turns.");
		System.out.println("The final result is:");
		System.out.println(game);
		char winner=' ';
		if(cx>co) winner='X';
		if(cx<co) winner='O';
		if(cx==co) winner='N';
		if(winner=='N') {
			System.out.println("Draw");
		} else {
			System.out.println(winner + " wins");
		}
	}
}