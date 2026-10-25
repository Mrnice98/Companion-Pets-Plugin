package com.example.NpcInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class NpcDefinition
{
    // Identity and models
    private Integer id;
    private String name;
    private Integer size;
    private int[] models;
    private int[] chatheadModels;

    // Animations
    private Integer standingAnimation;
    private Integer idleRotateLeftAnimation;
    private Integer idleRotateRightAnimation;
    private Integer walkingAnimation;
    private Integer rotate180Animation;
    private Integer rotateLeftAnimation;
    private Integer rotateRightAnimation;
    private Integer runAnimation;
    private Integer runRotate180Animation;
    private Integer runRotateLeftAnimation;
    private Integer runRotateRightAnimation;
    private Integer crawlAnimation;
    private Integer crawlRotate180Animation;
    private Integer crawlRotateLeftAnimation;
    private Integer crawlRotateRightAnimation;
    private Boolean idleAnimRestart;

    // Appearance
    @SerializedName("recolorToFind")
    private short[] colorToReplace;
    @SerializedName("recolorToReplace")
    private short[] colorToReplaceWith;

    private short[] retextureToFind;
    private short[] retextureToReplace;

    private Integer fullRecolor;
    private Integer widthScale;
    private Integer heightScale;
    private Integer renderPriority;
    private Integer ambient;
    private Integer contrast;
    private Integer height;
    private Integer footprintSize;
    private Boolean zbuf;
    private Integer rotationSpeed;
    private Boolean rotationFlag;
}
