

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int counter = 0;
        String challenger = "";
        String champion = "";

        while (!StdIn.isEmpty()) {
            String currentWord = StdIn.readString();
            double forBernoulli = 1 / (counter + 1.0);

            boolean chooser = StdRandom.bernoulli(forBernoulli);

            if (chooser) {
                champion = currentWord;
            }
            else challenger = currentWord;

            counter++;
        }
        if (!champion.equals(""))
            StdOut.println(champion);
        else
            StdOut.println(challenger);

    }
}
