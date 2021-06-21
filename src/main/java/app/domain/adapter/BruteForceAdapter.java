package app.domain.adapter;

import app.domain.algorithms.BruteForceAlgorithm;

import java.util.ArrayList;

public class BruteForceAdapter implements BiggestContiguousSubSequenceAlgorithm {

    /**
     * Adapts the algorithm to fit into our standards
     * @param sequence sequence
     * @return sub sequence
     */
    @Override
    public int[] getBiggestContiguousSubSequence(int[] sequence) {
        ArrayList<Integer> subSequenceTemp = BruteForceAlgorithm.biggestContiguousSubSequence(sequence);
        int[] subSequence = new int[subSequenceTemp.size()];
        for (int i = 0; i < subSequenceTemp.size(); i++) {
            subSequence[i] = subSequenceTemp.get(i);
        }
        return subSequence;
    }
}
