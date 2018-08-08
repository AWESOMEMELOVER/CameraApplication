package com.boscha.micka.cameraapplication.Entities;


/**
 * Created by micka on 8/8/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Camera {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ServerId")
    @Expose
    private Integer serverId;
    @SerializedName("zmUrl")
    @Expose
    private String zmUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getZmUrl() {
        return zmUrl;
    }

    public void setZmUrl(String zmUrl) {
        this.zmUrl = zmUrl;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serverId=" + serverId +
                ", zmUrl='" + zmUrl + '\'' +
                '}';
    }
}