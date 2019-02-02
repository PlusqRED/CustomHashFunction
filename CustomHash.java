import java.util.Random;

public class CustomHash {
    public static String getHash(String input, int hashSize) {
        if(input.length() == 0) throw new IllegalArgumentException("input.length = 0");
        else {
            char[] chars = input.toCharArray();
            long firstSeed = 1;
            for (char aChar : chars) {
                firstSeed *= aChar;
            }
            Random random = new Random(firstSeed);
            long result = 0, a = 1;
            for (char aChar : chars) {
                result += aChar * a;
                a += aChar + random.nextInt();
            }
            random.setSeed(result);
            long hash = random.nextLong();
            StringBuilder output = new StringBuilder(hashSize);
            for(int i = 0; i < hashSize; i++) {
                random.setSeed(hash + i + 1);
                output.append(returnSymbol(hash + random.nextInt()));
            }
            return output.toString();
        }

    }

    private static Random random = new Random();
    private static char returnSymbol(long seed) {
        random.setSeed(seed);
        int randCase = random.nextInt(3);
        if(randCase == 0) {
            return (char) (random.nextInt(26) + 97);
        } else if(randCase == 1) {
            return (char) (random.nextInt(26) + 65);
        } else {
            return (char) (random.nextInt(10) + 48);
        }
    }
}
