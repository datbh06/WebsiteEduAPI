package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.TinhTrangHocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Maps requests for the TinhTrangHoc API to the TinhTrangHocService.
 */
@RestController
@RequestMapping("/api/v1/tinhtranghoc")
public class TinhTrangHocController {
    private final TinhTrangHocService tinhTrangHocService;

    /**
     * Constructs a new TinhTrangHocController object with the specified TinhTrangHocService.
     *
     * @param tinhTrangHocService the TinhTrangHocService to be used by this controller
     */
    public TinhTrangHocController(TinhTrangHocService tinhTrangHocService) {
        this.tinhTrangHocService = tinhTrangHocService;
    }

    /**
     * Adds a new TinhTrangHoc object to the database.
     *
     * @param tinhTrangHoc a JSON representation of the new TinhTrangHoc object
     * @return a ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/add")
    public ResponseEntity<?> addTinhTrangHoc(@RequestBody String tinhTrangHoc) {
        return tinhTrangHocService.addTinhTrangHoc(tinhTrangHoc);
    }

    /**
     * Updates the TinhTrangHoc object with the specified id.
     *
     * @param id           the ID of the TinhTrangHoc object to be updated
     * @param tinhTrangHoc a JSON representation of the new TinhTrangHoc object
     * @return a ResponseEntity indicating the result of the update operation
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTinhTrangHoc(@PathVariable("id") int id, @RequestBody String tinhTrangHoc) {
        return tinhTrangHocService.updateTinhTrang(id, tinhTrangHoc);
    }

    /**
     * Deletes the TinhTrangHoc object with the specified id.
     *
     * @param id the ID of the TinhTrangHoc object to be deleted
     * @return a ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTinhTrangHoc(@PathVariable("id") int id) {
        return tinhTrangHocService.deleteTinhTrang(id);
    }
}
