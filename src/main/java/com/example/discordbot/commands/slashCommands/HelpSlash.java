package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.commands.managers.SlashCommandManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


import java.awt.*;
import java.time.Instant;

public class HelpSlash implements SlashCommand {
    private final SlashCommandManager manager;

    public HelpSlash(SlashCommandManager manager) { this.manager = manager; }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> { // Defer ile hemen acknowledge
            try {
                EmbedBuilder eb = new EmbedBuilder();
                eb.setAuthor("Help");
                for (SlashCommand command : manager.getCommands().values()) {
                    try {
                        eb.addField("/" + command.getName(), command.getDescription(event), true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                eb.setFooter(event.getJDA().getSelfUser().getName(), event.getJDA().getSelfUser().getAvatarUrl());
                eb.setTimestamp(Instant.now());
                eb.setColor(new Color(0x6A0DAD));

                MessageEmbed embed = eb.build();

                hook.sendMessageEmbeds(embed)
                        .setEphemeral(true)
                        .setComponents(ActionRow.of(Button.link("https://discord.com/invite/p2g9wkrgSV","Support")))
                        .queue();

            } catch (Exception e) {
                hook.sendMessage(Messages.get(event.getGuild().getId(),"generalError")).queue();
                e.printStackTrace();
            }
        }, error -> error.printStackTrace());
    }


    @Override
    public String getName() { return "Help"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.INFORMATION; }

    @Override
    public String getDescription(SlashCommandInteractionEvent event) { return Messages.get(event.getGuild().getId(),"helpDescription"); }

    @Override
    public String getDescription() { return Messages.get("helpDescription"); }

    @Override
    public int permissionLevel() {
        return 0;
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[0];
    }
}
