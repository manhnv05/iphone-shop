<template>
  <header class="bg-white shadow-md sticky top-0 z-50">
    <!-- Top Bar -->
    <div class="bg-gradient-to-r from-blue-600 to-blue-700 text-white">
      <div class="container mx-auto px-4 py-2">
        <div class="flex justify-between items-center text-sm">
          <div class="flex items-center space-x-4">
            <a href="tel:1900xxxx" class="hover:text-blue-200 transition-colors flex items-center">
              <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
              </svg>
              Hotline: 1900 xxxx
            </a>
            <span class="hidden md:inline">|</span>
            <a href="#" class="hidden md:inline hover:text-blue-200 transition-colors flex items-center">
              <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
              </svg>
              Hệ thống cửa hàng
            </a>
          </div>
          <div class="flex items-center space-x-4">
            <a href="#" class="hidden md:inline hover:text-blue-200 transition-colors">Khuyến mãi</a>
            <span class="hidden md:inline">|</span>
            <a href="#" class="hidden md:inline hover:text-blue-200 transition-colors">Tin tức</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Main Header -->
    <div class="container mx-auto px-4 py-4">
      <div class="flex items-center justify-between">
        <!-- Logo -->
        <router-link to="/home" class="flex items-center space-x-2 group">
          <div class="bg-gradient-to-br from-blue-600 to-blue-700 p-2 rounded-xl group-hover:scale-105 transition-transform">
            <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z" />
            </svg>
          </div>
          <span class="text-2xl font-bold bg-gradient-to-r from-blue-600 to-blue-800 bg-clip-text text-transparent">
            TopZone
          </span>
        </router-link>

        <!-- Navigation Menu (Desktop) - Moved to center -->
        <nav class="hidden lg:flex items-center space-x-1">
          <router-link
            to="/outlet-sale"
            class="flex items-center gap-1.5 px-4 py-2 rounded-lg text-sm font-semibold transition-all hover:bg-red-50"
            :class="isActiveRoute('/outlet-sale') ? 'text-red-600' : 'text-gray-700'"
          >
            <span class="uppercase">OUTLET</span>
            <span class="bg-red-600 text-white text-xs font-bold px-2 py-0.5 rounded uppercase">SALE</span>
          </router-link>

          <router-link
            v-for="category in categories"
            :key="category.id"
            :to="category.path"
            class="px-4 py-2 rounded-lg text-sm font-semibold transition-all hover:bg-blue-50 uppercase"
            :class="isActiveRoute(category.path) ? 'text-blue-600' : 'text-gray-700'"
          >
            {{ category.name }}
          </router-link>
        </nav>

        <!-- Right Actions -->
        <div class="flex items-center space-x-4">
          <!-- Search Icon -->
          <button @click="toggleSearch" class="p-2 hover:bg-blue-50 rounded-lg transition-colors group">
            <svg class="w-6 h-6 text-gray-600 group-hover:text-blue-600 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>
          </button>

          <!-- User Menu -->
          <div class="relative" v-if="isLoggedIn">
            <button @click="toggleUserMenu" class="flex items-center space-x-2 hover:text-blue-600 transition-colors p-2 rounded-lg hover:bg-blue-50">
              <div class="w-9 h-9 bg-gradient-to-br from-blue-500 to-blue-600 rounded-full flex items-center justify-center text-white font-semibold">
                {{ userInitials }}
              </div>
              <span class="hidden md:inline text-sm font-medium text-gray-700">{{ userName }}</span>
              <svg class="w-4 h-4 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </button>

            <!-- User Dropdown -->
            <Transition name="dropdown">
              <div v-if="showUserMenu" class="absolute right-0 mt-2 w-56 bg-white rounded-xl shadow-2xl border border-gray-200 py-2 z-50">
                <div class="px-4 py-3 border-b border-gray-100">
                  <p class="text-sm font-semibold text-gray-800">{{ userName }}</p>
                  <p class="text-xs text-gray-500">{{ userEmail }}</p>
                </div>
                <router-link to="/profile" class="flex items-center gap-3 px-4 py-2 text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition-colors">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                  </svg>
                  <span class="text-sm">Thông tin tài khoản</span>
                </router-link>
                <router-link to="/orders" class="flex items-center gap-3 px-4 py-2 text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition-colors">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z" />
                  </svg>
                  <span class="text-sm">Đơn hàng của tôi</span>
                </router-link>
                <router-link to="/wishlist" class="flex items-center gap-3 px-4 py-2 text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition-colors">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                  </svg>
                  <span class="text-sm">Sản phẩm yêu thích</span>
                </router-link>
                <hr class="my-2" />
                <button @click="logout" class="flex items-center gap-3 w-full px-4 py-2 text-red-600 hover:bg-red-50 transition-colors">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                  </svg>
                  <span class="text-sm">Đăng xuất</span>
                </button>
              </div>
            </Transition>
          </div>

          <!-- Login Button -->
          <router-link v-else to="/login" class="hidden md:flex items-center space-x-2 px-4 py-2 bg-gradient-to-r from-blue-600 to-blue-700 text-white rounded-lg hover:shadow-lg transition-all">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1" />
            </svg>
            <span class="font-medium">Đăng nhập</span>
          </router-link>

          <!-- Wishlist -->
          <router-link to="/wishlist" class="relative p-2 hover:bg-blue-50 rounded-lg transition-colors group">
            <svg class="w-6 h-6 text-gray-600 group-hover:text-red-500 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
            <span v-if="wishlistCount > 0" class="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center font-semibold">
              {{ wishlistCount }}
            </span>
          </router-link>

          <!-- Cart -->
          <router-link to="/cart" class="relative p-2 hover:bg-blue-50 rounded-lg transition-colors group">
            <svg class="w-6 h-6 text-gray-600 group-hover:text-blue-600 transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
            </svg>
            <span v-if="cartCount > 0" class="absolute -top-1 -right-1 bg-blue-600 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center font-semibold animate-bounce">
              {{ cartCount }}
            </span>
          </router-link>

          <!-- Mobile Menu Toggle -->
          <button @click="toggleMobileMenu" class="lg:hidden p-2 hover:bg-blue-50 rounded-lg transition-colors">
            <svg v-if="!showMobileMenu" class="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
            </svg>
            <svg v-else class="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- Search Overlay -->
    <Transition name="fade">
      <div v-if="showSearchOverlay" class="bg-white border-t border-gray-200">
        <div class="container mx-auto px-4 py-4">
          <div class="relative max-w-3xl mx-auto">
            <input
              v-model="searchQuery"
              @input="handleSearch"
              @focus="showSuggestions = true"
              @blur="hideSuggestions"
              type="text"
              placeholder="Tìm kiếm iPhone, iPad, Mac, Phụ kiện..."
              class="w-full pl-12 pr-4 py-3 border-2 border-gray-200 rounded-full focus:outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-200 transition-all"
              ref="searchInput"
            />
            <svg class="w-6 h-6 absolute left-4 top-3.5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>

            <!-- Search Suggestions Dropdown -->
            <Transition name="dropdown">
              <div v-if="showSuggestions && searchQuery.length >= 2" class="absolute top-full mt-2 w-full bg-white rounded-xl shadow-2xl border border-gray-200 max-h-96 overflow-y-auto z-50">
                <div class="p-2">
                  <!-- Popular Searches -->
                  <div v-if="!searchQuery" class="p-3">
                    <h4 class="text-xs font-semibold text-gray-500 uppercase mb-2">Tìm kiếm phổ biến</h4>
                    <div class="flex flex-wrap gap-2">
                      <button
                        v-for="term in popularSearches"
                        :key="term"
                        @mousedown.prevent="searchQuery = term"
                        class="px-3 py-1 bg-gray-100 hover:bg-gray-200 rounded-full text-sm text-gray-700 transition-colors"
                      >
                        {{ term }}
                      </button>
                    </div>
                  </div>

                  <!-- Search Results -->
                  <div v-else>
                    <div
                      v-for="product in searchResults"
                      :key="product.id"
                      @mousedown.prevent="goToProduct(product.id)"
                      class="flex items-center gap-3 p-3 hover:bg-blue-50 rounded-lg cursor-pointer transition-colors"
                    >
                      <img :src="product.duongDanAnh" :alt="product.tenSanPham" class="w-12 h-12 object-cover rounded-lg" />
                      <div class="flex-1">
                        <p class="text-sm font-medium text-gray-800 line-clamp-1">{{ product.tenSanPham }}</p>
                        <p class="text-sm text-red-600 font-semibold">{{ formatPrice(product.giaBan) }}</p>
                      </div>
                    </div>
                    <div v-if="searchResults.length === 0" class="p-6 text-center text-gray-500">
                      <svg class="w-16 h-16 mx-auto mb-2 text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                      </svg>
                      <p>Không tìm thấy sản phẩm</p>
                    </div>
                  </div>
                </div>
              </div>
            </Transition>
          </div>
        </div>
      </div>
    </Transition>

    <!-- Mobile Menu -->
    <Transition name="slide">
      <div v-if="showMobileMenu" class="lg:hidden bg-white border-t border-gray-200">
        <div class="container mx-auto px-4 py-4">
          <!-- Mobile Search -->
          <div class="mb-4">
            <div class="relative">
              <input
                v-model="searchQuery"
                type="text"
                placeholder="Tìm kiếm sản phẩm..."
                class="w-full pl-10 pr-4 py-2 border-2 border-gray-200 rounded-full focus:outline-none focus:border-blue-500 transition-colors"
              />
              <svg class="w-5 h-5 absolute left-3 top-2.5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
              </svg>
            </div>
          </div>

          <nav class="space-y-2">
            <router-link
              to="/outlet-sale"
              @click="showMobileMenu = false"
              class="flex items-center gap-3 px-4 py-3 rounded-lg text-gray-700 hover:bg-red-50 hover:text-red-600 transition-colors"
            >
              <span class="font-semibold uppercase">OUTLET</span>
              <span class="bg-red-600 text-white text-xs font-bold px-2 py-0.5 rounded uppercase">SALE</span>
            </router-link>

            <router-link
              v-for="category in categories"
              :key="category.id"
              :to="category.path"
              @click="showMobileMenu = false"
              class="flex items-center gap-3 px-4 py-3 rounded-lg text-gray-700 hover:bg-blue-50 hover:text-blue-600 transition-colors"
            >
              <span class="font-medium uppercase">{{ category.name }}</span>
            </router-link>
          </nav>

          <div v-if="!isLoggedIn" class="mt-4 pt-4 border-t border-gray-200">
            <router-link to="/login" @click="showMobileMenu = false" class="block w-full bg-gradient-to-r from-blue-600 to-blue-700 text-white text-center py-3 rounded-lg font-medium">
              Đăng nhập
            </router-link>
          </div>
        </div>
      </div>
    </Transition>
  </header>
