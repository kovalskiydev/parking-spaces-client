package ru.kovalskiy.data.repository;


import android.util.Log;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.kovalskiy.data.api.StreetRemoteDataSource;
import ru.kovalskiy.data.models.StreetData;
import ru.kovalskiy.data.storage.sqlite.StreetLocalDataSource;
import ru.kovalskiy.domain.repository.StreetRepository;
import ru.kovalskiy.domain.repository.models.Street;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StreetRepositoryImpl implements StreetRepository {
    StreetRemoteDataSource remoteDataSource;
    StreetLocalDataSource localDataSource;


    @Override
    public void fetchStreetById(int id, StreetFetchCallback callback) {
        Log.d("StreetRepositoryImpl", "fetchStreetById " + id + " " + callback);

        remoteDataSource.fetchStreetById(id, new StreetRemoteDataSource.StreetFetchCallback() {

            @Override
            public void onStreetFetched(StreetData streetData) {
                localDataSource.saveStreet(new StreetData(streetData.getId(), streetData.getName(), streetData.getTotalSpace(), streetData.getAvailableSpace(), streetData.getOccupiedSpace()));
                callback.onStreetFetched(streetData.toDomainModel());

                Log.d("StreetRepositoryImpl", "fetchStreetById " + id + " " + callback);
            }

            @Override
            public void onFetchFailure(String errorMessage) {
                callback.onFetchFailure(errorMessage);
                Log.d("StreetRepositoryImpl", "fetchFailure " + id + " " + callback);

            }
        });
    }

    @Override
    public List<Street> getAll() {
        return null;
    }

    @Override
    public void save(Street street) {
        localDataSource.saveStreet(new StreetData(street.getId(), street.getName(), street.getTotalSpace(), street.getAvailableSpace(), street.getOccupiedSpace()));
    }

    @Override
    public void update(Street street) {
        localDataSource.updateStreet(new StreetData(street.getId(), street.getName(), street.getTotalSpace(), street.getAvailableSpace(), street.getOccupiedSpace()));
    }

    @Override
    public void delete(int streetId) {

    }

    @Override
    public Street getById(int streetId) {
        Log.d("StreetRepositoryImpl", "getById " + streetId);
        return localDataSource.getStreetById(streetId);
    }
}