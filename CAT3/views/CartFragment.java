package com.example.shoppingcart.views;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.NotificationReceiver;
import com.example.shoppingcart.R;
import com.example.shoppingcart.adapters.CartListAdapter;
import com.example.shoppingcart.databinding.FragmentCartBinding;
import com.example.shoppingcart.models.CartItem;
import com.example.shoppingcart.viewmodels.ShopViewModel;

import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;
    private NotificationManager mNotificationManager;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final CartListAdapter cartListAdapter = new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotalTextView.setText("Total: Rs. " + aDouble.toString());
            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
                int NOTIFICATION_ID = 1;
                NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(getActivity().getApplicationContext(), "notify");
                Intent ii = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getActivity().getApplicationContext(), 0, ii, 0);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pizzahut.co.in/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
                Intent buttonIntent = new Intent(getContext(), NotificationReceiver.class);
                buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
                PendingIntent dismissIntent = PendingIntent.getActivity(getContext(), 0, buttonIntent, 0);

                mBuilder.setSmallIcon(R.drawable.message);
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
                mBuilder.setContentTitle("UD Learns");
                mBuilder.setStyle(new NotificationCompat.InboxStyle().addLine("Your order has been placed!").addLine("Thank you for your order.").addLine("Happy Learning and Have a great day!").setBigContentTitle("New Messages for you").setSummaryText("Inbox"));
                mBuilder.setAutoCancel(true);
                mBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
                mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
                mBuilder.addAction(android.R.drawable.ic_menu_view, "ORDER AGAIN", pi);
                mBuilder.addAction(android.R.drawable.ic_menu_view, "LINK", pendingIntent);
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

            private void clearNotification() {
                int notificationId = getActivity().getIntent().getIntExtra("notificationId", 0);

                mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.cancel(notificationId);
            }
        });
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem, quantity);
    }
}

