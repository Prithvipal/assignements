
import java.util.ArrayList;
import java.util.List;



public class MouseCheeseProblem {

	public static void main(String[] args) {
        
		MouseCheeseProblem cheeseProblem = new MouseCheeseProblem();
		//Integer[] input = {8, 25,  50, 100, 10, 110};
		//Integer[] input = {8, 25,  50, 100, 10, 40};
		Integer[] input = {8, 5,  10, 100, 10, 5};
		Integer[][] adjMatrix = cheeseProblem.createAdjMatrix(input);
		Integer totalFromZeroth = cheeseProblem.calculateLargestCheese(input, adjMatrix, 0);
		Integer totalFromFirst = cheeseProblem.calculateLargestCheese(input, adjMatrix, 1);
		Integer finalTotal = totalFromZeroth > totalFromFirst ? totalFromZeroth : totalFromFirst;
		System.out.println(finalTotal);
	}
	
	/**
	 * Finds the largest path from given starting point. Ideally starting point should be 0 or 1
	 * @param input
	 * @param adjMatrix
	 * @param startRow
	 * @return total weightage of largest path
	 */
	private Integer calculateLargestCheese(Integer[] input, Integer[][] adjMatrix, int startRow) {
		List<Integer> visited = new ArrayList<>();
		visited.add(startRow);
		findLargestPath(adjMatrix, startRow, visited);
		Integer total =  0;
		for (Integer index : visited) {
			total += input[index];
		}
		return total;
	}
	/**
	 * This method creates undirected graph adjacency matrix for given input array
	 * @param input Ex: {8, 25,  50, 100, 10, 110}
	 * @return Adjacency matrix
	 * {
	 * {null, null, 58, 108, 18, 118},
	 * {null, null, null, 125, 35, 135}
	 * {58, null, null, null, 60, 160}
	 * {108, 125, null, null, null, 210}
	 * {18, 35, 60, null, null, null}
	 * {118, 135, 160, 210, null, null}
	 * 
	 * }
	 */
	private Integer[][] createAdjMatrix(Integer[] input) {
		Integer[][] adjMetric = new Integer[input.length][input.length];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if(j<i-1 || j>i+1) {
					adjMetric[i][j] = input[i] + input[j];
				}
			}
		}
		return adjMetric;
	}
	
	
	/**
	 * It find largest path in graph from provided index. 
	 * @param adjMatrix 2D matrix represents undirected graph
	 * @param nextRow nexRow should be 0 or 1. 
	 * @param visited list of indexes of largest path. In each recursive call, the new index largest added into it.
	 */
	private void findLargestPath(Integer[][] adjMatrix, int nextRow, List<Integer> visited) {
		
		int midIndex = adjMatrix.length / 2;
		if(nextRow <= midIndex) {
			int max = 0;
			int maxIndex = -1;
			for(int i= nextRow+2; i< adjMatrix.length; i++) {
				if(adjMatrix[nextRow][i] > max && !visited.contains(i) && !visited.contains(i+1) && !visited.contains(i-1)) {
					max = adjMatrix[nextRow][i];
					maxIndex = i;
				}
			}
			if (maxIndex != -1) {
				visited.add(maxIndex);
				findLargestPath(adjMatrix, maxIndex, visited);
			}
		}else {
			int max = 0;
			int maxIndex = -1;
			for(int i=0; i < adjMatrix.length-2; i++) {
				if(adjMatrix[nextRow][i] > max && !visited.contains(i) && !visited.contains(i+1) && !visited.contains(i-1)) {
					max = adjMatrix[nextRow][i];
					maxIndex = i;
				}
			}
			if (maxIndex != -1) {
				visited.add(maxIndex);
				findLargestPath(adjMatrix, maxIndex, visited);
			}
		}
	}
}
