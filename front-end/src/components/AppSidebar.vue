<template>
  <div class="flex">
    <!-- Backdrop -->
    <div
      :class="isOpen ? 'block' : 'hidden'"
      @click="isOpen = false"
      class="fixed inset-0 z-20 transition-opacity bg-black opacity-50 lg:hidden"
    ></div>

    <div
      :class="isOpen ? 'translate-x-0 ease-out' : '-translate-x-full ease-in'"
      class="fixed inset-y-0 left-0 z-30 w-72 overflow-y-auto transition duration-300 transform bg-white lg:translate-x-0 lg:static lg:inset-0 shadow-xl border-r border-gray-200"
    >
      <!-- Logo Section -->
      <div class="relative py-6 px-6 border-b border-gray-200 bg-gradient-to-br from-blue-50 to-cyan-50">
        <router-link to="/dashboard" class="flex items-center space-x-3 group">
          <!-- Animated Logo -->
          <div class="relative">
            <div class="absolute inset-0 bg-blue-500 rounded-2xl blur-lg opacity-20 group-hover:opacity-30 transition-opacity"></div>
            <div class="relative bg-gradient-to-br from-blue-600 to-cyan-600 p-3 rounded-2xl shadow-lg group-hover:scale-110 transition-transform duration-300">
              <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z" />
              </svg>
            </div>
          </div>

          <!-- Logo Text -->
          <div>
            <h1 class="text-2xl font-black text-gray-800 tracking-tight">TopZone</h1>
