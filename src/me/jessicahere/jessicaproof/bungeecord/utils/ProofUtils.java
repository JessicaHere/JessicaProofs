package me.jessicahere.jessicaproof.bungeecord.utils;

import static me.jessicahere.jessicaproof.bungeecord.utils.StringUtils.colorize;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.Instant;

import javax.imageio.ImageIO;

import me.jessicahere.jessicaproof.bungeecord.core.Core;
import me.jessicahere.jessicaproof.bungeecord.settings.Settings;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ProofUtils {

	public static void takeProof(ProxiedPlayer player) {
		try {
			Robot robot = new Robot();
			robot.delay(Settings.delayTime * 1000);
			BufferedImage screenshot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(screenshot, "png", out);
			byte[] data = out.toByteArray();
			sendProof(data, player.getName(), player);
			player.sendMessage(new TextComponent(
					colorize("&eYour proof has been taked successfully , proof will send after a second !")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void sendProof(byte[] data, String playerName, ProxiedPlayer player) {
		JDA jda = Core.getBot().getJDA();
		String epochTime = Long.toString(Instant.now().getEpochSecond());

		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.DARK_GRAY);
		builder.setTitle("Proof from " + playerName);
		builder.setImage("attachment://proof.png");
		builder.setDescription("at : <t:" + epochTime + "> \n Server : " + player.getServer().getInfo().getName());

		Guild guild = jda.getGuildById(Settings.guildId);
		if (guild != null) {
			MessageChannel channel = guild.getTextChannelById(Settings.textChannelId);

			if (channel != null) {
				channel.sendMessage(builder.build()).addFile(data, "proof.png").queue();
				player.sendMessage(new TextComponent(colorize("&aYour proof has been sent successfully !")));
			} else {
				player.sendMessage(
						new TextComponent(colorize("&cFailed process there is problem , maybe channel id is null !")));
			}
		} else {
			player.sendMessage(new TextComponent(
					colorize("&cFailed process there is problem , maybe guild id is null or bot not in this guild !")));
		}

	}

}
