import java.util.Random;

public class CustomHash {
    private CustomHash() {
    }

    private static Random random = new Random();
    private static String alphabet = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";

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
        StringBuilder output = new StringBuilder(hashSize);
        for (int i = 1; i < hashsz; ++i) {
            random.setSeed(random.nextLong());
            output.append(alphabet.charAt(random.nextInt(62)));
        }
        return output.toString();
    }
}
