import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
    private final static ArrayList<String> listoffiles = new ArrayList<String>();
    int counter = 0;
    int playcounter = 0;
    File file;
    Media media;
    MediaPlayer player;
    MediaView view;
    
    public void init(final Stage stage){
        stage.setTitle("Active Historian Media Player");
        Group root = new Group();
        Scene scene = new Scene(root,500,500,Color.BLACK);
        stage.setScene(scene);
        final VBox vbox = new VBox(10);
        HBox texthbox = new HBox(25);
        HBox buttonhbox = new HBox(5);
        
        final Button previous = new Button("Previous");
        final Button play = new Button("Play");
        final Button next = new Button("Next");
        final Button stop = new Button("Stop");
        
        previous.setVisible(false);
        next.setVisible(false);
        
        Text numberoffiles = new Text("Number of Logs: " + listoffiles.size());
        final Text currentfilename = new Text("Current Log: " + listoffiles.get(counter));
        numberoffiles.setFill(Color.WHITE);
        currentfilename.setFill(Color.WHITE);
       
        media = new Media(file.toURI().toString());
        player = new MediaPlayer(media);
        view = new MediaView(player);
        
        next.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                vbox.getChildren().remove(view);
                counter++;
                file = new File(listoffiles.get(counter));
                currentfilename.setText(listoffiles.get(counter));
                media = new Media(file.toURI().toString());
                player = new MediaPlayer(media); 
                view = new MediaView(player);
                stage.setMinWidth(player.getMedia().getWidth());
                stage.setMinHeight(player.getMedia().getHeight());
                vbox.getChildren().add(view);
            }
        });

        stop.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                playcounter++;
                play.setText("Play");
                player.stop();
                previous.setVisible(true);
                next.setVisible(true);
            }
        });
        
        player.setOnReady(new Runnable(){
            @Override
            public void run(){
                stage.setMinWidth(player.getMedia().getWidth());
                stage.setMinHeight(player.getMedia().getHeight());
            }
        });
        
        play.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(playcounter % 2 == 0){
                    play.setText("Pause");
                    player.play();;
                    playcounter++;
                    previous.setVisible(false);
                    next.setVisible(false);
                }
                else{
                    play.setText("Play");
                    player.pause(); 
                    playcounter++;
                    previous.setVisible(false);
                    next.setVisible(false);
                }
            }
        });
        
        buttonhbox.getChildren().addAll(previous,play,stop,next);
        texthbox.getChildren().addAll(numberoffiles,currentfilename);
        vbox.getChildren().addAll(texthbox,buttonhbox,view);
        root.getChildren().add(vbox);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        try{
            FileInputStream fstream = new FileInputStream("filelist.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader (new InputStreamReader(in));
            String strLine;
            while((strLine = br.readLine()) != null) {listoffiles.add(strLine);System.out.println(strLine);}
            in.close();
        }
        catch (Exception e){System.out.println("Error");}
        file = new File(listoffiles.get(counter));
        init(stage);
        stage.show();
    }
    
    public static void main(String[] Pikachu){
        launch(Pikachu);
    }
    
}
