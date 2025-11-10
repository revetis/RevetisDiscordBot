package com.example.discordbot.commands.managers;

import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.slashCommands.HelpSlash;
import com.example.discordbot.commands.slashCommands.PingSlash;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.Map;

public class SlashCommandManager {
    private final Map<String, SlashCommand> commands = new HashMap<>();

    public Map<String, SlashCommand> getCommands() {
        return commands;
    }

    public SlashCommandManager(){
        registerCommand(new HelpSlash(this));
        registerCommand(new PingSlash());
    }

    private void registerCommand(SlashCommand command){
        commands.put(command.getName().toLowerCase(), command);
    }

    public void handle(SlashCommandInteractionEvent event){
        SlashCommand command = commands.get(event.getName().toLowerCase());

        if (command != null){
            command.execute(event);
        }else {
            event.reply("Bilinmeyen komut.").setEphemeral(true).queue();
        }

    }
}
