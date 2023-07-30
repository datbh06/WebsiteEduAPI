package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.model.QuyenHan;
import com.tyugen.WebsiteEduAPI.repository.QuyenHanRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The QuyenHanService class handles business logic for QuyenHan objects.
 */
@Service
public class QuyenHanService {
    private final QuyenHanRepository quyenHanRepository;

    /**
     * Constructs a new QuyenHanService object with the specified QuyenHanRepository.
     *
     * @param quyenHanRepository the QuyenHanRepository to be used by this service
     */
    public QuyenHanService(QuyenHanRepository quyenHanRepository) {
        this.quyenHanRepository = quyenHanRepository;
    }

    /**
     * Adds a new QuyenHan object to the database.
     *
     * @param quyenHan a JSON representation of the new QuyenHan object
     * @return a ResponseEntity indicating the result of the add operation
     */
    public ResponseEntity<?> addQuyenHan(String quyenHan) {
        Gson gson = new Gson();
        QuyenHan quyenHanNew = gson.fromJson(quyenHan, QuyenHan.class);

        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
        Set<ConstraintViolation<QuyenHan>> violations = validator.validate(quyenHanNew);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            quyenHanRepository.save(quyenHanNew);
            return ResponseEntity.ok().build();
        }

    }
}
