package com.tyugen.WebsiteEduAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer baiVietID;

    @Column
    @Size(max = 50, message = "Tên bài viết không được vượt quá 50 ký tự")
    private String tenBaiViet;

    @Column
    @Size(max = 50, message = "Tên tác giả không được vượt quá 50 ký tự")
    private String tenTacGia;

    @Column
    private String noiDung;

    @Column
    @Size(max = 1000, message = "Nội dung ngắn không được vượt quá 1000 ký tự")
    private String noiDungNgan;

    @Column
    private Date thoiGianTao;

    @Column
    private String hinhAnh;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chuDeID", foreignKey = @ForeignKey(name = "fk_baiviet_chude"))
    private ChuDe chuDes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiKhoanID", foreignKey = @ForeignKey(name = "fk_baiviet_taikhoan"))
    private TaiKhoan taiKhoan;

    public Integer getBaiVietID() {
        return baiVietID;
    }

    public void setBaiVietID(Integer baiVietID) {
        this.baiVietID = baiVietID;
    }

    public String getTenBaiViet() {
        return tenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        this.tenBaiViet = tenBaiViet;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDungNgan() {
        return noiDungNgan;
    }

    public void setNoiDungNgan(String noiDungNgan) {
        this.noiDungNgan = noiDungNgan;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public ChuDe getChuDes() {
        return chuDes;
    }

    public void setChuDes(ChuDe chuDes) {
        this.chuDes = chuDes;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
