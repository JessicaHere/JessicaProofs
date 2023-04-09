package me.jessicahere.jessicaproof.bungeecord.commands;

import static me.jessicahere.jessicaproof.bungeecord.utils.StringUtils.colorize;

import me.jessicahere.jessicaproof.bungeecord.utils.ProofUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProofCommand extends Command {

	public ProofCommand() {
		super("jpproof");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new TextComponent(colorize("&cThis command only to player use !")));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (args.length != 0) {
			player.sendMessage(new TextComponent(
					colorize("&cThis is an incorrect usage of the command. Please write it in this format /jpproof")));
			return;
		}

		if (!player.hasPermission("jessicaproofs.proof.use")) {
			player.sendMessage(
					new TextComponent(colorize("&cYou don't have enough permissions to use this command !")));
			return;
		}

		ProofUtils.takeProof(player);

	}

}
