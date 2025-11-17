package com.example.discordbot.events;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.example.discordbot.utilities.GuildSettings;

import java.util.Objects;


public class OnGuildMemberJoin extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Long logChannelId = GuildSettings.getLogChannelId(event.getGuild().getId());

        if (logChannelId == null)return;

        if (event.getGuild().getTextChannelById(logChannelId) != null){
            Objects.requireNonNull(event.getGuild().getTextChannelById(logChannelId)).sendMessage("Welcome, "+"<@"+event.getUser().getId()+">").queue();
        }
    }
}
