package app.domain.adapter;

import com.isep.mdis.Sum;

public class BenchmarkAdapter implements  BiggestContiguousSumAlgorithm {

    @Override
    public int[] getBiggestContiguousSubSequence(int[] sequence) {
        return Sum.Max(sequence);
    }
}
