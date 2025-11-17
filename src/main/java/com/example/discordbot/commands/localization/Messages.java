package com.example.discordbot.commands.localization;

import com.example.discordbot.utilities.GuildSettings;

import java.util.HashMap;
import java.util.Map;

public class Messages {

    private static final Map<String, Map<String, String>> guildMessages = new HashMap<>();

    static {
        // -------------------------
        // English messages
        // -------------------------
        Map<String, String> en = new HashMap<>();

        // --- General ---
        en.put("generalError", "An error occurred.");
        en.put("unknownCommand", "You used an unknown command.");
        en.put("unauthorized", "You do not have sufficient permission to use this command.");
        en.put("specialOfRevetis", "This command is specific to the bot maker, sorry.");
        en.put("notFoundMember", "I couldn't find the member you specified on the server.");
        en.put("isNotActive","This command is not active");

        // --- Help / Info ---
        en.put("help", "Revetis Bot Help Menu\nSupport: [Discord](https://discord.gg/nQygF6Sz5P)\nPrefix `!` or `/`\n\n");
        en.put("helpDescription", "Shows the bot's commands");
        en.put("pingDescription", "Shows the current ping of the bot");

        // --- Language ---
        en.put("setLanguage_missing_param", "You must provide a language code, e.g., 'en' or 'tr'.");
        en.put("setLanguage_invalid", "Invalid language code. Supported: 'en', 'tr'.");
        en.put("setLanguage_success", "Server language has been successfully updated.");
        en.put("setLanguage_error", "There was a problem updating the server language. Please try again later.");
        en.put("setLanguageDescription", "Sets the server's language");

        // --- Log Channel ---
        en.put("setLogChannel_missing_param", "You must provide a channel to set as log channel.");
        en.put("setLogChannel_invalid_channel", "Please select a valid text channel.");
        en.put("setLogChannel_success", "Log channel registered successfully!");
        en.put("setLogChannel_error", "There was a problem saving the log channel. Please try again later.");
        en.put("setLogChannel_not_found", "Could not find a matching channel in this server.");
        en.put("setLogChannelDescription", "Sets a default log channel");

        // --- Ping ---
        en.put("ping_response", "Pong %dms");

        // --- Ban / Moderation ---
        en.put("banDescription", "Bans members from the server");
        en.put("iCantBanMyself", "I can't ban myself");
        en.put("iCantBanOwner", "I can't ban the server owner");
        en.put("banFormatException", "You used the command incorrectly, the correct usage is: `!ban @user reason(optional) delete_days(optional)`");
        en.put("deleteDaysFormatException", "The number of days to delete messages can only be a number.");
        en.put("youCantBanTarget", "You cannot ban this person because they are equal or higher than you in the role hierarchy.");
        en.put("botCantBanTarget", "I cannot ban this user because they are higher than me in the role hierarchy.");
        en.put("banTargetException", "You must write the ID of the user you want to ban or tag them directly.");
        en.put("youCantBanYourSelf","You can't ban yourself");
        en.put("banSuccess", "**%s** has been banned from the server by **%s** for `%s`");
        en.put("reasonLimit","The reason cannot exceed 512 characters.");

        // --- Member Remove / Logs ---
        en.put("memberRemovedKick", "<@%s> was kicked by <@%s> for `%s`.");
        en.put("memberRemovedBan", "<@%s> was banned by <@%s> for `%s`.");
        en.put("memberRemovedTimeout", "<@%s> was timed out by <@%s> for `%d minutes` (Reason: %s).");

        // -------------------------
        // Turkish messages
        // -------------------------
        Map<String, String> tr = new HashMap<>();

        // --- General ---
        tr.put("generalError", "Bir hata oluştu.");
        tr.put("unknownCommand", "Bilinmeyen bir komut kullandınız.");
        tr.put("unauthorized", "Bu komutu kullanmak için yeterli yetkiniz yok.");
        tr.put("specialOfRevetis", "Bu komut sadece bot yapımcısına özeldir, üzgünüm.");
        tr.put("notFoundMember", "Belirttiğiniz kullanıcı sunucuda bulunamadı.");
        tr.put("isNotActive","Bu komut aktif değil");

        // --- Help / Info ---
        tr.put("help", "Revetis Bot Yardım Menüsü\nDestek: [Discord](https://discord.gg/nQygF6Sz5P)\nPrefix `!` veya `/`\n\n");
        tr.put("helpDescription", "Botun komutlarını gösterir");
        tr.put("pingDescription", "Botun mevcut ping değerini gösterir");

        // --- Language ---
        tr.put("setLanguage_missing_param", "Bir dil kodu belirtmelisiniz, örn. 'en' veya 'tr'.");
        tr.put("setLanguage_invalid", "Geçersiz dil kodu. Desteklenen: 'en', 'tr'.");
        tr.put("setLanguage_success", "Sunucu dili başarıyla güncellendi.");
        tr.put("setLanguage_error", "Sunucu dilini güncellerken bir sorun oluştu. Lütfen daha sonra tekrar deneyin.");
        tr.put("setLanguageDescription", "Sunucunun dilini ayarlar");

        // --- Log Channel ---
        tr.put("setLogChannel_missing_param", "Log kanalı olarak ayarlanacak bir kanal belirtmelisiniz.");
        tr.put("setLogChannel_invalid_channel", "Lütfen geçerli bir metin kanalı seçin.");
        tr.put("setLogChannel_success", "Log kanalı başarıyla kaydedildi!");
        tr.put("setLogChannel_error", "Log kanalı veritabanına kaydedilirken bir hata oluştu. Lütfen daha sonra tekrar deneyin.");
        tr.put("setLogChannel_not_found", "Sunucuda eşleşen bir kanal bulamadım.");
        tr.put("setLogChannelDescription", "Varsayılan log kanalını ayarlar");

        // --- Ping ---
        tr.put("ping_response", "Pong %dms");

        // --- Ban / Moderation ---
        tr.put("banDescription", "Sunucudan üyeleri yasaklar");
        tr.put("iCantBanMyself", "Kendimi banlayamam");
        tr.put("iCantBanOwner", "Sunucu sahibini yasaklayamam");
        tr.put("banFormatException", "Komutu yanlış kullandınız. Doğru kullanım: `!ban @kullanıcı sebep(optional) delete_days(optional)`");
        tr.put("deleteDaysFormatException", "Mesaj silme gün sayısı sadece sayı olabilir.");
        tr.put("youCantBanTarget", "Bu kişiyi yasaklayamazsınız çünkü rol hiyerarşisinde sizden eşit veya daha yüksek.");
        tr.put("botCantBanTarget", "Bu kullanıcıyı yasaklayamam çünkü rol hiyerarşisinde benden daha yüksek.");
        tr.put("banTargetException", "Yasaklayamak istediğiniz kullanıcının ID’sini yazmalı veya doğrudan etiketlemelisiniz.");
        tr.put("youCantBanYourSelf", "Kendinizi yasaklayamazsınız");
        tr.put("banSuccess", "**%s** adlı kullanıcı, **%s** adlı yetkili tarafından `%s` nedeniyle sunucudan yasaklandı.");
        tr.put("reasonLimit", "Sebep 512 karakteri geçemez.");

        // --- Member Remove / Logs ---
        tr.put("memberRemovedKick", "<@%s> kullanıcısı, <@%s> tarafından `%s` nedeniyle sunucudan atıldı.");
        tr.put("memberRemovedBan", "<@%s> kullanıcısı, <@%s> tarafından `%s` nedeniyle sunucudan yasaklandı.");
        tr.put("memberRemovedTimeout", "<@%s> kullanıcısı, <@%s> tarafından `%d dakika` süreyle susturuldu (Sebep: %s).");

        // -------------------------
        // Add maps
        // -------------------------
        guildMessages.put("en", en);
        guildMessages.put("tr", tr);
    }

    public static String get(String guildId, String key) {
        String lang = getGuildLanguage(guildId);
        Map<String, String> messages = guildMessages.getOrDefault(lang, guildMessages.get("en"));
        return messages.getOrDefault(key, "Message not found: " + key);
    }

    public static String get(String key) {
        String lang = "en";
        Map<String, String> messages = guildMessages.getOrDefault(lang, guildMessages.get("en"));
        return messages.getOrDefault(key, "Message not found: " + key);
    }

    private static String getGuildLanguage(String guildId) {
        String lang = GuildSettings.getLanguage(guildId);
        return lang.isEmpty() ? "en" : lang;
    }
}
