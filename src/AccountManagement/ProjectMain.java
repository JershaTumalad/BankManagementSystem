package project;

public class ProjectMain {
    public static void main(String[] args) {
        AccountFiles files = new AccountFiles();
        GUI1Frame hp = new GUI1Frame(files);
        hp.setVisible(true);
    }
}