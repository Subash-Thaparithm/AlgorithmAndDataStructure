package Generic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeJam_2020 {
    private static List<Integer> maximumSubArray(String[] inputAscending, int limit) {
        int sum = 0;
        List<Integer> output = new ArrayList<>();

        for (int i = inputAscending.length - 1; i >= 0; i--) {
            if (sum + Integer.parseInt(inputAscending[i]) <= limit) {
                sum = sum + Integer.parseInt(inputAscending[i]);
                output.add(i);
            }
            if (sum == limit) break;
        }
        Collections.reverse(output);

        return output;
    }

    public static void main(String args[]) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String firstLine = reader.readLine();
            int limit = Integer.parseInt(firstLine.split(" ")[0]);
            int count = Integer.parseInt(firstLine.split(" ")[1]);

            String secondLine = reader.readLine();
            String[] input = secondLine.split(" ");

            List<Integer>  output = CodeJam_2020.best_method(input, limit);
            System.out.println(output.size());
            for (int i = 0; i < output.size(); i++) {
                if (i == output.size() - 1) System.out.println(output.get(i));
                else System.out.print(output.get(i) + " ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> best_method(String[] inputAscending, int limit) {
        int rotation = 0;
        List<Integer> best_pizza_indexes = new ArrayList<>();
        int best_score = 0;

        while (rotation < inputAscending.length) {             // Rotate the array n times and compare the score. Greedy approach.
            String[] rotated_array = new String[inputAscending.length];

            for (int x = 0; x <= inputAscending.length-1; x++){ // rotate the array
                if (x+rotation >= inputAscending.length)  rotated_array[x+rotation -inputAscending.length] = inputAscending[x];
                else rotated_array[x+rotation] = inputAscending[x];
            }

            int total_ordered = 0;        // Total possible for current
            List<Integer> pizza_indexes = new ArrayList<>();
            boolean optimal_Found = false;

            int pizza_types = inputAscending.length;
            Collections.reverse(Arrays.asList(rotated_array));
            for (int i = 0; i < inputAscending.length; i++) { // Iterate through the elements in reverse order
                pizza_types--;
                int temp = total_ordered + Integer.parseInt(rotated_array[i]);

                if (temp > limit) continue;
                else {
                    total_ordered = temp;
                    int index = pizza_types - rotation;
                    if (index < 0) index = inputAscending.length + index;

                    pizza_indexes.add(0,index);

                    if (temp == limit) {
                        optimal_Found = true;
                        break;
                    }
                }
            }
                   //Check if this is the optimal combination
                    Collections.sort(pizza_indexes);
                    int score = 0;
                    for (int y = 0; y < pizza_indexes.size(); y++) {
                        score += Integer.parseInt(inputAscending[pizza_indexes.get(y)]);
                    }

                    if (rotation == 0) best_pizza_indexes = pizza_indexes;
                    else if (best_score < score) best_pizza_indexes = pizza_indexes;

                    if (rotation == inputAscending.length - 1) return best_pizza_indexes;

                    if (optimal_Found) return pizza_indexes;
                    else {
                        rotation++;
                    }
        }
        return best_pizza_indexes;
    }
}