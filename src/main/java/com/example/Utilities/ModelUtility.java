package com.example.Utilities;

import com.example.PetData;
import net.runelite.api.Client;
import net.runelite.api.Model;
import net.runelite.api.ModelData;

public class ModelUtility
{
    private static final int NPC_AMBIENT = 64;
    private static final int NPC_CONTRAST = 850;
    private static final int NPC_LIGHT_X = -30;
    private static final int NPC_LIGHT_Y = -50;
    private static final int NPC_LIGHT_Z = -30;

    private static ModelData createModel(Client client, ModelData... data)
    {
        return client.mergeModels(data);
    }

    private static ModelData createModel(Client client, int... data)
    {
        ModelData[] modelData = new ModelData[data.length];
        for (int i = 0; i < data.length; i++)
        {
            modelData[i] = client.loadModelData(data[i]);
        }
        return client.mergeModels(modelData);
    }

    public static Model provideModel(Client client, PetData petData)
    {
        int[] modelIDs = petData.getModelIDs();
        short[] recolorIDs = petData.getColorToReplace();
        short[] replaceRecolorIDs = petData.getColorToReplaceWith();

        ModelData[] modelDataArray = new ModelData[modelIDs.length];

        for (int i = 0; i < modelDataArray.length; i++)
        {
            modelDataArray[i] = client.loadModelData(modelIDs[i]);
        }

        ModelData modelData = createModel(client,modelDataArray);
        modelData.cloneVertices();

        if (recolorIDs != null && replaceRecolorIDs != null)
        {
            modelData.cloneColors();
            for (int i = 0; i < recolorIDs.length; i++)
            {
                modelData.recolor(recolorIDs[i],replaceRecolorIDs[i]);
            }
        }

        return modelData.light(petData.getAmbient()  + NPC_AMBIENT, petData.getContrast() + NPC_CONTRAST, NPC_LIGHT_X, NPC_LIGHT_Y, NPC_LIGHT_Z);
    }


}
