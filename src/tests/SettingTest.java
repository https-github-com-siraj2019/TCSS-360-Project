package tests;

import main.java.model.Setting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SettingTest {


    Setting<String> setting0;

    @BeforeEach
    void setUp() {
        setting0 = new Setting<String>("TEST", "VAL1");
    }

    @Test
    void setValue() {
        setting0.setValue("UPDATED");
        Assertions.assertEquals("UPDATED", setting0.getValue());
    }

    @Test
    void compareTo() {
        // fails on differing UUIDs
        Setting setting1 = new Setting("TEST", "VAL1");
        Assertions.assertNotEquals(0, setting0.compareTo(setting1));
        Assertions.assertEquals(0, setting0.compareTo(setting0));
        // Different objects with same UUID pass
        setting1 = new Setting(setting0.getID(),"TEST", "VAL1", true);
        Assertions.assertEquals(0, setting0.compareTo(setting1));
    }

    @Test
    void compareToByName() {
        // fails on differing UUIDs
        Setting setting1 = new Setting("TEST", "VAL1");
        Assertions.assertEquals(0, setting0.compareByName(setting1));
        // Different objects with same UUID pass
        setting1 = new Setting("TEST", "VAL2");
        Assertions.assertEquals(0, setting0.compareByName(setting1));
        setting1 = new Setting("TEST1", "VAL1");
        Assertions.assertNotEquals(0, setting0.compareTo(setting1));
    }

}