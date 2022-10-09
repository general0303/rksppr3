import rx.Observable;

import java.util.Random;

public class Task22 {
    public static void main(String[] args){
        Random rand = new Random();
        Integer[] array = new Integer[10];
        for (int i=0; i<10; i++){
            array[i] = rand.nextInt(1000);
        }
        Observable<Integer> observable = Observable.from(array);
        observable.subscribe(System.out::println);
        System.out.println("__________________________________");
        Integer[] array2 = new Integer[10];
        for (int i=0; i<10; i++){
            array2[i] = rand.nextInt(1000);
        }
        Observable<Integer> observable2 = Observable.from(array2);
        observable2.subscribe(System.out::println);
        System.out.println("__________________________________");
        Observable<Integer> observableUnion = Observable.concat(observable, observable2);
        observableUnion.subscribe(System.out::println);
    }
}
