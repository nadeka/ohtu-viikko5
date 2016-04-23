package ohtu;

public class Player {

    private String nimi;
    private int score;

    public Player(String nimi, int score) {
        this.nimi = nimi;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore() {
        this.score++;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
}
