package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.QuyenHanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The QuyenHanController class handles requests for QuyenHan objects and sends responses back.
 */
@RestController
@RequestMapping("/api/v1/quyenhan")
public class QuyenHanController {

    private final QuyenHanService quyenHanService;

    /**
     * Constructs a new QuyenHanController object with the specified QuyenHanService.
     *
     * @param quyenHanService the QuyenHanService to be used by this controller
     */
    public QuyenHanController(QuyenHanService quyenHanService) {
        this.quyenHanService = quyenHanService;
    }

    /**
     * Adds a new QuyenHan object to the database.
     *
     * @param quyenHan a JSON representation of the new QuyenHan object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addQuyenHan(@RequestBody String quyenHan) {
        return quyenHanService.addQuyenHan(quyenHan);
    }
}
