package com.example.foodforgood.Model;

public class RecipeModel {
    private String RecipeName,Origin,Uid,Raw1,Raw2,Raw3,Raw4,Raw5,Raw6,Raw7,Raw8,Raw9,Direction,Duration,Servingcount,Difficulty,RecipeImgName;

    public RecipeModel(String recipeName, String origin, String uid, String raw1, String raw2, String raw3, String raw4, String raw5, String raw6, String raw7, String raw8, String raw9, String direction, String duration, String servingcount, String difficulty, String recipeImgName) {
        RecipeName = recipeName;
        Origin = origin;
        Uid = uid;
        Raw1 = raw1;
        Raw2 = raw2;
        Raw3 = raw3;
        Raw4 = raw4;
        Raw5 = raw5;
        Raw6 = raw6;
        Raw7 = raw7;
        Raw8 = raw8;
        Raw9 = raw9;
        Direction = direction;
        Duration = duration;
        Servingcount = servingcount;
        Difficulty = difficulty;
        RecipeImgName = recipeImgName;
    }

    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getRaw1() {
        return Raw1;
    }

    public void setRaw1(String raw1) {
        Raw1 = raw1;
    }

    public String getRaw2() {
        return Raw2;
    }

    public void setRaw2(String raw2) {
        Raw2 = raw2;
    }

    public String getRaw3() {
        return Raw3;
    }

    public void setRaw3(String raw3) {
        Raw3 = raw3;
    }

    public String getRaw4() {
        return Raw4;
    }

    public void setRaw4(String raw4) {
        Raw4 = raw4;
    }

    public String getRaw5() {
        return Raw5;
    }

    public void setRaw5(String raw5) {
        Raw5 = raw5;
    }

    public String getRaw6() {
        return Raw6;
    }

    public void setRaw6(String raw6) {
        Raw6 = raw6;
    }

    public String getRaw7() {
        return Raw7;
    }

    public void setRaw7(String raw7) {
        Raw7 = raw7;
    }

    public String getRaw8() {
        return Raw8;
    }

    public void setRaw8(String raw8) {
        Raw8 = raw8;
    }

    public String getRaw9() {
        return Raw9;
    }

    public void setRaw9(String raw9) {
        Raw9 = raw9;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getServingcount() {
        return Servingcount;
    }

    public void setServingcount(String servingcount) {
        Servingcount = servingcount;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public String getRecipeImgName() {
        return RecipeImgName;
    }

    public void setRecipeImgName(String recipeImgName) {
        RecipeImgName = recipeImgName;
    }
}
