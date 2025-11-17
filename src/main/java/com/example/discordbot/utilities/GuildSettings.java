package com.example.discordbot.utilities;

import com.example.discordbot.db.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GuildSettings {

    public static Long getLogChannelId(String guildId) {
        try {
            PreparedStatement stmt = DatabaseManager.getConnection()
                    .prepareStatement("SELECT log_channel_id FROM guild_settings WHERE guild_id = ?");
            stmt.setString(1, guildId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Long.valueOf(rs.getString("log_channel_id"));
            }
        } catch (Exception e) {
            System.out.println("Hata olustu: " + e);
        }
        return null;
    }

    public static String getLanguage(String guildId) {
        try {
            PreparedStatement stmt = DatabaseManager.getConnection()
                    .prepareStatement("SELECT language FROM guild_settings WHERE guild_id = ?");
            stmt.setString(1, guildId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String lang = rs.getString("language");
                if (lang != null && !lang.isEmpty()) {
                    return lang;
                }
            }
        } catch (Exception e) {
            System.out.println("Hata olustu: " + e);
        }
        return "en"; // Default English
    }
}
