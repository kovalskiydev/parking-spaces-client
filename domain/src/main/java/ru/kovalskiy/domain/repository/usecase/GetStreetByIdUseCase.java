package ru.kovalskiy.domain.repository.usecase;

import ru.kovalskiy.domain.repository.models.Street;

import java.io.IOException;

public interface GetStreetByIdUseCase {

    Street execute(int streetId) throws IOException;
}
