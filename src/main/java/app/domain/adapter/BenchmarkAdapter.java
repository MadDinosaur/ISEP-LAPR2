package app.domain.adapter;

import com.isep.mdis.Sum;

public class BenchmarkAdapter implements BiggestContiguousSubSequenceAlgorithm {

    @Override
    public int[] getBiggestContiguousSubSequence(int[] sequence) {
        return Sum.Max(sequence);
    }
}
