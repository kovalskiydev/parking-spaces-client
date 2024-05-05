package ru.kovalskiy.domain.repository.usecase;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.kovalskiy.domain.repository.StreetRepository;
import ru.kovalskiy.domain.repository.models.Street;

import java.io.IOException;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetStreetByIdUseCaseImpl implements GetStreetByIdUseCase {
    StreetRepository streetRepository;

    @Override
    public Street execute(int streetId) throws IOException {
        return streetRepository.getById(streetId);
    }
}
