package gomoku;
public class ChessBoard {
	int[][] chessboard;
	public ChessBoard(){
		chessboard = new int[Config.ChessBoardWidth][Config.ChessBoardWidth];
	}
	
	public ChessBoard(int[][] chessboard){
		this.chessboard = chessboard;
	}
	
	public void initChessBoard(){
		chessboard = new int[Config.ChessBoardWidth][Config.ChessBoardWidth];
	}
	
	public boolean playChess(int i, int j, int role){
		if (chessboard[i][j] == Config.NoChess){
			if (role == Config.Computer){
				chessboard[i][j] = Config.PutByComputer;
				return true;
			}
			if (role == Config.Player){
				chessboard[i][j] = Config.PutByPlayer;
				return true;
			}
		}
		return false;
	}
	
	public int[][] ChessBoardInfo(){
		return chessboard;
	}
	
	public int VictoryJudge(){
		int point = 0;
		for (int i = 0; i < Config.ChessBoardWidth; i++){
			for (int j = 0; j < Config.ChessBoardHeight; j++){
				if ((i-4 >= 0) && (j-4 >= 0)){
					point = chessboard[i][j] + chessboard[i-1][j-1] + chessboard[i-2][j-2] + chessboard[i-3][j-3] + chessboard[i-4][j-4];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if (j-4 >= 0){
					point = chessboard[i][j] + chessboard[i][j-1] + chessboard[i][j-2] + chessboard[i][j-3] + chessboard[i][j-4];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if ((i+4 < Config.ChessBoardWidth) && (j-4 >= 0)){
					point = chessboard[i][j] + chessboard[i+1][j-1] + chessboard[i+2][j-2] + chessboard[i+3][j-3] + chessboard[i+4][j-4];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if (i+4 < Config.ChessBoardWidth){
					point = chessboard[i][j] + chessboard[i+1][j] + chessboard[i+2][j] + chessboard[i+3][j] + chessboard[i+4][j];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if ((i+4 < Config.ChessBoardWidth) && (j+4 < Config.ChessBoardHeight)){
					point = chessboard[i][j] + chessboard[i+1][j+1] + chessboard[i+2][j+2] + chessboard[i+3][j+3] + chessboard[i+4][j+4];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if (j+4 < Config.ChessBoardHeight){
					point = chessboard[i][j] + chessboard[i][j+1] + chessboard[i][j+2] + chessboard[i][j+3] + chessboard[i][j+4];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if ((i-4 >= 0) && (j+4 < Config.ChessBoardHeight)){
					point = chessboard[i][j] + chessboard[i-1][j+1] + chessboard[i-2][j+2] + chessboard[i-3][j+3] + chessboard[i-4][j+4];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
				if (i-4 >= 0) {
					point = chessboard[i][j] + chessboard[i-1][j] + chessboard[i-2][j] + chessboard[i-3][j] + chessboard[i-4][j];
					if (point == 5*Config.PutByComputer){
						return Config.ComputerWin;
					}
					if (point == 5*Config.PutByPlayer){
						return Config.PlayerWin;
					}
				}
			}
		}
		return Config.NoOneWin;
	}
}
