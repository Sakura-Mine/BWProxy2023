package com.tomkeuper.bedwars.proxy.command.main;

import com.tomkeuper.bedwars.proxy.api.command.ParentCommand;
import com.tomkeuper.bedwars.proxy.BedWarsProxy;
import com.tomkeuper.bedwars.proxy.language.Language;
import com.tomkeuper.bedwars.proxy.api.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class MainCommand extends ParentCommand {

    private static MainCommand instance;

    /**
     * Create a new Parent Command
     *
     * @param name command name.
     */
    public MainCommand(String name) {
        super(name);
        instance = this;
        setAliases(Collections.singletonList("bedwars"));
        addSubCommand(new SelectorCMD("gui", "bw.gui"));
        addSubCommand(new LangCMD("lang", ""));
        addSubCommand(new LangCMD("language", ""));
        addSubCommand(new JoinCMD("join", "bw.join"));
        addSubCommand(new ReJoinCMD("rejoin", "bw.rejoin"));
        addSubCommand(new TpCommand("tp", "bw.tp"));
    }

    @Override
    public void sendDefaultMessage(CommandSender s) {
        if (s instanceof ConsoleCommandSender) return;
        Player p = (Player) s;

        s.sendMessage(" ");
        s.sendMessage(" §d▶ §fWelcome to the Sakura §cBed§fWars");
        s.sendMessage(" §5● §d /party§f for party system");
        s.sendMessage(" §5● §d /stats§f see your stats");
        s.sendMessage(" §5● §d /private §f play private games");
        s.sendMessage(" §5● §d /cosmetic§f manage your cosmetics");
        s.sendMessage(" ");
        if (hasSubCommand("gui")) p.spigot().sendMessage(BedWarsProxy.createTC(Language.getMsg(p, Messages.COMMAND_GUI_DISPLAY), "/bw gui", Language.getMsg(p, Messages.COMMAND_GUI_HOVER)));
        if (hasSubCommand("lang")) p.spigot().sendMessage(BedWarsProxy.createTC(Language.getMsg(p, Messages.COMMAND_LANGUAGE_DISPLAY), "/bw lang", Language.getMsg(p, Messages.COMMAND_LANGUAGE_HOVER)));
        if (hasSubCommand("rejoin")) p.spigot().sendMessage(BedWarsProxy.createTC(Language.getMsg(p, Messages.COMMAND_REJOIN_DISPLAY), "/bw rejoin", Language.getMsg(p, Messages.COMMAND_REJOIN_HOVER)));
        if (hasSubCommand("tp") && getSubCommand("tp").hasPermission(s)) p.spigot().sendMessage(BedWarsProxy.createTC(Language.getMsg(p, Messages.COMMAND_TP_DISPLAY), "/bw tp", Language.getMsg(p, Messages.COMMAND_TP_HOVER)));

    }

    public static MainCommand getInstance() {
        return instance;
    }
}
