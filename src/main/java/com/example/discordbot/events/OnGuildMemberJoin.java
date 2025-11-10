package com.example.discordbot.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class OnGuildMemberJoin extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        List<TextChannel> channels = event.getGuild().getTextChannelsByName("incoming-members", true);
        if (!channels.isEmpty()){
            TextChannel channel = channels.get(0);
            channel.sendMessage("Welcome " + event.getMember().getEffectiveName()).queue();
        }
    }
}
