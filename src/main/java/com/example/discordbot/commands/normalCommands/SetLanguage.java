package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.db.DatabaseManager;
import com.example.discordbot.commands.localization.Messages;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.sql.PreparedStatement;

public class SetLanguage implements Command {

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length < 1) {
            event.getMessage()
                    .reply(Messages.get(event.getGuild().getId(), "setLanguage_missing_param"))
                    .queue();
            return;
        }

        String lang = args[0].toLowerCase();
        if (!lang.equals("en") && !lang.equals("tr")) {
            event.getMessage()
                    .reply(Messages.get(event.getGuild().getId(), "setLanguage_invalid"))
                    .queue();
            return;
        }

        try {
            String sql = "INSERT INTO guild_settings (guild_id, language) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE language = VALUES(language)";
            PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(sql);
            stmt.setString(1, event.getGuild().getId().trim()); // guild_id
            stmt.setString(2, lang.trim());                     // language
            stmt.executeUpdate();

            event.getMessage()
                    .reply(Messages.get(event.getGuild().getId(), "setLanguage_success"))
                    .queue();
        } catch (Exception e) {
            event.getMessage()
                    .reply(Messages.get(event.getGuild().getId(), "setLanguage_error"))
                    .queue();
            e.printStackTrace();
        }
    }

    @Override
    public String getName() { return "SetLanguage"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MODERATION; }

    @Override
    public String getDescription(MessageReceivedEvent event) {
        return Messages.get(event.getGuild().getId(),"setLanguageDescription");
    }

    @Override
    public int permissionLevel() { return 10; } // Yöneticiler
}
