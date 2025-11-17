package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.db.DatabaseManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.sql.PreparedStatement;

public class SetLanguageSlash implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(hook -> {
            OptionMapping option = event.getOption("language");
            if (option == null) {
                hook.sendMessage("Please provide a language code (e.g., 'en', 'tr').").queue();
                return;
            }

            String lang = option.getAsString().toLowerCase();
            if (!lang.equals("en") && !lang.equals("tr")) {
                hook.sendMessage("Invalid language code. Supported: 'en', 'tr'").queue();
                return;
            }

            try {
                String updateSql = "UPDATE guild_settings SET language = ? WHERE guild_id = ?";
                PreparedStatement updateStmt = DatabaseManager.getConnection().prepareStatement(updateSql);
                updateStmt.setString(1, lang);
                updateStmt.setString(2, event.getGuild().getId().trim());
                int rowsAffected = updateStmt.executeUpdate();

                if (rowsAffected == 0) {
                    String insertSql = "INSERT INTO guild_settings (guild_id, language) VALUES (?, ?)";
                    PreparedStatement insertStmt = DatabaseManager.getConnection().prepareStatement(insertSql);
                    insertStmt.setString(1, event.getGuild().getId().trim());
                    insertStmt.setString(2, lang);
                    insertStmt.executeUpdate();
                }

                hook.sendMessage("Server language set to: " + lang).queue();
            } catch (Exception e) {
                hook.sendMessage("Failed to update language.").queue();
                e.printStackTrace();
            }
        }, error -> error.printStackTrace());
    }


    @Override
    public String getName() { return "SetLanguage"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MODERATION; }

    @Override
    public String getDescription(SlashCommandInteractionEvent event) { return Messages.get(event.getGuild().getId(),"setLanguageDescription"); }

    @Override
    public String getDescription() { return Messages.get("setLanguageDescription"); }

    @Override
    public int permissionLevel() {
        return 10;
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{
                new OptionData(OptionType.STRING,"language","language code",true).addChoice("English","en").addChoice("Turkish","tr")
        };
    }
}
