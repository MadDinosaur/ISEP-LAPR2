package app.domain.adapter;

import com.isep.mdis.Sum;

public class BenchmarkAdapter implements BiggestContiguousSubSequenceAlgorithm {

    /**
     * Adapts the algorithm to fit into our standards
     * @param sequence sequence
     * @return sub sequence
     */
    @Override
    public int[] getBiggestContiguousSubSequence(int[] sequence) {
        return Sum.Max(sequence);
    }
}
