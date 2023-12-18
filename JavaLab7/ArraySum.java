public class ArraySum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        SumThread thread1 = new SumThread(arr, 0, arr.length / 2);
        SumThread thread2 = new SumThread(arr, arr.length / 2, arr.length);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalSum = thread1.getSum() + thread2.getSum();
        System.out.println("Total Sum: " + totalSum);
    }
}

class SumThread extends Thread {
    private int[] arr;
    private int start;
    private int end;
    private int sum;

    public SumThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
    }

    public int getSum() {
        return sum;
    }
}
