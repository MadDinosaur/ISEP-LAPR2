package app.domain.algorithms;

import java.util.ArrayList;

public class BruteForceAlgorithm {

    public BruteForceAlgorithm() {
        //empty constructor
    }

    public static ArrayList<Integer> biggestSumSubSequence(int[] sequence) {
        int maxSum = sequence[0];
        ArrayList<Integer> maxSubsequenceTemp = new ArrayList<>();
        ArrayList<Integer> maxSubsequence = new ArrayList<>();
        for (int i = 0; i < sequence.length; i++) {
            int sum = 0;
            maxSubsequenceTemp.clear();
            for (int a = i; a < sequence.length; a++) {
                sum = sum + sequence[a];
                maxSubsequenceTemp.add(sequence[a]);
                if (sum > maxSum) {
                    maxSubsequence.clear();
                    maxSum = sum;
                    for (Integer integer : maxSubsequenceTemp) {
                        maxSubsequence.add(integer);
                    }
                }
            }
        }
        return maxSubsequence;
    }
}
