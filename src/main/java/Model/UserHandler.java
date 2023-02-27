package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for handling multiple users. Uses the Singleton pattern since you only want one
 * instance of the UserHandler.
 */
public class UserHandler {

    public List<User> userList;
    private int currentUserIndex;

    private static UserHandler instance = new UserHandler();

    private UserHandler() {
        userList = new ArrayList<>();
        currentUserIndex = 0;
    }

    public static UserHandler getInstance() {
        return instance;
    }

    /**
     * Contains pretty straightforward and simple methods for adding, removing, setting, getting
     * and switching the current user.
     */

    public void addNewUser(int id, int startValue, String startName){
        userList.add(new User(id, startValue, startName));
    }

    public void removeCurrentUser(){
        userList.remove(currentUserIndex);
        if (userList.size() > 0) {
            currentUserIndex = (currentUserIndex - 1) % userList.size();
        } else {
            currentUserIndex = 0;
        }
    }

    public User getCurrentUser() {
        return userList.get(currentUserIndex);
    }

    public void setCurrentUser(User user) {
        if (userList.contains(user)) {
            currentUserIndex = userList.indexOf(user);
        } else {
            System.out.println("user not found");
        }
    }

    public User nextUser() {
        currentUserIndex = (currentUserIndex + 1) % userList.size();
        return userList.get(currentUserIndex);
    }

    /**
     * A method for resetting the userList if that would be needed for some reason.
     */

    public void resetUserList() {
        userList = new ArrayList<>();
        currentUserIndex = 0;
    }


}
