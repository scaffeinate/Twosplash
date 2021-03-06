package io.phoenix.app.splasho.photos;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.data.models.Photo;
import io.phoenix.app.splasho.util.DisplayUtils;

/**
 * Created by sudharti on 10/22/17.
 */

// TODO (8): ProgressBar Footer in the Adapter
public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.ViewHolder> {

    private List<Photo> mPhotos;
    private Activity mActivity;

    public PhotosGridAdapter(Activity activity) {
        this.mActivity = activity;
        this.mPhotos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void setPhotos(List<Photo> photos) {
        this.mPhotos = photos;
        notifyDataSetChanged();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView mPhotoImageView;
        final RelativeLayout mPlaceholderContainer;
        final ImageButton mLikeButton;
        final ImageButton mDownloadButton;
        final ImageButton mWallpaperButton;

        public ViewHolder(View itemView) {
            super(itemView);
            mPlaceholderContainer = itemView.findViewById(R.id.rl_placeholder_container);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            mLikeButton = itemView.findViewById(R.id.btn_like);
            mDownloadButton = itemView.findViewById(R.id.btn_download);
            mWallpaperButton = itemView.findViewById(R.id.btn_wallpaper);
        }

        // TODO (4): Check the size of images downloaded
        private void bind(int position) {
            Photo photo = mPhotos.get(position);
            if (photo != null) {
                String imageUrl = photo.getUrls().getSmall();

                int screenHeight = DisplayUtils.getInstance(mActivity).getScreenHeight();
                int height = (int) (screenHeight / 2.25);

                mPlaceholderContainer.setMinimumHeight(height);
                mPhotoImageView.setMinimumHeight(height);

                if (imageUrl != null) {
                    Picasso.with(itemView.getContext())
                            .load(imageUrl)
                            .centerCrop().fit()
                            .into(mPhotoImageView);
                }
            }
        }
    }
}
