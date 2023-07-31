package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.model.ChuDe;
import com.tyugen.WebsiteEduAPI.service.ChuDeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ChuDeController is a controller class that handles requests related to ChuDe objects.
 */
@RestController
@RequestMapping("/api/v1/chude")
public class ChuDeController {
    private final ChuDeService chuDeService;

    /**
     * Constructs a new ChuDeController object with the specified ChuDeService.
     *
     * @param chuDeService the ChuDeService to be used by this controller
     */
    public ChuDeController(ChuDeService chuDeService) {
        this.chuDeService = chuDeService;
    }

    /**
     * Adds a new ChuDe object to the database.
     *
     * @param chuDe a JSON representation of the new ChuDe object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addChuDe(@RequestBody String chuDe) {
        return chuDeService.addChuDe(chuDe);
    }

    /**
     * Update an existing ChuDe object in the database.
     *
     * @param id    the id of the ChuDe object to be updated
     * @param chuDe a JSON representation of the updated ChuDe object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateChuDe(@PathVariable int id, @RequestBody String chuDe) {
        return chuDeService.updateChuDe(id, chuDe);
    }

    /**
     * Delete an existing ChuDe object in the database.
     *
     * @param id the id of the ChuDe object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteChuDe(@PathVariable int id) {
        return chuDeService.deleteChuDe(id);
    }

    /**
     * Get all ChuDe objects from the database.
     *
     * @return a ResponseEntity containing a List of all ChuDe objects
     */
    @GetMapping("/list")
    public ResponseEntity<?> getAllChuDe() {
        return chuDeService.getAllChuDe();
    }

    /**
     * Get a page of ChuDe objects from the database.
     *
     * @param page the page number (default: 0)
     * @param size the number of ChuDe objects per page (default: 5)
     * @return a Page of ChuDe objects
     */
    @GetMapping("/list/page")
    public Page<ChuDe> getAllChuDePage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return chuDeService.getAllOnPage(pageable);
    }
}
