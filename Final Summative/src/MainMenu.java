/**
 * 
 */

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Het Patel
 * Date: January 2024
 * Description: 
 * 
 * 
 * 
 * Update Log:
 * 		Date: January 11th, 2024 
 * 		Description: 
 */
public class MainMenu extends JFrame implements ActionListener{

	private JPanel frontPanel, backPanel;

	private JLabel lblBackground, lblTitle;
	private JButton btnLoop, btnBeatMaker, btnMixer, btnRecorder;


	private int width, height;


	/**
	 * 
	 */
	public MainMenu() {
		super("Main Menu");

		width = 900;
		height = 650;
		
		Font f1 = new Font("Arial", Font.PLAIN, 16);

		// >>>>>>> Panels
		backPanel = new JPanel();
		backPanel.setLayout(new GridLayout(1, 1));
		backPanel.setBounds(0, 0, width, height);

		frontPanel = new JPanel();
		frontPanel.setLayout(null);
		frontPanel.setOpaque(false);
		frontPanel.setBounds(0, 0, width, height);

		// >>>>>>>>>>>>>>>>> Creating Components + Adding to Panel

		// JLabels
		lblBackground = new JLabel("");
		lblBackground.setBounds(40, 70, 235, 41);
		backPanel.add(lblBackground);

		lblTitle = new JLabel("Main Menu");
		lblTitle.setFont(f1);
		lblTitle.setBounds(getBounds());
		frontPanel.add(lblTitle);
		
		
		
		

		// add panels
		add(frontPanel);
		add(backPanel);



		setSize(width, height);
		setLocation(100, 100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenu gui = new MainMenu();

	}
}
