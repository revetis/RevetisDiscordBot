package com.example.discordbot.commands.interfaces;

import com.example.discordbot.commands.CommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommand {
    String getName();
    CommandCategory getCategory();
    String getDescription();

    void execute(SlashCommandInteractionEvent event);
}
