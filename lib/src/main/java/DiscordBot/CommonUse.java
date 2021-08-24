package DiscordBot;

import com.jagrosh.jdautilities.command.CommandEvent;

public class CommonUse {
	public void unCorrectEntry(CommandEvent event) {
		event.reply("Oops! " + event.getAuthor().getAsMention() +" you're entry is wrong, type !helpme to get instructions of Bot.");
	}
}
