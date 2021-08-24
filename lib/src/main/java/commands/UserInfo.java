package commands;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

//JDA-Utilities and Waiter
public class UserInfo extends Command{
	
	private EventWaiter waiter;
	
	public UserInfo(EventWaiter waiter) {
		super.name = "user-info";
		super.aliases = new String[]{"userinfo"};
		super.help = "type the command and follow instruction.";
		super.category = new Category("Information");
		this.waiter = waiter;
	}
	
	@Override
	protected void execute(CommandEvent event) {
		event.reply("Give me a name of a user! Like this @Chasel");
		waiter.waitForEvent(GuildMessageReceivedEvent.class, e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel()), e -> {
			try {
				Member name = e.getMessage().getMentionedMembers().get(0);
				processEmbed(event, name);
			}catch(IndexOutOfBoundsException ex){
				System.out.println("Exception Occurred");
				event.reply("You need to provide the name as a mention, like this: @name, try again.");
			}
		}, 10, TimeUnit.SECONDS, () -> event.reply("You didn't give me a name to search. Try again."));
		
	}
	
	private String getRolesAsString(List rolesList) {
		String roles = ""; 
		if(!rolesList.isEmpty()) {
			Role tempRole = (Role) rolesList.get(0);
			for(int i = 1; i < rolesList.size(); i++) {
				tempRole = (Role) rolesList.get(i);
				roles = roles + ", " + tempRole.getName();
			}
		}else {
			roles = "No roles";
		}
		return roles;
	}
	
	public void processEmbed(CommandEvent event,  Member name) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		EmbedBuilder eb = new EmbedBuilder()
				.setColor(Color.GREEN)
				.setThumbnail("https://newsimgs.sina.tw/article/images/news-16267464373027.png")
				.setAuthor("Information on " + name.getUser().getName(), "https://www.google.com/", name.getUser().getAvatarUrl())
				.setDescription(name.getUser().getName() + " joined on " + name.getTimeJoined().format(fmt) + " :clock:")
				.addField("Status: ", name.getOnlineStatus().toString(), true)
				.addField("Roles: ", getRolesAsString(name.getRoles()), true)
				.addField("Nickname", name.getNickname() == null ? "No Nickname" : name.getNickname(), true);		
			
		event.reply(eb.build());
	}
}
