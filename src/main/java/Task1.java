import rx.Observable;
import rx.Observer;

import java.util.Random;

public class Task1 {

    public Observable<Integer> getTemperature(){
        Random rand = new Random();
        return Observable.create(subscriber -> {
                Runnable r = () -> {
                    while (true){
                        int temperature = rand.nextInt(15) + 15;
                        subscriber.onNext(temperature);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(r).start();
            }
        );
    }

    public Observable<Integer> getCO2(){
        Random rand = new Random();
        return Observable.create(subscriber -> {
                    Runnable r = () -> {
                        while (true){
                            int co2 = rand.nextInt(70) + 30;
                            subscriber.onNext(co2);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    new Thread(r).start();
                }
        );
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Observable<Integer> temperature = task1.getTemperature();
        Observable<Integer> co2 = task1.getCO2();
        Observable<Integer[]> observable = Observable.zip(temperature, co2, (t, c) -> new Integer[]{t, c});
        Observer<Integer[]> observer = new Observer<>() {

            @Override
            public void onNext(Integer[] a) {
                System.out.println(a[0] + " " + a[1]);
                if (a[0] > 25 && a[1] > 70) {
                    System.out.println("ALARM!!!");
                } else if (a[0] > 25) {
                    System.out.println("Warning. Temperature");
                } else if (a[1] > 70) {
                    System.out.println("Warning. CO2");
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onCompleted() {
            }
        };
        observable.subscribe(observer);
    }
}
