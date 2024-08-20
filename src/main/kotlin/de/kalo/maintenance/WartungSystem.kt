package de.kalo.maintenance

import de.kalo.maintenance.commands.Wartung_Command
import de.kalo.maintenance.utils.SQL
import de.kalo.maintenance.utils.manager.SQLManager
import org.bukkit.plugin.java.JavaPlugin

class WartungSystem : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()

        getCommand("wartung")?.setExecutor(Wartung_Command())



        if (config.getString("knockit.saveMethode")?.equals("FILE") == false) {
            SQLManager().createFile()
            SQL().connect(SQLManager().get("mysql.host"), SQLManager().get("mysql.port"), SQLManager().get("mysql.database"),
                SQLManager().get("mysql.username"), SQLManager().get("mysql.password"))

            SQL().createTable("whitelisted_players", "UUID varchar(64), NAME varchar(16)")
            SQL().createTable("maintenance", "MODE varchar(5)")

        }else{

        }
    }
    override fun onDisable() {
        super.onDisable()
    }
}