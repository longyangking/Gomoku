package gomoku;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameEngine {
	View view;
	ChessBoard chessboard;
	Player player;
	Computer computer;
	Circle[][] playboard;
	public GameEngine(Pane root){
		view = new View(root);
		chessboard = new ChessBoard();
		player = new Player();
		computer = new Computer();
	}
	
	public void Start(){
		playboard = view.initChessBoard();
		chessboard.initChessBoard();
		int Nx = Config.ChessBoardWidth, Ny = Config.ChessBoardHeight;
		for(int i = 0; i < Nx; i++) 
        	for (int j = 0; j < Ny; j++){
        		int PutI = i, PutJ = j;
        		Circle circle = playboard[i][j];
        		circle.setOnMouseEntered((MouseEvent me)->{
        			circle.setStroke(Color.RED);
        		});
        		circle.setOnMouseExited((MouseEvent me)->{
        			circle.setStroke(Color.TRANSPARENT);
        		});
        		circle.setOnMouseClicked((MouseEvent me)->{
        			if (chessboard.playChess(PutI, PutJ, Config.Player)){
        				view.PutChess(PutI, PutJ, Config.Player);
        				int[] pos = computer.PlayChess(chessboard.ChessBoardInfo());
        				if (chessboard.playChess(pos[0], pos[1], Config.Computer)){
        					view.PutChess(pos[0], pos[1], Config.Computer);
        				}
        				int roleWin = chessboard.VictoryJudge();
        				if (roleWin != Config.NoOneWin){
        					if (roleWin == Config.ComputerWin){
        						this.End(Config.ComputerWin);
        					}
        					if (roleWin == Config.PlayerWin){
        						this.End(Config.PlayerWin);
        					}
        				}
        			}
        			/*
        			else {
        				//Just for test!
        				System.out.println("Already token!");
        				view.EndGame(Config.ComputerWin);
        			}
        			*/
        		});
        }
	}
	
	public void End(int role){
		view.EndGame(role);
		this.Start();
	}
}
