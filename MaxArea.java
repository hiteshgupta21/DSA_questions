import java.util.*;
class MaxArea {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] convertedArray = new int[m];

        // For 0-th row -->
        for(int j = 0; j < m; j++) {
            convertedArray[j] = matrix[0][j] - '0';
        }


        int maxArea = largestRectangleArea(convertedArray); // Get the max area

        // For the 1 - (n-1) row and 0 - (m-1) col-->
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] == '0') {
                    convertedArray[j] = 0;
                } else {
                    convertedArray[j] += matrix[i][j] - '0';
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(convertedArray));
        }

        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length, area = 0;

        // Find the next smaller left -->
        Stack<Integer> s = new Stack<>();
        int[] nsl = new int[n];
        for(int i = 0; i < n; i++) {
            while(!s.isEmpty() && heights[s.peek()] >= heights[i]) {
                s.pop();
            }

            if(!s.isEmpty()) {
                nsl[i] = s.peek();
            } else {
                nsl[i] = -1;
            }

            s.push(i);
        }


        // Find the next smaller right -->
        s = new Stack<>();
        int[] nsr = new int[n];
        for(int i = n-1; i >= 0; i--) {
            while(!s.isEmpty() && heights[s.peek()] >= heights[i]) {
                s.pop();
            }

            if(!s.isEmpty()) {
                nsr[i] = s.peek();
            } else {
                nsr[i] = n;
            }

            s.push(i);
        }


        // Calculating the area of Histogram -->
        for(int i = 0; i < n; i++) {
            int hei = heights[i];
            int width = nsr[i] - nsl[i] - 1;
            int currArea = hei * width;
            area = Math.max(area, currArea);
        }

        return area;
    }
}
