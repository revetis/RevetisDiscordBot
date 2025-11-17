package com.example.discordbot.commands.slashCommands;

import com.example.discordbot.commands.CommandCategory;
import com.example.discordbot.commands.interfaces.SlashCommand;
import com.example.discordbot.commands.localization.Messages;
import com.example.discordbot.utilities.SendLog;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BanSlash implements SlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        event.deferReply(true).queue(hook -> {

            User user = Objects.requireNonNull(event.getOption("member")).getAsUser();
            String reason = event.getOption("reason") != null ? event.getOption("reason").getAsString() : "Unspecified";
            int days = event.getOption("days") != null ? event.getOption("days").getAsInt() : 0;

            if (days < 0) days = 0;
            if (days > 7) days = 7;

            long targetID = user.getIdLong();
            int finalDeleteDays = days;
            String finalReason = reason;

            long authorID = event.getMember() != null ? event.getMember().getIdLong() : 0;
            long selfID = Objects.requireNonNull(event.getGuild()).getSelfMember().getIdLong();
            long ownerID = event.getGuild().getOwnerIdLong();

            if (targetID == selfID) {
                hook.sendMessage(Messages.get(event.getGuild().getId(), "iCantBanMyself")).queue();
                return;
            }
            if (targetID == authorID) {
                hook.sendMessage(Messages.get(event.getGuild().getId(), "youCantBanYourSelf")).queue();
                return;
            }
            if (targetID == ownerID) {
                hook.sendMessage(Messages.get(event.getGuild().getId(), "iCantBanOwner")).queue();
                return;
            }

            event.getGuild().ban(List.of(UserSnowflake.fromId(targetID)), Duration.ofDays(finalDeleteDays))
                    .reason(finalReason)
                    .queue(
                            success -> {
                                hook.sendMessage(
                                        String.format(Messages.get(event.getGuild().getId(), "banSuccess"),
                                                targetID,
                                                event.getUser().getName(),
                                                finalReason)
                                ).queue();

                                SendLog.sendLog(event,
                                        Messages.get(event.getGuild().getId(), "memberRemovedBan"),
                                        String.valueOf(targetID),
                                        event.getUser().getId(),
                                        finalReason
                                );
                            },
                            error -> {
                                hook.sendMessage(Messages.get(event.getGuild().getId(), "generalError")).queue();
                                error.printStackTrace();
                            }
                    );

        }, error -> error.printStackTrace());
    }


    @Override
    public String getName() { return "Ban"; }

    @Override
    public CommandCategory getCategory() { return CommandCategory.MODERATION; }

    @Override
    public String getDescription(SlashCommandInteractionEvent event) {
        return Messages.get(event.getGuild().getId(), "banDescription");
    }

    @Override
    public String getDescription() {
        return Messages.get("banDescription");
    }

    @Override
    public int permissionLevel() { return 7; }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]{
                new OptionData(OptionType.USER, "member", "Member to be banned", true),
                new OptionData(OptionType.STRING, "reason", "Reason for ban"),
                new OptionData(OptionType.INTEGER, "days", "How many days ago should the member's messages be deleted (max 7)")
        };
    }
}
