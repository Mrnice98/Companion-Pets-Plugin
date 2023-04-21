package com.example;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;

public class CompanionPetOverlay extends Overlay {

    private CompanionPetPlugin plugin;

    private Client client;

    private CompanionPetConfig config;

    @Inject
    public CompanionPetOverlay(CompanionPetPlugin plugin, Client client, CompanionPetConfig config) {
        this.plugin = plugin;
        this.client = client;
        this.config = config;
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPosition(OverlayPosition.DYNAMIC);
    }


    @Override
    public Dimension render(Graphics2D graphics) {


        if (config.debug() && plugin.petPoly != null && plugin.pet != null && plugin.pet.isActive() && plugin.pet.getLocalLocation() != null)
        {
            if (plugin.petPoly.contains(client.getMouseCanvasPosition().getX(), client.getMouseCanvasPosition().getY()))
            {
                graphics.setColor(Color.GREEN);
            } else
            {
                graphics.setColor(Color.WHITE);
            }

            graphics.draw(plugin.petPoly);

            graphics.draw(Perspective.getCanvasTileAreaPoly(client,plugin.pet.getLocalLocation(),plugin.petData.getSize()));


            for (WorldPoint worldPoint : plugin.pet.getWorldArea().toWorldPointList())
            {
                graphics.draw(Perspective.getCanvasTilePoly(client, LocalPoint.fromWorld(client,worldPoint)));
            }

            graphics.setFont(FontManager.getRunescapeBoldFont());


        }

        return null;
    }
}
