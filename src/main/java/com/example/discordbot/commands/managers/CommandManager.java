package com.example.discordbot.commands.managers;

import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.normalCommands.Help;
import com.example.discordbot.commands.normalCommands.Ping;
import com.example.discordbot.main.Settings;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commands = new HashMap<>();

    public Map<String, Command> getCommands() {
        return commands;
    }

    public CommandManager(){
        registerCommand(new Ping());
        registerCommand(new Help(this));
    }

    private void registerCommand(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public void handle(MessageReceivedEvent event){
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
            if (command != null) {
                command.execute(event, args);
            }




        } catch (Exception e) {
            event.getChannel().sendMessage("An error occurred. The error has been reported to the developer. Please try again later.").queue();
            System.out.println("Hata olustu: "+e);
        }
    }


}
