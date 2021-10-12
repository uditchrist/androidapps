package com.example.shoppingcart.views;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.databinding.FragmentOrderBinding;
import com.example.shoppingcart.viewmodels.ShopViewModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderFragment extends Fragment {

    NavController navController;
    FragmentOrderBinding fragmentOrderBinding;
    ShopViewModel shopViewModel;
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView= null;
    ProgressDialog p;
    private String pic = "https://st2.depositphotos.com/1177973/11013/i/950/depositphotos_110130116-stock-photo-collage-of-different-pizza.jpg";

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_order, container, false);
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_order, container,false);
        fragmentOrderBinding = FragmentOrderBinding.inflate(inflater, container, false);
        imageView=root.findViewById(R.id.imageView);
        return fragmentOrderBinding.getRoot();
    }

    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(getActivity());
            p.setMessage("Please wait...It is loading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmImg;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(imageView!=null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
            }else {
                p.show();
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);

        fragmentOrderBinding.continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_shopFragment);
            }
        });
        fragmentOrderBinding.asyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask = new AsyncTaskExample();
                asyncTask.execute("https://st2.depositphotos.com/1177973/11013/i/950/depositphotos_110130116-stock-photo-collage-of-different-pizza.jpg");
            }
        });
    }
}