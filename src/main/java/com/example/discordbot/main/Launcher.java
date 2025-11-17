package com.example.discordbot.main;

import com.example.discordbot.commands.managers.CommandManager;
import com.example.discordbot.commands.managers.SlashCommandManager;
import com.example.discordbot.events.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Launcher {
    public static final CommandManager commandManager = new CommandManager();
    public static final SlashCommandManager slashCommandManager = new SlashCommandManager();

    public static void main(String[] args) throws Exception {

        //BOT BASLATILIYOR
        JDABuilder builder = JDABuilder.createDefault(Settings.getToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.SCHEDULED_EVENTS, GatewayIntent.GUILD_EXPRESSIONS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MODERATION, GatewayIntent.AUTO_MODERATION_CONFIGURATION)
                .addEventListeners(
                        new OnGuildMemberJoin(),
                        new ReadyListener(commandManager),
                        new MessageListener(commandManager, slashCommandManager),
                        new OnGuildMemberLeave()
                );
        slashCommandManager.slashCommandsBuilder(builder.build());


    }
}
