package src;
import java.io.*;
import java.util.*;

/**
 * Player Statistics Class
 * Outputs information on stats on statistics.txt
 * */

public class PlayerStatistics {

    // Variables
    private static final int DASH_NUM = 42;
    private int totalScore = 0;
    private int rounds = 1;
    private HashMap<String, Integer> countPieces = new HashMap<>();

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

    // Update total score
    public void checkScore(int score) {
        totalScore += score;
        rounds++;
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
            pw.println("Average score per round: " + totalScore/rounds);
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

    // Overwrite score per round in text file
    public void overwriteScore(File statistics, int score) {
        try {
            /* Read text and append to StringBuffer */
            Scanner sc = new Scanner(statistics);
            StringBuffer buffer = new StringBuffer();

            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            /* Replace score */
            String oldScore = "Score: " + (score-1);
            String newScore = "Score: " + score;

            fileContents = fileContents.replaceAll(oldScore, newScore);
            FileWriter writer = new FileWriter(statistics);
            writer.append(fileContents);
            writer.flush();
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
            String blockName = piece.blockName;
            int count = countPieces.get(blockName);

            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }

            String fileContents = buffer.toString();
            sc.close();

            // Replace piece count
            String oldPiece = blockName + ": " + count;
            String newPiece = blockName + ": " + (count+1);
            countPieces.replace(blockName, count+1);

            fileContents = fileContents.replaceAll(oldPiece, newPiece);
            FileWriter writer = new FileWriter(statistics);
            writer.append(fileContents);
            writer.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

}




