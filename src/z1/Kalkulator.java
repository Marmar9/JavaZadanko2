package z1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Kalkulator {
    public Kalkulator() {}
    public void oblicz(String inputPath, String outputPath){
        List<String> results = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                List<String> operation = Arrays.asList(line.split(" "));

                Integer x = Integer.parseInt(operation.get(0));
                Integer y = Integer.parseInt(operation.get(2));


                char operand = operation.get(1).charAt(0);
                if (operation.size() == 3) {
                    Integer result;
                    switch (operand){
                        case '+':
                            result = (x + y);
                            results.add(result.toString());
                            break;
                        case '-':
                            result = (x - y);
                            results.add(result.toString());
                            break;
                        case '*':
                            result = (x * y);
                            results.add(result.toString());
                            break;
                        case '/':
                            try {
                                result = (x / y);
                                results.add(result.toString());
                            }catch (ArithmeticException e){
                                results.add("Dzielenie przez zero");
                            }
                            break;
                    }
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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