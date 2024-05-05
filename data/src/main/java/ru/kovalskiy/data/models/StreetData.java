package ru.kovalskiy.data.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.kovalskiy.domain.repository.models.Street;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StreetData {
    Integer id;
    String name;
    Integer totalSpace;
    Integer availableSpace;
    Integer occupiedSpace;

    public Street toDomainModel() {
        return new Street(id, name, totalSpace, availableSpace, occupiedSpace);
    }
}
