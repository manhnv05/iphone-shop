import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

// Client Views
import HomePage from "../views/Client/HomePage.vue";
import CartPage from "../views/Client/CartPage.vue";
import CheckoutPage from "../views/Client/CheckoutPage.vue";
import ShopPage from "../views/Client/Shop.vue";
import ProductDetail from "../views/Client/ProductDetail.vue";
import About from "../views/Client/About.vue";
import Blog from "../views/Client/Blog.vue";
import Contact from "../views/Client/Contact.vue";
import OutletSale from "../views/Client/OutletSale.vue";

// Thống kê
import Dashboard from "../views/ThongKe/AppDashboard.vue";

// Giảm giá
import Voucher from "../views/Voucher/AppVoucher.vue";
import DotVoucher from "../views/Voucher/DotVoucher.vue";
import FormAddPgg from "../views/Voucher/components/FormAddPgg.vue";
import FormUpdatePgg from "@/views/Voucher/components/FormUpdatePgg.vue";

// View Login
import Login from "../views/LoginView/AppLogin.vue";
import DangKy from "@/views/LoginView/DangKy/DangKy.vue";
import QuenMk from "@/views/LoginView/QuenMk/QuenMk.vue";

// Đơn hàng
import LichSuHD from "../views/Bill/LichSuHoaDon.vue";
import HoaDon from "../views/Bill/HoaDon.vue";
import HoaDonChiTiet from "../views/Bill/HoaDonChiTiet.vue";
import ShowHoaDon from "../views/Bill/ShowHoaDon.vue";

// Tài khoản
import NhanVien from "../views/AccountType/NhanVien.vue";
import EmployeeForm from "@/views/AccountType/EmployeeForm.vue";
import KhachHang from "../views/AccountType/KhachHang.vue";
import CustmerForm from "@/views/AccountType/CustmerForm.vue";
import NotFound from "../views/NotFound.vue";
import UpdateNhanVien from "@/views/AccountType/UpdateNhanVien.vue";
import UpdateCustmer from "@/views/AccountType/UpdateCustmer.vue";
import UpdateMk from "@/views/LoginView/QuenMk/UpdateMk.vue";

// Products
import SanPham from "@/views/Products/SanPham/SanPham.vue";
import NhaSanXuat from "@/views/Products/Manufacturer/Manufacturer.vue";
import CumCamera from "@/views/Products/CameraSanPham/CumCamera.vue";
import CongNgheManHinh from "@/views/Products/Screen/CongNgheMH.vue";
import HoTroCongNgheSac from "@/views/Products/Sac/HoTroCongNgheSac.vue";
import Ram from "@/views/Products/MemorySanPham/RamSanPham.vue";
import BoNhoTrong from "@/views/Products/MemorySanPham/BoNhoTrong.vue";
import BoNhoNgoai from "@/views/Products/MemorySanPham/BoNhoNgoai.vue";
import Cpu from "@/views/Products/Chip/CPU.vue";
import Gpu from "@/views/Products/Chip/GPU.vue";
import CongNgheMang from "@/views/Products/Other/CongNgheMang.vue";
import HeDieuHanh from "@/views/Products/Other/HeDieuHanh.vue";
import KhangNuocBui from "@/views/Products/Other/KhangBuiNuoc.vue";
import MauSac from "@/views/Products/Other/MauSac.vue";
import Pin from "@/views/Products/Other/PinSanPham.vue";
import Sim from "@/views/Products/Other/SimSanPham.vue";
import ThietKe from "@/views/Products/Other/ThietKe.vue";

