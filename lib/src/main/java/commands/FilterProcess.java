package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class FilterProcess extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if(FilterSetting.filterSwitch) {
			String[] message = event.getMessage().getContentRaw().split(" ");
			checkBadWord(event, message);
		}
	}
	
	public void checkBadWord(GuildMessageReceivedEvent event, String[] message) {
		for(int i = 0; i < message.length; i++) {
			if(message[i].contentEquals("fuck")) {
				event.getMessage().delete().queue();
				if(FilterSetting.msgSwitch) {
					event.getChannel().sendMessage("Don't say that word!! " + event.getAuthor().getAsMention()).queue();;
				}
			}
		}
	}
}
