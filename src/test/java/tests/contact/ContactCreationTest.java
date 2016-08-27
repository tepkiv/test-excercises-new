package tests.contact;

import fw.ContactData;
import fw.TestBase;
import org.testng.annotations.Test;
import fw.ContactHelper;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    ContactHelper contactHelper = app.getContactHelper();

    @Test(dataProvider = "randomValidContactGenerator")
    public void TestContactCreation(ContactData contact) throws Exception {
        app.navigateTo().mainPage();

        // save old state

        List<ContactData> oldList = contactHelper.getContacts();
        System.out.println("old list " + oldList.size());

        // actions
        contactHelper.openContactPage();
        contactHelper.fillContactForm(contact, ContactHelper.CREATION);
        contactHelper.submitContactCreation();
        contactHelper.openMainPage();

        //save new state
        contactHelper.refreshPage();
        contactHelper.waitUntilContactListAppear();
        List<ContactData> newList = contactHelper.getContacts();
        System.out.println("newList list " + newList.size());
        // compare states
        //assertEquals(newList.size(), (oldList.size() + 1));

        // compare items in the lists
        contact.withFirstName(" ");
        oldList.add(contact);

        System.out.println("old list " + oldList.size());
        Collections.sort(oldList);
        Collections.sort(newList);
        assertEquals(newList, oldList);//Actual Expected
    }
}
