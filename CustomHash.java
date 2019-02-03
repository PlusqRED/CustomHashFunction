import java.math.BigInteger;
import java.util.Random;

public class CustomHash {
    private CustomHash() {
    }

    private static Random random = new Random();

    public static String getHash(String input, int hashSize) {
        int hashsz = hashSize + 1;
        char[] chars = input.toCharArray();
        long result = 0, firstSeed = 1, a = 1;
        for (char aChar : chars) {
            result += aChar * a;
            firstSeed *= aChar;
            random.setSeed(firstSeed);
            a += aChar + random.nextLong();
        }
        random.setSeed(result);
        long hash = random.nextLong();
        StringBuilder output = new StringBuilder(hashSize);
        for (int i = 1; i < hashsz; ++i) {
            random.setSeed(hash + i);
            output.append(returnSymbol(hash + random.nextLong()));
        }
        return output.toString();
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
