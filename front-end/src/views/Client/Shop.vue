<template>
  <div class="bg-gray-50 min-h-screen">
    <ClientHeader />

    <!-- Breadcrumb -->
    <div class="bg-white border-b border-gray-200">
      <div class="container mx-auto px-4 py-4">
        <div class="flex items-center text-sm text-gray-600">
          <router-link to="/home" class="hover:text-blue-600 transition-colors">
            <svg class="w-4 h-4 inline mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
            </svg>
            Trang chủ
          </router-link>
          <svg class="w-4 h-4 mx-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
          </svg>
          <span class="text-gray-800 font-medium">{{ categoryName }}</span>
        </div>
      </div>
    </div>

    <!-- Category Banner -->
    <div v-if="categoryBanner" class="bg-gradient-to-r from-blue-600 to-blue-800 text-white">
      <div class="container mx-auto px-4 py-12">
        <div class="flex items-center justify-between">
          <div class="max-w-2xl">
            <h1 class="text-4xl font-bold mb-4">{{ categoryName }}</h1>
            <p class="text-blue-100 text-lg">{{ categoryDescription }}</p>
          </div>
          <div class="hidden lg:block">
            <img :src="categoryBanner" :alt="categoryName" class="h-48 object-contain" />
          </div>
        </div>
      </div>
    </div>

    <div class="container mx-auto px-4 py-8">
      <div class="flex flex-col lg:flex-row gap-6">
        <!-- Sidebar Filters -->
        <aside class="lg:w-72 flex-shrink-0">
          <!-- Filter Toggle (Mobile) -->
          <button
            @click="showFilters = !showFilters"
            class="lg:hidden w-full flex items-center justify-between bg-white p-4 rounded-lg shadow-sm mb-4"
          >
            <span class="font-semibold text-gray-800">Bộ lọc</span>
            <svg class="w-5 h-5" :class="{ 'rotate-180': showFilters }" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
            </svg>
          </button>

          <!-- Filters Container -->
          <div :class="['lg:block', showFilters ? 'block' : 'hidden']" class="space-y-4">
            <!-- Price Range Filter -->
            <div class="bg-white rounded-lg shadow-sm p-5 border border-gray-200">
              <div class="flex items-center justify-between mb-4">
                <h3 class="font-bold text-gray-800">Khoảng giá</h3>
                <button v-if="filters.priceRange" @click="filters.priceRange = null" class="text-xs text-blue-600 hover:text-blue-700">
                  Xóa
                </button>
              </div>
              <div class="space-y-2">
                <label
                  v-for="range in priceRanges"
                  :key="range.id"
                  class="flex items-center cursor-pointer group"
                >
                  <input
                    type="radio"
                    :value="range.id"
                    v-model="filters.priceRange"
                    class="w-4 h-4 text-blue-600 border-gray-300 focus:ring-blue-500"
                  />
                  <span class="ml-3 text-sm text-gray-700 group-hover:text-blue-600 transition-colors">
                    {{ range.label }}
                  </span>
                </label>
              </div>
            </div>

            <!-- Color Filter -->
            <div class="bg-white rounded-lg shadow-sm p-5 border border-gray-200">
              <div class="flex items-center justify-between mb-4">
                <h3 class="font-bold text-gray-800">Màu sắc</h3>
                <button v-if="filters.colors.length > 0" @click="filters.colors = []" class="text-xs text-blue-600 hover:text-blue-700">
                  Xóa
                </button>
              </div>
              <div class="grid grid-cols-4 gap-3">
                <button
                  v-for="color in colors"
                  :key="color.id"
                  @click="toggleFilter('colors', color.id)"
                  :title="color.name"
                  :class="[
                    'w-12 h-12 rounded-full border-2 transition-all transform hover:scale-110',
                    filters.colors.includes(color.id)
                      ? 'border-blue-600 ring-2 ring-blue-200 scale-110'
                      : 'border-gray-300 hover:border-blue-400'
                  ]"
                  :style="{ backgroundColor: color.hex }"
                >
                  <svg v-if="filters.colors.includes(color.id)" class="w-6 h-6 mx-auto text-white drop-shadow-lg" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="3" d="M5 13l4 4L19 7" />
                  </svg>
                </button>
              </div>
              <div class="mt-3 flex flex-wrap gap-2">
                <span
                  v-for="colorId in filters.colors"
                  :key="colorId"
                  class="text-xs bg-blue-100 text-blue-700 px-2 py-1 rounded-full"
                >
                  {{ colors.find(c => c.id === colorId)?.name }}
                </span>
              </div>
            </div>

            <!-- Storage Filter -->
            <div class="bg-white rounded-lg shadow-sm p-5 border border-gray-200">
              <div class="flex items-center justify-between mb-4">
                <h3 class="font-bold text-gray-800">Bộ nhớ</h3>
                <button v-if="filters.storages.length > 0" @click="filters.storages = []" class="text-xs text-blue-600 hover:text-blue-700">
                  Xóa
                </button>
              </div>
              <div class="grid grid-cols-2 gap-2">
                <button
                  v-for="storage in storages"
                  :key="storage.id"
                  @click="toggleFilter('storages', storage.id)"
                  :class="[
                    'px-3 py-2 rounded-lg text-sm font-medium transition-all',
                    filters.storages.includes(storage.id)
                      ? 'bg-blue-600 text-white shadow-lg scale-105'
                      : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
                  ]"
                >
                  {{ storage.label }}
                </button>
              </div>
            </div>

            <!-- RAM Filter -->
            <div class="bg-white rounded-lg shadow-sm p-5 border border-gray-200">
              <div class="flex items-center justify-between mb-4">
                <h3 class="font-bold text-gray-800">RAM</h3>
                <button v-if="filters.rams.length > 0" @click="filters.rams = []" class="text-xs text-blue-600 hover:text-blue-700">
                  Xóa
                </button>
              </div>
              <div class="space-y-2">
                <label
                  v-for="ram in rams"
                  :key="ram.id"
                  class="flex items-center cursor-pointer group"
                >
                  <input
                    type="checkbox"
                    :value="ram.id"
                    v-model="filters.rams"
                    class="w-4 h-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                  />
                  <span class="ml-3 text-sm text-gray-700 group-hover:text-blue-600 transition-colors">
                    {{ ram.label }}
                  </span>
                </label>
              </div>
            </div>

            <!-- Brand Filter -->
            <div class="bg-white rounded-lg shadow-sm p-5 border border-gray-200">
              <div class="flex items-center justify-between mb-4">
                <h3 class="font-bold text-gray-800">Thương hiệu</h3>
                <button v-if="filters.brands.length > 0" @click="filters.brands = []" class="text-xs text-blue-600 hover:text-blue-700">
                  Xóa
                </button>
              </div>
              <div class="space-y-2">
                <label
                  v-for="brand in brands"
                  :key="brand.id"
                  class="flex items-center cursor-pointer group"
                >
                  <input
                    type="checkbox"
                    :value="brand.id"
                    v-model="filters.brands"
                    class="w-4 h-4 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
                  />
                  <span class="ml-3 text-sm text-gray-700 group-hover:text-blue-600 transition-colors">
                    {{ brand.name }}
                  </span>
                </label>
              </div>
            </div>

            <!-- Clear All Filters -->
            <button
              @click="clearAllFilters"
              class="w-full bg-gradient-to-r from-red-500 to-red-600 hover:from-red-600 hover:to-red-700 text-white font-semibold py-3 px-4 rounded-lg transition-all shadow-md hover:shadow-lg"
            >
              Xóa tất cả bộ lọc
            </button>

            <!-- Filter Summary -->
            <div v-if="activeFiltersCount > 0" class="bg-blue-50 border border-blue-200 rounded-lg p-4">
              <p class="text-sm text-blue-800">
                <strong>{{ activeFiltersCount }}</strong> bộ lọc đang áp dụng
              </p>
            </div>
          </div>
        </aside>

        <!-- Products Grid -->
        <main class="flex-1">
          <!-- Toolbar -->
          <div class="bg-white rounded-lg shadow-sm p-4 mb-6 border border-gray-200">
            <div class="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
              <div>
                <h2 class="text-2xl font-bold text-gray-800">
                  {{ categoryName }}
                </h2>
                <p class="text-sm text-gray-600 mt-1">
                  Tìm thấy <strong class="text-blue-600">{{ filteredProducts.length }}</strong> sản phẩm
                </p>
              </div>

              <div class="flex items-center gap-3 w-full sm:w-auto">
                <!-- View Toggle -->
                <div class="flex bg-gray-100 rounded-lg p-1">
                  <button
                    @click="viewMode = 'grid'"
                    :class="[
                      'p-2 rounded-md transition-all',
                      viewMode === 'grid' ? 'bg-white text-blue-600 shadow-sm' : 'text-gray-600 hover:text-gray-800'
                    ]"
                    title="Grid View"
                  >
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z" />
                    </svg>
                  </button>
                  <button
                    @click="viewMode = 'list'"
                    :class="[
                      'p-2 rounded-md transition-all',
                      viewMode === 'list' ? 'bg-white text-blue-600 shadow-sm' : 'text-gray-600 hover:text-gray-800'
                    ]"
                    title="List View"
                  >
                    <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                    </svg>
                  </button>
                </div>

                <!-- Sort Dropdown -->
                <select
                  v-model="sortBy"
                  class="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent bg-white text-sm font-medium text-gray-700"
                >
                  <option value="default">Mặc định</option>
                  <option value="price-asc">Giá thấp → cao</option>
                  <option value="price-desc">Giá cao → thấp</option>
                  <option value="newest">Mới nhất</option>
                  <option value="popular">Phổ biến nhất</option>
                  <option value="discount">Giảm giá nhiều</option>
                </select>
              </div>
            </div>

            <!-- Active Filters Tags -->
            <div v-if="activeFiltersCount > 0" class="mt-4 flex flex-wrap gap-2">
              <span class="text-sm text-gray-600 font-medium">Bộ lọc:</span>

              <span v-if="filters.priceRange" class="inline-flex items-center gap-1 bg-blue-100 text-blue-700 px-3 py-1 rounded-full text-sm">
                {{ priceRanges.find(r => r.id === filters.priceRange)?.label }}
                <button @click="filters.priceRange = null" class="hover:bg-blue-200 rounded-full p-0.5">
                  <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              </span>

              <span
                v-for="colorId in filters.colors"
                :key="`color-${colorId}`"
                class="inline-flex items-center gap-1 bg-purple-100 text-purple-700 px-3 py-1 rounded-full text-sm"
              >
                {{ colors.find(c => c.id === colorId)?.name }}
                <button @click="toggleFilter('colors', colorId)" class="hover:bg-purple-200 rounded-full p-0.5">
                  <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              </span>

              <span
                v-for="storageId in filters.storages"
                :key="`storage-${storageId}`"
                class="inline-flex items-center gap-1 bg-green-100 text-green-700 px-3 py-1 rounded-full text-sm"
              >
                {{ storages.find(s => s.id === storageId)?.label }}
                <button @click="toggleFilter('storages', storageId)" class="hover:bg-green-200 rounded-full p-0.5">
                  <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              </span>
            </div>
          </div>

          <!-- Loading State -->
          <div v-if="loading" class="grid gap-6" :class="viewMode === 'grid' ? 'grid-cols-2 md:grid-cols-3 lg:grid-cols-4' : 'grid-cols-1'">
            <div v-for="n in 12" :key="n" class="bg-white rounded-lg shadow-sm overflow-hidden animate-pulse">
              <div class="w-full bg-gray-300" :class="viewMode === 'grid' ? 'h-48' : 'h-32'"></div>
              <div class="p-4">
                <div class="h-4 bg-gray-300 rounded mb-2"></div>
                <div class="h-4 bg-gray-300 rounded w-2/3"></div>
              </div>
            </div>
          </div>

          <!-- Grid View -->
          <div v-else-if="viewMode === 'grid' && paginatedProducts.length > 0" class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
            <ProductCard
              v-for="product in paginatedProducts"
              :key="product.id"
              :product="product"
              @add-to-cart="addToCart"
              @toggle-wishlist="toggleWishlist"
            />
          </div>

          <!-- List View -->
          <div v-else-if="viewMode === 'list' && paginatedProducts.length > 0" class="space-y-4">
            <ProductListItem
              v-for="product in paginatedProducts"
              :key="product.id"
              :product="product"
              @add-to-cart="addToCart"
              @toggle-wishlist="toggleWishlist"
            />
          </div>

          <!-- No Results -->
          <div v-else class="bg-white rounded-lg shadow-sm p-12 text-center border border-gray-200">
            <svg class="w-24 h-24 mx-auto text-gray-300 mb-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <h3 class="text-2xl font-bold text-gray-800 mb-3">Không tìm thấy sản phẩm</h3>
            <p class="text-gray-600 mb-6">Vui lòng thử lại với bộ lọc khác hoặc tìm kiếm sản phẩm khác</p>
            <button @click="clearAllFilters" class="bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-lg font-semibold transition-colors">
              Xóa bộ lọc
            </button>
          </div>

          <!-- Pagination -->
          <div v-if="totalPages > 1" class="mt-8">
            <Pagination
              :current-page="currentPage"
              :total-pages="totalPages"
              @change="handlePageChange"
            />
          </div>
        </main>
      </div>
    </div>

    <ClientFooter />

    <!-- Toast Notification -->
    <Transition name="toast">
      <div
        v-if="toast.show"
        :class="[
          'fixed bottom-4 right-4 px-6 py-4 rounded-lg shadow-2xl text-white z-50 flex items-center gap-3',
          toast.type === 'success' ? 'bg-green-500' : 'bg-red-500'
        ]"
      >
        <svg v-if="toast.type === 'success'" class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
        <svg v-else class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
        </svg>
        <span>{{ toast.message }}</span>
      </div>
    </Transition>
  </div>
