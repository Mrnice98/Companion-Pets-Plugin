package com.example.Utilities;

import com.example.PetObjectModel;
import net.runelite.api.AABB;
import net.runelite.api.Client;
import net.runelite.api.Model;
import net.runelite.api.Perspective;
import net.runelite.api.Renderable;
import net.runelite.api.RuneLiteObject;
import net.runelite.api.geometry.SimplePolygon;
import net.runelite.api.model.Jarvis;
import net.runelite.client.callback.RenderCallback;

import java.util.function.BiPredicate;

public class RenderUtility {

    public static SimplePolygon calculateAABB(Client client, Model m, int jauOrient, int x, int y, int z, int zOff)
    {
        AABB aabb = m.getAABB(jauOrient);

        int x1 = aabb.getCenterX();
        int y1 = aabb.getCenterZ();
        int z1 = aabb.getCenterY() + zOff;

        int ex = aabb.getExtremeX();
        int ey = aabb.getExtremeZ();
        int ez = aabb.getExtremeY();

        int x2 = x1 + ex;
        int y2 = y1 + ey;
        int z2 = z1 + ez;

        x1 -= ex;
        y1 -= ey;
        z1 -= ez;

        float[] xa = new float[]{
                x1, x2, x1, x2,
                x1, x2, x1, x2
        };
        float[] ya = new float[]{
                y1, y1, y2, y2,
                y1, y1, y2, y2
        };
        float[] za = new float[]{
                z1, z1, z1, z1,
                z2, z2, z2, z2
        };

        int[] x2d = new int[8];
        int[] y2d = new int[8];

        Perspective.modelToCanvas(client,client.getTopLevelWorldView(),8, x, y, z, 0, xa, ya, za, x2d, y2d);
        return Jarvis.convexHull(x2d, y2d);
    }


    public static void conditionalHide(PetObjectModel pom, boolean shouldHide)
    {
        //should force the object to render far under the ground
        int hideZ = 1000;
        RuneLiteObject rlo = pom.getRlObject();
        if (shouldHide && rlo.getZ() != hideZ)
        {
            rlo.setZ(hideZ);
        }
        else if (!shouldHide && rlo.getZ() == hideZ)
        {
            rlo.setZ(pom.getZOff());
        }
    }

    public static RenderCallback createDrawListener(BiPredicate<Renderable, Boolean> method)
    {
        return new RenderCallback()
        {
            @Override
            public boolean addEntity(Renderable renderable, boolean ui)
            {
                return method.test(renderable, ui);
            }
        };
    }

}
