import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class RandomWordSelect {
    private String[] words; //здесь мы будем хранить слова с нашего файла

    public RandomWordSelect(){
        populateWordsArrayFromFile();
    }

    public String getRandomWord(){
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    private void populateWordsArrayFromFile(){
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\danka\\IdeaProjects\\hangman-console\\src\\main\\java\\words.txt"))) {
            br.lines().forEach(sb::append);
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        String wordsSeparatedByCommaAndSpace = sb.toString();
        words = wordsSeparatedByCommaAndSpace.split(", ");
    }
}
