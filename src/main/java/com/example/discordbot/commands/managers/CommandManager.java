package com.example.discordbot.commands.managers;

import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.commands.normalCommands.*;
import com.example.discordbot.main.Settings;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public Map<String, Command> getCommands() {
        return commands;
    }

    public CommandManager(){
        registerCommand(new Ping());
        registerCommand(new Help(this));
        registerCommand(new SetLogChannel());
        registerCommand(new SetLanguage());
        registerCommand(new Ban());
    }

    private void registerCommand(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }
    private int getUserPermissionLevel(Member member) {
        if (member.getIdLong() == member.getGuild().getOwnerIdLong()) return 11;
        int level = 0;

        if (member.hasPermission(Permission.ADMINISTRATOR)) return 10; // Tam yetki, her şey
        if (member.hasPermission(Permission.BAN_MEMBERS)) level = Math.max(level, 7);
        if (member.hasPermission(Permission.KICK_MEMBERS)) level = Math.max(level, 6);
        if (member.hasPermission(Permission.MANAGE_SERVER)) level = Math.max(level, 5);
        if (member.hasPermission(Permission.MANAGE_ROLES)) level = Math.max(level, 5);
        if (member.hasPermission(Permission.MANAGE_CHANNEL)) level = Math.max(level, 4);
        if (member.hasPermission(Permission.MESSAGE_MANAGE)) level = Math.max(level, 3);
        if (member.hasPermission(Permission.NICKNAME_CHANGE)) level = Math.max(level, 2);
        if (member.hasPermission(Permission.MESSAGE_MENTION_EVERYONE)) level = Math.max(level, 1);

        return level;
    }

    public void handle(MessageReceivedEvent event){
        int userLevel = getUserPermissionLevel(Objects.requireNonNull(event.getMember()));
        try {
            String msg = event.getMessage().getContentRaw();

            assert Settings.prefix != null;
            if (!msg.startsWith(Settings.prefix))return;

            String[] parts = msg.substring(Settings.prefix.length()).split(" ");
            String commandName = Normalizer.normalize(parts[0], Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "")
                    .toLowerCase(Locale.ROOT);

            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);
            Command command = commands.get(commandName);
            if (command == null){
                event.getMessage().reply(Messages.get(event.getGuild().getId(),"unknownCommand")).queue();
                return;
            }
            if (command.isSpecialOfRevetis()){
               if (event.getAuthor().getId().equals("690162863762964522")){
                   command.execute(event, args);
               } else {
                  event.getMessage().reply(Messages.get(event.getGuild().getId(),"specialOfRevetis")).queue();
               }
            }else {
                if (userLevel >= command.permissionLevel()) {
                    if (command.isActive()){
                        command.execute(event, args);
                    }else {
                     event.getMessage().reply(Messages.get(event.getGuild().getId(),"isNotActive")).queue();

                    }

                } else {
                    event.getMessage().reply(Messages.get(event.getGuild().getId(),"unauthorized")).queue();
                }
            }




        } catch (Exception e) {
            event.getMessage().reply(Messages.get(event.getGuild().getId(),"generalError")).queue();
            System.out.println("Hata olustu: "+e);
            e.printStackTrace();
        }
    }


}
