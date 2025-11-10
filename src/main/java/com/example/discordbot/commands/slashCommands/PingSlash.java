package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingSlash implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        try {
            long ping = event.getJDA().getGatewayPing();
            event.getChannel().sendMessage("Pong "+ping+"ms").queue();
        } catch (Exception e) {
            event.reply("An error occurred. The error has been reported to the developer. Please try again later.").queue();
            System.out.println("Hata olustu: "+e);
        }
    }
    @Override
    public String getName() {
        return "Ping";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.INFORMATION;
    }

    @Override
    public String getDescription() {
        return "Shows the current ping of the bot";
    }
}
