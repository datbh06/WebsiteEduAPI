package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.TinhTrangHoc;
import com.tyugen.WebsiteEduAPI.repository.TinhTrangHocRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TinhTrangHocService is a service class that handles the business logic of the application
 */
@Service
public class TinhTrangHocService {

    private final TinhTrangHocRepository tinhTrangHocRepository;

    /**
     * Constructs a new TinhTrangHocService object with the specified TinhTrangHocRepository.
     *
     * @param tinhTrangHocRepository the TinhTrangHocRepository to be used by this controller
     */
    public TinhTrangHocService(TinhTrangHocRepository tinhTrangHocRepository) {
        this.tinhTrangHocRepository = tinhTrangHocRepository;
    }

    /**
     * Adds a new TinhTrangHoc object to the database.
     *
     * @param tinhTrangHoc a JSON representation of the new TinhTrangHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addTinhTrangHoc(String tinhTrangHoc) {
        Gson gson = new Gson();
        TinhTrangHoc tinhTrangNew = gson.fromJson(tinhTrangHoc, TinhTrangHoc.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<TinhTrangHoc>> violations = validator.validate(tinhTrangNew);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            tinhTrangHocRepository.save(tinhTrangNew);
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Updates the TinhTrangHoc object with the specified ID.
     *
     * @param id           the ID of the TinhTrangHoc object to be updated
     * @param tinhTrangHoc a JSON representation of the new TinhTrangHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateTinhTrang(int id, String tinhTrangHoc) {
        Gson gson = new Gson();
        TinhTrangHoc tinhTrangHocNew = gson.fromJson(tinhTrangHoc, TinhTrangHoc.class);
        Optional<TinhTrangHoc> tinhTrangHocOld = tinhTrangHocRepository.findById(id);
        if (tinhTrangHocOld.isPresent()) {
            Validator validator;
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                validator = validatorFactory.getValidator();
            }
            Set<ConstraintViolation<TinhTrangHoc>> violations = validator.validate(tinhTrangHocNew);
            if (!violations.isEmpty()) {
                List<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                tinhTrangHocNew.setTinhTrangHocID(id);
                tinhTrangHocRepository.save(tinhTrangHocNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
