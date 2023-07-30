package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tyugen.WebsiteEduAPI.Adapter.DateTypeAdapter;
import com.tyugen.WebsiteEduAPI.model.DangKyHoc;
import com.tyugen.WebsiteEduAPI.repository.DangKyHocRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DangKyHocService is a service class that handles the business logic of the DangKyHocController class.
 */
@Service
public class DangKyHocService {
    private final DangKyHocRepository dangKyHocRepository;

    /**
     * Constructor for DangKyHocService class.
     *
     * @param dangKyHocRepository is a repository class that handles the database operations of the DangKyHocController class.
     */
    public DangKyHocService(DangKyHocRepository dangKyHocRepository) {
        this.dangKyHocRepository = dangKyHocRepository;
    }

    /**
     * This method is used to add a new DangKyHoc object to the database.
     *
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    public ResponseEntity<?> addDangKyHoc(String dangKyHoc) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        DangKyHoc dangKyHocNew = gson.fromJson(dangKyHoc, DangKyHoc.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<DangKyHoc>> violations = validator.validate(dangKyHocNew);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            dangKyHocRepository.save(dangKyHocNew);
            return ResponseEntity.ok().build();
        }
    }
}
