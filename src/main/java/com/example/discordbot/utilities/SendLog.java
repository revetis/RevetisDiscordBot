package com.example.discordbot.utilities;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class SendLog {

    public static void sendLog(MessageReceivedEvent event, String message, Object... formatArgs) {
        Long logChannelID = GuildSettings.getLogChannelId(event.getGuild().getId());
        if (logChannelID == null) return;

        TextChannel logChannel = event.getGuild().getTextChannelById(logChannelID);
        if (logChannel == null) return;

        String finalMessage;


        if (formatArgs != null && formatArgs.length > 0) {
            try {
                finalMessage = String.format(message, formatArgs);
            } catch (Exception e) {
                finalMessage = message;
            }
        } else {
            finalMessage = message;
        }

        logChannel.sendMessage(finalMessage).queue();
    }
    public static void sendLog(SlashCommandInteractionEvent event, String message, Object... formatArgs) {
        Long logChannelID = GuildSettings.getLogChannelId(event.getGuild().getId());
        if (logChannelID == null) return;

        TextChannel logChannel = event.getGuild().getTextChannelById(logChannelID);
        if (logChannel == null) return;

        String finalMessage;


        if (formatArgs != null && formatArgs.length > 0) {
            try {
                finalMessage = String.format(message, formatArgs);
            } catch (Exception e) {
                finalMessage = message;
            }
        } else {
            finalMessage = message;
        }

        logChannel.sendMessage(finalMessage).queue();
    }
}

