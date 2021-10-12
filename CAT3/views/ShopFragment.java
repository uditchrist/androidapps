package com.example.shoppingcart.views;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.NotificationReceiver;
import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.ShopListAdapter;
import com.example.shoppingcart.databinding.FragmentShopBinding;
import com.example.shoppingcart.models.Product;
import com.example.shoppingcart.viewmodels.ShopViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShopFragment extends Fragment implements ShopListAdapter.ShopInterface {

    private static final String TAG = "ShopFragment";
    FragmentShopBinding fragmentShopBinding;
    private ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    private NavController navController;
    private NotificationManager mNotificationManager;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentShopBinding = FragmentShopBinding.inflate(inflater, container, false);
        return fragmentShopBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(this);
        fragmentShopBinding.shopRecyclerView.setAdapter(shopListAdapter);
        fragmentShopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        fragmentShopBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopListAdapter.submitList(products);
            }
        });

        navController = Navigation.findNavController(view);

    }

    @Override
    public void addItem(Product product) {
        boolean isAdded = shopViewModel.addItemToCart(product);
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + " added to cart.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_shopFragment_to_cartFragment);
                        }
                    })
                    .show();
        } else {
            Snackbar.make(requireView(), "Already have the max quantity in cart.", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onItemClick(Product product) {
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFragment_to_productDetailFragment);
        int NOTIFICATION_ID = 1;
        Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        Intent buttonIntent = new Intent(getActivity().getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getActivity().getBaseContext(), 0, buttonIntent, 0);
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(getActivity().getApplicationContext(), "notify");
        mBuilder.setSmallIcon(R.drawable.message);
        mBuilder.setContentTitle("Big Picture Style");
        mBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(pic));
        mBuilder.setAutoCancel(true);
        mBuilder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);
        mNotificationManager =(NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        mNotificationManager.notify(0, mBuilder.build());
    }
}