</template>

<script>
import { ref, computed, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import ClientHeader from '../components/ClientHeader.vue';
import ClientFooter from '../components/ClientFooter.vue';
import ProductCard from '../components/ProductCard.vue';
import ProductListItem from '../components/ProductListItem.vue';
import Pagination from '../components/Pagination.vue';

export default {
  name: 'Shop',
  components: {
    ClientHeader,
    ClientFooter,
    ProductCard,
    ProductListItem,
    Pagination,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();

    // State
    const products = ref([]);
    const loading = ref(false);
    const viewMode = ref('grid');
    const sortBy = ref('default');
    const currentPage = ref(1);
    const itemsPerPage = 16;
    const showFilters = ref(false);

    const filters = ref({
      priceRange: null,
      colors: [],
      storages: [],
      rams: [],
      brands: [],
    });

    const toast = ref({
      show: false,
      message: '',
      type: 'success',
    });

    // Filter Options
    const priceRanges = ref([
      { id: 'under-10', label: 'Dưới 10 triệu', min: 0, max: 10000000 },
      { id: '10-20', label: '10 - 20 triệu', min: 10000000, max: 20000000 },
      { id: '20-30', label: '20 - 30 triệu', min: 20000000, max: 30000000 },
      { id: '30-40', label: '30 - 40 triệu', min: 30000000, max: 40000000 },
      { id: 'above-40', label: 'Trên 40 triệu', min: 40000000, max: 999999999 },
    ]);

    const colors = ref([
      { id: 'black', name: 'Đen', hex: '#000000' },
      { id: 'white', name: 'Trắng', hex: '#FFFFFF' },
      { id: 'blue', name: 'Xanh Dương', hex: '#0071E3' },
      { id: 'purple', name: 'Tím', hex: '#9B59B6' },
      { id: 'red', name: 'Đỏ', hex: '#E74C3C' },
      { id: 'gold', name: 'Vàng', hex: '#FFD700' },
      { id: 'silver', name: 'Bạc', hex: '#C0C0C0' },
      { id: 'green', name: 'Xanh Lá', hex: '#34C759' },
    ]);

    const storages = ref([
      { id: '64', label: '64GB' },
      { id: '128', label: '128GB' },
      { id: '256', label: '256GB' },
      { id: '512', label: '512GB' },
      { id: '1024', label: '1TB' },
      { id: '2048', label: '2TB' },
    ]);

    const rams = ref([
      { id: '4', label: '4GB' },
      { id: '6', label: '6GB' },
      { id: '8', label: '8GB' },
      { id: '12', label: '12GB' },
      { id: '16', label: '16GB' },
      { id: '32', label: '32GB' },
    ]);

    const brands = ref([
      { id: 'apple', name: 'Apple' },
      { id: 'samsung', name: 'Samsung' },
      { id: 'xiaomi', name: 'Xiaomi' },
      { id: 'oppo', name: 'OPPO' },
      { id: 'vivo', name: 'Vivo' },
    ]);

    // Computed
    const categoryName = computed(() => {
      const category = route.query.category || 'all';
      const categoryMap = {
        all: 'Tất cả sản phẩm',
        iphone: 'iPhone',
        ipad: 'iPad',
        mac: 'Mac',
        watch: 'Apple Watch',
        audio: 'Âm thanh',
        accessories: 'Phụ kiện',
      };
      return categoryMap[category] || 'Sản phẩm';
    });

    const categoryDescription = computed(() => {
      const descriptions = {
        iphone: 'Khám phá dòng iPhone mới nhất với công nghệ tiên tiến và thiết kế sang trọng',
        ipad: 'iPad - Máy tính bảng mạnh mẽ cho công việc và giải trí',
        mac: 'MacBook & iMac - Hiệu năng vượt trội với chip Apple Silicon',
        watch: 'Apple Watch - Đồng hồ thông minh theo dõi sức khỏe và thể thao',
        audio: 'AirPods & Tai nghe chất lượng cao từ Apple',
        accessories: 'Phụ kiện chính hãng Apple và các thương hiệu uy tín',
      };
      return descriptions[route.query.category] || 'Sản phẩm chính hãng, giá tốt nhất';
    });

    const categoryBanner = computed(() => {
      return 'https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5?w=400&h=300&fit=crop';
    });

    const activeFiltersCount = computed(() => {
      let count = 0;
      if (filters.value.priceRange) count++;
      count += filters.value.colors.length;
      count += filters.value.storages.length;
      count += filters.value.rams.length;
      count += filters.value.brands.length;
      return count;
    });

    const filteredProducts = computed(() => {
      let result = products.value;

      // Category filter
      const category = route.query.category;
      if (category && category !== 'all') {
        result = result.filter(p =>
          p.tenSanPham.toLowerCase().includes(category)
        );
      }

      // Search filter
      const search = route.query.search;
      if (search) {
        result = result.filter(p =>
          p.tenSanPham.toLowerCase().includes(search.toLowerCase()) ||
          p.maSanPham.toLowerCase().includes(search.toLowerCase())
        );
      }

      // Price range filter
      if (filters.value.priceRange) {
        const range = priceRanges.value.find(r => r.id === filters.value.priceRange);
        if (range) {
          result = result.filter(p => {
            const price = p.giaSauKhiGiam || p.giaBan;
            return price >= range.min && price <= range.max;
          });
        }
      }

      // Color filter
      if (filters.value.colors.length > 0) {
        result = result.filter(p =>
          filters.value.colors.some(color =>
            p.mauSac && p.mauSac.toLowerCase().includes(color)
          )
        );
      }

      // Storage filter
      if (filters.value.storages.length > 0) {
        result = result.filter(p =>
          filters.value.storages.some(storage =>
            p.boNhoTrong && p.boNhoTrong.includes(storage)
          )
        );
      }

      // RAM filter
      if (filters.value.rams.length > 0) {
        result = result.filter(p =>
          filters.value.rams.some(ram =>
            p.ram && p.ram.includes(ram)
          )
        );
      }

      // Brand filter
      if (filters.value.brands.length > 0) {
        result = result.filter(p =>
          filters.value.brands.some(brand =>
            p.thuongHieu && p.thuongHieu.toLowerCase().includes(brand)
          )
        );
      }

      // Sorting
      if (sortBy.value === 'price-asc') {
        result.sort((a, b) => (a.giaSauKhiGiam || a.giaBan) - (b.giaSauKhiGiam || b.giaBan));
      } else if (sortBy.value === 'price-desc') {
        result.sort((a, b) => (b.giaSauKhiGiam || b.giaBan) - (a.giaSauKhiGiam || a.giaBan));
      } else if (sortBy.value === 'newest') {
        result.sort((a, b) => b.id - a.id);
      } else if (sortBy.value === 'discount') {
        result.sort((a, b) => (b.phanTramGiam || 0) - (a.phanTramGiam || 0));
      }

      return result;
    });

    const paginatedProducts = computed(() => {
      const start = (currentPage.value - 1) * itemsPerPage;
      const end = start + itemsPerPage;
      return filteredProducts.value.slice(start, end);
    });

    const totalPages = computed(() => {
      return Math.ceil(filteredProducts.value.length / itemsPerPage);
    });

    // Methods
    const fetchProducts = async () => {
      loading.value = true;
      try {
        const response = await axios.get('http://localhost:8080/api/chi-tiet-san-pham/ban-hang');
        products.value = response.data.map(product => ({
          ...product,
          isNew: Math.random() > 0.8,
        }));
      } catch (error) {
        console.error('Error fetching products:', error);
        showToast('Không thể tải sản phẩm', 'error');
      } finally {
        loading.value = false;
      }
    };

    const toggleFilter = (filterType, value) => {
      const index = filters.value[filterType].indexOf(value);
      if (index > -1) {
        filters.value[filterType].splice(index, 1);
      } else {
        filters.value[filterType].push(value);
      }
      currentPage.value = 1;
    };

    const clearAllFilters = () => {
      filters.value = {
        priceRange: null,
        colors: [],
        storages: [],
        rams: [],
        brands: [],
      };
      currentPage.value = 1;
    };

    const addToCart = (product) => {
      showToast(`Đã thêm ${product.tenSanPham} vào giỏ hàng`, 'success');
    };

    const toggleWishlist = (product) => {
      showToast('Đã thêm vào yêu thích', 'success');
    };

    const handlePageChange = (page) => {
      currentPage.value = page;
      window.scrollTo({ top: 0, behavior: 'smooth' });
    };

    const showToast = (message, type = 'success') => {
      toast.value = { show: true, message, type };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    // Watch route changes
    watch(() => route.query, () => {
      currentPage.value = 1;
      fetchProducts();
    });

    onMounted(() => {
      fetchProducts();
    });

    return {
      products,
      loading,
      viewMode,
      sortBy,
      currentPage,
      showFilters,
      filters,
      priceRanges,
      colors,
      storages,
      rams,
      brands,
      toast,
      categoryName,
      categoryDescription,
      categoryBanner,
      activeFiltersCount,
      filteredProducts,
      paginatedProducts,
      totalPages,
      toggleFilter,
      clearAllFilters,
      addToCart,
      toggleWishlist,
      handlePageChange,
    };
  },
};
</script>

<style scoped>
.toast-enter-active,
.toast-leave-active {
  transition: all 0.3s ease;
}

.toast-enter-from,
.toast-leave-to {
  opacity: 0;
  transform: translateX(100px);
}

.rotate-180 {
  transform: rotate(180deg);
}

@media (max-width: 1024px) {
  .lg\:w-72 {
    width: 100%;
  }
}
</style>