<!--            <p class="text-xs text-gray-500 font-medium mt-0.5">Admin Dashboard</p>-->
          </div>
        </router-link>
      </div>

      <!-- Navigation -->
      <nav class="mt-6 px-4 pb-24">
        <!-- General Section -->
        <div class="mb-6">
          <p class="px-3 text-xs font-bold mb-3 text-gray-400 uppercase tracking-widest">Dashboard</p>

          <router-link
            class="flex items-center px-4 py-3.5 mt-2 duration-200 rounded-xl group relative overflow-hidden"
            :class="[$route.name === 'Dashboard' ? activeClass : inactiveClass]"
            to="/dashboard"
          >
            <div class="absolute inset-0 bg-gradient-to-r from-blue-50 to-cyan-50 opacity-0 group-hover:opacity-100 transition-opacity"></div>
            <svg class="w-5 h-5 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
            </svg>
            <span class="ml-3 font-semibold relative z-10">Thống kê</span>
          </router-link>
        </div>

        <!-- Management Section -->
        <div class="mb-6">
          <p class="px-3 text-xs font-bold mb-3 text-gray-400 uppercase tracking-widest">Quản lý</p>

          <!-- Bán hàng -->
          <router-link
            class="flex items-center px-4 py-3.5 mt-2 duration-200 rounded-xl group relative overflow-hidden"
            :class="[$route.name === 'Orders' ? activeClass : inactiveClass]"
            to="/ban-hang"
          >
            <div class="absolute inset-0 bg-gradient-to-r from-green-50 to-emerald-50 opacity-0 group-hover:opacity-100 transition-opacity"></div>
            <svg class="w-5 h-5 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
            </svg>
            <span class="ml-3 font-semibold relative z-10">Bán hàng tại quầy</span>
          </router-link>

          <!-- Hóa đơn -->
          <router-link
            class="flex items-center px-4 py-3.5 mt-2 duration-200 rounded-xl group relative overflow-hidden"
            :class="[$route.name === 'HoaDon' ? activeClass : inactiveClass]"
            to="/hoa-don"
          >
            <div class="absolute inset-0 bg-gradient-to-r from-purple-50 to-pink-50 opacity-0 group-hover:opacity-100 transition-opacity"></div>
            <svg class="w-5 h-5 relative z-10" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
            </svg>
            <span class="ml-3 font-semibold relative z-10">Hóa đơn</span>
          </router-link>

          <!-- Sản phẩm Dropdown -->
          <div class="mt-2">
            <button
              @click="toggleDropdown('dropdown3')"
              class="flex items-center justify-between w-full px-4 py-3.5 duration-200 rounded-xl group relative overflow-hidden"
              :class="[openDropdown === 'dropdown3' ? activeClass : inactiveClass]"
            >
              <div class="absolute inset-0 bg-gradient-to-r from-orange-50 to-red-50 opacity-0 group-hover:opacity-100 transition-opacity"></div>
              <div class="flex items-center relative z-10">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z" />
                </svg>
                <span class="ml-3 font-semibold">Sản Phẩm</span>
              </div>
              <svg
                class="w-4 h-4 relative z-10 transform transition-transform duration-300"
                :class="{ 'rotate-180': openDropdown === 'dropdown3' }"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
              </svg>
            </button>

            <Transition
              enter-active-class="transition-all duration-300 ease-out"
              leave-active-class="transition-all duration-200 ease-in"
              enter-from-class="transform -translate-y-2 opacity-0"
              enter-to-class="transform translate-y-0 opacity-100"
              leave-from-class="transform translate-y-0 opacity-100"
              leave-to-class="transform -translate-y-2 opacity-0"
            >
              <div v-if="openDropdown === 'dropdown3'" class="ml-6 mt-2 space-y-1 max-h-96 overflow-y-auto custom-scrollbar">
                <router-link
                  v-for="(item, index) in productCategories"
                  :key="index"
                  class="flex items-center px-4 py-2.5 text-sm duration-200 rounded-lg group relative"
                  :class="[$route.name === item.route ? subActiveClass : subInactiveClass]"
                  :to="item.path"
                >
                  <div class="w-1.5 h-1.5 rounded-full mr-3 group-hover:scale-150 transition-transform" :class="$route.name === item.route ? 'bg-blue-600' : 'bg-gray-400'"></div>
                  <span class="font-medium">{{ item.name }}</span>
                </router-link>
              </div>
            </Transition>
          </div>

          <!-- Tài khoản Dropdown -->
          <div class="mt-2">
            <button
              @click="toggleDropdown('dropdown4')"
              class="flex items-center justify-between w-full px-4 py-3.5 duration-200 rounded-xl group relative overflow-hidden"
              :class="[openDropdown === 'dropdown4' ? activeClass : inactiveClass]"
            >
              <div class="absolute inset-0 bg-gradient-to-r from-indigo-50 to-violet-50 opacity-0 group-hover:opacity-100 transition-opacity"></div>
              <div class="flex items-center relative z-10">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                </svg>
                <span class="ml-3 font-semibold">Tài khoản</span>
              </div>
              <svg
                class="w-4 h-4 relative z-10 transform transition-transform duration-300"
                :class="{ 'rotate-180': openDropdown === 'dropdown4' }"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
              </svg>
            </button>

            <Transition
              enter-active-class="transition-all duration-300 ease-out"
              leave-active-class="transition-all duration-200 ease-in"
              enter-from-class="transform -translate-y-2 opacity-0"
              enter-to-class="transform translate-y-0 opacity-100"
              leave-from-class="transform translate-y-0 opacity-100"
              leave-to-class="transform -translate-y-2 opacity-0"
            >
              <div v-if="openDropdown === 'dropdown4'" class="ml-6 mt-2 space-y-1">
                <router-link
                  class="flex items-center px-4 py-2.5 text-sm duration-200 rounded-lg group"
                  :class="[$route.name === 'NhanVien' ? subActiveClass : subInactiveClass]"
                  to="/nhan-vien"
                >
                  <svg class="w-4 h-4 mr-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                  </svg>
                  <span class="font-medium">Nhân viên</span>
                </router-link>
                <router-link
                  class="flex items-center px-4 py-2.5 text-sm duration-200 rounded-lg group"
                  :class="[$route.name === 'KhachHang' ? subActiveClass : subInactiveClass]"
                  to="/khach-hang"
                >
                  <svg class="w-4 h-4 mr-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                  </svg>
                  <span class="font-medium">Khách hàng</span>
                </router-link>
              </div>
            </Transition>
          </div>

          <!-- Khuyến mãi Dropdown -->
          <div class="mt-2">
            <button
              @click="toggleDropdown('dropdown5')"
              class="flex items-center justify-between w-full px-4 py-3.5 duration-200 rounded-xl group relative overflow-hidden"
              :class="[openDropdown === 'dropdown5' ? activeClass : inactiveClass]"
            >
              <div class="absolute inset-0 bg-gradient-to-r from-rose-50 to-pink-50 opacity-0 group-hover:opacity-100 transition-opacity"></div>
              <div class="flex items-center relative z-10">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z" />
                </svg>
                <span class="ml-3 font-semibold">Khuyến mãi</span>
              </div>
              <svg
                class="w-4 h-4 relative z-10 transform transition-transform duration-300"
                :class="{ 'rotate-180': openDropdown === 'dropdown5' }"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path fill-rule="evenodd" d="M5.293 7.293a1 1 0 011.414 0L10 10.586l3.293-3.293a1 1 0 111.414 1.414l-4 4a1 1 0 01-1.414 0l-4-4a1 1 0 010-1.414z" clip-rule="evenodd" />
              </svg>
            </button>

            <Transition
              enter-active-class="transition-all duration-300 ease-out"
              leave-active-class="transition-all duration-200 ease-in"
              enter-from-class="transform -translate-y-2 opacity-0"
              enter-to-class="transform translate-y-0 opacity-100"
              leave-from-class="transform translate-y-0 opacity-100"
              leave-to-class="transform -translate-y-2 opacity-0"
            >
              <div v-if="openDropdown === 'dropdown5'" class="ml-6 mt-2 space-y-1">
                <router-link
                  class="flex items-center px-4 py-2.5 text-sm duration-200 rounded-lg group"
                  :class="[$route.name === 'PhieuGiamGia' ? subActiveClass : subInactiveClass]"
                  to="/phieu-giam-gia"
                >
                  <svg class="w-4 h-4 mr-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 5v2m0 4v2m0 4v2M5 5a2 2 0 00-2 2v3a2 2 0 110 4v3a2 2 0 002 2h14a2 2 0 002-2v-3a2 2 0 110-4V7a2 2 0 00-2-2H5z" />
                  </svg>
                  <span class="font-medium">Phiếu giảm giá</span>
                </router-link>
                <router-link
                  class="flex items-center px-4 py-2.5 text-sm duration-200 rounded-lg group"
                  :class="[$route.name === 'DotGiamGia' ? subActiveClass : subInactiveClass]"
                  to="/dot-giam-gia"
                >
                  <svg class="w-4 h-4 mr-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                  </svg>
                  <span class="font-medium">Đợt giảm giá</span>
                </router-link>
              </div>
            </Transition>
          </div>
        </div>
      </nav>

      <!-- Footer User Info -->
      <div class="absolute bottom-0 left-0 right-0 p-4 bg-gray-50 border-t border-gray-200">
        <div class="flex items-center space-x-3 px-2">
          <div class="w-10 h-10 rounded-full bg-gradient-to-br from-blue-600 to-cyan-600 flex items-center justify-center text-white font-bold text-sm shadow-md">
            MN
          </div>
          <div class="flex-1 min-w-0">
            <p class="text-sm font-semibold text-gray-800 truncate">manhnv05</p>
            <p class="text-xs text-gray-500">Administrator</p>
          </div>
          <button class="p-2 hover:bg-gray-200 rounded-lg transition-colors" title="Đăng xuất">
            <svg class="w-5 h-5 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
            </svg>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useSidebar } from "../hooks/useSidebar";

