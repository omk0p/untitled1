package c.m.ff;

import c.m.utils.Utils;

public class LinearFF implements FitnessFunction {

	private static final int GENOME_SIZE = 13;
	long k;
	
	public long[] input() {
		long[] r = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		return r;
	}

	public double[] target() {
		double[] r = { 4650, 9300, 13950, 18600, 23250, 27900, 32550, 37200, 41850, 46500 };
		return r;
	}

	@Override
	public long evaluate(String bits, long[] inputs, double[] targets) {
		char sign = bits.charAt(0);
		bits = bits.substring(1);
		this.k = Integer.parseInt(bits, 2);
		if (sign == '1') {
			this.k = -this.k;
		}
		double[] output = outputsInner(inputs);
		return Utils.mse(output, targets);
	}

	public double[] outputsInner(long[] inputs) {
		double[] output = new double[inputs.length];
		int i = 0;
		for (long in : inputs) {
			output[i] = in * k;
			i++;
		}
		return output;
	}

	@Override
	public double[] output() {
		return outputsInner(input());
	}

	@Override
	public int getGenomeSize() {
		return GENOME_SIZE;
	}

}
