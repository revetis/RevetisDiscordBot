package com.example.discordbot.commands.interfaces;

import com.example.discordbot.commands.CommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface SlashCommand {

    void execute(SlashCommandInteractionEvent event);

    default Boolean isActive(){
        return true;
    }

    String getName();

    CommandCategory getCategory();

    String getDescription(SlashCommandInteractionEvent event);

    String getDescription();

    int permissionLevel();

    OptionData[] getOptions();
}
