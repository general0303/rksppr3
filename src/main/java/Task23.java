import rx.Observable;

import java.util.Random;

public class Task23 {
    public static void main(String[] args) {
        Random rand = new Random();
        Integer[] array = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array[i] = rand.nextInt(1000);
        }
        Observable<Integer> observable = Observable.from(array);
        observable.subscribe(System.out::println);
        System.out.println("__________________________________");
        observable = observable.take(5);
        observable.subscribe(System.out::println);
    }
}
