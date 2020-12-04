package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


		
	}

	public static class MastermindGame {

        private final int[][] guesses;
        private final int[] completedCode;

        private final int numColors;
        private final int numGuesses;
        private final int pegsInRow;
        private int attemptNum;

        public final int correctColor = 1;
        public final int Position = 2;
        private int calcEval;


        public MastermindGame(int aNumberOfColor, int pegsInRow, int[] completedCode, int aNumberOfGuesses, int pegsInRow1, int attemptNum) {
            this.completedCode = completedCode;
            this.numColors = aNumberOfColor;
            this.numGuesses = aNumberOfGuesses;
            this.pegsInRow = pegsInRow1;
            this.attemptNum = attemptNum;


            guesses = new int[numGuesses][pegsInRow];

            int[] calcEval = new int[numGuesses];
            completedCode = new int[pegsInRow];


            randomCode();

        }



        private void randomCode() {

            Random random = new Random();

            for (int i = 0; i < 4; i++) {

                completedCode[i] = random.nextInt(numColors) + 1;
            }

        }


        public void GuessEntry (int[] userGuess) {

            if (attemptNum < numGuesses) {

                if (userGuess.length == pegsInRow) {

                    System.arraycopy(userGuess, 0, guesses[attemptNum], 0, userGuess.length);
                }

                calcEval();
                attemptNum++;
            }
        }


        private void calcEval() {

            for (int i = 0; i < guesses[attemptNum].length; i++) {

                if (guesses[attemptNum][i] == completedCode[i]) {

                    pileResult(Position);

                    break;
                }


                else {

                    for (int j = 0; j < guesses[attemptNum].length; j++) {

                        if (guesses[attemptNum][j] == completedCode[i]) {

                            pileResult(correctColor);

                            break;
                        }
                    }
                }
            }
        }


        private void pileResult(int resultToAdd) {


            for (int i = 0; i < 7; i++) {

                if (guesses[attemptNum][i] == 0) {
                    guesses[attemptNum][i] = resultToAdd;
                    break;
                }
            }
        }



        public boolean Completion() {
            boolean codeCompletion = true;

            for (int i = 0; i < 4; i++) {
                if (completedCode[i] != guesses[attemptNum][i]) {
                    codeCompletion = false;
                    break;
                }
            }

            return codeCompletion;
        }

        public  boolean GameOver() {
            return (attemptNum == numGuesses);
        }
        public int[][] getGuesses(){
            return guesses;
        }
        public int getCalcEval(){

            return calcEval;
        }
        public int[] getCompletedCode(){
            return completedCode;
        }
        public int getAttemptNum(){
            return attemptNum;
        }


    }}