package com.example.discordbot.commands.managers;

import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.commands.slashCommands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.*;

public class SlashCommandManager {

    private final Map<String, SlashCommand> commands = new HashMap<>();

    public Map<String, SlashCommand> getCommands() {
        return commands;
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

    public SlashCommandManager(){
        registerCommand(new HelpSlash(this));
        registerCommand(new PingSlash());
        registerCommand(new SetLogChannelSlash());
        registerCommand(new SetLanguageSlash());
        registerCommand(new BanSlash());
    }

    private void registerCommand(SlashCommand command){
        commands.put(command.getName().toLowerCase(), command);
    }

    public void handle(SlashCommandInteractionEvent event){

        if (!event.isFromGuild()) return;
        if (event.getUser().isBot()) return;


        SlashCommand command = commands.get(event.getName().toLowerCase());
        if (command == null) {
            if (event.getHook() != null)
                event.getHook().sendMessage("Bilinmeyen komut.").queue();
            return;
        }

        int userLevel = getUserPermissionLevel(Objects.requireNonNull(event.getMember()));
        if (!(userLevel >= command.permissionLevel())){
            if (event.getHook() != null)
                event.getHook().sendMessage(Messages.get(event.getGuild().getId(),"unauthorized")).queue();
            return;
        }
        if (!command.isActive()){
            if (event.getHook() != null)
                event.getHook().sendMessage(Messages.get(event.getGuild().getId(),"isNotActive")).queue();
            return;
        }

        try {
            command.execute(event);
        } catch (Exception e) {
            if (event.getHook() != null)
                event.getHook().sendMessage(Messages.get(event.getGuild().getId(),"generalError")).queue();
            e.printStackTrace();
        }
    }

    public void slashCommandsBuilder(JDA jda){
        List<CommandData> data = new ArrayList<>();

        for (Map.Entry<String, SlashCommand> entry : commands.entrySet()){
            String name = entry.getKey();
            SlashCommand cmd = entry.getValue();

            SlashCommandData slash = Commands.slash(name, cmd.getDescription());



            OptionData[] options = cmd.getOptions();

            if (options.length > 0) {
                slash.addOptions(options);
            }

            data.add(slash);
        }

        jda.updateCommands().addCommands(data).queue(success -> {
            System.out.println("Slash komutlari guncellendi");
        },
        error ->{
            System.out.println("Slash komutlari guncellenirken hata oldu");
        });
    }

}
