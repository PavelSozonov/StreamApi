import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by pavel on 11.06.17.
 */
public class FileResource {
    private String fileName;
    private InputStream fileStream;
    private DataInputStream inputStream;
    private BufferedReader bufferedReader;
    private List<Integer> numbers = new ArrayList<>();

    public FileResource(String fileName) {
        this.fileName = fileName;
        try {
            fileStream = new FileInputStream(fileName);
            inputStream = new DataInputStream(fileStream);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        readAllNumbers();
    }

    private void readAllNumbers() {
        while (nextInt() != null) {
            numbers.add(nextInt());
        }
    }

    public Integer nextInt() throws IllegalArgumentException {
        int currentSymbol;
        StringBuilder word = new StringBuilder();
        try {
            boolean stop = false;

            while (!stop) {
                currentSymbol = bufferedReader.read();
                if (currentSymbol == -1) {
                    stop = true;
                } else {
                    if (currentSymbol == ' ') {
                        if (word.length() != 0) {
                            stop = true;
                        }
                    } else {
                        if (isLetter((char)currentSymbol)) {
                            throw new IllegalArgumentException("Illegal symbols are found");
                        }
                        word.append((char)currentSymbol);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (word.length() == 0) return null;
        return Integer.parseInt(word.toString());
    }

    private boolean isLetter(char letter) {
        if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z')) return true;
        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        bufferedReader.close();
        inputStream.close();
        fileStream.close();
    }

    public Stream<Integer> getStream() {
        return numbers.stream();
    }
}
