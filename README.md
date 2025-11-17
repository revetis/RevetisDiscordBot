Revetis Discord Bot

Java 20 ve JDA kullanılarak geliştirilmiş modern ve modüler Discord botu. Güçlü moderasyon araçları, log sistemi ve özelleştirilebilir sunucu ayarları sunar.

🇹🇷 Türkçe
Özellikler

Java 20 + JDA ile hızlı ve kararlı yapı

Slash komut desteği

Moderasyon komutları (ban, kick, timeout…)

Log sistemi

Sunucuya özel ayarlar

Çoklu dil desteği

Gereksinimler

Java 20

Gradle

Discord Bot Token

Kurulum
1. Depoyu klonla
git clone <repo-url>
cd RevetisDiscordBot

2. config.json oluştur

src/main/resources/config.json içine:

{
  "token": "BOT_TOKEN",
  "defaultLanguage": "en",
  "logChannel": "LOG_CHANNEL_ID"
}

3. Çalıştır
gradle build
java -jar build/libs/RevetisDiscordBot.jar

Komutlar

/ban — Kullanıcıyı yasaklar

/kick — Kullanıcıyı atar

/language — Sunucu dilini ayarlar

/settings — Sunucu ayarlarını yönetir

🇬🇧 English
Features

Fast and stable structure with Java 20 + JDA

Slash command support

Moderation tools (ban, kick, timeout…)

Logging system

Guild-specific settings

Multi-language support

Requirements

Java 20

Gradle

Discord Bot Token

Setup
1. Clone the repository
git clone <repo-url>
cd RevetisDiscordBot

2. Create config.json

Place this inside src/main/resources/config.json:

{
  "token": "BOT_TOKEN",
  "defaultLanguage": "en",
  "logChannel": "LOG_CHANNEL_ID"
}

3. Run
gradle build
java -jar build/libs/RevetisDiscordBot.jar

Commands

/ban — bans a member

/kick — kicks a member

/language — changes server language

/settings — server configuration panel
