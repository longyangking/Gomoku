package gomoku;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Record every play into file
 * @author LongYang
 *
 */
public class GameData {
	private List<int[][]> list;
	private int result;
	private String name;
	
	/**
	 * Default generation of Game Data
	 */
	public GameData(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.name = df.format(new Date());
		this.list = new ArrayList<int[][]>();
	}

	/**
	 * Generation of Game Data
	 * @param name Game Data Name
	 * @param list Data List
	 */
	public GameData(String name, List<int[][]> list){
		this.name = name;
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
	public static GameData load(String filename) throws IOException{
		FileReader fr = null;
		BufferedReader br = null;
		fr = new FileReader("./data/"+filename);
		br = new BufferedReader(fr,100);
		// TODO Read Data
		br.close();
		fr.close();
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
