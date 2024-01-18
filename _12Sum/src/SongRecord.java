import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class SongRecord {
    private String fileName;
    private long fileSize;
    private FileTime dateCreated;
    private FileTime dateAccessed;
    private Draggable playBtn;

    public SongRecord(String fileName, Draggable d) throws IOException {
        // path of the file
        String path = "/Users/shubhpatel/Downloads/final summative/Music-Mixer-main 4/_12Sum/sounds/A.wav";
        // creating a object of Path class
       Path file = Paths.get(path);

        // creating a object of BasicFileAttributes
        BasicFileAttributes attr = Files.readAttributes(
                file, BasicFileAttributes.class);

        this.fileName = fileName;
       this.fileSize = attr.size();
       this.dateCreated = attr.creationTime();
       this.dateAccessed = attr.lastAccessTime();
        this.playBtn = d;
        this.playBtn.setIcon("playBtn.png");
        this.playBtn.setNote(fileName);
    }

    public SongRecord(String input) {
        processString(input);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public FileTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(FileTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public FileTime getDateAccessed() {
        return dateAccessed;
    }

    public void setDateAccessed(FileTime dateAccessed) {
        this.dateAccessed = dateAccessed;
    }

    public Draggable getPlayBtn() {
        return playBtn;
    }

    public void setPlayBtn(Draggable playBtn) {
        this.playBtn = playBtn;
    }

    public void processString(String record) {
        String[] info = record.split("/");

        this.fileName = info[0];
        this.fileSize = Integer.parseInt(info[1]);
        this.dateCreated = FileTime.fromMillis(Long.parseLong((info[2])));
        this.dateAccessed = FileTime.fromMillis(Long.parseLong((info[3])));

        this.playBtn = new Draggable(info[0]);
        this.playBtn.setIcon("playBtn.png");
    }



    /**
     * Self testing main
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Draggable d = new Draggable();
        d.setBounds(0, 0, 50, 50);
        SongRecord s = new SongRecord("A.wav", d);

        System.out.println(s.getFileSize());
        System.out.println(s.getDateCreated());

        JFrame f = new JFrame();
        f.setLayout(null);
        f.add(s.getPlayBtn());
        f.setVisible(true);
        f.setSize(400, 400);
    }
}
