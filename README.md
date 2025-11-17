Revetis Discord Bot

Java 20 ve JDA kullanılarak geliştirilmiş, modüler, ölçeklenebilir ve çoklu dil destekli Discord botu.

Genel Özellikler

Java 20 tabanlı yüksek performanslı mimari

JDA ile stabil Discord API iletişimi

Slash komut sistemi

Moderasyon araçları (ban, kick, timeout)

Loglama ve guild bazlı ayarlar

Çoklu dil sistemi (Türkçe / İngilizce)

Gradle ile kolay kurulum ve dağıtım

Proje Yapısı
RevetisDiscordBot/
 ├── src/
 │   └── main/
 │       ├── java/                # Botun tüm kaynak kodları
 │       ├── resources/           # config ve dil dosyaları
 │       │    ├── config.json
 │       │    └── languages/
 │       └── ...
 ├── build.gradle
 ├── settings.gradle
 └── README.md

Kurulum
1. Depoyu klonlayın
git clone <repo-url>
cd RevetisDiscordBot

2. config.json oluşturun

src/main/resources/config.json içine:

{
  "token": "BOT_TOKEN",
  "defaultLanguage": "en",
  "logChannel": "LOG_CHANNEL_ID"
}

3. Build / Çalıştırma
gradle build
java -jar build/libs/RevetisDiscordBot.jar

Komutlar
Komut	Açıklama	English Description
/ban	Üyeyi yasaklar	Bans a member
/kick	Üyeyi atar	Kicks a member
/timeout	Zamana dayalı susturma verir	Applies timeout to a member
/language	Dil ayarı	Changes server language
/settings	Sunucu ayar paneli	Server configuration panel
Yükleme & Çalıştırma Notları

Java 20 gerektirir.

Gradle wrapper ile ek yapılandırma gerektirmez.

JDA güncel sürüm ile uyumludur.

Dil dosyaları resources/languages klasöründen otomatik yüklenir.

Guild bazlı ayarlar hafızada tutulur veya istenirse dosya/veritabanına taşınabilir.

Uluslararası (English) Bölümü
Overview

Revetis Discord Bot is a modular and scalable bot built with Java 20 and JDA.
It provides a clean command system, moderation tools, guild-specific settings and multi-language support.

Requirements

Java 20

Gradle

Discord Bot Token

Setup
git clone <repo-url>
cd RevetisDiscordBot


Create config.json:

{
  "token": "BOT_TOKEN",
  "defaultLanguage": "en",
  "logChannel": "LOG_CHANNEL_ID"
}


Build & Run:

gradle build
java -jar build/libs/RevetisDiscordBot.jar

Geliştirme Notları

Komutlar arayüz tabanlıdır ve yeni komut eklemek oldukça kolaydır.

JDA event sistemi ile entegre çalışır.

Gerektiğinde veritabanı desteği eklenebilir.

Loglama sistemi genişletilebilir yapıdadır (moderasyon, kullanıcı hareketi, hata kayıtları).

Çoklu dil sistemi anahtar tabanlıdır ve yeni dil eklemek için yalnızca JSON dosyası eklemek yeterlidir.

Lisans

Bu proje özgürce geliştirilebilir, değiştirilebilir ve dağıtılabilir.
