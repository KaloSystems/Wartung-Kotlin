package de.kalo.maintenance.utils.manager

import de.kalo.maintenance.WartungSystem
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class SQLManager {

    private var file: File? = null
    private var cfg: FileConfiguration? = null

    fun ConfigManager() {
        file = File(WartungSystem().getDataFolder(), "mysql.yml")
        cfg = YamlConfiguration.loadConfiguration(file!!)
    }

    fun createFile(): SQLManager {
        if (!file!!.exists()) {
            try {
                file!!.getParentFile().mkdir()
                file!!.createNewFile()
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            addDefaults()
        }
        return this
    }

    private fun addDefaults(): SQLManager {
        cfg!!.options().copyDefaults(true)
        cfg!!.addDefault("mysql.host", "127.0.0.1")
        cfg!!.addDefault("mysql.port", 3306)
        cfg!!.addDefault("mysql.database", "wartung")
        cfg!!.addDefault("mysql.username", "user")
        cfg!!.addDefault("mysql.password", "password")
        return this
    }

    fun save(): SQLManager {
        try {
            cfg!!.save(file!!)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return this
    }

    operator fun get(path: String?): SQLManager {
        cfg!![path!!]
        return this
    }

    fun getString(path: String?) {
        cfg!!.getString(path!!)
    }

    fun getConfiguration(): FileConfiguration? {
        return cfg
    }
}