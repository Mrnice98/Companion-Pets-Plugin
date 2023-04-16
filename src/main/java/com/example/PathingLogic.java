package com.example;

import net.runelite.api.Client;
import net.runelite.api.Constants;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;

import java.util.function.Predicate;

public class PathingLogic {

    public static WorldArea calculateNextTravellingPoint(Client client, WorldArea start, WorldArea target, boolean stopAtMeleeDistance)
    {
        return calculateNextTravellingPoint(client,start, target, stopAtMeleeDistance, x -> true);
    }

    /**
     * Calculates the next area that will be occupied if this area attempts
     * to move toward it by using the normal NPC travelling pattern.
     *
     * @param client the client to calculate with
     * @param target the target area
     * @param stopAtMeleeDistance whether to stop at melee distance to the target
     * @param extraCondition an additional condition to perform when checking valid tiles,
     * 	                     such as performing a check for un-passable actors
     * @return the next occupied area
     */
    public static WorldArea calculateNextTravellingPoint(Client client,WorldArea start, WorldArea target, boolean stopAtMeleeDistance, Predicate<? super WorldPoint> extraCondition)
    {
        if (start.getPlane() != target.getPlane())
        {
            return null;
        }

        if (start.intersectsWith(target))
        {
            if (stopAtMeleeDistance)
            {
                // Movement is unpredictable when the NPC and actor stand on top of each other
                return null;
            }
            else
            {
                return start;
            }
        }

        int dx = target.getX() - start.getX();
        int dy = target.getY() - start.getY();

        Point axisDistances = getAxisDistances(start,target);

        if (stopAtMeleeDistance && axisDistances.getX() + axisDistances.getY() == 1)
        {
            // NPC is in melee distance of target, so no movement is done
            return start;
        }

        LocalPoint lp = LocalPoint.fromWorld(client, start.getX(), start.getY());
        if (lp == null ||
                lp.getSceneX() + dx < 0 || lp.getSceneX() + dy >= Constants.SCENE_SIZE ||
                lp.getSceneY() + dx < 0 || lp.getSceneY() + dy >= Constants.SCENE_SIZE)
        {
            // NPC is travelling out of the scene, so collision data isn't available
            return null;
        }

        int dxSig = Integer.signum(dx);
        int dySig = Integer.signum(dy);
        if (stopAtMeleeDistance && axisDistances.getX() == 1 && axisDistances.getY() == 1)
        {
            // When it needs to stop at melee distance, it will only attempt
            // to travel along the x axis when it is standing diagonally
            // from the target
            if (start.canTravelInDirection(client, dxSig, 0, extraCondition))
            {
                return new WorldArea(start.getX() + dxSig, start.getY(), start.getWidth(), start.getHeight(), start.getPlane());
            }
        }
        else
        {
            if (start.canTravelInDirection(client, dxSig, dySig, extraCondition))
            {
                return new WorldArea(start.getX() + dxSig, start.getY() + dySig, start.getWidth(), start.getHeight(), start.getPlane());
            }
            else if (dx != 0 && start.canTravelInDirection(client, dxSig, 0, extraCondition))
            {
                return new WorldArea(start.getX() + dxSig, start.getY(), start.getWidth(), start.getHeight(), start.getPlane());
            }
            else if (dy != 0 && Math.max(Math.abs(dx), Math.abs(dy)) > 1 &&
                    start.canTravelInDirection(client, 0, dy, extraCondition))
            {
                // Note that NPCs don't attempts to travel along the y-axis
                // if the target is <= 1 tile distance away
                return new WorldArea(start.getX(), start.getY() + dySig, start.getWidth(), start.getHeight(), start.getPlane());
            }
        }

        // The NPC is stuck
        return start;
    }

    private static Point getAxisDistances(WorldArea wa1,WorldArea wa2)
    {
        Point p1 = getComparisonPoint(wa1,wa2);
        Point p2 = getComparisonPoint(wa2,wa1);
        return new Point(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
    }


    private static Point getComparisonPoint(WorldArea wa1,WorldArea wa2)
    {
        int x, y;
        if (wa2.getX() <= wa1.getX() )
        {
            x = wa1.getX() ;
        }
        else if (wa2.getX()  >= wa1.getX()  + wa1.getWidth() - 1)
        {
            x = wa1.getX()  + wa1.getWidth() - 1;
        }
        else
        {
            x = wa2.getX() ;
        }
        if (wa2.getY() <= wa1.getY())
        {
            y = wa1.getY();
        }
        else if (wa2.getY() >= wa1.getY() + wa1.getHeight() - 1)
        {
            y = wa1.getY() + wa1.getHeight() - 1;
        }
        else
        {
            y = wa2.getY();
        }
        return new Point(x, y);
    }



}
