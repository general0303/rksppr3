public class Task3UserFriend {
    private final int userId;
    private final int friendId;

    public int getUserId() {
        return userId;
    }

    public int getFriendId() {
        return friendId;
    }

    Task3UserFriend(int userId, int friendId){
        this.userId = userId;
        this.friendId = friendId;
    }
}
