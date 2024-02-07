package src.PlayerStatistics;
import src.TetrisPiece.Piece;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Player Statistics Class
 * Outputs information on stats on statistics.txt
 * */

public class PlayerStatistics {

    // Variables
    private static final int DASH_NUM = 42;
    private int prevScore = 0;
    private int aveScore = 0;
    private int totalScore = 0;
    private HashMap<String, Integer> countPieces = new HashMap<>();
    private HashMap<Integer, Integer> scorePerRound = new HashMap<>();

    // Set count pieces
    public void setCountPieces() {
        countPieces.put("I", 0);
        countPieces.put("J", 0);
        countPieces.put("L", 0);
        countPieces.put("O", 0);
        countPieces.put("S", 0);
        countPieces.put("T", 0);
        countPieces.put("Z", 0);
        countPieces.put("+", 0);
        countPieces.put("P", 0);
        countPieces.put("Q", 0);
    }

    // Set score for given round
    public void setScorePerRound(int round, int score) {
        scorePerRound.put(round, score);
    }

    // Previous score
    public void setPrevScore(int score) {
        prevScore = score;
    }

    // Update total score
    public void setTotalScore() {
        totalScore += prevScore;
    }

    // Create (or overwrite) text file
    public void createFile(File statistics, String difficulty, int round) {
        try {
            if(!statistics.createNewFile()) {
                FileWriter fw = new FileWriter(statistics, false); //overwrite
                fw.close();
            }
            PrintWriter pw = new PrintWriter(statistics);
            pw.println("Difficulty: " + difficulty);
            pw.println("Average score per round: " + aveScore);
            pw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Save game data for a round to text file
    public void appendRoundToFile(File statistics, int round, int score) {
        try {
            FileWriter fw = new FileWriter(statistics, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            setScorePerRound(round, score);

            for(int i = 0; i < DASH_NUM; i++){
                pw.print("-");
            }

            pw.println();
            pw.println("Round #" + round);
            pw.println("Score: " + score);

            setCountPieces();
            countPieces.forEach((key, value) -> {
                pw.println(key + ": " + value);
            });
            pw.close();
        }
        catch(IOException e) {
            System.out.println("An error occurred.");
        }
    }

    // Overwrite ave per round in text file
    public void overwriteAve(File statistics, int round, int score) {
        try {
            /* Read text and append to StringBuffer */
            Scanner sc = new Scanner(statistics);
            StringBuffer buffer = new StringBuffer();

            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            // Replace average score
            String oldAve = "Average score per round: " + aveScore;
            aveScore = (totalScore + score)/round;
            String newAve = "Average score per round: " + aveScore;
            fileContents = fileContents.replace(oldAve, newAve);

            FileWriter writer = new FileWriter(statistics);
            fileContents = fileContents.replace(oldAve, newAve);
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    // Overwrite score per round in text file
    public void overwriteScore(File statistics, int round, int score) {
        overwriteAve(statistics, round, score);
        try {
            /* Read text and append to StringBuffer */
            Scanner sc = new Scanner(statistics);
            StringBuffer buffer = new StringBuffer();
            int prevScore = scorePerRound.get(round);

            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            // Replace score
            String oldScore = "Score: " + prevScore;
            String newScore = "Score: " + score;
            int start = fileContents.lastIndexOf(oldScore);
            StringBuilder b = new StringBuilder();
            b.append(fileContents.substring(0, start));
            b.append(newScore);
            b.append(fileContents.substring(start + newScore.length()));
            FileWriter writer = new FileWriter(statistics);
            writer.append(b);
            writer.flush();
            writer.close();
            scorePerRound.replace(round, score);
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    // Overwrite score per round in text file
    public void overwritePiece(File statistics, Piece piece) {
        try {
            // Read text and append to StringBuffer
            Scanner sc = new Scanner(statistics);
            StringBuffer buffer = new StringBuffer();
            String blockName = piece.getBlockName();
            int count = countPieces.get(blockName);

            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            // Replace piece count
            String oldPiece = blockName + ": " + count;
            String newPiece = blockName + ": " + (count+1);
            int start = fileContents.lastIndexOf(oldPiece);
            StringBuilder b = new StringBuilder();
            b.append(fileContents.substring(0, start));
            b.append(newPiece);
            b.append(fileContents.substring(start + newPiece.length()));
            FileWriter writer = new FileWriter(statistics);
            writer.append(b);
            writer.flush();
            writer.close();
            countPieces.replace(blockName, count+1);

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

}