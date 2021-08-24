package DiscordBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import com.jagrosh.jdautilities.command.*;

import commands.FilterProcess;

public class BotMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JDA jda = JDABuilder.createDefault("ODc3MTU2ODg2NDk4MTQwMTYx.YRuiAg.GjARW0p_2IGygQibgiCALujCP0c").build();
		CommandClientBuilder builder = new CommandClientBuilder();
		BuilderSetting set = new BuilderSetting();
		
		set.AddEvents(builder, jda);
		set.SetInfo(builder);
		jda.addEventListener(builder.build());
	}
}
