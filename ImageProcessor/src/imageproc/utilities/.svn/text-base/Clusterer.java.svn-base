/*
 * Clusterer.java
 *
 * Created on February 1, 2001, 5:54 PM
 */

package imageproc.utilities;

/**
 * Implementation of several unsupervised clustering algorithms. The primary
 * algorithms are those defined in Pattern Recognition Using Neural Networks,
 * Carl G. Looney, 1997.
 * 
 * @author Karl A. Gossett
 * @version %I%,%G%
 */
public class Clusterer extends java.lang.Object {

	private double[] vector;

	private int initialClusters;

	private int targetClusters;

	private double splittingThreshold;

	private double lumpingDistance;

	private int minClusterSize;

	private int[] classAssigned;

	private double[] mse;

	private double totMSE;

	/** Creates new Clusterer */
	public Clusterer() {
	}

	private void isoDataLump() {
	}

	private void isoDatainit() {
	}

	private void isoDataClusterAssign() {
	}

	private void isoDataRemoveSmall() {
	}

	private void isoDataCalcStats() {
	}

	private void isoDataReorganize() {
	}

	/**
	 * Perform K-Means clustering on the input vector. Uses standard k-means
	 * algorithm to cluster data into specified number of clusters. This will
	 * cluster the data into AT MOST the specified number of clusters. It will
	 * start at one and expand as required. (So although there are n clusters
	 * established, some k of them might be empty).
	 * 
	 * @param numClusters
	 *            The desired number of clusters.
	 * 
	 * @return An integer array containing the cluster assignments.
	 */
	public int[] kMeansClassify(int numClusters) {
		if (numClusters < 1)
			return null;

		double[] z = new double[numClusters];
		int[] n = new int[numClusters];
		double[] center = new double[numClusters];
		classAssigned = new int[vector.length];
		boolean change_flag = true;

		// Initialize classes
		System.arraycopy(vector, 0, z, 0, vector.length);
		int iterations = 1000;
		// Begin clustering
		while (change_flag) {
			java.util.Arrays.fill(n, 0);
			// Assign each sample to the closest cluster (based on cluster
			// center
			for (int q = 0; q < vector.length; q++) {
				double minD = Double.MAX_VALUE;
				int minK = 0;
				for (int k = 0; k < numClusters; k++) {
					if (Math.abs(vector[q] - z[k]) < minD) {
						minD = Math.abs(vector[q] - z[k]);
						minK = k;
					}
				}
				classAssigned[q] = minK;
				n[minK]++;
			}

			// Compute new center averages
			double[] newZ = new double[numClusters];
			mse = new double[numClusters];
			totMSE = 0;
			for (int k = 0; k < numClusters; k++) {
				newZ[k] = 0;
				mse[k] = 0;
				for (int q = 0; q < vector.length; q++) {
					if (classAssigned[q] == k)
						newZ[k] += vector[q];
				}
				newZ[k] = newZ[k] / n[k];
				for (int q = 0; q < vector.length; q++) {
					if (classAssigned[q] == k)
						mse[k] += Math.pow(Math.abs(vector[q] - newZ[k]), 2.0);
				}
				mse[k] = mse[k] / n[k];
				totMSE += mse[k];
			}

			change_flag = false;
			for (int k = 0; k < numClusters; k++) {
				if (Math.abs(newZ[k] - z[k]) > 0.0005) {
					change_flag = true;
				}
			}

			if (change_flag) {
				System.arraycopy(newZ, 0, z, 0, newZ.length);
			}
			iterations--;
			if (iterations < 0) {
				change_flag = false;
				System.out.println("Couldn't find optimum clustering.");
			}
		}

		// Sort classes in increasing order of cluster center
		double[] sort = new double[numClusters];
		System.arraycopy(z, 0, sort, 0, z.length);
		java.util.Arrays.sort(sort);
		int[] newK = new int[numClusters];
		for (int k = 0; k < numClusters; k++) {
			int kn = 0;
			for (int k2 = 0; k2 < numClusters; k2++) {
				if (sort[k2] == z[k]) {
					kn = k2;
				}
			}
			newK[k] = kn;
		}
		for (int q = 0; q < classAssigned.length; q++) {
			int c = classAssigned[q];
			if (newK[c] == numClusters - 1) {
				classAssigned[q] = 8;
			} else if (n[newK[c] + 1] == 0) {
				classAssigned[q] = 8;
			} else {
				classAssigned[q] = newK[c];
			}
		}
		return classAssigned;
	}

	/**
	 * Set the vector data for clustering.
	 */
	public void setVector(double[] vect) {
		vector = new double[vect.length];
		System.arraycopy(vect, 0, vector, 0, vect.length);
	}
}