package tests.contact;

import fw.pages.ContactData;
import tests.TestBase;
import fw.utils.ModifiedSortedList;
import org.testng.annotations.Test;
import fw.pages.ContactHelper;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactCreationTest extends TestBase {

    @Test(dataProvider = "randomValidContactGenerator")
    public void TestContactCreation(ContactData contact) throws Exception {
        String generatedFirstname = generateRandomString();
        ContactHelper contactHelper = app.getContactHelper();

        // save old state
        ModifiedSortedList<ContactData> oldList = contactHelper.getContacts();
        System.out.println("old list " + oldList.size());

        // actions
        contactHelper.createContact(contact.withFirstName(generatedFirstname));

        //save new state
        contactHelper.waitUntilContactListAppear();
        ModifiedSortedList<ContactData> newList = contactHelper.getContacts();
        System.out.println("newList list " + newList.size());
        // compare states
        //assertEquals(newList.size(), (oldList.size() + 1));

        // compare items in the lists
        //oldList.add(contact.withFirstName(generatedFirstname));

        System.out.println("old list " + oldList.size());
        //assertEquals(newList, oldList,"Should be equals");//Actual Expected
        //oldList.add(contact.withFirstName(generatedFirstname));
        //assertTrue(newList.equals(oldList));
        assertThat(newList, equalTo(oldList.withAdded(contact.withFirstName(generatedFirstname))));
    }

}
