package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import DiscordBot.CommonUse;

import com.jagrosh.jdautilities.command.Command.Category;

public class FilterSetting extends Command{
	
	static boolean filterSwitch = true;
	static boolean msgSwitch = false;
	CommonUse common = new CommonUse();
	
	public FilterSetting() {
		super.name = "filter";
		super.arguments = "[switch/msg] [on/off]";
		super.help = "delete the bad words in chat";
		super.category = new Category("Chat Order");
	}
	
	@Override
	protected void execute(CommandEvent event) {
		if(event.getArgs().equalsIgnoreCase("")) {
				common.unCorrectEntry(event);
		}else {
			String[] args = event.getArgs().split(" ");
			if(args.length != 2) {
					common.unCorrectEntry(event);
			}else {
					modeChoose(args,event);
			}
		}
	}
	
	public void modeChoose(String[] args, CommandEvent event) {
		String mode = args[0];
		String status = args[1];
		if(mode.equalsIgnoreCase("switch")) {
			if(status.equalsIgnoreCase("on")) {
				filterSwitch = true;
				event.reply("The Bad_Word_Filter has been enabled by " + event.getAuthor().getAsMention());
			}else if(status.equalsIgnoreCase("off")) {
				filterSwitch = false;
				event.reply("The Bad_Word_Filter has been disabled by " + event.getAuthor().getAsMention());
			}else {
				common.unCorrectEntry(event);
			}
		}else if(mode.equalsIgnoreCase("msg")) {
			if(status.equalsIgnoreCase("on")) {
				msgSwitch = true;
				event.reply("The Response_From_Filter has been enabled by " + event.getAuthor().getAsMention());
			}else if(status.equalsIgnoreCase("off")) {
				msgSwitch = false;
				event.reply("The Response_From_Filter has been disabled by " + event.getAuthor().getAsMention());
			}else {
				common.unCorrectEntry(event);
			}
		}else {
			common.unCorrectEntry(event);
		}
	}
}
