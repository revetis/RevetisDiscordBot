<!-- Banner: değiştirmek için URL'yi değiştir -->
[![banner](https://raw.githubusercontent.com/<kullanici>/<repo>/main/assets/banner.png)](https://github.com/<kullanici>/<repo>)

# Revetis Discord Bot

Java 20 · Gradle · JDA

---

![Java](https://img.shields.io/badge/Java-20-informational?logo=java&style=flat)
![Gradle](https://img.shields.io/badge/Gradle-Installed-success?logo=gradle&style=flat)
![JDA](https://img.shields.io/badge/JDA-Enabled-blue?style=flat)

## Hızlı Bakış
- **Platform:** Java 20, Gradle  
- **Kütüphane:** JDA  
- **Özellikler:** Moderasyon, Slash komutları, Log sistemi, Çoklu dil

---

## Kurulum (kısa)
1. `git clone <repo-url>`  
2. `cd RevetisDiscordBot`  
3. `src/main/resources/config.json` oluştur:
```json
{
  "token":"BOT_TOKEN",
  "defaultLanguage":"tr",
  "logChannel":"LOG_CHANNEL_ID"
}
gradle build

java -jar build/libs/RevetisDiscordBot.jar

Komutlar
/ban — kullanıcıyı yasaklar

/kick — kullanıcıyı atar

/language — sunucu dilini ayarlar

/settings — sunucu ayar paneli
