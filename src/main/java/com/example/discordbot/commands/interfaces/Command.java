package com.example.discordbot.commands.interfaces;

import com.example.discordbot.commands.CommandCategory;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command {
    void execute(MessageReceivedEvent event, String[] args);
    String getName();
    CommandCategory getCategory();
    String getDescription();
}
