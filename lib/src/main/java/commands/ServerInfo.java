package commands;

import java.awt.Color;
import java.util.Arrays;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import DiscordBot.CommonUse;
import net.dv8tion.jda.api.EmbedBuilder;

//JDA-Utilities
public class ServerInfo extends Command{
	
	CommonUse common = new CommonUse();
	
	public ServerInfo() {
		super.name = "serverinfo";
		super.aliases = new String[] {"server"};
		super.help = "Gives information about the server.";
		super.category = new Category("Information");
	}
	
	protected void execute(CommandEvent e) {
		if(e.getMessage().getContentRaw().split(" ").length != 1) {
			common.unCorrectEntry(e);
		}else {
			String[] members = new String[e.getGuild().getMembers().size()];
			for(int i = 0; i < e.getGuild().getMembers().size(); i++) {
				members[i] = e.getGuild().getMembers().get(i).getEffectiveName();
			}
			setEmbed(e, members);
		}
	}
	
	public void setEmbed(CommandEvent e, String[] members) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setColor(Color.RED)
			.setAuthor(e.getGuild().getName())
			.setThumbnail("https://newsimgs.sina.tw/article/images/news-16267464373027.png")
			.addField("Server Owner: ", e.getGuild().getOwner().getEffectiveName(), true)
			.addField("Member Count:", Integer.toString(members.length), true)
			.setDescription("**Members:** \n" + Arrays.toString(members) + "\n **Invite Link:** \n" + "https://discord.gg/4Tkbca4Cza");
		e.reply(eb.build());
	}
}
