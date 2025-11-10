package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping implements Command {

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        try {
            long ping = event.getJDA().getGatewayPing();
            event.getChannel().sendMessage("Pong "+ping+"ms").queue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "Ping";
    }

    public CommandCategory getCategory(){
        return CommandCategory.INFORMATION;
    }

    public String getDescription(){
        return "Shows the current ping of the bot";
    }
}
