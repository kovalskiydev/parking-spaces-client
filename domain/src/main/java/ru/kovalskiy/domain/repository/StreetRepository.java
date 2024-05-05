package ru.kovalskiy.domain.repository;

import ru.kovalskiy.domain.repository.models.Street;

import java.io.IOException;
import java.util.List;

public interface StreetRepository {

    void fetchStreetById(int id, StreetFetchCallback callback);

    interface StreetFetchCallback {
        void onStreetFetched(Street street);
        void onFetchFailure(String errorMessage);
    }

    List<Street> getAll();
    void save(Street street);
    void update(Street street);
    void delete(int streetId);
    Street getById(int streetId) throws IOException;
}
