package com.example.amyas.grocery.async.rxjava;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * author: Amyas
 * date: 2018/3/1
 */

public class LandResponse {


    /**
     * id : 1
     * name : 地块1
     * description :
     * area : 33.44
     * longitude : 111.11
     * latitude : 44.44
     * create_time : 2017-11-11 11:11:11
     * images : []
     * crop : {"id":1,"name":"水稻","price":"3.5"}
     * contact_name : 张强
     * contact_phone : 13394857768
     * gather_longitude : 111.11
     * gather_latitude : 33.33
     */

    private int id;
    private String name;
    private String description;
    private double area;
    private double longitude;
    private double latitude;
    private CropBean crop;
    private String contact_name;
    private String contact_phone;
    private double gather_longitude;
    private double gather_latitude;
    private List<String> images;
    private List<String> boundary;
    private boolean is_reserving;


    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", area=" + area +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", crop=" + crop +
                ", contact_name='" + contact_name + '\'' +
                ", contact_phone='" + contact_phone + '\'' +
                ", gather_longitude=" + gather_longitude +
                ", gather_latitude=" + gather_latitude +
                ", images=" + images +
                ", boundary=" + boundary +
                ", is_reserving=" + is_reserving +
                '}';
    }

    public boolean isIs_reserving() {
        return is_reserving;
    }

    public void setIs_reserving(boolean is_reserving) {
        this.is_reserving = is_reserving;
    }

    public List<String> getBoundary() {
        return boundary;
    }

    public void setBoundary(List<String> boundary) {
        this.boundary = boundary;
    }



    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public CropBean getCrop() {
        return crop;
    }

    public void setCrop(CropBean crop) {
        this.crop = crop;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public double getGather_longitude() {
        return gather_longitude;
    }

    public void setGather_longitude(double gather_longitude) {
        this.gather_longitude = gather_longitude;
    }

    public double getGather_latitude() {
        return gather_latitude;
    }

    public void setGather_latitude(double gather_latitude) {
        this.gather_latitude = gather_latitude;
    }

    public static class CropBean {

        /**
         * id : 1
         * name : 水稻
         * price : 3.5
         */

        private int id;
        private String name;
        private double price;

        public CropBean() {
        }



        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
