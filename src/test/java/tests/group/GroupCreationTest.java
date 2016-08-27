package tests.group;

import static org.testng.Assert.assertEquals;

import fw.GroupData;
import fw.GroupHelper;
import fw.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class GroupCreationTest extends TestBase {

    GroupHelper groupHelper = app.getGroupHelper();

    @Test(dataProvider = "randomValidGroupGenerator")
    public void testValidGroupCreation(GroupData group) throws Exception {
        app.navigateTo().mainPage();
        app.navigateTo().groupsPage();

        List<GroupData> oldList = groupHelper.getGroups();

        groupHelper.createGroup(group);

        List<GroupData> newList = groupHelper.getGroups();
        //assertEquals(newList.size(), oldList.size() + 1);
        oldList.add(group);
        Collections.sort(oldList);
        assertEquals(newList, oldList);
    }
}
