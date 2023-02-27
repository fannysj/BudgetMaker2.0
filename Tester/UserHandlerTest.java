import Model.User;
import Model.UserHandler;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing for UserHandler class.
 */

public class UserHandlerTest {

    private UserHandler setup() {
        UserHandler UH = UserHandler.getInstance();
        UH.resetUserList();

        UH.addNewUser(1, 300, "Maja");
        UH.addNewUser(2, 300, "Lukas");
        UH.addNewUser(3, 300, "Lycke");
        return UH;
    }

    @Test
    public void addingUsers() {
        UserHandler UH = setup();
        assertEquals(UH.userList.size(), 3);

    }

    @Test
    public void removingUser() {
        UserHandler UH = setup();
        UH.removeCurrentUser();
        assertEquals(2, UH.userList.size());
    }

    @Test
    public void switchToNextUser() {
        UserHandler UH = setup();
        User currentUser = UH.getCurrentUser();
        User nextUser = UH.nextUser();
        assertNotEquals(currentUser, nextUser);
    }

    @Test
    public void setNewCurrentUser() {
        UserHandler UH = setup();
        User oldCurrentUser = UH.getCurrentUser();
        User nextUser = UH.nextUser();
        UH.setCurrentUser(nextUser);
        assertNotEquals(oldCurrentUser, UH.getCurrentUser());
    }


}
