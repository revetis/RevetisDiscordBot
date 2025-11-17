package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.localization.Messages;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class PingSlash implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> {
            long ping = event.getJDA().getGatewayPing();
            hook.sendMessage("Pong " + ping + "ms").queue();
        }, error -> error.printStackTrace());
    }


    @Override
    public String getName() { return "Ping"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.INFORMATION; }

    @Override
    public String getDescription(SlashCommandInteractionEvent event) { return Messages.get(event.getGuild().getId(),"pingDescription"); }
    @Override
    public String getDescription() { return Messages.get("pingDescription"); }

    @Override
    public int permissionLevel() {
        return 0;
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }
}
