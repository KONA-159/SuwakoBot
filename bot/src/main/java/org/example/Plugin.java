package org.example;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.example.controller.Controller;

import java.io.IOException;

public final class Plugin extends JavaPlugin {

    public static final Plugin INSTANCE = new Plugin();

    private Plugin() {
        super(new JvmPluginDescriptionBuilder("org.example.plugin", "1.0-SNAPSHOT").build());
    }


    @Override
    public void onEnable() {
        try {
            new Controller();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getLogger().info("Bilibili自动推送插件------装载完成！");
    }
}
