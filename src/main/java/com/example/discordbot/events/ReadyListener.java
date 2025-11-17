package com.example.discordbot.events;

import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.managers.CommandManager;
import com.example.discordbot.db.DatabaseManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
    private final CommandManager commandManager;

    public ReadyListener(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Revetis Bot Aktif! - "+ event.getJDA().getSelfUser().getName());
        event.getJDA().getPresence().setStatus(OnlineStatus.IDLE);
        event.getJDA().getPresence().setActivity(Activity.customStatus("/help | V1"));
        int i = 0;
        for (Command command : commandManager.getCommands().values()) {
            try {
                i++;
                System.out.println(i+" - Komut Yuklendi: " + command.getName());
            } catch (Exception e) {
                i++;
                System.out.println(i+" - Komut Yuklenirken hata olustu: " + e);
            }
        }

        DatabaseManager.connect();

    }
    }

