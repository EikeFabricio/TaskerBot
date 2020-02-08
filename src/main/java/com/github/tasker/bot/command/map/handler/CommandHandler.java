package com.github.tasker.bot.command.map.handler;

import com.github.tasker.bot.command.DiscordCommand;
import com.github.tasker.bot.command.map.CommandMap;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandHandler extends ListenerAdapter {

    private final CommandMap commandMap;

    public CommandHandler(CommandMap map) {
        this.commandMap = map;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String contentStripped = event.getMessage().getContentStripped();
        Member member = event.getMember();
        if (member.getUser().isBot()) return;

        String notFound = String.format("Comando não encontrado ou você não tem permissão, %s.", member.getAsMention());

        if (!commandMap.isCommand(contentStripped)) return;

        if (!commandMap.isCommandKey(contentStripped.split(" ")[0])) {
            event.getChannel().sendMessage(notFound).queue();
            return;
        }

        DiscordCommand command = commandMap.of(contentStripped.split(" ")[0]);

        if (!member.getPermissions().contains(command.getPermission())) {
            event.getChannel().sendMessage(notFound).queue();
            return;
        }

        command.execute(event);
    }
}
