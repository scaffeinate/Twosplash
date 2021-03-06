package io.phoenix.app.splasho.data.repositories.photos;

import java.util.List;

import io.phoenix.app.splasho.data.models.Photo;

import static io.phoenix.app.splasho.photos.PhotosContract.OrderBy;

/**
 * Created by sudharti on 10/21/17.
 */

public interface PhotosDataSource {

    void loadPhotos(int page, @OrderBy String orderBy, LoadPhotosCallback callback);

    void loadCuratedPhotos(int page, @OrderBy String orderBy, LoadPhotosCallback callback);

    interface LoadPhotosCallback {
        void onPhotosLoaded(List<Photo> photos);

        void onDataNotAvailable(String errorMessage);
    }
}
