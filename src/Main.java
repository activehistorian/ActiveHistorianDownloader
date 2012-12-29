import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static JButton download = new JButton("Download Videos");
	
	public static void main (String[] PIkachu){
		frame.setVisible(true);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.add(download);
		frame.add(panel);
		
		
		
		
	}
	
	public void downloadFiles(String end){
		
		 
	}
}
