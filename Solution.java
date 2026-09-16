import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Result {

    public static long findMaximumProfit(List<Integer> category, List<Integer> price) {
        if (category == null || price == null || category.size() != price.size()) {
            throw new IllegalArgumentException("category and price must have the same length");
        }

        Map<Integer, Long> minimumPriceByCategory = new HashMap<Integer, Long>();
        long totalPrice = 0L;

        for (int i = 0; i < category.size(); i++) {
            int currentCategory = category.get(i);
            long currentPrice = price.get(i);
            totalPrice += currentPrice;  

            Long minimumPrice = minimumPriceByCategory.get(currentCategory);
            if (minimumPrice == null || currentPrice < minimumPrice) {
                minimumPriceByCategory.put(currentCategory, currentPrice);
            }
        }

        List<Long> categoryMinimums = new ArrayList<Long>(minimumPriceByCategory.values());
        Collections.sort(categoryMinimums);

        long categoryCount = categoryMinimums.size();
        long minimumPriceTotal = 0L;
        long totalProfit = 0L;

        for (int i = 0; i < categoryMinimums.size(); i++) {
            long minimumPrice = categoryMinimums.get(i);
            totalProfit += minimumPrice * (i + 1L);
            minimumPriceTotal += minimumPrice;
        }

        totalProfit += (totalPrice - minimumPriceTotal) * categoryCount;
        return totalProfit;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int categoryCount = scanner.nextInt();
        List<Integer> category = new ArrayList<Integer>(categoryCount);
        for (int i = 0; i < categoryCount; i++) {
            category.add(scanner.nextInt());
        }

        int priceCount = scanner.nextInt();
        List<Integer> price = new ArrayList<Integer>(priceCount);
        for (int i = 0; i < priceCount; i++) {
            price.add(scanner.nextInt());
        }

        long result = Result.findMaximumProfit(category, price);
        String outputPath = System.getenv("OUTPUT_PATH");
        if (outputPath == null || outputPath.isEmpty()) {
            System.out.println(result);
            return;
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath));
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    private static final class FastScanner {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer;

        int nextInt() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new IOException("Unexpected end of input");
                }
                tokenizer = new StringTokenizer(line);
            }
            return Integer.parseInt(tokenizer.nextToken());
        }
    }
}
