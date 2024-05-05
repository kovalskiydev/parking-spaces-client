package ru.kovalskiy.domain.repository.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Street {
    Integer id;
    String name;
    Integer totalSpace;
    Integer availableSpace;
    Integer occupiedSpace;

}