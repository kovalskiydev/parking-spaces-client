package ru.kovalskiy.data.storage.sqlite;

import ru.kovalskiy.data.models.StreetData;
import ru.kovalskiy.domain.repository.models.Street;

public interface StreetLocalDataSource {
    void saveStreet(StreetData streetData);
    void updateStreet(StreetData streetData);
    Street getStreetById(int id);
}
