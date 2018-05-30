package AT.MSev.Mango.Zones;

import org.bukkit.Effect;

public class ZoneVision implements Runnable {
    static Boolean IsEnabled = true;

    public void run() {
        if(IsEnabled) {
            for (ZoneBase zb : ZoneBase.All) {
                for (int i = 0; i < zb.Setting.ZoneXSize(); i++) {

                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(i, 0, 0),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(i, zb.Setting.ZoneYSize(), 0),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(i, 0, zb.Setting.ZoneZSize()),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(i, zb.Setting.ZoneYSize(), zb.Setting.ZoneZSize()),
                            Effect.SMOKE,
                            31);
                }
                for (int i = 0; i < zb.Setting.ZoneYSize(); i++) {
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(0, i, 0),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(zb.Setting.ZoneXSize(), i, 0),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(0, i, zb.Setting.ZoneZSize()),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(zb.Setting.ZoneXSize(), i, zb.Setting.ZoneZSize()),
                            Effect.SMOKE,
                            31);
                }
                for (int i = 0; i < zb.Setting.ZoneZSize(); i++) {
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(0, 0, i),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(zb.Setting.ZoneXSize(), 0, i),
                            Effect.SMOKE,
                            31);
                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(0, zb.Setting.ZoneYSize(), i),
                            Effect.SMOKE,
                            31);

                    zb.Setting.getWorld().playEffect(
                            zb.Setting.RelLocLesser(zb.Setting.ZoneXSize(), zb.Setting.ZoneYSize(), i),
                            Effect.SMOKE,
                            31);
                }
            }
        }
    }
}
