package DTO;
public class DonHangDTO {
    private String maDonHang;
    private String ngayMua;
    private String tenKhachHang;
    private double tongTien;

    public DonHangDTO(String maDonHang, String ngayMua, String tenKhachHang, double tongTien) {
        this.maDonHang = maDonHang;
        this.ngayMua = ngayMua;
        this.tenKhachHang = tenKhachHang;
        this.tongTien = tongTien;
    }

    public String getMaDonHang() { return maDonHang; }
    public String getNgayMua() { return ngayMua; }
    public String getTenKhachHang() { return tenKhachHang; }
    public double getTongTien() { return tongTien; }

    public void setMaDonHang(String maDonHang) { this.maDonHang = maDonHang; }
    public void setNgayMua(String ngayMua) { this.ngayMua = ngayMua; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}
