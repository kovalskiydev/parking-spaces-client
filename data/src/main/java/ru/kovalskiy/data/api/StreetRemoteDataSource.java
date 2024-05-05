package ru.kovalskiy.data.api;

import ru.kovalskiy.data.models.StreetData;
import ru.kovalskiy.domain.repository.models.Street;

public interface StreetRemoteDataSource {
    void fetchStreetById(int id, StreetFetchCallback callback);

    interface StreetFetchCallback {
        void onStreetFetched(StreetData street);

        void onFetchFailure(String errorMessage);
    }
}
