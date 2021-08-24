package commands;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URL;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import DiscordBot.CommonUse;
import net.coobird.thumbnailator.*;

//JDA-Utilities and coobird.thumbnailator
public class Image extends Command{
	
	CommonUse common = new CommonUse();
	
	public Image() {
		super.name = "image";
		super.arguments = "[width] [height] [image-url] [degrees to rotate](optional)";
		super.help = "Manipulates images. Provide an image link and you can resize and or rotate it.";
		super.category = new Category("Tools");
	}

	@Override
	protected void execute(CommandEvent e) {
		String[] message = e.getMessage().getContentRaw().split(" ");
		if(message.length == 4 || message.length == 5) {
			 processImage(e, message);
		}else {
			common.unCorrectEntry(e);
		}
	}
	
	public void processImage(CommandEvent e, String[] message) {
		try {
			int width = Integer.parseInt(message[1]);
			int height = Integer.parseInt(message[2]);
			URL imageURL = new URL(message[3]);
			int rotateAmount = 0;
			if(message.length == 5) {
				rotateAmount = Integer.parseInt(message[4]);
			}
			
			OutputStream os = new ByteArrayOutputStream();
			Thumbnails.of(imageURL).forceSize(width, height).rotate(rotateAmount).outputFormat("png").toOutputStream(os);
			byte[] imageInBytes = ((ByteArrayOutputStream) os).toByteArray();
			
			e.getChannel().sendFile(imageInBytes, "newfile.png").queue();
			
		  } catch (Exception ex) {
			System.out.println("an exception happened.");
		}
	}
}
