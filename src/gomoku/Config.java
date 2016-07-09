package gomoku;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Config {
	//ChessBoard Info
	public static final int ChessBoardHeight = 20;
	public static final int ChessBoardWidth = 20;
	public static final int ChessSize = 30; //2*Radius
	
	public static final Paint PlayerChessColor = Color.BLACK;
	public static final Paint ComputerChessColor = Color.WHITE;
	public static final Paint BackgroundColor = Color.GRAY;
	
	//Role Info
	public static final int Player = 1;
	public static final int Computer = -1;
	
	//Chess Info
	public static final int PutByPlayer = 1;
	public static final int PutByComputer = -1;
	public static final int NoChess = 0;
	
	//Victory Info
	public static final int PlayerWin = 1;
	public static final int ComputerWin = -1;
	public static final int NoOneWin = 0;
}
