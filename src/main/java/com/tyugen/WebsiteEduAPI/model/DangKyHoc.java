package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table
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
    @JoinColumn(name = "khoaHocID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_khoahoc"))
    private KhoaHoc khoaHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hocVienID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_hocvien"))
    private HocVien hocVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tinhTrangHocID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_tinhtranghoc"))
    private TinhTrangHoc tinhTrangHoc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiKhoanID", foreignKey = @ForeignKey(name = "fk_dangkyhoc_taikhoan"))
    private TaiKhoan taiKhoan;

    public Integer getDangKyHocID() {
        return dangKyHocID;
    }

    public void setDangKyHocID(Integer dangKyHocID) {
        this.dangKyHocID = dangKyHocID;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public KhoaHoc getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(KhoaHoc khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public HocVien getHocVien() {
        return hocVien;
    }

    public void setHocVien(HocVien hocVien) {
        this.hocVien = hocVien;
    }

    public TinhTrangHoc getTinhTrangHoc() {
        return tinhTrangHoc;
    }

    public void setTinhTrangHoc(TinhTrangHoc tinhTrangHoc) {
        this.tinhTrangHoc = tinhTrangHoc;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
