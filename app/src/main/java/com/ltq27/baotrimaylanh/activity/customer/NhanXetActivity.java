package com.ltq27.baotrimaylanh.activity.customer;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ltq27.baotrimaylanh.R;
import com.ltq27.baotrimaylanh.apiresponse.NhanXetDTO;
import com.ltq27.baotrimaylanh.apiresponse.NhanXetResponse;
import com.ltq27.baotrimaylanh.databinding.ActivityNhanXetBinding;
import com.ltq27.baotrimaylanh.recyclerview.RcvNhanXetAdapter;
import com.ltq27.baotrimaylanh.retrofit2.ApiInterface;
import com.ltq27.baotrimaylanh.retrofit2.RetrofitClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhanXetActivity extends AppCompatActivity {
    private List<NhanXetResponse> listNhanXet;
    private ActivityNhanXetBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityNhanXetBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        bd.rcvNhanXet.setHasFixedSize(true);
        bd.rcvNhanXet.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 0);

        fetchReviews(id);

        bd.imgBack.setOnClickListener(v -> finish());

        bd.submitButton.setOnClickListener(view -> {
            NhanXetDTO nhanXetDTO = new NhanXetDTO();
            nhanXetDTO.setGoiDichVuId(id);
            UserSession userSession = new UserSession(getApplicationContext());
            Long idC = Long.valueOf(userSession.getUserId());
            nhanXetDTO.setCustomerId(idC);
            nhanXetDTO.setStart(bd.ratingBar.getNumStars());
            nhanXetDTO.setComment(bd.commentEditText.getText().toString());

            // Gọi API phân tích cảm xúc và xử lý kết quả
            String commentText = bd.commentEditText.getText().toString();
            new Thread(() -> {
                String sentiment = getSentimentFromAPI(commentText);

                // Cập nhật nhãn dựa trên kết quả sentiment
                String label = "Trung tính";  // Mặc định là Trung tính
                if ("positive".equalsIgnoreCase(sentiment)) {
                    label = "Tích cực";
                } else if ("negative".equalsIgnoreCase(sentiment)) {
                    label = "Tiêu cực";
                }

                // Gán nhãn cho đối tượng nhanXetDTO
                nhanXetDTO.setLabel(label);

                // Sau khi có nhãn, thực hiện submit review
                runOnUiThread(() -> submitReview(nhanXetDTO));
            }).start();
        });
    }

    private String getSentimentFromAPI(String commentText) {
        String sentiment = "neutral";  // Mặc định là trung tính
        try {
            // Tạo URL cho API
            URL url = new URL("http://localhost:5000/predict?text=" + URLEncoder.encode(commentText, "UTF-8"));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000); // Thời gian timeout (5 giây)
            urlConnection.setReadTimeout(5000);    // Thời gian đọc timeout

            // Đọc kết quả từ InputStream
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Phân tích JSON trả về từ API
            JSONObject jsonResponse = new JSONObject(response.toString());
            sentiment = jsonResponse.getString("sentiment"); // Lấy giá trị sentiment từ JSON

            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sentiment;
    }


    private void fetchReviews(Long id) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<List<NhanXetResponse>> call = apiInterface.danhSachNhanXet(id);
        call.enqueue(new Callback<List<NhanXetResponse>>() {
            @Override
            public void onResponse(Call<List<NhanXetResponse>> call, Response<List<NhanXetResponse>> response) {
                if (response.isSuccessful()) {
                    listNhanXet = response.body();
                    RcvNhanXetAdapter rcvNhanXetAdapter = new RcvNhanXetAdapter(listNhanXet, NhanXetActivity.this);
                    bd.rcvNhanXet.setAdapter(rcvNhanXetAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NhanXetResponse>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void submitReview(NhanXetDTO nhanXetDTO) {
        ApiInterface apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<NhanXetResponse> call = apiInterface.themNhanXet(nhanXetDTO);
        call.enqueue(new Callback<NhanXetResponse>() {
            @Override
            public void onResponse(Call<NhanXetResponse> call, Response<NhanXetResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    // Cập nhật lại danh sách đánh giá
                    fetchReviews(nhanXetDTO.getGoiDichVuId());
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<NhanXetResponse> call, Throwable t) {
                // Handle failure
            }
        });

        // Adjust RecyclerView when keyboard shows or hides
        adjustRecyclerViewForKeyboard();
    }

    private void adjustRecyclerViewForKeyboard() {
        final ConstraintLayout mainLayout = findViewById(R.id.main);
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                mainLayout.getWindowVisibleDisplayFrame(r);
                int screenHeight = mainLayout.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) { // If keyboard is opened
                    bd.rcvNhanXet.setLayoutParams(new ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            300 // Adjust as needed
                    ));
                } else {
                    bd.rcvNhanXet.setLayoutParams(new ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                    ));
                }
            }
        });
    }
}
