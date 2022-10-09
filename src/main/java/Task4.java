import rx.Observable;
import rx.Observer;

public class Task4 {
    private int count = 0;

    public class Handler implements Runnable{
        Task4File file;
        Handler(Task4File file){
            this.file = file;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(file.getSize()* 70L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
            System.out.println(file.getType() + " файл весом " + file.getSize() + " байт обработан");
        }
    }

    Observer<Task4File> getQueue() {
        return new Observer<>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(Task4File file) {
                Handler handler = new Handler(file);
                new Thread(handler).start();
            }
        };
    }

    public Observable<Task4File> getGenerator(){
        String[] types = new String[]{"xml", "json", "xls"};
        return Observable.create(subscriber -> {
                    Runnable r = () -> {
                        while (true){
                            String type = types[(int)(Math.random()*3)];
                            int size = (int)(Math.random()*90)+10;
                            try {
                                Thread.sleep((int)(Math.random()*900)+100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Task4File file = new Task4File(type, size);
                            System.out.println(type + " файл весом " + size + " байт сгенерирован");
                            while (count == 4){
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println(file.getType() + " файл весом " + file.getSize() + " байт добавлен в очередь");
                            count++;
                            subscriber.onNext(file);
                        }
                    };
                    new Thread(r).start();
                }
        );
    }

    public static void main(String[] args){
        Task4 task4 = new Task4();
        Observer<Task4File> queue = task4.getQueue();
        Observable<Task4File> generator = task4.getGenerator();
        generator.subscribe(queue);
    }
}
