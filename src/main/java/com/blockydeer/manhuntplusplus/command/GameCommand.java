package com.blockydeer.manhuntplusplus.command;

import com.blockydeer.manhuntplusplus.GameState;
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
                break;
            }
            case "set": {
                if (args.length != 3) {
                    return false;
                }
                if (args[1].equals("runner")) {
                    GameState.getGameState().updateRunner(args[2]);
                } else if (args[1].equals("hunter")) {
                    GameState.getGameState().updateHunter(args[2]);
                }
                return false;
            }
        }
        return true;
    }
}
