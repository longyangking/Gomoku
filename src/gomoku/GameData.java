package gomoku;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Record every play into file
 * @author LongYang
 *
 */
public class GameData {
	private List<int[][]> list;
	private int result;
	public GameData(){
		list = new ArrayList<int[][]>();
	}
	public GameData(List<int[][]> list){
		this.list = list;
	}
	public void append(ChessBoard chessboard){
		list.add(chessboard.ChessBoardInfo());
	}
	public void GameEnd(int status){
		this.result = status;
	}
	public List<int[][]> getdata(){
		return this.list;
	}
	public int getresult(){
		return this.result;
	}
	
	/**
	 * Get Game Data from file
	 * @param filename File name
	 * @return GameData Game Data
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
		// TODO Write Data
		bw.flush();
		bw.close();
		fw.close();
	}
}
