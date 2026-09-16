import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static long findMaximumProfit(int[] category, int[] price) {
        if (category == null || price == null || category.length != price.length) {
            throw new IllegalArgumentException("category and price must have the same length");
        }

        // The cheapest item in each category should be used to introduce
        // that category. All other items can be sold after every category
        // has been introduced.
        Map<Integer, Integer> cheapestPriceByCategory = new HashMap<Integer, Integer>();
        long allPricesTotal = 0L;

        for (int i = 0; i < category.length; i++) {
            allPricesTotal += price[i];

            int currentCategory = category[i];
            int currentPrice = price[i];
            Integer cheapestPrice = cheapestPriceByCategory.get(currentCategory);

            if (cheapestPrice == null || currentPrice < cheapestPrice) {
                cheapestPriceByCategory.put(currentCategory, currentPrice);
            }
        }

        // Sort the category-introducing prices from smallest to largest.
        // This gives them multipliers 1, 2, 3, ... in the best order.
        List<Integer> cheapestPrices = new ArrayList<Integer>(cheapestPriceByCategory.values());
        Collections.sort(cheapestPrices);

        long numberOfCategories = cheapestPrices.size();
        long cheapestPricesTotal = 0L;
        long maximumProfit = 0L;

        for (int i = 0; i < cheapestPrices.size(); i++) {
            long cheapestPrice = cheapestPrices.get(i);
            long numberOfCategoriesSold = i + 1L;

            maximumProfit += cheapestPrice * numberOfCategoriesSold;
            cheapestPricesTotal += cheapestPrice;
        }

        // Every item that did not introduce a category can be sold now.
        maximumProfit += (allPricesTotal - cheapestPricesTotal) * numberOfCategories;
        return maximumProfit;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] category = new int[n];
        for (int i = 0; i < n; i++) {
            category[i] = scanner.nextInt();
        }

        int priceCount = scanner.nextInt();
        if (priceCount != n) {
            throw new IllegalArgumentException("category and price lengths must match");
        }

        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            price[i] = scanner.nextInt();
        }

        System.out.println(findMaximumProfit(category, price));
    }

    // Reads integers quickly from standard input.
    
    private static final class FastScanner {
        // The input provided when the program is run in the terminal.
        private final BufferedInputStream input = new BufferedInputStream(System.in);

        // Stores a block of input bytes so that read() can process them efficiently.
        private final byte[] buffer = new byte[1 << 16];

        // Position of the next unread byte and number of valid bytes in the buffer.
        private int bufferPosition;
        private int bufferLength;

        // Reads and returns the next integer from the input.
        int nextInt() throws IOException {
            int character;

            // Ignore spaces, tabs, and new lines before the number.
            do {
                character = read();
            } while (character <= ' ' && character != -1);

            // Support negative numbers, even though this problem uses positive values.
            int sign = 1;
            if (character == '-') {
                sign = -1;
                character = read();
            }

            // Convert each digit from a character into an integer value.
            int value = 0;
            while (character > ' ') {
                value = value * 10 + character - '0';
                character = read();
            }
            return value * sign;
        }

        // Returns one byte from the buffer. Refill the buffer when it is empty.
        private int read() throws IOException {
            if (bufferPosition >= bufferLength) {
                bufferLength = input.read(buffer);
                bufferPosition = 0;

                // -1 means there is no more input to read.
                if (bufferLength == -1) {
                    return -1;
                }
            }
            return buffer[bufferPosition++];
        }
    }
}
