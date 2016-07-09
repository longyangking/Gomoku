package gomoku;
public class Player {
	int Score;
	public Player(){
		this.Score = 0;
	}
	public int[] PlayChess(){
		int x = 0, y = 0;
		//ToDo: Will receive the data from UI
		return new int[]{x, y};
	}
}
