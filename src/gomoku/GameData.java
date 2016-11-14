package gomoku;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameData {
	private List<int[][]> list;
	public GameData(){
		list = new ArrayList<int[][]>();
	}
	public void append(ChessBoard chessboard){
		list.add(chessboard.ChessBoardInfo());
	}
	public List<int[][]> getdata(){
		return list;
	}
	
	/**
	 * Get Game Data from file
	 * @param filename
	 * @return GameData
	 * @throws IOException
	 */
	public GameData load(String filename) throws IOException{
		
		return new GameData();
	}
	
	/**
	 * Save Game Data into files
	 * 
	 * @param filename File Name
	 * @throws IOException
	 */
	public void save(String filename) throws IOException{
		FileWriter fw = null;
		BufferedWriter bw = null;
		fw = new FileWriter("./data/"+filename,true);
		bw = new BufferedWriter(fw,100);
		bw.flush();
		bw.close();
		fw.close();
	}
}
