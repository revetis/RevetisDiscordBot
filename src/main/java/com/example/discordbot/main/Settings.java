package com.example.discordbot.main;
import io.github.cdimascio.dotenv.Dotenv;

public class Settings{
    static Dotenv dotenv = Dotenv.configure()
            .directory("./")
            .load();
    private static final String token = dotenv.get("TOKEN");
    public static final String name =  dotenv.get("NAME");
    public static final String prefix = dotenv.get("PREFIX");

    protected static String getToken(){
        return token;
    }
}