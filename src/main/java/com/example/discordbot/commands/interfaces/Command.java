package com.example.discordbot.commands.interfaces;

import com.example.discordbot.commands.CommandCategory;
import kotlin.io.encoding.Base64;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {
    void execute(MessageReceivedEvent event, String[] args);
    default Boolean isActive(){
        return true;
    }
    String getName();
    CommandCategory getCategory();
    String getDescription(MessageReceivedEvent event);
    default Boolean isSpecialOfRevetis(){
        return false;
    };
    default int permissionLevel() {
        return 0;
    }
}
