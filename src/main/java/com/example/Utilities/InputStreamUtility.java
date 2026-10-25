package com.example.Utilities;

import com.example.NpcInfo.NpcDefinition;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static net.runelite.http.api.RuneLiteAPI.GSON;

public class InputStreamUtility
{
    public static <T> T load(InputStream input, Type type) throws IOException
    {
        try (Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8))
        {
            return GSON.fromJson(reader, type);
        }
    }

    public static Map<Integer, NpcDefinition> loadById(InputStream input) throws IOException
    {
        Map<Integer, NpcDefinition> npcs = new LinkedHashMap<>();
        for (NpcDefinition npc : loadAllNpcs(input))
        {
            npcs.put(npc.getId(), npc);
        }
        return npcs;
    }

    public static HashMap<Integer, Integer> loadAllOffsets(InputStream input) throws IOException
    {
        Type type = new TypeToken<Map<Integer, Integer>>() {}.getType();
        return load(input, type);
    }

    public static List<NpcDefinition> loadAllNpcs(InputStream input) throws IOException
    {
        Type type = new TypeToken<List<NpcDefinition>>() {}.getType();
        return load(input, type);
    }

}