const { isOpen } = useSidebar();
const openDropdown = ref<string | null>(null);

const activeClass = ref("bg-gradient-to-r from-blue-600 to-cyan-600 text-white shadow-lg shadow-blue-500/30");
const inactiveClass = ref("text-gray-700 hover:bg-gray-100 hover:text-gray-900");
const subActiveClass = ref("bg-blue-50 text-blue-700 font-semibold");
const subInactiveClass = ref("text-gray-600 hover:bg-gray-50 hover:text-gray-900");

function toggleDropdown(dropdownId: string) {
  if (openDropdown.value === dropdownId) {
    openDropdown.value = null;
  } else {
    openDropdown.value = dropdownId;
  }
}

const productCategories = [
  { name: "Sản Phẩm", path: "/products", route: "products" },
  { name: "Nhà Sản Xuất", path: "/manufacturer", route: "manufacturer" },
  { name: "Cụm Camera", path: "/camera-module", route: "cameraModule" },
  { name: "Công Nghệ Màn Hình", path: "/screens/technology", route: "screenTechnology" },
  { name: "Hỗ Trợ Công Nghệ Sạc", path: "/charging/tech-support", route: "chargingTechSupport" },
  { name: "RAM", path: "/ram", route: "ram" },
  { name: "Bộ Nhớ Trong", path: "/internal-storage", route: "internalStorage" },
  { name: "Bộ Nhớ Ngoài", path: "/external-storage", route: "externalStorage" },
  { name: "CPU", path: "/cpu", route: "cpu" },
  { name: "GPU", path: "/gpu", route: "gpu" },
  { name: "Công Nghệ Mạng", path: "/network-technology", route: "networkTechnology" },
  { name: "Hệ Điều Hành", path: "/operating-system", route: "operatingSystem" },
  { name: "Kháng Bụi - Kháng Nước", path: "/dust-water-resistance", route: "dustWaterResistance" },
  { name: "Màu Sắc", path: "/color", route: "color" },
  { name: "Pin", path: "/battery", route: "battery" },
  { name: "Sim", path: "/sim", route: "sim" },
  { name: "Thiết Kế", path: "/design", route: "design" },
];
</script>

<style scoped>
/* Custom Scrollbar */
.custom-scrollbar::-webkit-scrollbar {
  width: 4px;
}

.custom-scrollbar::-webkit-scrollbar-track {
  background: rgba(229, 231, 235, 0.5);
  border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb {
  background: rgba(59, 130, 246, 0.5);
  border-radius: 10px;
}

.custom-scrollbar::-webkit-scrollbar-thumb:hover {
  background: rgba(59, 130, 246, 0.8);
}
</style>