</template>

<script>
import { ref, computed, nextTick, inject } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';

export default {
  name: 'ClientHeader',
  setup() {
    const router = useRouter();
    const route = useRoute();
    const appLogout = inject('logout');

    // State
    const searchQuery = ref('');
    const showSuggestions = ref(false);
    const showUserMenu = ref(false);
    const showMobileMenu = ref(false);
    const showSearchOverlay = ref(false);
    const searchResults = ref([]);
    const searchInput = ref(null);

    // User data (mock - should come from Vuex)
    const isLoggedIn = ref(true);
    const userName = ref('Nguyễn Văn Mạnh');
    const userEmail = ref('manhnv05@example.com');
    const cartCount = ref(3);
    const wishlistCount = ref(5);

    const categories = ref([
      { id: 'trang-chu', name: 'Trang chủ', path: '/home' },
      { id: 'cua-hang', name: 'Cửa Hàng', path: '/shop' },
      { id: 'gioi-thieu', name: 'Giới Thiệu', path: '/about' },
      { id: 'bai-viet', name: 'Bài viết', path: '/blog' },
      { id: 'lien-he', name: 'Liên hệ', path: '/contact' },
    ]);

    const popularSearches = ref([
      'iPhone 15 Pro Max',
      'MacBook Air M2',
      'AirPods Pro',
      'iPad Pro',
      'Apple Watch Series 9',
    ]);

    // Computed
    const userInitials = computed(() => {
      const names = userName.value.split(' ');
      return names.length >= 2
        ? `${names[0][0]}${names[names.length - 1][0]}`.toUpperCase()
        : names[0][0].toUpperCase();
    });

    // Methods
    const toggleSearch = async () => {
      showSearchOverlay.value = !showSearchOverlay.value;
      if (showSearchOverlay.value) {
        await nextTick();
        searchInput.value?.focus();
      }
    };

    const handleSearch = async () => {
      if (searchQuery.value.length >= 2) {
        try {
          // Mock search - replace with actual API call
          const response = await fetch(`/api/chi-tiet-san-pham/search?keyword=${searchQuery.value}`);
          searchResults.value = await response.json();
        } catch (error) {
          console.error('Search error:', error);
          searchResults.value = [];
        }
      } else {
        searchResults.value = [];
      }
    };

    const hideSuggestions = () => {
      setTimeout(() => {
        showSuggestions.value = false;
      }, 200);
    };

    const goToProduct = (id) => {
      router.push(`/product/${id}`);
      showSuggestions.value = false;
      showSearchOverlay.value = false;
      searchQuery.value = '';
    };

    const toggleUserMenu = () => {
      showUserMenu.value = !showUserMenu.value;
    };

    const toggleMobileMenu = () => {
      showMobileMenu.value = !showMobileMenu.value;
    };

    const logout = async () => {
      try {
        await axios.post('http://localhost:8080/tai-khoan/logout', null, { withCredentials: true });
      } catch (e) {
        console.warn('Logout request failed, proceed to clear local state');
      } finally {
        if (appLogout) appLogout();
        isLoggedIn.value = false;
        showUserMenu.value = false;
        router.replace('/login');
      }
    };

    const isActiveRoute = (path) => {
      return route.path === path || route.path.startsWith(path + '/');
    };

    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    return {
      searchQuery,
      showSuggestions,
      showUserMenu,
      showMobileMenu,
      showSearchOverlay,
      searchResults,
      searchInput,
      isLoggedIn,
      userName,
      userEmail,
      userInitials,
      cartCount,
      wishlistCount,
      categories,
      popularSearches,
      toggleSearch,
      handleSearch,
      hideSuggestions,
      goToProduct,
      toggleUserMenu,
      toggleMobileMenu,
      logout,
      isActiveRoute,
      formatPrice,
    };
  },
};
</script>

<style scoped>
.dropdown-enter-active,
.dropdown-leave-active {
  transition: all 0.2s ease;
}

.dropdown-enter-from,
.dropdown-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}

.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s ease;
}

.slide-enter-from,
.slide-leave-to {
  opacity: 0;
  transform: translateY(-100%);
}

.fade-enter-active,
.fade-leave-active {
  transition: all 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.line-clamp-1 {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* Custom scrollbar for dropdown */
::-webkit-scrollbar {
  width: 6px;
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background: #cbd5e0;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb:hover {
  background: #a0aec0;
}
</style>