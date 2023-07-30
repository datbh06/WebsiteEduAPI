package com.tyugen.WebsiteEduAPI.Controller;

import com.tyugen.WebsiteEduAPI.service.DangKyHocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/dangkyhoc")
public class DangKyHocController {

    private final DangKyHocService dangKyHocService;

    public DangKyHocController(DangKyHocService dangKyHocService) {
        this.dangKyHocService = dangKyHocService;
    }

    /**
     * This method is used to add a new DangKyHoc object to the database.
     *
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addDangKyHoc(@RequestBody String dangKyHoc) {
        return dangKyHocService.addDangKyHoc(dangKyHoc);
    }

    /**
     * This method is used to update an existing DangKyHoc object in the database.
     *
     * @param id        is the ID of the DangKyHoc object that needs to be updated.
     * @param dangKyHoc is a JSON string that contains the information of the DangKyHoc object.
     * @return a ResponseEntity object that contains the status of the request.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDangKyHoc(@PathVariable("id") int id, @RequestBody String dangKyHoc) {
        return dangKyHocService.updateDangKyHoc(id, dangKyHoc);
    }
}
