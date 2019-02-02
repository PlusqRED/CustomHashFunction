import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        checkHashFunc(4);
    }

    private static void checkHashFunc(int hashSize) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("dataset.txt"));
        HashMap<String, String> elements = new HashMap<>(10000);
        int count = 0;
        while(scanner.hasNext()) {
            String key = scanner.nextLine();
            String hash = CustomHash.getHash(key, hashSize);
            if(elements.containsValue(hash)) {
                Set<String> keySet = elements.keySet();
                String samekey = "";
                for (String s : keySet) {
                    if(elements.get(s).equals(hash)) {
                        samekey = s;
                        break;
                    }
                }
                System.out.println("Collision! Key: [" + key + "]" + " Hash: " + hash + " Same key ~~ " + samekey);
                count++;
            } else {
                elements.put(key, hash);
            }
        }
        System.out.println("Collision amount = " + count);
        System.out.format("Collision chance = %.2f", (count / 10000d) * 100d);
    }
}
