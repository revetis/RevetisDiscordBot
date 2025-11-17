package com.example.discordbot.commands.normalCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.Command;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.utilities.SendLog;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Ban implements Command {

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        if (args.length < 1) {
            event.getMessage().reply(Messages.get(event.getGuild().getId(), "banFormatException")).queue();
            return;
        }

        String target = args[0];
        int deleteDays = 0;
        String reason;

        if (args.length > 1) {
            try {
                deleteDays = Integer.parseInt(args[args.length - 1]);
                reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length - 1));

                if (reason.length() > 512) {
                    event.getMessage().reply(Messages.get(event.getGuild().getId(), "reasonLimit")).queue();
                    return;
                }

                if (deleteDays < 0) deleteDays = 0;
                if (deleteDays > 7) deleteDays = 7;

            } catch (NumberFormatException e) {
                reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            }
        } else {
            reason = "Unspecified";
        }

        long targetID;
        try {
            if (target.startsWith("<@")) {
                targetID = Long.parseLong(target.replaceAll("[<@!>]", ""));
            } else {
                targetID = Long.parseLong(target);
            }
        } catch (NumberFormatException e) {
            event.getMessage().reply(Messages.get(event.getGuild().getId(), "banTargetException")).queue();
            return;
        }

        long finalTargetID = targetID;
        int finalDeleteDays = deleteDays;
        String finalReason = reason;

        // Artık retrieveMemberById kullanmıyoruz, direkt ID üzerinden ban atıyoruz
        event.getGuild().ban(List.of(UserSnowflake.fromId(finalTargetID)), Duration.ofDays(finalDeleteDays))
                .reason(finalReason)
                .queue(
                        success -> {
                            event.getMessage().reply(String.format(
                                    Messages.get(event.getGuild().getId(), "banSuccess"),
                                    finalTargetID,
                                    event.getAuthor().getName(),
                                    finalReason
                            )).queue();

                            // Log göndermek için SendLog fonksiyonunu çağır
                            SendLog.sendLog(event,
                                    Messages.get(event.getGuild().getId(), "memberRemovedBan"),
                                    String.valueOf(finalTargetID),
                                    event.getAuthor().getName(),
                                    finalReason
                            );
                        },
                        error -> event.getMessage().reply(
                                Messages.get(event.getGuild().getId(), "generalError")
                        ).queue()
                );
    }

    @Override
    public String getName() {
        return "Ban";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.MODERATION;
    }

    @Override
    public String getDescription(MessageReceivedEvent event) {
        return Messages.get(event.getGuild().getId(), "banDescription");
    }

    @Override
    public int permissionLevel() {
        return 7;
    }
}
