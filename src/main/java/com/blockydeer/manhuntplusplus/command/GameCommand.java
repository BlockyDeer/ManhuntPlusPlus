package com.blockydeer.manhuntplusplus.command;

import com.blockydeer.manhuntplusplus.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            return false;
        }

        switch (args[0]) {
            case "start": {
                GameState.getGameState().startGame();
                Bukkit.broadcastMessage(ChatColor.GREEN + "游戏开始！");
                break;
            }
            case "set": {
                if (args.length != 3) {
                    return false;
                }
                if (args[1].equals("runner")) {
                    GameState.getGameState().updateRunner(args[2]);
                    Bukkit.broadcastMessage(ChatColor.GREEN + "成功设置 " + args[2] + "为逃脱者");
                } else if (args[1].equals("hunter")) {
                    GameState.getGameState().updateHunter(args[2]);
                    Bukkit.broadcastMessage(ChatColor.GREEN + "成功设置 " + args[2] + "为猎人");
                }
                return false;
            }
            case "list": {
                commandSender.sendMessage("Runner " + GameState.getGameState().getRunnerList().toString());
                commandSender.sendMessage("Hunter" + GameState.getGameState().getHunterList().toString());
                break;
            }
            default: {
                commandSender.sendMessage("参数错误");
            }
        }
        return true;
    }
}
