package DiscordBot;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import commands.*;
import net.dv8tion.jda.api.JDA;

public class BuilderSetting {
	

	public void AddEvents(CommandClientBuilder builder, JDA jda) {
		EventWaiter waiter = new EventWaiter(); 
		
		builder.addCommand(new FilterSetting());
		builder.addCommand(new Image());
		builder.addCommand(new ServerInfo());
		builder.addCommand(new UserInfo(waiter));
		builder.addCommand(new Calculate());
		builder.addCommand(new MemeCommand());
		
		jda.addEventListener(waiter);
		jda.addEventListener(new FilterProcess()); //use jda api
	}
	
	public void SetInfo(CommandClientBuilder builder) {
		builder.setOwnerId("877156886498140161");
		builder.setPrefix("!");
		builder.setHelpWord("helpme");	
	}
}
