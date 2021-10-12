package com.example.shoppingcart.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingcart.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts() {
        if (mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "MCA", 100000, true, "https://www.indcareer.com/files/Master%20of%20Computer%20Applications.jpg" ));
        productList.add(new Product(UUID.randomUUID().toString(), "MCS", 100000, true, "https://crispy-pics.s3.amazonaws.com/uploads/ckeditor/pictures/226/content_Masters_in_Computer_Science.png"));
        productList.add(new Product(UUID.randomUUID().toString(), "MDS", 100000, true, "https://simad.edu.so/wp-content/uploads/2020/12/WhatsApp-Image-2020-12-26-at-19.03.22.jpeg"));
        productList.add(new Product(UUID.randomUUID().toString(), "BCA", 60000, true, "https://www.javatpoint.com/fullformpages/images/BCA.jpg"));

        mutableProductList.setValue(productList);
    }
}