const routes: Array<RouteRecordRaw> = [
  // Client Routes
  {
    path: "/home",
    name: "HomePage",
    component: HomePage,
    meta: { layout: "empty" },
  },
  {
    path: "/cart",
    name: "CartPage",
    component: CartPage,
    meta: { layout: "empty" },
  },
  {
    path: "/checkout",
    name: "CheckoutPage",
    component: CheckoutPage,
    meta: { layout: "empty" },
  },
  {
    path: "/shop",
    name: "ShopPage",
    component: ShopPage,
    meta: { layout: "empty" },
  },
  {
    path: "/product/:id",
    name: "ProductDetail",
    component: ProductDetail,
    meta: { layout: "empty" },
  },
  {
    path: "/about",
    name: "AboutPage",
    component: About,
    meta: { layout: "empty" },
  },
  {
    path: "/blog",
    name: "BlogPage",
    component: Blog,
    meta: { layout: "empty" },
  },
  {
    path: "/contact",
    name: "ContactPage",
    component: Contact,
    meta: { layout: "empty" },
  },
  {
    path: "/outlet-sale",
    name: "OutletSalePage",
    component: OutletSale,
    meta: { layout: "empty" },
  },
  // Admin & Other Routes
  {
    path: "/",
    name: "Login",
    component: Login,
    meta: { layout: "empty" },
  },
  // ... (all other existing routes)
  {
    path: "/dang-ky",
    name: "DangKy",
    component: DangKy,
    meta: { layout: "empty", breadcrumb: [] },
  },
  {
    path: "/quen-mk",
    name: "Quenmk",
    component: QuenMk,
    meta: { layout: "empty", breadcrumb: [] },
  },
  {
    path: "/update-mk",
    name: "updatemk",
    component: UpdateMk,
    meta: { layout: "empty", breadcrumb: [] },
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: Dashboard,
    meta: { breadcrumb: ["Thống kê"] },
  },
  {
    path: "/ban-hang",
    name: "BanHangTaiQuay",
    component: () => import("@/views/BanHang/BanHang.vue"),
    meta: { breadcrumb: ["Bán Hàng Tại Quầy"] },
  },
  {
    path: "/phieu-giam-gia",
    name: "Voucher",
    component: Voucher,
    meta: { breadcrumb: ["Phiếu Giảm Giá", "Phiếu giảm giá"] },
  },
  {
    path: "/dot-giam-gia",
    name: "DotVoucher",
    component: DotVoucher,
    meta: { breadcrumb: ["Phiếu Giảm Giá", "Đợt giảm giá"] },
  },
  {
    path: "/add-phieu-giam-gia",
    name: "FormAddPgg",
    component: FormAddPgg,
    meta: { breadcrumb: ["Phiếu Giảm Giá", "Phiếu giảm giá", "Thêm phiếu giảm giá"] },
  },
  {
    path: "/update-phieu-giam-gia/:id",
    name: "FormUpdatePgg",
    component: FormUpdatePgg,
    props: true,
    meta: { breadcrumb: (route: any) => ["Phiếu Giảm Giá", "Phiếu giảm giá", `Cập nhật phiếu giảm giá #${route.params.id}`] },
  },
  {
    path: "/ViewAddDotGiamGia",
    name: "ViewAddDotGiamGia",
    component: () => import("@/views/Voucher/ViewAddDotGiamGia.vue"),
    meta: { breadcrumb: ["Phiếu Giảm Giá", "Đợt giảm giá", "Thêm đợt giảm giá"] },
  },
  {
    path: "/hoa-don",
    name: "HoaDon",
    component: HoaDon,
    meta: { breadcrumb: ["Hóa đơn"] },
  },
  {
    path: "/show-hoa-don/:id",
    name: "ShowHoaDon",
    component: ShowHoaDon,
    props: true,
    meta: { breadcrumb: (route: any) => ["Hóa đơn", `Chi tiết hóa đơn #${route.params.id}`] },
  },
  {
    path: "/lich-su-hoa-don",
    name: "LichSuHD",
    component: LichSuHD,
    meta: { breadcrumb: ["Lịch sử hóa đơn"] },
  },
  {
    path: "/hoa-don-chi-tiet",
    name: "HDCT",
    component: HoaDonChiTiet,
    meta: { breadcrumb: ["Hóa đơn", "Hóa đơn chi tiết"] },
  },
  {
    path: "/nhan-vien",
    name: "NhanVien",
    component: NhanVien,
    meta: { breadcrumb: ["Tài khoản", "Nhân viên"] },
  },
  {
    path: "/them-nhan-vien",
    name: "ThemNhanVien",
    component: EmployeeForm,
    meta: { breadcrumb: ["Tài khoản", "Nhân viên", "Thêm nhân viên"] },
  },
  {
    path: "/update-nhan-vien",
    name: "UpdateNhanVien",
    component: UpdateNhanVien,
    meta: { breadcrumb: ["Tài khoản", "Nhân viên", "Cập nhật nhân viên"] },
  },
  {
    path: "/khach-hang",
    name: "KhachHang",
    component: KhachHang,
    meta: { breadcrumb: ["Tài khoản", "Khách hàng"] },
  },
  {
    path: "/them-khach-hang",
    name: "ThemKhachHang",
    component: CustmerForm,
    meta: { breadcrumb: ["Tài khoản", "Khách hàng", "Thêm khách hàng"] },
  },
  {
    path: "/update-khach-hang",
    name: "UpdateKhachHang",
    component: UpdateCustmer,
    meta: { breadcrumb: ["Tài khoản", "Khách hàng", "Cập nhật khách hàng"] },
  },
  // Product Routes
  { path: "/products", name: "products", component: SanPham, meta: { breadcrumb: ["Sản phẩm", "Danh sách sản phẩm"] } },
  { path: "/manufacturer", name: "manufacturer", component: NhaSanXuat, meta: { breadcrumb: ["Sản phẩm", "Nhà sản xuất"] } },
  { path: "/camera-module", name: "cameraModule", component: CumCamera, meta: { breadcrumb: ["Sản phẩm", "Cụm Camera"] } },
  { path: "/screens/technology", name: "screenTechnology", component: CongNgheManHinh, meta: { breadcrumb: ["Sản phẩm", "Công nghệ màn hình"] } },
  { path: "/charging/tech-support", name: "chargingTechSupport", component: HoTroCongNgheSac, meta: { breadcrumb: ["Sản phẩm", "Hỗ trợ sạc"] } },
  { path: "/ram", name: "ram", component: Ram, meta: { breadcrumb: ["Sản phẩm", "RAM"] } },
  { path: "/internal-storage", name: "internalStorage", component: BoNhoTrong, meta: { breadcrumb: ["Sản phẩm", "Bộ nhớ trong"] } },
  { path: "/external-storage", name: "externalStorage", component: BoNhoNgoai, meta: { breadcrumb: ["Sản phẩm", "Bộ nhớ ngoài"] } },
  { path: "/cpu", name: "cpu", component: Cpu, meta: { breadcrumb: ["Sản phẩm", "CPU"] } },
  { path: "/gpu", name: "gpu", component: Gpu, meta: { breadcrumb: ["Sản phẩm", "GPU"] } },
  { path: "/network-technology", name: "networkTechnology", component: CongNgheMang, meta: { breadcrumb: ["Sản phẩm", "Công nghệ mạng"] } },
  { path: "/operating-system", name: "operatingSystem", component: HeDieuHanh, meta: { breadcrumb: ["Sản phẩm", "Hệ điều hành"] } },
  { path: "/dust-water-resistance", name: "dustWaterResistance", component: KhangNuocBui, meta: { breadcrumb: ["Sản phẩm", "Kháng bụi/nước"] } },
  { path: "/color", name: "color", component: MauSac, meta: { breadcrumb: ["Sản phẩm", "Màu sắc"] } },
  { path: "/battery", name: "battery", component: Pin, meta: { breadcrumb: ["Sản phẩm", "Pin"] } },
  { path: "/sim", name: "sim", component: Sim, meta: { breadcrumb: ["Sản phẩm", "SIM"] } },
  { path: "/design", name: "design", component: ThietKe, meta: { breadcrumb: ["Sản phẩm", "Thiết kế"] } },
  {
    path: "/:pathMatch(.*)*",
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;