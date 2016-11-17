package gomoku;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

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
	private int status;
	
	/**
	 * Default generation of Game Data
	 */
	public GameData(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		this.name = df.format(new Date());
		this.list = new ArrayList<int[][]>();
		this.status = 0;
	}

	/**
	 * Generation of Game Data
	 * @param name Game Data Name
	 * @param list Data List
	 */
	public GameData(String name, List<int[][]> list){
		this.name = name;
		this.list = list;
		this.status = 0;
	}
	
	public void append(ChessBoard chessboard){
		int[][] data = new int[Config.ChessBoardWidth][Config.ChessBoardWidth];
		int[][] input = chessboard.ChessBoardInfo();
		
		for(int i=0; i<Config.ChessBoardWidth; i++){
 			for(int j=0; j<Config.ChessBoardHeight; j++){
 				data[i][j] = input[i][j];
 			}
 		}
		
		this.list.add(data);
		this.status = this.status + 1;
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
		File file = new File(System.getProperty("user.dir")+"/data/"+filename);
		File directory = new File(System.getProperty("user.dir")+"/data");
		if (!directory.exists() && !directory.isDirectory() && !file.exists()){
			return new GameData();
		}
;
		FileReader fr = new FileReader(System.getProperty("user.dir")+"/data/"+filename);
		BufferedReader br = new BufferedReader(fr,100);
		
		int chessboardheight = Integer.parseInt(br.readLine());
		int chessboardwidth = Integer.parseInt(br.readLine());
		int totalstatus = Integer.parseInt(br.readLine());
		int gameresult = Integer.parseInt(br.readLine());
		
		GameData gamedata = new GameData();
		//System.out.println(totalstatus);
		
		for(int s=0; s<totalstatus; s++){
			int[][] data = new int[chessboardwidth][chessboardheight];
			br.readLine(); //Ignore Status Code
			for(int i=0; i<chessboardwidth; i++){
				String line = br.readLine();
				//System.out.println(line);
				String[] strs = line.split(" ");
	 			for(int j=0; j<chessboardheight; j++){
	 				data[i][j] = Integer.parseInt(strs[j]);
	 			}	
	 		}
			gamedata.append(new ChessBoard(data));
		}
		gamedata.GameEnd(gameresult);
		
		br.close();
		fr.close();
		return gamedata;
	}
	
	/**
	 * Save Game Data into files
	 * 
	 * @param filename File Name
	 * @throws IOException
	 */
	public void save() throws IOException{
		File file = new File(System.getProperty("user.dir")+"/data/"+this.name+".txt");
		File directory = new File(System.getProperty("user.dir")+"/data");
		if (!directory.exists() && !directory.isDirectory()){
			directory.mkdir();
		}
		if (!file.exists()){
			file.createNewFile();
		} 
		StringBuffer content = new StringBuffer();
		FileWriter fw = new FileWriter(System.getProperty("user.dir")+"/data/"+this.name+".txt");

		content.append(Config.ChessBoardHeight+"\n");
		content.append(Config.ChessBoardWidth+"\n");
		content.append(this.status+"\n");
		content.append(this.result+"\n");
		
 		for(int s=0; s<this.status; s++){
 			int[][] data = list.get(s);
 			content.append(s+"\n");
	 		for(int i=0; i<Config.ChessBoardWidth; i++){
	 			for(int j=0; j<Config.ChessBoardHeight; j++){
	 				content.append(data[i][j]+" "); 
	 			}
	 			content.append("\n");
	 		}
 		}

		fw.write(content.toString()); 
		fw.close();
	}
	
	/**
	 * Save Game Data into files
	 * 
	 * @param filename File Name
	 * @throws IOException
	 */
	public void save(String filename) throws IOException{
		File file = new File(System.getProperty("user.dir")+"/data/"+filename);
		File directory = new File(System.getProperty("user.dir")+"/data");
		if (!directory.exists() && !directory.isDirectory()){
			directory.mkdir();
		}
		if (!file.exists()){
			file.createNewFile();
		}
		StringBuffer content = new StringBuffer();
		FileWriter fw = new FileWriter(System.getProperty("user.dir")+"/data/"+filename);

		content.append(Config.ChessBoardHeight+"\n");
		content.append(Config.ChessBoardWidth+"\n");
		content.append(this.status+"\n");
		content.append(this.result+"\n");
		
 		for(int s=0; s<this.status; s++){
 			int[][] data = list.get(s);
 			content.append(s+"\n");
	 		for(int i=0; i<Config.ChessBoardWidth; i++){
	 			for(int j=0; j<Config.ChessBoardHeight; j++){
	 				content.append(data[i][j]+" "); 
	 			}
	 			content.append("\n");
	 		}
 		}

		fw.write(content.toString()); 
		fw.close();
		
	}
}
