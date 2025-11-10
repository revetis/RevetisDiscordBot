package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.managers.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help implements Command {
    private final CommandManager manager;

    public Help(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        StringBuilder sb = new StringBuilder("Revetis Bot Help Menu\nIf you need help join the [support server](https://discord.gg/nQygF6Sz5P)\n\n");
        for (Command command:manager.getCommands().values()){
            sb.append("`").append(command.getName()).append("` - ")
                    .append(command.getDescription())
                    .append(" (").append(command.getCategory()).append(")\n");
        }
        event.getChannel().sendMessage(sb.toString()).queue();
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
