package com.blockydeer.manhuntplusplus.command;

import com.blockydeer.manhuntplusplus.GameState;
import com.blockydeer.manhuntplusplus.ManhuntPlusPlus;
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

        GameState gameState = GameState.getGameState();
        switch (args[0]) {
            case "start": {
                if (!ManhuntPlusPlus.getInstance().getPluginConfig().autoStartGame) {
                    gameState.startGame();
                } else {
                    gameState.setGameReady();
                }
                Bukkit.broadcastMessage(ChatColor.GREEN + "游戏开始！");
                break;
            }
            case "set": {
                if (args.length != 3) {
                    return false;
                }
                if (args[1].equals("runner")) {
                    gameState.updateRunner(args[2]);
                    Bukkit.broadcastMessage(ChatColor.GREEN + "成功设置 " + args[2] + "为逃脱者");
                } else if (args[1].equals("hunter")) {
                    gameState.updateHunter(args[2]);
                    Bukkit.broadcastMessage(ChatColor.GREEN + "成功设置 " + args[2] + "为猎人");
                }
                return false;
            }
            case "list": {
                commandSender.sendMessage("Runner " + gameState.getRunnerList().toString());
                commandSender.sendMessage("Hunter" + gameState.getHunterList().toString());
                break;
            }
            default: {
                commandSender.sendMessage("参数错误");
            }
        }
        return true;
    }
}
