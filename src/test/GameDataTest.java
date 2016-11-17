package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import gomoku.ChessBoard;
import gomoku.Config;
import gomoku.GameData;

public class GameDataTest {
	private GameData gamedata;
	
	@Before
	public void setUp() throws Exception {
		this.gamedata = new GameData();
		ChessBoard chessboard = new ChessBoard();
		
		chessboard.playChess(3, 3, Config.Player);
		this.gamedata.append(chessboard);
		
		chessboard.playChess(3, 4, Config.Computer);
		this.gamedata.append(chessboard);
		
		gamedata.GameEnd(Config.ComputerWin);
		gamedata.save("test.txt");
	}

	@Test
	public void testGetdata() {
		int error = 0;
		ChessBoard chessboard = new ChessBoard();
		try {
			chessboard.playChess(3, 3, Config.Player);
			GameData gd = GameData.load("test.txt");
			List<int [][]> list = gd.getdata();
			int[][] data = list.get(0);
			int[][] cd = chessboard.ChessBoardInfo();
			for(int i=0; i<Config.ChessBoardWidth; i++){
	 			for(int j=0; j<Config.ChessBoardHeight; j++){
	 				if (cd[i][j] != data[i][j]) error += 1;
	 			}
			}
			
			chessboard.playChess(3, 4, Config.Computer);
			data = list.get(1);
			cd = chessboard.ChessBoardInfo();
			for(int i=0; i<Config.ChessBoardWidth; i++){
	 			for(int j=0; j<Config.ChessBoardHeight; j++){
	 				if (cd[i][j] != data[i][j]) error += 1;
	 			}
			}
			assertEquals(0,error);
		}catch (IOException e) {
			System.out.println("Test Reading Error!");
            e.printStackTrace(); 
        }		
	}

	@Test
	public void testGetresult() {
		try {
			GameData gd = GameData.load("test.txt");
			assertEquals(Config.ComputerWin,gd.getresult());
		}catch (IOException e) {
			System.out.println("Test Reading Error!");
            e.printStackTrace(); 
        }
	}

}
