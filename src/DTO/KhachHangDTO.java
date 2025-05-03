package DTO;

public class KhachHangDTO {
    private int maKH;
    private String tenKH;
    private String diaChi;

    public KhachHangDTO(int maKH, String tenKH, String diaChi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
    }

    public KhachHangDTO(String tenKH, String diaChi) {
        this.tenKH = tenKH;
        this.diaChi = diaChi;
    }

    public int getMaKH() { return maKH; }
    public String getTenKH() { return tenKH; }
    public String getDiaChi() { return diaChi; }

    public void setMaKH(int maKH) { this.maKH = maKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
}
