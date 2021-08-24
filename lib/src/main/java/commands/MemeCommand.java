package commands;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import DiscordBot.CommonUse;
import net.dv8tion.jda.api.EmbedBuilder;

//json-simple in https://www.youtube.com/watch?v=bsgUVRGwK_8&list=PLfu_Bpi_zcDNcs3j5tR6VQA5fSVDCpNeC&index=13
public class MemeCommand extends Command{
	
	CommonUse common = new CommonUse();
	
	public MemeCommand() {
		super.name = "meme";
		super.help = "Type command and get meme from reddit.";
		super.category = new Category("Tools");
	}
	
	@Override
	protected void execute(CommandEvent event) {
		String[] message = event.getMessage().getContentRaw().split(" ");
		if(message.length > 1) {
			common.unCorrectEntry(event);
		}else {
			processJson(event);
		}
	}
	
	public void processJson(CommandEvent event) {
		JSONParser parser = new JSONParser();
		String postLink = "";
		String title = "";
		String url = "";
		
		try {
			URL memeURL = new URL("https://meme-api.herokuapp.com/gimme");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memeURL.openConnection().getInputStream()));
			String lines;
			while((lines=bufferedReader.readLine()) != null) {
				JSONArray array = new JSONArray();
				array.add(parser.parse(lines));
				
				for(Object o : array) {
					JSONObject jsonObject = (JSONObject) o;
					postLink = (String)jsonObject.get("postLink");
					title = (String)jsonObject.get("title");
					url = (String)jsonObject.get("url");
				}
			}
			bufferedReader.close();
			event.getMessage().delete().queue();
			processEmbed(event, postLink, title, url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processEmbed(CommandEvent event, String postLink, String title, String url) {
		EmbedBuilder eb = new EmbedBuilder()
				.setTitle(title, postLink)
				.setImage(url)
				.setColor(Color.ORANGE);
		event.reply(eb.build());
	}
	
}
