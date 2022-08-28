import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class WordFrequencyCounter {

    private static final String RELATIVE_PATH = "src/main/resources/words.txt";

    private List<String> toListReader() {
        List<String> wordList = new ArrayList<>();
        try(FileInputStream fileInputStream = new FileInputStream(RELATIVE_PATH)) {
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < buffer.length; i++) {
                if ((char) buffer[i] == '\n') continue;
                if ((char) buffer[i] == ' ' || (char) buffer[i] == '\r') {
                    wordList.add(builder.toString());
                    builder = new StringBuilder();
                } else {
                    builder.append((char) buffer[i]);
                }
            }
            wordList.add(builder.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return wordList;
    }

    private Map<String, Integer> listToMap() {
        List<String> list = toListReader();
        Map<String, Integer> map = new HashMap<>();
        for (String j : list) {
            Integer i = map.get(j);
            map.put(j, (i == null) ? 1 : i + 1);
        }
        return map;
    }

    public void wordCounter() {
        Map<String, Integer> map = listToMap();
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() { // почему в буфер только два аргумента с компаратром
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        printMap(sortedMap);
    }

    private void printMap(Map<String, Integer> map)
    {
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            System.out.println("Element " + entry.getKey() + ": " +entry.getValue());
        }
        System.out.println("\n");
    }
}
