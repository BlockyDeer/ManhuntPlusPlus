package com.blockydeer.manhuntplusplus.util.math;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public final class RadiusRandom {
    public static @NotNull Location radiusRandom(@NotNull Random random, @NotNull Location center, double maxRadius) {
        double radius = random.nextDouble() * maxRadius;
        double theta = random.nextDouble() * 2 * Math.PI;
        double x = center.getX() + radius * cos(theta);
        double z = center.getZ() + radius * sin(theta);
        return new Location(center.getWorld(), x, center.getY(), z);
    }
}
