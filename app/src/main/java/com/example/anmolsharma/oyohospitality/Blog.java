package com.example.anmolsharma.oyohospitality;

/**
 * Created by anmolsharma on 09/07/17.
 */

public class Blog
{

    private String title0,desc0,link0,image0;

    public Blog(){

    }


    public Blog(String title0, String desc0, String link0,String image0) {
        this.title0 = title0;
        this.desc0 = desc0;
        this.link0 = link0;
        this.image0=image0;

    }



    public String getTitle0() {
        return title0;

    }

    public String getDesc0() {
        return desc0;
    }

    public String getImage0() {
        return image0;
    }

    public String getLink0() {
        return link0;
    }


    public void setTitle0(String title0) {
        this.title0 = title0;
    }

    public void setDesc0(String desc0) {
        this.desc0 = desc0;
    }

    public void setLink0(String link0) {
        this.link0 = link0;
    }



}
