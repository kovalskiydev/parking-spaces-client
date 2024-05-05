package ru.kovalskiy.parkingspaces.client;

import androidx.lifecycle.ViewModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.kovalskiy.domain.repository.StreetRepository;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class StreetViewModel extends ViewModel {
    StreetRepository streetRepository;

    public StreetViewModel() {

//
    }
}
