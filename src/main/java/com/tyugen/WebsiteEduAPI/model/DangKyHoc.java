package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

/**
 * Represents a DangKyHoc entity.
 * <p>
 * This class is mapped to a database table named "dangkyhoc".
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dangKyHocID;
    @Column
    private Date ngayDangKy;
    @Column
    private Date ngayBatDau;
    @Column
    private Date ngayKetThuc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("dangKyHocs")
    @JoinColumn(name = "khoaHocID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_khoahoc"))
    private KhoaHoc khoaHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("dangKyHocs")
    @JoinColumn(name = "hocVienID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_hocvien"))
    private HocVien hocVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("dangKyHocs")
    @JoinColumn(name = "tinhTrangHocID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_tinhtranghoc"))
    private TinhTrangHoc tinhTrangHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("dangKyHocs")
    @JoinColumn(name = "taiKhoanID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_taikhoan"))
    private TaiKhoan taiKhoan;

    /**
     * Sets the ngayDangKy field to the current date when the entity is first persisted.
     * Validates that the related entities already exist.
     */
    @PrePersist
    public void prePersist() {
        if (khoaHoc == null || hocVien == null || tinhTrangHoc == null || taiKhoan == null) {
            throw new IllegalStateException("Related entities must exist");
        }
        updateNgayBatDauAndNgayKetThuc();
    }

    /**
     * Updates the ngayBatDau and ngayKetThuc fields when the entity is updated.
     */
    @PreUpdate
    public void preUpdate() {
        updateNgayBatDauAndNgayKetThuc();
    }


    /**
     * Updates the ngayBatDau and ngayKetThuc fields based on the tinhTrangHoc and khoaHoc field.
     */
    private void updateNgayBatDauAndNgayKetThuc() {
        ngayDangKy = new Date(System.currentTimeMillis());
        if (tinhTrangHoc != null && tinhTrangHoc.getTinhTrangHocID() == 2) {
            ngayBatDau = new Date(System.currentTimeMillis());
            if (khoaHoc != null) {
                int thoiGianHoc = khoaHoc.getThoiGianHoc();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(ngayBatDau);
                calendar.add(Calendar.MONTH, thoiGianHoc);
                ngayKetThuc = new Date(calendar.getTimeInMillis());
            }
        }
    }
}
