package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.db.DatabaseManager;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.GuildChannelUnion;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.sql.PreparedStatement;
import java.util.Objects;

public class SetLogChannelSlash implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> {
            OptionMapping option = event.getOption("channel");
            if (option == null) {
                hook.sendMessage("Please provide a channel option.").queue();
                return;
            }

            TextChannel textChannel = option.getAsChannel().asTextChannel();


            try {
                long channelId = textChannel.getIdLong();
                String sql = "INSERT INTO guild_settings (guild_id, log_channel_id) VALUES (?, ?) " +
                        "ON DUPLICATE KEY UPDATE log_channel_id = ?";
                PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql);
                stmt.setString(1, event.getGuild().getId());
                stmt.setString(2, Long.toString(channelId));
                stmt.setString(3, Long.toString(channelId));
                stmt.executeUpdate();

                hook.sendMessage("Log channel registered successfully!").queue();
            } catch (Exception e) {
                hook.sendMessage("Failed to save log channel to the database.").queue();
                e.printStackTrace();
            }
        }, error -> error.printStackTrace());
    }


    @Override
    public String getName() { return "SetLogChannel"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MODERATION; }

    @Override
    public String getDescription(SlashCommandInteractionEvent event) { return Messages.get(Objects.requireNonNull(event.getGuild()).getId(),"setLogChannelDescription"); }

    @Override
    public String getDescription() {
        return Messages.get("setLogChannelDescription");
    }

    @Override
    public int permissionLevel() {
        return 10;
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{
                new OptionData(OptionType.CHANNEL,"channel","Select a channel",true).setChannelTypes(ChannelType.TEXT)
        };
    }
}
