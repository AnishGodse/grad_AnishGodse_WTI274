import java.util.*;
import java.util.concurrent.*;

class Bikers implements Callable<Bikers> {

    private String name;
    private int distanceToFinish;
    private int speed;
    private long endTimeMs;
    private CountDownLatch startSignal;
    
    public Bikers(String name, int raceDistance, CountDownLatch startSignal) {
        this.name = name;
        this.distanceToFinish = raceDistance;
        this.speed = (int) (Math.random() * 10) + 1;
        this.startSignal = startSignal;
    }

    public String getBikerName() {
        return name;
    }

    public long getEndTimeMs() {
        return endTimeMs;
    }

    public void display() {
        System.out.println("Name: " + name + " | Distance left: " + distanceToFinish);
    }

    
    public void displayAll(long raceStartTimeMs){
        long elapsedMs = endTimeMs - raceStartTimeMs;
        System.out.println("Name: " + name + " | Finished at: " + endTimeMs +" | Elapsed: " + elapsedMs + " ms | Speed: " + speed);
    }


    @Override
    public Bikers call() throws Exception {

        startSignal.await();

        while (distanceToFinish > 0) {
            distanceToFinish -= speed;
            if (distanceToFinish < 0)
                distanceToFinish = 0;

            display();
            Thread.sleep(100);
        }

        endTimeMs = System.currentTimeMillis();
        System.out.println(name + " finished at " + endTimeMs);

        return this;
    }
}



public class BikeGame {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter race distance: ");
        int raceDistance = sc.nextInt();
        System.out.print("Enter number of bikers: ");
        int n = sc.nextInt();
        sc.nextLine();

        CountDownLatch startSignal = new CountDownLatch(1);
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(processors);

        List<Future<Bikers>> results = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter name of biker " + i + ": ");
            String name = sc.nextLine();

            Bikers biker = new Bikers(name, raceDistance, startSignal);
            results.add(es.submit(biker));
        }

        System.out.println("\nAll bikers ready");
        Thread.sleep(2000);
        System.out.println("RACE STARTS\n");

        long raceStartTime = System.currentTimeMillis();
        startSignal.countDown(); 

        
        List<Bikers> finished = new ArrayList<>(results.size());
        for (Future<Bikers> f : results) {
            finished.add(f.get());
        }

        finished.sort(Comparator.comparingLong(Bikers::getEndTimeMs));

        System.out.println("Final results!===============>");
        int rank = 1;
        for (Bikers b : finished) {
            System.out.print(rank + " - ");
            b.displayAll(raceStartTime);
            rank++;
        }


        es.shutdown();
    }
}