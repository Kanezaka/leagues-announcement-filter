package com.github.kanezaka;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatLineBuffer;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Leagues Announcement Filter",
        description = "End the spam."
)
public class LeaguesAnnouncementFilterPlugin extends Plugin {
    @Inject
    private Client client;

    @Override
    protected void startUp() throws Exception {
        log.info("LeaguesAnnouncementFilterPlugin started!");
        client.refreshChat();
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("LeaguesAnnouncementFilterPlugin stopped!");
        client.refreshChat();
    }

    @Subscribe(priority = -2) // run after ChatMessageManager
    public void onChatMessage(ChatMessage chatMessage) {
        if (chatMessage.getType().equals(ChatMessageType.CLAN_MESSAGE)) {
            final String LEAGUES_ICON_TXT = "<img=22>";
            if (chatMessage.getMessage().startsWith(LEAGUES_ICON_TXT)) {
                ChatLineBuffer clanMessages = client.getChatLineMap().get(chatMessage.getMessageNode().getType().getType());
                if (clanMessages != null) {
                    clanMessages.removeMessageNode(chatMessage.getMessageNode());
                    log.debug("Removed message: {}", chatMessage.getMessage());
                }
            }
        }
    }
}
