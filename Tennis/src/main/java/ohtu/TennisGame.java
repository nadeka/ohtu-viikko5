package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private Player player1;
    private Player player2;

    private Map<Integer, String> scores;
    private Map<Integer, String> results;

    public TennisGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name, 0);
        player2 = new Player(player2Name, 0);
        scores = new HashMap<>();
        results = new HashMap<>();
        initScores();
        initResults();
    }

    private void initScores() {
        scores.put(0, "Love");
        scores.put(1, "Fifteen");
        scores.put(2, "Thirty");
        scores.put(3, "Forty");
        scores.put(4, "Deuce");
    }

    private void initResults() {
        results.put(-1, "Advantage player2");
        results.put(1, "Advantage player1");
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1.incScore();
        else
            player2.incScore();
    }

    public String getScore() {
        if (sameScore()) {
            return getSameScore();
        } else if (gameEnded()) {
            return getResult();
        } else {
           return getDifferentScore();
        }
    }

    private String getScoreSuffix() {
        return player1.getScore() == 4 ? "" : "-All";
    }

    private boolean sameScore() {
        return player1.getScore() == player2.getScore();
    }

    private String getSameScore() {
        return scores.get(player1.getScore()) + getScoreSuffix();
    }

    private String getDifferentScore() {
        String score = "";
        int tempScore;

        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = player1.getScore();
            } else {
                score += "-";
                tempScore = player2.getScore();
            }

            score += scores.get(tempScore);
        }

        return score;
    }

    private String getResult() {
        int minusResult = player1.getScore() - player2.getScore();

        if (results.containsKey(minusResult)) {
            return results.get(minusResult);
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private boolean gameEnded() {
        return player1.getScore() >= 4 || player2.getScore() >= 4;
    }
}