package com.tyugen.WebsiteEduAPI.service;

import com.google.gson.Gson;
import com.tyugen.WebsiteEduAPI.exceptions.ValidationUtils;
import com.tyugen.WebsiteEduAPI.model.KhoaHoc;
import com.tyugen.WebsiteEduAPI.model.QuyenHan;
import com.tyugen.WebsiteEduAPI.repository.QuyenHanRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        List<String> errorMessages = ValidationUtils.validate(quyenHanNew, QuyenHan.class);
        if (!errorMessages.isEmpty()) {
            return ResponseEntity.badRequest().body(errorMessages);
        } else {
            quyenHanRepository.save(quyenHanNew);
            return ResponseEntity.ok().build();
        }

    }

    /**
     * Updates a QuyenHan object with the specified ID.
     *
     * @param id       the ID of the QuyenHan object to be updated
     * @param quyenHan a JSON representation of the new QuyenHan object
     * @return a ResponseEntity indicating the result of the update operation
     */
    public ResponseEntity<?> updateQuyenHan(int id, String quyenHan) {
        Gson gson = new Gson();
        QuyenHan quyenHanNew = gson.fromJson(quyenHan, QuyenHan.class);
        Optional<QuyenHan> quyenHanOld = quyenHanRepository.findById(id);
        if (quyenHanOld.isPresent()) {
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
                quyenHanNew.setQuyenHanID(id);
                quyenHanRepository.save(quyenHanNew);
                return ResponseEntity.ok().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a QuyenHan object with the specified ID.
     *
     * @param id the ID of the QuyenHan object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    public ResponseEntity<?> deleteQuyenHan(int id) {
        Optional<QuyenHan> quyenHan = quyenHanRepository.findById(id);
        if (quyenHan.isPresent()) {
            quyenHanRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieve a list of all quyen han in the database
     *
     * @return a ResponseEntity containing a list of all quyen han in the database
     */
    public ResponseEntity<?> getAllQuyenHan() {
        Optional<List<QuyenHan>> quyenHan = Optional.of(quyenHanRepository.findAll());
        return quyenHan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a page of QuyenHan objects from the database (Pagination)
     *
     * @param pageable the Pageable object containing the page number and page size
     * @return a Page containing a list of QuyenHan objects
     */
    public Page<QuyenHan> getPageQuyenHan(Pageable pageable) {
        return quyenHanRepository.findAll(pageable);
    }
}
