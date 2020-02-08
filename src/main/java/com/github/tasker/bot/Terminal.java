package com.github.tasker.bot;

import com.github.tasker.bot.command.map.CommandMap;
import net.dv8tion.jda.api.JDABuilder;

public class Terminal {

    private static final CommandMap map = new CommandMap();

    public static void main(String[] args) {
        try {
            new JDABuilder()
                    .setToken("seu_token_aqui")
                    .addEventListeners(map.getHandler())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
