import rx.Observable;

import java.util.ArrayList;
import java.util.Random;

public class Task3 {
    Observable<Task3UserFriend> userFriend;

    Observable<Task3UserFriend> getFriend(int userId){
        return Observable.concat(userFriend.filter(u -> u.getFriendId() == userId), userFriend.filter(u -> u.getUserId() == userId));
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Task3UserFriend[] array = new Task3UserFriend[100];
        for (int i = 0; i < 100; i++) {
            int userId = rand.nextInt(50);
            int friendId = rand.nextInt(50);
            while (userId == friendId){
                friendId = rand.nextInt(50);
            }
            Task3UserFriend user = new Task3UserFriend(userId, friendId);
            array[i] = user;
            //user = new Task3UserFriend(friendId, userId);
            //array[i+1] = user;
        }
        Task3 task3 = new Task3();
        task3.userFriend = Observable.from(array);
        task3.userFriend.subscribe(a -> System.out.println(a.getUserId() + " " + a.getFriendId()));
        System.out.println("______________________");
        Integer[] id = new Integer[]{1, 3, 5};
        Observable<Integer> ids = Observable.from(id);
        ids.subscribe(it -> task3.getFriend(it).subscribe(a -> System.out.println(a.getUserId() + " " + a.getFriendId())));
    }
}
