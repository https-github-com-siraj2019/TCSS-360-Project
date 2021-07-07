package tests;

import javafx.collections.ObservableSet;
import main.java.controller.SettingsHandler;
import main.java.model.Setting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SettingsHandlerTest {

    SettingsHandler handler;
    Setting<String> setting0;

    @BeforeEach
    void setUp() {
        this.handler = new SettingsHandler();
        setting0 = new Setting<String>("TEST", "VAL1");
    }

    @Test
    void addSetting() {
        this.handler.addSetting(setting0);
        Assertions.assertEquals(setting0, this.handler.getSettings().toArray()[0]);
    }


    @Test
    void updateSetting() {
        this.addSetting();
        this.handler.updateSetting(setting0, "UpdatedVal");
        Assertions.assertEquals("UpdatedVal", ((Setting) this.handler.getSettings().toArray()[0]).getValue());
    }

    @Test
    void getSettings() {
        this.addSetting();
        ObservableSet set = handler.getSettings();
        Assertions.assertEquals(set.toArray()[0],setting0);
    }
}