package net.runelite.client.plugins.configexample;


import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.MenuAction;
import net.runelite.api.events.MenuOptionClicked;

@Slf4j
@PluginDescriptor(
        name = "Config Example",
        description = "A simple example plugin to illustrate how to write plugin config menus",
        tags = {"config", "menu"},
        //loadWhenOutdated = true,
        enabledByDefault = false
)

public class ConfigExamplePlugin extends Plugin
{
    @Inject
    private Client client;


    @Inject
    private ConfigExampleConfig config;

    @Override
    protected void startUp() throws Exception
    {
        log.info("Example started");
    }

    @Override
    protected  void shutDown() throws  Exception
    {
        log.info("Example stopped");
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged)
    {
        if(gameStateChanged.getGameState() == GameState.LOGGED_IN)
        {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says: " + config.greeting(), null);
        }
    }
    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (event.getMenuAction() == MenuAction.NPC_FIRST_OPTION
                || event.getMenuAction() == MenuAction.NPC_SECOND_OPTION
                || event.getMenuAction() == MenuAction.NPC_THIRD_OPTION
                || event.getMenuAction() == MenuAction.NPC_FOURTH_OPTION
                || event.getMenuAction() == MenuAction.NPC_FIFTH_OPTION) {

            // Log or perform your action here
            String npcAction = event.getMenuOption(); // E.g., "Talk-to"
            String npcName = event.getMenuTarget();   // E.g., "Banker"
            int npcIndex = event.getId();             // NPC index, not ID

            log.info("Action: " + npcAction + ", NPC: " + npcName + ", Index: " + npcIndex);
            // You can add more logic here to react to the interaction
        }
    }

    @Subscribe
    // get dialog content when talking with npc
    public void onChatMessage(ChatMessage event){
        if (event.getType() == ChatMessageType.DIALOG) {
            String dialogueText = event.getMessage();
            String sender = event.getSender();
            String name = event.getName();

            log.info("dialogue text = " + dialogueText);
            event.setMessage("Japanese Man|I want to speak Japanese!");
        }
    }

    @Provides
    ConfigExampleConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ConfigExampleConfig.class);
    }
}
/*dialog option: Id	14352384

normal dialog:
S 231.4 DIALOG_NPC_NAME     Id	15138820
S 231.5                     Id	15138821 (click here to continue)
S 231.6 DIALOG_NPC_TEXT     Id	15138822 (the text itself)

 */