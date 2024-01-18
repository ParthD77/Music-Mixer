/**
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @author Het Patel
 * Date: January 2024
 * Description: Main Menu Frame for the Music Mixer
 *
 * Method List:
 * 	- public MainMenu() - Constructor
 *	- public void actionPerformed(ActionEvent e) - Action Listener
 *	- public static void main(String[] args) - Self Testing Main Method
 */
public class MainMenu extends JFrame implements ActionListener{

	// Declaring variables for GUI
	private JPanel frontPanel, backPanel;
	private JLabel lblBackground;
	private JButton btnLoop, btnBeatMaker, btnMixer, btnRecorder;
	private JButton btnHelp, btnSettings;

	// Declaring variables as a placeholder
	private int width, height;
	private String background;


	/**
	 * 
	 */
	public MainMenu() {
		super("Main Menu");

		// initializing variables
		width = 900;
		height = 650;
		background = "violetTheme.png";
		
		Font f1 = new Font("Arial", Font.PLAIN, 16);

		// >>>>>>> Panels
		backPanel = new JPanel();	// creating new JPanel
		backPanel.setLayout(new GridLayout(1, 1));
		backPanel.setBounds(0, 0, width, height);	 // sending size of panel

		frontPanel = new JPanel();	// creating new JPanel
		frontPanel.setLayout(null);
		frontPanel.setOpaque(false);
		frontPanel.setBounds(0, 0, width, height); // sending size of panel

		// >>>>>>>>>>>>>>>>> Creating Components + Adding to Panel

		try {
		// JLabels
		lblBackground = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("imgs/"+background))));
		lblBackground.setBounds(40, 70, width, height);
		backPanel.add(lblBackground);

		// JButton
		btnLoop = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/WLoopsLibraryButton.png"))));
		btnLoop.setBorderPainted(false);
		btnLoop.setBounds(width/2 - 155, height/2 - 165, 150, 150);
		frontPanel.add(btnLoop);

		btnBeatMaker = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/WAudioMixerButton.png"))));
		btnBeatMaker.setBorderPainted(false);
		btnBeatMaker.setBounds(width/2 - 155, height/2 - 5, 150, 150);
		frontPanel.add(btnBeatMaker);

		btnMixer = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/WBeatMakerButton.png"))));
		btnMixer.setBorderPainted(false);
		btnMixer.setBounds(width/2 + 5, height/2 - 165, 150, 150);
		frontPanel.add(btnMixer);

		btnRecorder = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/WRecorderButton.png"))));
		btnRecorder.setBorderPainted(false);
		btnRecorder.setBounds(width/2 + 5, height/2 - 5, 150, 150);
		frontPanel.add(btnRecorder);

		btnHelp = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/WHelpButton.png"))));
		btnHelp.setBorderPainted(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBounds(width - 110, height - 90, 40, 40);
		frontPanel.add(btnHelp);

		btnSettings = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/WSettingsButton.png"))));
		btnSettings.setBorderPainted(false);
		btnSettings.setContentAreaFilled(false);
		btnSettings.setBounds(width - 70, height - 90, 40, 40);
		frontPanel.add(btnSettings);
		}
		catch(Exception r) {	
			r.printStackTrace();
		}
		// add panels
		add(frontPanel);
		add(backPanel);

		btnLoop.addActionListener(this);
		btnBeatMaker.addActionListener(this);
		btnMixer.addActionListener(this);
		btnRecorder.addActionListener(this);
		btnHelp.addActionListener(this);
		btnSettings.addActionListener(this);

		setSize(width, height);
		setLocation(30, 120);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoop) {
			this.setVisible(false);
			new LoopLibrary();
		} else if (e.getSource() == btnBeatMaker) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Beat Maker");
		} else if (e.getSource() == btnMixer) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Mixer");
		} else if (e.getSource() == btnRecorder) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "recorder");
		} else if (e.getSource() == btnHelp) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Help");
		} else if (e.getSource() == btnSettings) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Settings");
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenu gui = new MainMenu();

	}
}
