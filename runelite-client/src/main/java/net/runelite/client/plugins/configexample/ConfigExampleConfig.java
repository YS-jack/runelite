package net.runelite.client.plugins.configexample;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("configexample")
public interface ConfigExampleConfig extends Config {
    @ConfigItem(
            position = 1, /* placement order from top*/
            keyName = "booleanConfig", /* name of this config item*/
            name = "Boolean Checkbox", /* display name of config item, should be short*/
            description = "Simple example of a checkbox to store a booleans value" /* mouse hover name*/
    )
    default boolean booleanConfig() {return false;}

    @ConfigItem(
            position = 2,
            keyName = "greeting",
            name = "name of config item 2",
            description = "The message to show to the user when they login"
    )
    default String greeting() {return "hello";}
}
