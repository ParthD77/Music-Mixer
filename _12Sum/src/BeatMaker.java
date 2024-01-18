import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
Author: Parth
Date: test
Description;
 */

public class BeatMaker extends JFrame implements ActionListener {
    private Instrument[] instruments;
    private JPanel panel;
    private JScrollPane scrollPane;
    private int counter, spot;
    private JButton play;
    private JLabel playHead;
    private Timer timer, timerAnim;


    public BeatMaker() {
        setLayout(null);
        setResizable(false);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(4000, 500));
        panel.setBackground(Color.black);
        panel.setLayout(null);
        timer = new Timer(1000, this);
        timerAnim = new Timer(100, this);
        spot = 0;


        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 300, 1000, 400);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        counter = 0;
        instruments = new Instrument[0];
        instruments = newInstrument();
        instruments = newInstrument();
        instruments = newInstrument();

        play = new JButton("Play");
        play.setBounds(0, 0, 100, 50);
        play.addActionListener(this);

        try {
            playHead = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("imgs/"+"playhead.png"))));
        }
        catch (Exception e) {}
        playHead.setBounds(0, 0, 100, 600);


        add(playHead);
        add(play);
        add(scrollPane);
        revalidate();
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public Instrument[] newInstrument(){
        Instrument[] temp = new Instrument[counter+1];
        for (int i = 0; i < counter; i++) {
            temp[i] = instruments[i];
        }
        temp[counter] = new Instrument(counter, 20, "ceeday-huh-sound-effect.wav");
        temp[counter].addBeats(panel);
        counter++;
        return temp;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play){
            timer.start();
            timerAnim.start();
        }

        if(e.getSource() == timer){
            if (spot > instruments[0].getBeatsCount()-1) spot = 0; // loop around

            // play a sound from each instrument every time timer fires
            for (int i = 0; i < counter-1; i++) {
                instruments[i].forceSound(spot);
            }
            spot++;
        }

        if (e.getSource() == timerAnim){
            scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue()+10);
        }
    }


    public static void main(String[] args) {
        new BeatMaker();

    }

}
