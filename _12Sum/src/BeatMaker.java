import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
Author: Parth
Date: test
Description;
 */


// EXTNESIONS
// make a extra beat where it plays a loop from loop library thats 20 or 60 seconds long
// add a microphone button where you can record audio for 20 or 60 seconds
// this audio will be played at a beat that is draggable
public class BeatMaker extends JFrame implements ActionListener {
    private Instrument[] instruments;
    Draggable mic;
    Recorder r;
    private JPanel panel;
    private JScrollPane scrollPane;
    private int counter, spot, delay, size, length, beatsAmount, speed;
    private JButton play, download, goStart, goEnd, saveProgress, micInput;
    private JLabel playHead;
    private Timer timerAnim;
    private boolean playing;


    public BeatMaker(String user, char accType, String[] fileNames, String[] activity) {
        setLayout(null);
        setResizable(false);


        speed = 5;
        delay = 1;
        timerAnim = new Timer(delay, this);
        playing = false;
        spot = 0;


        length = 20;
        size = 6000;
        beatsAmount = 55;

        if (accType == 'p') {
            length *= 3;
            size *= 3;
            beatsAmount = 175;
        }

        r = new Recorder();
        panel = new JPanel();
        // 552 is to make sure the last button is played
        // for some reason the scroll pane needs a extra 2 at the end to stick to incremants
        panel.setPreferredSize(new Dimension(size+552, 500));
        panel.setBackground(Color.black);
        panel.setLayout(null);

        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(0, 380, 1000, 400);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(50);

        counter = 0;
        instruments = new Instrument[0];
        instruments = newInstrument(fileNames[0], activity[0]);
        instruments = newInstrument(fileNames[1], activity[1]);
        instruments = newInstrument(fileNames[2], activity[2]);
        instruments = newInstrument(fileNames[3], activity[3]);
        mic = new Draggable(Color.BLUE, "mic.wav", 0);
        mic.changeMoveable(true);
        mic.setBounds(100, 0, 50, 50);



        play = buttonFactory("paused.png");
        play.setBounds(0, 0, 200, 200);
        download = buttonFactory("paused.png");
        download.setBounds(300, 0, 200, 200);
        goStart = buttonFactory("paused.png");
        goStart.setBounds(500, 0, 200, 200);
        goEnd = buttonFactory("paused.png");
        goEnd.setBounds(700, 0, 200, 200);
        saveProgress = buttonFactory("paused.png");
        saveProgress.setBounds(900, 0, 200, 200);
        micInput = buttonFactory("paused.png");
        micInput.setBounds(900, 200, 200, 200);


        try {
            playHead = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("imgs/"+"playhead.png"))));
        }
        catch (Exception e) {}
        playHead.setBounds(0, 0, 100, 600);

        add(micInput);
        add(saveProgress);
        add(goStart);
        add(goEnd);
        add(download);
        add(playHead);
        add(play);
        add(scrollPane);
        panel.add(mic, 0);

        revalidate();
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton buttonFactory(String name){
        JButton temp = null;
        try {
            temp = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("imgs/"+name))));
            temp.addActionListener(this);
            temp.setOpaque(false);
            temp.setContentAreaFilled(false);
            temp.setBorderPainted(false);
        }
        catch (Exception e) {}
        return temp;
    }


    public Instrument[] newInstrument(String fileName, String activity){
        Instrument[] temp = new Instrument[counter+1];
        for (int i = 0; i < counter; i++) {
            temp[i] = instruments[i];
        }
        temp[counter] = new Instrument(counter, beatsAmount, fileName, activity);
        temp[counter].addBeats(panel);
        counter++;
        return temp;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play){

            try {
                playing = !playing;
                if (playing) {
                    play.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("imgs/" + "playing.png"))));
                    timerAnim.start();
                } else {
                    play.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("imgs/" + "paused.png"))));
                    timerAnim.stop();
                }
                repaint();
            }
            catch (Exception e1) {}
        }

        if (e.getSource() == saveProgress){
            // call hets method to save info
          //  user = user;
          //  accType = accType;
          //  fileNames = fileNames;


            String actives = "";

            // get all the beats that are active
            for (int i  = 0; i < beatsAmount; i++){
                for (int j = 0; j < counter; j++) {
                    if (instruments[j].getBeat(i).getActivity()) {
                        actives = actives + 1;
                    }
                    else {
                        actives = actives + 0;
                    }
                }
            }

        }

        if (e.getSource() == micInput){
            Recorder r = new Recorder();
            r.record("mic.wav");

            // get size of audio file
            // then scale it accordingly
            try {
                File inputFile = new File("sounds/mic.wav");

                //https://stackoverflow.com/a/3009973/22589268 // get size of it
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);
                AudioFormat format = audioInputStream.getFormat();
                long frames = audioInputStream.getFrameLength();
                double durationInSeconds = (frames+0.0) / format.getFrameRate();
                long durationInMilliSeconds = (long)(durationInSeconds * 1000);

                // display size
                mic.setSize((int)(durationInSeconds*300), 50);
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == goStart){
            scrollPane.getHorizontalScrollBar().setValue(0);
        }

        if (e.getSource() == goEnd){
            scrollPane.getHorizontalScrollBar().setValue(size);
        }

        if (e.getSource() == download){
            try {
                int count = 0;
                AudioMerger merger = new AudioMerger();

                for (int i = 0; i < panel.getWidth(); i+=5){ // 5 is to maintain a speed
                    if (count >= beatsAmount) break; // to not crash after playing last beat
                    if (instruments[0].getBeat(count).getX() == i) {
                        for (int j = 0; j < counter; j++) {
                            if (instruments[j].getBeat(count).getActivity()){
                                MergeSound sound = new MergeSound(new File("sounds/" + instruments[j].getInstrument()));
                                merger.addSound(i/300.0, sound);
                            }
                        }
                        count++;
                    }
                    else if (mic.getX() == i && mic.getActivity()){
                        MergeSound sound = new MergeSound(new File("sounds/" + mic.getNote()));
                        merger.addSound(i/300.0, sound);
                    }
                }

                merger.merge(length);
                merger.saveToFile(new File("sounds/out.wav"));
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }

        }

        if (e.getSource() == timerAnim){

            scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue()+5);

            if (scrollPane.getHorizontalScrollBar().getValue() % 100 == 0){
                if (scrollPane.getHorizontalScrollBar().getValue() == mic.getX() && mic.getActivity()){
                    mic.makeSound();
                }

                spot = scrollPane.getHorizontalScrollBar().getValue()-100;
                for (int i = 0; i < counter; i++) {
                    if (instruments[i].getBeat(spot / 100).getActivity()) { // if beat is active
                        instruments[i].getBeat(spot / 100).makeSound();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        String user = "parth";
        char accType = 'p';
        String[] fileNames = {"ceeday-huh-sound-effect.wav", "hihat.wav", "kick.wav", "snare.wav"};
        String[] activity = new String[4];

        for (int i = 0; i < 4; i++) {
            activity[i] = "";
            for (int j = 0; j < 175; j++) {
                if (j%2==0) {
                    activity[i] = activity[i] + 1;
                }
                else {
                    activity[i] = activity[i] + 0;
                }
            }
        }

        new BeatMaker(user, accType, fileNames, activity);

    }

}
