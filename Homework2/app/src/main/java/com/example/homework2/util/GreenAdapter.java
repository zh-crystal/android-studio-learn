package com.example.homework2.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework2.R;
import com.example.homework2.model.Message;
import com.example.homework2.widget.CircleImageView;

import java.util.List;


/**
 * 适配器
 */
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = "GreenAdapter";
    private int mNumberItems;
    private final ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private List<Message> messages;

    public GreenAdapter(int numListItems, List<Message> messages, ListItemClickListener listener) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        this.messages = messages;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final CircleImageView avatarCircleImageView;
        private final ImageView noticeImageView;
        private final TextView titleTextView;
        private final TextView timeTextView;
        private final TextView despTextView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarCircleImageView = itemView.findViewById(R.id.iv_avatar);
            noticeImageView = itemView.findViewById(R.id.robot_notice);
            titleTextView = itemView.findViewById(R.id.tv_title);
            timeTextView = itemView.findViewById(R.id.tv_time);
            despTextView = itemView.findViewById(R.id.tv_description);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            Message message = messages.get(position);
            titleTextView.setText(message.getTitle());
            timeTextView.setText(message.getTime());
            despTextView.setText(message.getDescription());
            if (message.isOfficial()) {
                noticeImageView.setVisibility(View.VISIBLE);
            } else {
                noticeImageView.setVisibility(View.INVISIBLE);
            }
            //获取头像
            int icon = IconSelector.getIconId(message.getIcon());
            avatarCircleImageView.setImageResource(icon);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition+1, titleTextView.getText().toString());
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedPosition, String nickName);
    }
}
