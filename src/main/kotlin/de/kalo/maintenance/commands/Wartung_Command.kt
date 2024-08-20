package de.kalo.maintenance.commands

import de.kalo.maintenance.WartungSystem
import de.kalo.maintenance.utils.Data
import de.kalo.maintenance.utils.SQL
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
class Wartung_Command : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender

        if(sender is Player){

            /*
            /wartung off/on
            /wartung player add <Player>
            /wartung player remove <Player>
            */

            if(player.hasPermission("wartung.command")){

            if(args.size == 1){
                if(args[0].equals("off")){
                    if (WartungSystem().config.getString("knockit.saveMethode")?.equals("FILE") == false) {
                        SQL().update("UPDATE maintenance SET MODE=false")
                    }else{
                        WartungSystem().config.set("maintenance.mode", false);
                    }
                    player.sendMessage("${Data().prefix}§7Du hast §a§nerfolgreich§7 den Wartungsmodus deaktiviert.")

                }else if(args[0].equals("on")){
                    if (WartungSystem().config.getString("knockit.saveMethode")?.equals("FILE") == false) {
                        SQL().update("UPDATE maintenance SET MODE=true")
                    }else{
                        WartungSystem().config.set("maintenance.mode", true);
                    }
                    player.sendMessage("${Data().prefix}§7Du hast §a§nerfolgreich§7 den Wartungsmodus aktiviert.")

                }
            }else if(args.size == 3){

            }else{

            }

            }else{
                player.sendMessage("${Data().prefix}§cKeine Rechte")
                return true
            }

        }else{
            System.out.println("Du bist kein Spieler!")
            return true
        }


        return true
    }


}