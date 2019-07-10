package com.example.foodforgood;

import com.example.foodforgood.BussinessLogic.DisplaydishBL;
import com.example.foodforgood.BussinessLogic.LoginBL;
import com.example.foodforgood.BussinessLogic.RegisterBL;
import com.example.foodforgood.Model.RecipeModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UTest {
    @Test
    public void Logintest()
    {
        LoginBL loginBL=new LoginBL("dia","dia");
        boolean result=loginBL.checkUser();
        assertEquals(true,result);
    }

    @Test
    public void registerUserTest()
    {
        RegisterBL registerBL=new RegisterBL("pradip","","kandel","pradip","pradip","expert cook","default.jpg","","male","Nepal");
        boolean result=registerBL.registerUser();
        assertEquals(true,result);
    }

    @Test
    public void displayrecipeTest()
    {
        DisplaydishBL displaydishBL=new DisplaydishBL();
        List<RecipeModel> recipeModelList=displaydishBL.latestdish();
        String dishname=recipeModelList.get(0).getRecipeName();
        assertEquals("Fried rice",dishname);
    }
}
