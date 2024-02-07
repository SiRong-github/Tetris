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

    b. Tetris Components, TetrisGameCallback, TetroBlock

        i. TetrisComponents

        Includes the GUI of the project.
  
        ii. TetrisGameCallback

        Logs the game activity and is used for testing by the university staff.

        iii. TetroBlock 

        Displays the provided Tetris piece.

    c. Tetris

   Contains the main program for the game and is to be refactored.

    d. J, L, S, Z, T, I, O

   Tetris pieces for Simple Tetris.

# Testing
Open the file in IntelliJ or Eclipse and run the Driver program.

To change the test case, simply change DEFAULT_PROPERTIES_PATH in the Driver program (inside the utility package) to one of the test cases in the properties folder.
