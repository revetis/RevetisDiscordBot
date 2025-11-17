package com.example.discordbot.main;
import io.github.cdimascio.dotenv.Dotenv;

public class Settings{
    static Dotenv dotenv = Dotenv.configure().directory("F:\\Yazilim\\RevetisBot\\RevetisDiscordBot\\.env")
            .load();
    private static final String token = dotenv.get("TOKEN");
    public static final String name =  dotenv.get("NAME");
    public static final String prefix = dotenv.get("PREFIX");
    public static final String dbHost = dotenv.get("DB_HOST");
    public static final String dbUser = dotenv.get("DB_USER");
    public static final String dbName = dotenv.get("DB_NAME");
    public static final String dbPass = dotenv.get("DB_PASS");
    public static final String dbURL = dotenv.get("DB_URL");

    protected static String getToken(){
        return token;
    }
}