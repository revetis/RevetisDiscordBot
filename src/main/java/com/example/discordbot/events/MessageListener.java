package com.example.discordbot.events;

import com.example.discordbot.commands.managers.CommandManager;
import com.example.discordbot.commands.managers.SlashCommandManager;
import com.example.discordbot.main.Settings;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    private final CommandManager commandManager;
    private final SlashCommandManager slashCommandManager;

    public MessageListener(CommandManager commandManager, SlashCommandManager slashCommandManager){
        this.commandManager = commandManager;
        this.slashCommandManager = slashCommandManager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            Message message = event.getMessage();
            String content = message.getContentRaw();
            User author = event.getAuthor();
            if (author.isBot())return;
            if (!event.isFromGuild()) {
                author.openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage("Commands Only Work on the Server").queue();
                });
                return;
            }
            Guild guild = event.getGuild();

            if (content.startsWith(Settings.prefix)){
                commandManager.handle(event);
            }

        } catch (Exception e) {
            event.getMessage().reply("An error occurred. The error has been reported to the developer. Please try again later.").queue();
            System.out.println("Hata olustu: "+e);
        }
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        try {
            if (event.getUser().isBot())return;
            if (!event.isFromGuild()){
                event.getUser().openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage("Commands Only Work on the Server").queue();
                });
                return;
            }
            slashCommandManager.handle(event);
        } catch (Exception e) {
            event.reply("An error occurred. The error has been reported to the developer. Please try again later.").queue();
            System.out.println("Hata olustu: "+e);
        }
    }
}
