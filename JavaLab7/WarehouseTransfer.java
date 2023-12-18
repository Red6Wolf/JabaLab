import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseTransfer {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition enoughWeight = lock.newCondition();

        Goods goods = new Goods();

        Loader loader1 = new Loader("Loader 1", goods, lock, enoughWeight);
        Loader loader2 = new Loader("Loader 2", goods, lock, enoughWeight);
        Loader loader3 = new Loader("Loader 3", goods, lock, enoughWeight);

        loader1.start();
        loader2.start();
        loader3.start();
    }
}

class Goods {
    private int totalWeight = 0;
    public final int MAX_WEIGHT = 150; // Теперь MAX_WEIGHT public

    public synchronized void addWeight(int weight) {
        totalWeight += weight;
    }

    public boolean enoughWeight(int weight) {
        return totalWeight + weight <= MAX_WEIGHT;
    }

    public synchronized void resetWeight() {
        totalWeight = 0;
    }

    public synchronized int getTotalWeight() {
        return totalWeight;
    }
}

class Loader extends Thread {
    private final String name;
    private final Goods goods;
    private final ReentrantLock lock;
    private final Condition enoughWeight;

    public Loader(String name, Goods goods, ReentrantLock lock, Condition enoughWeight) {
        this.name = name;
        this.goods = goods;
        this.lock = lock;
        this.enoughWeight = enoughWeight;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                int itemWeight = 10; // Вес товара
                while (!goods.enoughWeight(itemWeight)) {
                    try {
                        enoughWeight.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                goods.addWeight(itemWeight);
                System.out.println(name + " added weight: " + itemWeight + ", Total weight: " + goods.getTotalWeight());
                if (goods.getTotalWeight() == goods.MAX_WEIGHT) {
                    System.out.println("Reached maximum weight! " + name + " is going to unload...");
                    goods.resetWeight();
                    enoughWeight.signalAll(); // Сигнализируем остальным потокам о возможности добавления веса
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
