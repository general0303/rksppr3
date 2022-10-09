import rx.Observable;

import java.util.Random;

public class Task21 {
    public static void main(String[] args){
        Random rand = new Random();
        Integer[] array = new Integer[1000];
        for (int i=0; i<1000; i++){
            array[i] = rand.nextInt(1000);
        }
        Observable<Integer> observable = Observable.from(array);
        observable.subscribe(System.out::println);
        System.out.println("__________________________________");
        observable = observable.filter(a ->  a > 500);
        observable.subscribe(System.out::println);
    }
}
