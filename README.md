# University Subject
This project was done by a group of 3 students for SWEN30006 Software Modelling and Design of the University of Melbourne.

# About the Project

## File Purpose
The program refactors a provided replica of the simple Tetris game to better adhere to the GRASP principles and patterns. 

## File-Level Documentation
This version of Tetris is similar to the original Tetris game, especially in terms of the gameplay. However, there are a few modifications:

1. There are 7 differently-shaped pieces: I, J, L, O, S, T, Z. Each of these are made of 4 individual blocks called TetroBlocks. 

2. There are 3 difficulty levels

    a. Simple

    This is the same as the original Tetris.

    b. Medium

    There are 3 additional pieces: P, Q, Plus. Each of these are made of 5 TetroBlocks.

    The speed is 20% faster than the Simple Level.

    c. Madness

    Same as Medium, only that the rotate function is disabled and each piece falls at a random speed within the range of [S, 2S], where s is the speed of the Simple Level.

4. There is a Statistics file that updates the average score per round and the number of pieces played as well as the average score for each round.

## Demo


https://github.com/SiRong-github/Tetris/assets/62817554/307ce0ec-df6f-45aa-a492-7a534217a2c1


<img width="470" alt="Screenshot 2024-02-07 at 1 47 00 pm" src="https://github.com/SiRong-github/Tetris/assets/62817554/9f0dfab0-ce50-4ce0-9d4e-668e92f459cb">


# Provided Template Folder, Source Files (and Their Libraries) and Test Cases

1. lib folder

Contains the JGameGrid library

2. properties 

Contains the test cases. 

test1.properties is the only playable test case while the rest are coded.

3. sprites

Contains the gif images of the TetroBlocks.

4. src
   
    a. Driver

    Runs the project based on the given command line arguments which are based on the selected test case in the properties folder.

    b. Tetris Components, TetrisGameCallback, TetroBlock, Tetris.Form

        i. TetrisComponents

        Includes the GUI of the project.
  
        ii. TetrisGameCallback

        Logs the game activity and is used for testing by the university staff.

        iii. TetroBlock 

        Displays the provided Tetris piece.

        iv. Tetris.Form

        Also includes the GUI of the project.


    c. Tetris

   Contains the main program for the game and is to be refactored.

    d. J, L, S, Z, T, I, O

   Tetris pieces for Simple Tetris.

# Testing

## Changing Test Case
Head to the Driver Program in the utility folder and change DEFAULT_PROPERTIES_PATH to one of the test cases in the properties folder.

## In the Terminal
javac -cp lib/JGameGrid.jar -d out src/*/*.java
java -cp out:lib/JGameGrid.jar:properties:sprites src.utility.Driver
