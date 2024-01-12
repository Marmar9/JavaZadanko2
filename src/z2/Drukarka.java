package z2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Comparator;
public class Drukarka {
    public Drukarka() {

    }
    public void start(String inputPath, String outputPath){
        List<Integer> queue = new ArrayList();
        List<String> results = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Integer value = Integer.parseInt(line);
                    queue.add(value);
                }catch (NumberFormatException e) {
                    if (line.equals("drukuj")) {
                        if (queue.isEmpty()){
                            results.add("brak");
                        }else {

                            int indexOfMax = queue.indexOf(
                                    queue.stream().max(Comparator.naturalOrder()).orElseThrow()
                            );
                            results.add( queue.remove(indexOfMax).toString());
                        }
                    } else if (line.equals("koniec")) {
                        if (queue.isEmpty()){
                            results.add("brak");
                        }
                        while (!queue.isEmpty()){
                            int indexOfMax = queue.indexOf(
                                    queue.stream().max(Comparator.naturalOrder()).orElseThrow()
                            );
                            results.add( queue.remove(indexOfMax).toString());
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            results.add("plik dane nie istnieje");
            e.printStackTrace();
        }
        System.out.println(results);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String line : results) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
