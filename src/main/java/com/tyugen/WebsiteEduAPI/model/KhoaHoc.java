package com.tyugen.WebsiteEduAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer khoaHocID;

    @Column
    @Size(max = 50, message = "Tên khóa học không được quá 50 ký tự")
    private String tenKhoaHoc;
    @Column
    private Integer thoiGianHoc;
    @Column
    private String gioiThieu;
    @Column
    private String noiDung;
    @Column
    private Double hocPhi;
    @Column
    private Integer soHocVien;
    @Column
    private Integer soLuongMon;
    @Column
    private String hinhAnh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaiKhoaHocID", foreignKey = @ForeignKey(name = "fk_khoahoc_loaikhoahoc"))
    private LoaiKhoaHoc loaiKhoaHoc;

    @OneToMany(mappedBy = "khoaHoc", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("khoaHoc")
    private Set<DangKyHoc> dangKyHocs;

    public Integer getKhoaHocID() {
        return khoaHocID;
    }

    public void setKhoaHocID(Integer khoaHocID) {
        this.khoaHocID = khoaHocID;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public Integer getThoiGianHoc() {
        return thoiGianHoc;
    }

    public void setThoiGianHoc(Integer thoiGianHoc) {
        this.thoiGianHoc = thoiGianHoc;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public Integer getSoHocVien() {
        return soHocVien;
    }

    public void setSoHocVien(Integer soHocVien) {
        this.soHocVien = soHocVien;
    }

    public Integer getSoLuongMon() {
        return soLuongMon;
    }

    public void setSoLuongMon(Integer soLuongMon) {
        this.soLuongMon = soLuongMon;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LoaiKhoaHoc getLoaiKhoaHoc() {
        return loaiKhoaHoc;
    }

    public void setLoaiKhoaHoc(LoaiKhoaHoc loaiKhoaHoc) {
        this.loaiKhoaHoc = loaiKhoaHoc;
    }

    public Set<DangKyHoc> getDangKyHocs() {
        return dangKyHocs;
    }

    public void setDangKyHocs(Set<DangKyHoc> dangKyHocs) {
        this.dangKyHocs = dangKyHocs;
    }

    @Override
    public String toString() {
        return "\n" + "KhoaHoc{" + "\n" +
                " khoaHocID= " + khoaHocID + '\n' +
                " tenKhoaHoc= " + tenKhoaHoc + '\n' +
                " thoiGianHoc= " + thoiGianHoc + '\n' +
                " gioiThieu= " + gioiThieu + '\n' +
                " noiDung= " + noiDung + '\n' +
                " hocPhi= " + hocPhi + '\n' +
                " soHocVien= " + soHocVien + '\n' +
                " soLuongMon= " + soLuongMon + '\n' +
                " hinhAnh= " + hinhAnh + '\n' +
                loaiKhoaHoc + '\n' +
                "}" + '\n';
    }
}
