package models;

import java.util.List;

public class UserLists {
    User[] users;
    List<Integer> userIds;

    public UserLists(User[] users, List<Integer> userIds) {
        this.users = users;
        this.userIds = userIds;
    }

    public User[] getUsers() {
        return users;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }
}
