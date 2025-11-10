package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.managers.SlashCommandManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelpSlash implements SlashCommand {
    private final SlashCommandManager manager;

    public HelpSlash(SlashCommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            StringBuilder sb = new StringBuilder("Revetis Bot Help Menu\nIf you need help join the [support server](https://discord.gg/nQygF6Sz5P)\n\n");
            for (SlashCommand slashCommand:manager.getCommands().values()){
                sb.append("`").append(slashCommand.getName()).append("` - ")
                        .append(slashCommand.getDescription())
                        .append(" (").append(slashCommand.getCategory()).append(")\n");
            }
            event.getChannel().sendMessage(sb.toString()).queue();
        } catch (Exception e) {
            System.out.println("Hata olustu: "+e);
        }
    }
    @Override
    public String getName() {
        return "Help";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.INFORMATION;
    }

    @Override
    public String getDescription() {
        return "Shows the Bot's Commands and Usage";
    }
}
