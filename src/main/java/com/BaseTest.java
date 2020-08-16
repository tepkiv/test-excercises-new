package com;


import com.data.ContactData;
import com.data.ContactDataGenerator;
import com.data.GroupData;
import com.data.GroupDataGenerator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.util.*;


public class BaseTest {
    public static WebDriver driver;
    protected ApplicationManager app;
    int checkCounter;
    int checkFrequency;

    @BeforeTest
    public void setUp() throws Exception {
        // set default properties file
        String configFile = System.getProperty("configFile", "application.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(new File(configFile)));
        app = new ApplicationManager(properties);
        driver = ApplicationManager.getDriver();
        checkCounter = 0;
        checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
    }

    public boolean ttc(){
        checkCounter++;
        if(checkCounter>checkFrequency){
            checkCounter=0;
            return true;

        }else{
            return false;
        }

    }

    @AfterTest
    public void tearDown() throws Exception {
        app.stop();
    }

    public static String generateRandomString() {
        Random rnd = new Random();
        if (rnd.nextInt(3) == 0) {
            return "";
        } else {
            return "tests.test" + rnd.nextInt();
        }
    }

    /* Contact */
    @DataProvider
    public Iterator<Object[]> randomValidContactGenerator() {
        return wrapContactsForDataProvider(ContactDataGenerator.generateRandomContacts(1)).iterator();
    }

    protected List<Object[]> wrapContactsForDataProvider(List<ContactData> listContactData) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (ContactData contact : listContactData) {
            list.add(new Object[]{contact});
        }
        return list;
    }

    /* Group */
    @DataProvider
    public Iterator<Object[]> randomValidGroupGenerator() {
        return wrapGroupsForDataProvider(GroupDataGenerator.generateRandomGroups(3)).iterator();
    }

    protected List<Object[]> wrapGroupsForDataProvider(List<GroupData> listGroupData) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (GroupData group : listGroupData) {
            list.add(new Object[]{group});
        }
        return list;
    }

    public void open(String s) {
        driver.get(s);
    }

    private void open() {
        driver.get("google.com");
    }

}