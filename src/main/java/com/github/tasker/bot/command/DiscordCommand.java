package com.github.tasker.bot.command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public abstract class DiscordCommand {

    private final Permission permission;

    public DiscordCommand(Permission permission) {
        this.permission = permission;
    }

    public DiscordCommand() {
        this.permission = Permission.MESSAGE_READ;
    }

    public Permission getPermission() {
        return permission;
    }

    public abstract void execute(GuildMessageReceivedEvent e);
}
