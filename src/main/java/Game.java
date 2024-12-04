import java.util.Scanner;

public class Game {
    private final HangmanDrawer hangmanDrawer = new HangmanDrawer();
    private final RandomWordSelect wordSelect = new RandomWordSelect();
    private final WordMaskOperator maskOperator = new WordMaskOperator();

    public void start(){
        Scanner scanner = new Scanner(System.in);
        String option;
        int mistakeCount;

        while(true){
            System.out.println("Меню: [N]ew game / [E]xit");
            option = scanner.nextLine();


            if(option.equalsIgnoreCase("N")){
                mistakeCount = 0;
                maskOperator.clearBuffer();
                hangmanDrawer.clearDrawing();
                String letter;
                String guessedWord = wordSelect.getRandomWord();
                maskOperator.setWord(guessedWord);
                System.out.println("Случайное слово загадано");
                maskOperator.printMask();

                while(!maskOperator.userWon()){
                    System.out.println("Введите букву");
                    letter = scanner.nextLine();

                    if(maskOperator.isLetterAlreadyUsed(letter)){
                        System.out.printf("Вы ввели эту букву ранее '%s'!\n", letter);
                    }else {
                        maskOperator.userInputLetter(letter);
                        if(maskOperator.containsLetter(letter)){
                            System.out.println("Вы угадали!");
                            System.out.print("Слово: ");
                            maskOperator.updateMask(letter);
                            maskOperator.printMask();
                        }else{
                            System.out.println("Вы не угадали");
                            mistakeCount++;
                            hangmanDrawer.updateHangmanDrawingMatrix(mistakeCount);
                            hangmanDrawer.printHangman();
                            System.out.printf("Текущее кол-во ошибок: %s/5\n", mistakeCount);
                            System.out.println("Слово: ");
                            maskOperator.printMask();
                        }

                    }
                    if(mistakeCount == 5){
                        System.out.println("Вы проиграли");
                        System.out.printf("Слово которое вы должны были угадать: %s\n", guessedWord);
                        break;
                    }else if(maskOperator.userWon()){
                        System.out.println("Вы выиграли! Поздравляем!!!");
                    }
                }
            }
            else if(option.equalsIgnoreCase("E")){
                System.out.println("Выход...");
                System.exit(0);
            }
            else {
                System.out.println("Вы ввели не корректный символ! Попробуйте еще раз!");
            }
        }
    }
}
