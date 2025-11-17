package com.example.discordbot.events;

import com.example.discordbot.utilities.GuildSettings;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Objects;

public class OnGuildMemberLeave extends ListenerAdapter {

    public void onGuildMemberLeave(GuildMemberRemoveEvent event){
        Long logChannelId = GuildSettings.getLogChannelId(event.getGuild().getId());

        if (logChannelId == null)return;

        if (event.getGuild().getTextChannelById(logChannelId) != null){
            Objects.requireNonNull(event.getGuild().getTextChannelById(logChannelId)).sendMessage("Bye Bye, "+"<@"+event.getUser().getId()+">").queue();
        }
    }
}
