package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hocVienID;

    @Column
    private String hinhAnh;
    @Column
    private String hoTen;
    @Column
    private Date ngaySinh;
    @Column
    private String soDienThoai;
    @Column
    private String email;
    @Column
    private String tinhThanh;
    @Column
    private String quanHuyen;
    @Column
    private String phuongXa;
    @Column
    private String soNha;

    @OneToMany(mappedBy = "hocVien", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("hocVien")
    private Set<DangKyHoc> dangKyHocs;

    public Integer getHocVienID() {
        return hocVienID;
    }

    public void setHocVienID(Integer hocVienID) {
        this.hocVienID = hocVienID;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public String getQuanHuyen() {
        return quanHuyen;
    }

    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public Set<DangKyHoc> getDangKyHocs() {
        return dangKyHocs;
    }

    public void setDangKyHocs(Set<DangKyHoc> dangKyHocs) {
        this.dangKyHocs = dangKyHocs;
    }
}
