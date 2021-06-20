package app.domain.adapter;

import app.domain.algorithms.BruteForceAlgorithm;

import java.util.ArrayList;

public class BruteForceAdapter implements BiggestContiguousSubSequenceAlgorithm {

    @Override
    public int[] getBiggestContiguousSubSequence(int[] sequence) {
        ArrayList<Integer> subSequenceTemp = BruteForceAlgorithm.biggestSumSubSequence(sequence);
        int[] subSequence = new int[subSequenceTemp.size()];
        for (int i = 0; i < subSequenceTemp.size(); i++) {
            subSequence[i] = subSequenceTemp.get(i);
        }
        return subSequence;
    }
}
