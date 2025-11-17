package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.localization.Messages;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping implements Command {

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        long ping = event.getJDA().getGatewayPing();
        event.getMessage().reply("Pong " + ping + "ms").queue();
    }

    @Override
    public String getName() { return "Ping"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.INFORMATION; }

    @Override
    public String getDescription(MessageReceivedEvent event) { return Messages.get(event.getGuild().getId(),"pingDescription"); }
}
