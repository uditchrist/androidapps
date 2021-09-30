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
        productList.add(new Product(UUID.randomUUID().toString(), "ONE PLUS 9G", 55000, true, "https://m.media-amazon.com/images/I/61MJilnI5pL._SL1500_.jpg" ));
        productList.add(new Product(UUID.randomUUID().toString(), "MI 11X Pro 5G", 41000, true, "https://m.media-amazon.com/images/I/71Px952NO9S._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "OnePlus 9R 5G", 39999, true, "https://m.media-amazon.com/images/I/61IwksZ-DGL._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Redmi Note 10 Pro Max", 20000, false, "https://m.media-amazon.com/images/I/8186D4Mph7L._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Samsung Galaxy M32 5G", 15000, true, "https://m.media-amazon.com/images/I/71os5DRhuSL._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Asus Selfie", 8000, true, "https://m.media-amazon.com/images/I/81ZebwXE7eL._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Asus Zenfone 2 Laser", 7500, true, "https://m.media-amazon.com/images/I/61t1m1J4DWL._SL1000_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Samsung Galaxy M01 Core", 9900, true, "https://m.media-amazon.com/images/I/71AYb2AGHXL._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "Redmi 9A", 6999, true, "https://m.media-amazon.com/images/I/71sxlhYhKWL._SL1500_.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "realme C11 (2021)", 8799, true, "https://m.media-amazon.com/images/I/31RDanklxiL.jpg"));
        mutableProductList.setValue(productList);
    }
}
