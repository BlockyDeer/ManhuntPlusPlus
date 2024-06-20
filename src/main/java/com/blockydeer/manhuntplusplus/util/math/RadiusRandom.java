package com.blockydeer.manhuntplusplus.util.math;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public final class RadiusRandom {
    public static @NotNull Location radiusRandom(double randDouble1, double randDouble2, @NotNull Location center, double maxRadius) {
        double radius = randDouble1 * maxRadius;
        double theta = randDouble2 * 2 * Math.PI;
        double x = center.getX() + radius * cos(theta);
        double z = center.getZ() + radius * sin(theta);
        return new Location(center.getWorld(), x, center.getY(), z);
    }
}
