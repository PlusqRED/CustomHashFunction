import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;

public class StringGenerator {

    private static StringGenerator stringGenerator = new StringGenerator();
    private static Random random = new Random();

    private StringGenerator() {
    }

    public static StringGenerator getInstance() {
        return stringGenerator;
    }

    public void generate(File file, int stringAmount, int symbolAmount) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        StringBuilder result = new StringBuilder(symbolAmount);
        for (int i = 0; i < symbolAmount; ++i) result.append('a');
        HashSet<String> strings = new HashSet<>();
        long hash;
        while (strings.size() <= stringAmount) {
            hash = random.nextLong();
            for (int i = 0; i < symbolAmount; ++i) {
                random.setSeed(hash + i + 1);
                result.setCharAt(i, returnSymbol(hash + random.nextLong()));
            }
            strings.add(result.toString());
        }
        for (String string : strings) {
            writer.println(string);
        }

    }

    private static char returnSymbol(long seed) {
        random.setSeed(seed);
        int randCase = random.nextInt(3);
        if (randCase == 0) {
            return (char) (random.nextInt(26) + 97);
        } else if (randCase == 1) {
            return (char) (random.nextInt(26) + 65);
        } else {
            return (char) (random.nextInt(10) + 48);
        }
    }
}
