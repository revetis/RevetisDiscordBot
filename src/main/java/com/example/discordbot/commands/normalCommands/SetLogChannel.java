package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.db.DatabaseManager;
import net.dv8tion.jda.api.entities.MessageActivity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.PreparedStatement;
import java.util.List;

public class SetLogChannel implements Command {

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length < 1) {
            event.getMessage()
                    .reply(Messages.get(event.getGuild().getId(),"setLogChannel_missing_param"))
                    .queue();
            return;
        }

        String input = args[0];
        Long channelId = null;
        String channelName = null;

        if (input.startsWith("<#")) channelId = Long.parseLong(input.replaceAll("[^0-9]", ""));
        else if (input.startsWith("#")) channelId = Long.parseLong(input.substring(1));
        else channelName = input;

        List<TextChannel> channels = event.getGuild().getTextChannels();
        boolean found = false;

        for (TextChannel chn : channels) {
            if ((channelId != null && chn.getIdLong() == channelId) ||
                    (channelName != null && chn.getName().equalsIgnoreCase(channelName))) {
                setDataBaseLogChannel(event, chn);
                found = true;
                break;
            }
        }

        if (!found) {
            event.getMessage().reply(Messages.get(event.getGuild().getId(),"setLogChannel_not_found")).queue();
        }
    }

    public void setDataBaseLogChannel(MessageReceivedEvent event, TextChannel channel) {
        try {
            long channelId = channel.getIdLong();
            String sql = "INSERT INTO guild_settings (guild_id, log_channel_id) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE log_channel_id = ?";
            PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql);
            stmt.setString(1, event.getGuild().getId());
            stmt.setString(2, Long.toString(channelId));
            stmt.setString(3, Long.toString(channelId));
            stmt.executeUpdate();

            event.getMessage().reply(Messages.get(event.getGuild().getId(),"setLogChannel_success")).queue();
        } catch (Exception e) {
            event.getMessage().reply(Messages.get(event.getGuild().getId(),"setLogChannel_error")).queue();
            e.printStackTrace();
        }
    }

    @Override
    public String getName() { return "SetLogChannel"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MODERATION; }

    @Override
    public String getDescription(MessageReceivedEvent event) { return Messages.get(event.getGuild().getId(),"setLogChannelDescription"); }

    @Override
    public int permissionLevel() { return 10; }
}
