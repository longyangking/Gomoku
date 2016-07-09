package gomoku;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.effect.Lighting;

public class View {
	Pane root;
	Circle[][] chesses;
	public View(Pane root){
		this.root = root;
	}
		
	public Circle[][] initChessBoard(){
		int Nx = Config.ChessBoardWidth, Ny = Config.ChessBoardHeight, 
				Radius = Config.ChessSize/2, size = Config.ChessSize;       
		
		root.getChildren().clear();
		
		Rectangle background = new Rectangle(0,0,size*Nx,size*Ny);
		background.setFill(Color.rgb(211, 169, 105));
		//background.setFocusTraversable(true);
		root.getChildren().add(background);
		
        for(int i = 0; i < Nx; i++){
        	Line line = new Line((2*i+1)*Radius,0,(2*i+1)*Radius,size*Ny);
        	line.setStroke(Color.BLACK);
        	line.setStrokeWidth(1);
        	root.getChildren().add(line);
        }
        for (int j = 0; j < Ny; j++){
        	Line line = new Line(0,(2*j+1)*Radius,size*Nx,(2*j+1)*Radius);
        	line.setStroke(Color.BLACK);
        	line.setStrokeWidth(1);
        	root.getChildren().add(line);
        }
        
        chesses = new Circle[Nx][Ny];
        for(int i = 0; i < Nx; i++) 
        	for (int j = 0; j < Ny; j++){
        		chesses[i][j] = new Circle((2*i+1)*Radius,(2*j+1)*Radius,Radius);
        		chesses[i][j].setCursor(Cursor.HAND);
        		chesses[i][j].setFill(Color.rgb(0, 0, 0,0));
        		root.getChildren().add(chesses[i][j]);
        }
        
        return chesses;
	}
	
	public void PutChess(int i, int j, int role){
		if (role == Config.Player){ 
			chesses[i][j].setFill(Config.PlayerChessColor);
		}
		if (role == Config.Computer){
			chesses[i][j].setFill(Config.ComputerChessColor);
		}
	}
	
	public void EndGame(int role){
		String result = "";
		if (role == Config.PlayerWin){
			result = "Player Win";
		}
		if (role == Config.ComputerWin){
			result = "Computer Win";
		}
		
		Stage stage = new Stage();
		Group group = new Group();
		Scene scene = new Scene(group, 300, 200, Color.WHITESMOKE);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("Result");
		stage.centerOnScreen();
		stage.show();
		Text text = new Text(20,100,result);
		text.setFill(Color.DODGERBLUE);
        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily(), 50));
        
        group.getChildren().add(text);
	}
}
