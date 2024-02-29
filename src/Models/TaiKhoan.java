package Models;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private String hoTen;
	private String diaChi;
	private String soDT;
	public TaiKhoan() {
	}
	public TaiKhoan(String tenDangNhap, String matKhau, String hoTen, String diaChi, String soDT) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
		this.soDT = soDT;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", hoTen=" + hoTen+ ", diaChi=" + diaChi + ", soDT=" + soDT + "]";
	}
}
