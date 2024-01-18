import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ExporterTester {

    public ExporterTester() throws IOException, URISyntaxException {

        AudioMerger merger = new AudioMerger();
        MergeSound sound = new MergeSound(new File(getClass().getResource("sounds/kick.wav").toURI()));
        MergeSound sound2 = new MergeSound(new File(getClass().getResource("sounds/snare.wav").toURI()));
        MergeSound sound3 = new MergeSound(new File(getClass().getResource("sounds/hihat.wav").toURI()));
        MergeSound sound4 = new MergeSound(new File(getClass().getResource("sounds/ceeday-huh-sound-effect.wav").toURI()));


        merger.addSound(0, sound);
        merger.addSound(0, sound2);

        double set = 0;
        for (int i = 0 ; i < 200; i++) {
            merger.addSound(set, sound3);
            merger.addSound(set, sound2);
            merger.addSound(set, sound);
            set += 1;
        }

        merger.merge(10);
        merger.saveToFile(new File("out.wav"));
    }
    public static void main(String args[]) throws IOException, URISyntaxException {
        new ExporterTester();
    }
}
