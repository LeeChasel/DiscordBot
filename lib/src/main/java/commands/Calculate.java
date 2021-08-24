package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import DiscordBot.CommonUse;

public class Calculate extends Command{

	CommonUse common = new CommonUse();
	
	public Calculate() {
		super.name = "cal";
		super.arguments = "[add/sub/mul/div] [num1] [num2]";
		super.help = "Simply Calculate two numbers";
		super.category = new Category("Tools");
	}
	
	@Override
	protected void execute(CommandEvent event) {
		String[] message = event.getMessage().getContentRaw().split(" ");
		
		if(message.length != 4) {
			common.unCorrectEntry(event);
		}else {
				String chosen = message[1];
				float num1 = Float.parseFloat(message[2]);
				float num2 = Float.parseFloat(message[3]);
				float res = decideAndCalculate(chosen, num1, num2, event);
				event.reply("The resault is " + res);
			}
		}
	
	public float decideAndCalculate(String chosen, float num1, float num2, CommandEvent event) {
		float res = 0;
		switch(chosen) {
			case "add":
				res = num1+num2;
				break;
			case "sub":
				res = num1-num2;
				break;
			case "mul":
				res = num1*num2;
				break;
			case "div":
				res = num1/num2;
				break;
			default:
				common.unCorrectEntry(event);
		}
		return res;
	}
}
