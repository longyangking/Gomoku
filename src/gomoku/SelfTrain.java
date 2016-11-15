package gomoku;

/**
 * The Class of Self-playing
 * @author LongYang
 *
 */
public class SelfTrain {
	public SelfTrain(){
		// TODO Actually the virtual competitor for computer will be itself
		Computer computer = new Computer();
		Computer selfplayer = new Computer();
		
	}
	public void train(){
		// TODO Train by self-playing and random-playing in the same time
	}
	/**
	 * A virtual player who play chess randomly to make mutation and evolution happen
	 * @author LongYang
	 */
	class RandomPlayer {
		public RandomPlayer(){
			
		}
		/**
		 * Play Chess
		 */
		public int[] PlayChess(int[][] chessboard){
			int x = 0, y = 0, value = 0, maxvalue = 0;
			// TODO The action will be done by the trained AI
			for (int i = 0; i < Config.ChessBoardWidth; i++){
				for (int j = 0; j < Config.ChessBoardHeight; j++){
					if (chessboard[i][j] == Config.NoChess){
						value = this.Value(i, j, chessboard);
						if (value >= maxvalue){
							maxvalue = value;
							x = i; y = j;
						}
					}
				}
			}
			return new int[]{x,y};
		}
		
		/**
		 * A simple value network
		 */
		private int Value(int i, int j, int[][] chessboard){
			int point = 0;
			if ((i-4 >= 0) && (j-4 >= 0)){
				point += 4*chessboard[i-1][j-1] + 3*chessboard[i-2][j-2] + 2*chessboard[i-3][j-3] + chessboard[i-4][j-4];
			}
			if (j-4 >= 0){
				point += 4*chessboard[i][j-1] + 3*chessboard[i][j-2] + 2*chessboard[i][j-3] + chessboard[i][j-4];
			}
			if ((i+4 < Config.ChessBoardWidth) && (j-4 >= 0)){
				point += 4*chessboard[i+1][j-1] + 3*chessboard[i+2][j-2] + 2*chessboard[i+3][j-3] + chessboard[i+4][j-4];
			}
			if (i+4 < Config.ChessBoardWidth){
				point += 4*chessboard[i+1][j] + 3*chessboard[i+2][j] + 2*chessboard[i+3][j] + chessboard[i+4][j];
			}
			if ((i+4 < Config.ChessBoardWidth) && (j+4 < Config.ChessBoardHeight)){
				point += 4*chessboard[i+1][j+1] + 3*chessboard[i+2][j+2] + 2*chessboard[i+3][j+3] + chessboard[i+4][j+4];
			}
			if (j+4 < Config.ChessBoardHeight){
				point += 4*chessboard[i][j+1] + 3*chessboard[i][j+2] + 2*chessboard[i][j+3] + chessboard[i][j+4];
			}
			if ((i-4 >= 0) && (j+4 < Config.ChessBoardHeight)){
				point += 4*chessboard[i-1][j+1] + 3*chessboard[i-2][j+2] + 2*chessboard[i-3][j+3] + chessboard[i-4][j+4];
			}
			if (i-4 >= 0) {
				point += 4*chessboard[i-1][j] + 3*chessboard[i-2][j] + 2*chessboard[i-3][j] + chessboard[i-4][j];
			}
			return point;
		}
	}
}
