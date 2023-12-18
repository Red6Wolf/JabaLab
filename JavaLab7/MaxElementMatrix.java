public class MaxElementMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        MaxFinder[] threads = new MaxFinder[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            threads[i] = new MaxFinder(matrix[i]);
            threads[i].start();
        }

        int max = Integer.MIN_VALUE;
        for (MaxFinder thread : threads) {
            try {
                thread.join();
                if (thread.getMax() > max) {
                    max = thread.getMax();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Max Element: " + max);
    }
}

class MaxFinder extends Thread {
    private int[] row;
    private int max;

    public MaxFinder(int[] row) {
        this.row = row;
        this.max = Integer.MIN_VALUE;
    }

    public void run() {
        for (int value : row) {
            if (value > max) {
                max = value;
            }
        }
    }

    public int getMax() {
        return max;
    }
}
