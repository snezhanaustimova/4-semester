package units;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends TestCase {

    public void testGetAllUsers() {
        User user = new User("Сергей", 23, Sex.MALE);
        User user1 = new User("Мария", 23, Sex.FEMALE);
        List<User> actual = User.getAllUsers();
        List<User> expected = new ArrayList<User>();
        expected.add(user);
        expected.add(user1);
        Assert.assertEquals(expected, actual);
    }

    public void testTestGetAllUsers() {
    }

    public void testGetCount() {
    }

    public void testTestGetCount() {
    }
}