package com.fastfood.repository;

import com.fastfood.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private List<MenuItem> menuList= new ArrayList<>();

    // them mon
    public void addMenuItem(MenuItem item){
        menuList.add(item);
    }
    // lay toan bo menu
    public List<MenuItem> getAllMenuItems(){
        return menuList;
    }
    // xoa mon theo id
    public void removeMenuItem(int id){
        menuList.removeIf((item)->item.getId()==id);
    }
}
