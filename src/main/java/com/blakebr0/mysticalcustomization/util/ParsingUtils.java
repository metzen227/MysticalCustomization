package com.blakebr0.mysticalcustomization.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.util.JSONUtils;

public class ParsingUtils {
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public static int parseHex(String s, String name) {
        try {
            return Integer.parseInt(s, 16);
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException("Invalid color provided for color " + name);
        }
    }

    public static CompoundNBT parseNBT(JsonElement json) {
        try {
            if (json.isJsonObject()) {
                return JsonToNBT.getTagFromJson(GSON.toJson(json));
            } else {
                return JsonToNBT.getTagFromJson(JSONUtils.getString(json, "nbt"));
            }
        } catch (CommandSyntaxException e) {
            throw new JsonSyntaxException("Invalid NBT entry: " + e.toString());
        }
    }
}
