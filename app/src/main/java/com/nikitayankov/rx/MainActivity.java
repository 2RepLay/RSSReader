package com.nikitayankov.rx;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nikitayankov.rx.Retrofit.Channel;
import com.nikitayankov.rx.Retrofit.Feed;
import com.nikitayankov.rx.Retrofit.FeedItem;
import com.nikitayankov.rx.Retrofit.RssAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.list_items)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ChannelAdapter mChannelAdapter;
    LinearLayoutManager mLinearLayoutManager;

    ArrayList<FeedItem> mFeedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mToolbar.setTitle("Retrofit 2 RSS");

        mChannelAdapter = new ChannelAdapter(mFeedItems);
        mLinearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mChannelAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.goha.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        RssAdapter adapter = retrofit.create(RssAdapter.class);

        Call<Feed> call = adapter.GetItems("cybersport");
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(@NonNull Call call,@NonNull Response response) {
                Feed feed = (Feed)response.body();
                Channel channel = feed.getChannel();

                updateFeed(channel.getFeedItems());
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {

            }
        });
    }

    private void updateFeed(ArrayList<FeedItem> feedItems) {
        this.mFeedItems.addAll(feedItems);
        this.mChannelAdapter.notifyDataSetChanged();
    }

    class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {
        ArrayList<FeedItem> mFeedItems;

        ChannelAdapter(ArrayList<FeedItem> feedItems) {
            this.mFeedItems = feedItems;
        }

        @Override
        public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, parent, false);

            return new ChannelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ChannelViewHolder holder, int position) {
            FeedItem feedItem = mFeedItems.get(position);

            holder.mTitle.setText(feedItem.getTitleString());
            holder.mLink.setText(feedItem.getLinkString());
            holder.mDescription.setText(prepareDescription(feedItem.getDescriptionString()));
            holder.mLinkAuthor.setText(feedItem.getAuthorString());
            holder.mPublicationDate.setText(prepareDate(feedItem.getDateString()));
            holder.mCategory.setText(feedItem.getCategoryString());
        }

        @Override
        public int getItemCount() {
            return this.mFeedItems.size();
        }

        Spanned prepareDescription(String description) {
            String prepared;
            prepared = description.substring(0, 100).concat("...");

            return Html.fromHtml(prepared);
        }

        String prepareDate(String date) {
            String prepared;

            SimpleDateFormat format = new SimpleDateFormat("dd MMMM hh:mm", Locale.ENGLISH);
            prepared = format.format(Date.parse(date));

            return prepared;
        }

        class ChannelViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.item_title)
            TextView mTitle;

            @BindView(R.id.item_link)
            TextView mLink;

            @BindView(R.id.item_description)
            TextView mDescription;

            @BindView(R.id.item_link_author)
            TextView mLinkAuthor;

            @BindView(R.id.item_publication_date)
            TextView mPublicationDate;

            @BindView(R.id.item_category)
            TextView mCategory;

            @OnClick(R.id.card)
            void open(View view) {
                Toast.makeText(view.getContext(), "Toasted", Toast.LENGTH_SHORT).show();
            }

            ChannelViewHolder(View itemView) {
                super(itemView);

                ButterKnife.bind(this, itemView);
            }
        }
    }
}
