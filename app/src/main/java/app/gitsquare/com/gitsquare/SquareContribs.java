package app.gitsquare.com.gitsquare;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import app.gitsquare.com.gitsquare.adapter.ContribAdapter;
import app.gitsquare.com.gitsquare.model.Model_Contrib;
import app.gitsquare.com.gitsquare.Util.AppUtil;
import app.gitsquare.com.gitsquare.retrofit.APIclient;
import app.gitsquare.com.gitsquare.retrofit.APIinterFace;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.gitsquare.com.gitsquare.Util.AppUtil.showToast;

public class SquareContribs extends AppCompatActivity {

    private Activity activity;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerViewContributers;

    private LinearLayout layoutNoDataFound;

    private ArrayList<Model_Contrib> arrayList = new ArrayList<>();

    private String TAG = "CONTRIB";

    private ProgressBar progressBar;

    private ContribAdapter adapter;

    private boolean isSwapToRefreshOn = false;

    private TextView txtFilter;

    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_square_contribs);

        activity = SquareContribs.this;

        initViews();

        onclickListner();
    }

    // ALL VIEW CLICK LISTNER
    private void onclickListner() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                isSwapToRefreshOn = true;

                getAllSquareContribs();
            }
        });

        txtFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(arrayList.size()>0)
                {
                    Collections.sort(arrayList, new Comparator<Model_Contrib>() {
                        @Override
                        public int compare(Model_Contrib lhs, Model_Contrib rhs) {

                           return  (lhs.getContribution() < rhs.getContribution()) ? -1: (lhs.getContribution() > rhs.getContribution()) ? 1:0 ;
                        }
                    });



                    setAdapter();
                }else
                {
                    showToast(activity,"No data found.");
                }

            }
        });

    }

    // INIT ALL VIEWS
    private void initViews() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        txtFilter = (TextView) findViewById(R.id.textViewFilterContribution);

        layoutNoDataFound = (LinearLayout) findViewById(R.id.layoutNoDataFound);

        recyclerViewContributers = (RecyclerView) findViewById(R.id.recycleviewContributers);

         layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);

        recyclerViewContributers.setLayoutManager(layoutManager);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        adapter = new ContribAdapter(activity, arrayList);

        recyclerViewContributers.setAdapter(adapter);

        getAllSquareContribs();

    }


    // GET DATA FROM API
    private void getAllSquareContribs() {

        if (AppUtil.isNetworkAvailable(activity)) {


            if (!isSwapToRefreshOn) {
                progressBar.setVisibility(View.VISIBLE);
            }


            APIinterFace.getContributers api = APIclient.getClient().create(APIinterFace.getContributers.class);

            Call<ResponseBody> call = api.responce("");

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {

                        if (!isSwapToRefreshOn) {
                            progressBar.setVisibility(View.GONE);
                        } else {
                            swipeRefreshLayout.setRefreshing(false);
                        }

                        if (!response.isSuccessful()) {
                            showToast(activity, activity.getResources().getString(R.string.please_try_again));
                            return;
                        }


                        String result = response.body().string();

                        if (result != null) {
                            Log.e(TAG, result);

                            JSONArray array = new JSONArray(result);


                            if (array.length() > 0) {

                                arrayList.clear();


                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject row = array.getJSONObject(i);

                                    Model_Contrib model = new Model_Contrib();

                                    model.setLogin(row.getString("login"));
                                    model.setImagePath(row.getString("avatar_url"));
                                    model.setRespons_url(row.getString("repos_url"));
                                    model.setContribution(row.getInt("contributions"));

                                    arrayList.add(model);
                                }
                            }

                            setAdapter();


                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    if (!isSwapToRefreshOn) {
                        progressBar.setVisibility(View.GONE);
                    } else {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                }
            });
        } else {
            //NO INTERNET CONNECTION
            showToast(activity, activity.getResources().getString(R.string.no_internet_connection_message));
            if (isSwapToRefreshOn) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    // NOTIFY RECYCLEVIEW
    private void setAdapter() {

        if (arrayList.size() > 0) {
            adapter.notifyDataSetChanged();
            layoutNoDataFound.setVisibility(View.GONE);
            recyclerViewContributers.setVisibility(View.VISIBLE);
            layoutManager.scrollToPositionWithOffset(0, 20);
        } else {
            // NO DATA FOUND

            layoutNoDataFound.setVisibility(View.VISIBLE);
            recyclerViewContributers.setVisibility(View.GONE);
        }


    }
}
