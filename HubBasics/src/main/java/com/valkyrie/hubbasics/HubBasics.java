package com.valkyrie.hubbasics;


import com.valkyrie.hubbasics.captcha.listener.CaptchaListener;
import com.valkyrie.hubbasics.captcha.listener.JoinListener;
import com.valkyrie.hubbasics.captcha.menu.CaptchaMenu;
import com.valkyrie.hubbasics.commands.chat.ClearChatCommand;
import com.valkyrie.hubbasics.commands.chat.MuteChatCommand;
import com.valkyrie.hubbasics.commands.chat.SlowChatCommand;
import com.valkyrie.hubbasics.commands.essentials.FlyCommand;
import com.valkyrie.hubbasics.commands.essentials.PingCommand;
import com.valkyrie.hubbasics.commands.media.DiscordCommand;
import com.valkyrie.hubbasics.commands.media.StoreCommand;
import com.valkyrie.hubbasics.commands.media.WebsiteCommand;
import com.valkyrie.hubbasics.commands.spawn.SetSpawnCommand;
import com.valkyrie.hubbasics.commands.spawn.SpawnCommand;
import com.valkyrie.hubbasics.hub.PlayerListener;
import com.valkyrie.hubbasics.hub.features.AlwaysDay;
import com.valkyrie.hubbasics.hub.features.DoubleJump;
import com.valkyrie.hubbasics.hub.features.JoinFly;
import com.valkyrie.hubbasics.hub.features.LaunchPad;
import com.valkyrie.hubbasics.hub.items.EnderButt;
import com.valkyrie.hubbasics.hub.items.ServerSelector;
import com.valkyrie.hubbasics.hub.listeners.Chat;
import com.valkyrie.hubbasics.hub.listeners.SlowChat;
import com.valkyrie.hubbasics.scoreboard.Assemble;
import com.valkyrie.hubbasics.scoreboard.AssembleStyle;
import com.valkyrie.hubbasics.scoreboard.adapter.ScoreboardAdapter;
import com.valkyrie.hubbasics.utils.BungeecordListeners;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.PlayerCountThread;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class HubBasics extends JavaPlugin {

    private static final List<Player> playerArrayList = new ArrayList<>();
    public ArrayList<Player> hideplayers = new ArrayList<>();
    private File messagesYmlFile;
    private FileConfiguration messagesYml;
    private File permissionsYmlFile;
    private FileConfiguration permissionsYml;
    private PlayerCountThread playerCountThread;

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage(CC.translate("&d&m------------------"));
        getServer().getConsoleSender().sendMessage(CC.translate("&fLoading up HubBasics by &dkryunek & lowg0d_ &fv" + getDescription().getVersion()));
        getServer().getConsoleSender().sendMessage(CC.translate("&fLoading classes...."));
        getServer().getConsoleSender().sendMessage(CC.translate("&fLoading Scoreboard & features...."));
        getServer().getConsoleSender().sendMessage(CC.translate("&dHubBasics loaded successfully !"));
        getServer().getConsoleSender().sendMessage(CC.translate("&d&m------------------"));
        this.saveDefaultConfig();
        this.setupListeners();
        this.setupBungee();
        this.setupCommands();
        createMessagesYml();
        createPermissionsYml();
        playerCountThread = new PlayerCountThread();
        playerCountThread.start();
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new CaptchaListener(this), this);
        pluginManager.registerEvents(new JoinListener(), this);


        Assemble assemble = new Assemble(this, new ScoreboardAdapter());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.VIPER);

    }



    private void setupListeners() {
        Arrays.asList(
                new PlayerListener(),
                new ServerSelector(),
                new Chat(),
                new SlowChat(),
                new JoinFly(),
                new AlwaysDay(),
                new EnderButt(),
                new LaunchPad(),
                new DoubleJump()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void setupCommands() {
        getCommand("mutechat").setExecutor(new MuteChatCommand());
        getCommand("slowchat").setExecutor(new SlowChatCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("website").setExecutor(new WebsiteCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("ping").setExecutor(new PingCommand());
    }

    private void setupBungee() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeecordListeners());
    }

    public static HubBasics get() {
        return getPlugin(HubBasics.class);
    }

    public FileConfiguration getMessagesYml() {
        return this.messagesYml;
    }

    public void createMessagesYml() {
        messagesYmlFile = new File(getDataFolder(), "messages.yml");
        if (!messagesYmlFile.exists()) {
            messagesYmlFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }

        messagesYml = new YamlConfiguration();
        try {
            messagesYml.load(messagesYmlFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getPermissionsYml() {
        return this.permissionsYml;
    }

    public void createPermissionsYml() {
        permissionsYmlFile = new File(getDataFolder(), "permissions.yml");
        if (!permissionsYmlFile.exists()) {
            permissionsYmlFile.getParentFile().mkdirs();
            saveResource("permissions.yml", false);
        }

        permissionsYml = new YamlConfiguration();
        try {
            permissionsYml.load(permissionsYmlFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static List<Player> getPlayerArrayList(){
        return playerArrayList;
    }
}





