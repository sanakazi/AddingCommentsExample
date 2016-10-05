package com.example.sanakazi.addingcommentsexample.POJOS;

import java.util.ArrayList;

/**
 * Created by SanaKazi on 10/5/2016.
 */
public class ResponseJson {
    private int status;
    private ArrayList<MyMessage> Message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<MyMessage> getMessage() {
        return Message;
    }

    public void setMessage(ArrayList<MyMessage> message) {
        Message = message;
    }

    public class MyMessage{
        private int num;
        private String productCategoryName,imageurl;


        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getProductCategoryName() {
            return productCategoryName;
        }

        public void setProductCategoryName(String productCategoryName) {
            this.productCategoryName = productCategoryName;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
    }
}
