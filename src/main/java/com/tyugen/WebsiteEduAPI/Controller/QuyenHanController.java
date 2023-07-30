package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.QuyenHan;
import com.tyugen.WebsiteEduAPI.service.QuyenHanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * Updates the QuyenHan object with the specified id.
     *
     * @param id       the ID of the QuyenHan object to be updated
     * @param quyenHan a JSON representation of the updated QuyenHan object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuyenHan(@PathVariable("id") int id, @RequestBody String quyenHan) {
        return quyenHanService.updateQuyenHan(id, quyenHan);
    }

    /**
     * Deletes the QuyenHan object with the specified id.
     *
     * @param id the ID of the QuyenHan object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuyenHan(@PathVariable("id") int id) {
        return quyenHanService.deleteQuyenHan(id);
    }

    /**
     * Retrieve a list of QuyenHan objects.
     *
     * @return a list of QuyenHan objects
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllQuyenHan() {
        return quyenHanService.getAllQuyenHan();
    }

    /**
     * Retrieves a page of QuyenHan objects from the database (Pagination)
     *
     * @param page   the page number to be returned (default: 0)
     * @param size   the number of items to be returned per page (default: 5)
     * @return a Page containing a list of QuyenHan objects
     */
    @GetMapping("/list/page")
    public Page<QuyenHan> getAllQuyenHanPage(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return quyenHanService.getPageQuyenHan(pageable);
    }

}
