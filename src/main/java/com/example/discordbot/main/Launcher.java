package com.example.discordbot.main;
import com.example.discordbot.commands.managers.CommandManager;
import com.example.discordbot.commands.managers.SlashCommandManager;
import com.example.discordbot.events.MessageListener;
import com.example.discordbot.events.OnGuildMemberJoin;
import com.example.discordbot.events.ReadyListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Launcher {
    public static final CommandManager commandManager = new CommandManager();
    public static final SlashCommandManager slashCommandManager = new SlashCommandManager();
    public static void main(String[] args) throws Exception {

        //BOT BASLATILIYOR
        JDABuilder builder = JDABuilder.createDefault(Settings.getToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(
                        new OnGuildMemberJoin(),
                        new ReadyListener(commandManager),
                        new MessageListener(commandManager, slashCommandManager)
                );

        builder.build().awaitReady().updateCommands()
                .addCommands(
                        Commands.slash("ping", "Shows the bot's ping"),
                        Commands.slash("help", "Shows command list")
                ).queue();

    }}
