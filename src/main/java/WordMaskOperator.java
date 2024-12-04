import java.util.*;

public class WordMaskOperator {
        private String word;
        private String[] mask;
        //храним те буквы которые пользователь уже использовал
        private final Set<String> userLetters = new HashSet<>();
        //Храним уникальные буквы нашего слова
        private final Set<String> wordUniqueLetters = new HashSet<>();
        //Поле в котором хранится угаданное пользователем букв
        private int numberOfGuestLetters = 0;

        public void setWord(String word){
            this.word = word;
            this.mask = new String[word.length()];
            Arrays.fill(mask, "*");
            Collections.addAll(wordUniqueLetters, word.split(""));
        }

        public void printMask(){
            System.out.println(String.join("", mask));
        }

        public void updateMask(String letter){
            for (int i = 0; i < word.length(); i++) {
                if(Character.toString(word.charAt(i)).equalsIgnoreCase(letter)){
                    mask[i] = letter;
                }
            }
            numberOfGuestLetters++;
        }

        public boolean containsLetter(String letters){
            return wordUniqueLetters.contains(letters);
        }

        public void userInputLetter(String letter){
            userLetters.add(letter);
        }

        public boolean isLetterAlreadyUsed(String letter){
            return userLetters.contains(letter);
        }

        public boolean userWon(){
            return numberOfGuestLetters == wordUniqueLetters.size();
        }

        public void clearBuffer(){
            userLetters.clear();
            wordUniqueLetters.clear();
            numberOfGuestLetters = 0;
        }

}
