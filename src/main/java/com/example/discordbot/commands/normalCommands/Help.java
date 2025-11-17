package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.commands.managers.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.Instant;

public class Help implements Command {
    private final CommandManager manager;

    public Help(CommandManager manager) { this.manager = manager; }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        try {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor("Help");
            for (Command command:manager.getCommands().values()){
                try {
                    eb.addField(("/"+command.getName()),command.getDescription(event),true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            eb.setFooter(event.getJDA().getSelfUser().getName(),event.getJDA().getSelfUser().getAvatarUrl());
            eb.setTimestamp(Instant.now());
            eb.setColor(new Color(0x6A0DAD));

            MessageEmbed embed = eb.build();

            event.getMessage().replyEmbeds(embed).setComponents(ActionRow.of(Button.link("https://discord.com/invite/p2g9wkrgSV","Support"))).queue();
        } catch (Exception e) {
            event.getMessage().reply(Messages.get(event.getGuild().getId(),"generalError")).queue();
            e.printStackTrace();
        }
    }

    @Override
    public String getName() { return "Help"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.INFORMATION; }

    @Override
    public String getDescription(MessageReceivedEvent event) { return Messages.get(event.getGuild().getId(),"helpDescription"); }
}
