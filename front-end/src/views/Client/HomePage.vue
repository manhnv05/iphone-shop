<template>
  <div class="bg-gray-50 min-h-screen">
    <Header />

    <!-- Banner Slider -->
    <section class="container mx-auto px-4 py-6">
      <div class="relative overflow-hidden rounded-2xl shadow-lg">
        <div class="flex transition-transform duration-500" :style="{ transform: `translateX(-${currentSlide * 100}%)` }">
          <div v-for="(banner, index) in banners" :key="index" class="min-w-full">
            <img :src="banner.image" :alt="banner.title" class="w-full h-64 md:h-96 object-cover" />
          </div>
        </div>
        <button @click="prevSlide" class="absolute left-4 top-1/2 -translate-y-1/2 bg-white/80 hover:bg-white p-2 rounded-full shadow-lg">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
          </svg>
        </button>
        <button @click="nextSlide" class="absolute right-4 top-1/2 -translate-y-1/2 bg-white/80 hover:bg-white p-2 rounded-full shadow-lg">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
          </svg>
        </button>
      </div>
    </section>

    <!-- Filters -->
    <section class="container mx-auto px-4 py-4">
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex flex-wrap gap-4">
          <!-- Price Filter -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">Mức giá</label>
            <div class="flex flex-wrap gap-2">
              <button
                v-for="price in priceRanges"
                :key="price.id"
                @click="selectPriceRange(price.id)"
                :class="[
                  'px-4 py-2 rounded-lg text-sm transition-colors border',
                  selectedPriceRange === price.id
                    ? 'bg-blue-600 text-white border-blue-600'
                    : 'bg-white text-gray-700 border-gray-300 hover:border-blue-600'
                ]"
              >
                {{ price.label }}
              </button>
            </div>
          </div>

          <!-- Color Filter -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">Màu sắc</label>
            <div class="flex flex-wrap gap-2">
              <button
                v-for="color in colors"
                :key="color.id"
                @click="selectColor(color.id)"
                :class="[
                  'px-4 py-2 rounded-lg text-sm transition-all border-2',
                  selectedColor === color.id
                    ? 'border-blue-600 bg-blue-50'
                    : 'border-gray-300 hover:border-blue-400'
                ]"
              >
                <span
                  class="inline-block w-4 h-4 rounded-full mr-2"
                  :style="{ backgroundColor: color.hex }"
                ></span>
                {{ color.name }}
              </button>
            </div>
          </div>

          <!-- Storage Filter -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">Bộ nhớ</label>
            <div class="flex flex-wrap gap-2">
              <button
                v-for="storage in storages"
                :key="storage.id"
                @click="selectStorage(storage.id)"
                :class="[
                  'px-4 py-2 rounded-lg text-sm transition-colors border',
                  selectedStorage === storage.id
                    ? 'bg-blue-600 text-white border-blue-600'
                    : 'bg-white text-gray-700 border-gray-300 hover:border-blue-600'
                ]"
              >
                {{ storage.label }}
              </button>
            </div>
          </div>
        </div>

        <div v-if="hasActiveFilters" class="mt-4 flex items-center gap-2">
          <span class="text-sm text-gray-600">Đang lọc:</span>
          <button @click="clearFilters" class="text-sm text-blue-600 hover:text-blue-800 font-medium">
            Xóa tất cả
          </button>
        </div>
      </div>
    </section>

    <!-- Products Grid -->
    <main class="container mx-auto px-4 py-8">
      <div class="flex justify-between items-center mb-6">
        <h2 class="text-2xl font-bold text-gray-800">
          Sản phẩm nổi bật
          <span class="text-gray-500 text-lg font-normal">({{ filteredProducts.length }} sản phẩm)</span>
        </h2>
        <select
          v-model="sortBy"
          class="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          <option value="default">Sắp xếp</option>
          <option value="price-asc">Giá thấp đến cao</option>
          <option value="price-desc">Giá cao đến thấp</option>
          <option value="newest">Mới nhất</option>
        </select>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <div v-for="n in 8" :key="n" class="bg-white rounded-lg shadow-md overflow-hidden animate-pulse">
          <div class="w-full h-64 bg-gray-300"></div>
          <div class="p-4">
            <div class="h-4 bg-gray-300 rounded mb-2"></div>
            <div class="h-4 bg-gray-300 rounded w-2/3"></div>
          </div>
        </div>
      </div>

      <!-- Products Grid -->
      <div v-else-if="filteredProducts.length > 0" class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        <ProductCard
          v-for="product in paginatedProducts"
          :key="product.id"
          :product="product"
          @add-to-cart="addToCart"
          @toggle-wishlist="toggleWishlist"
        />
      </div>

      <!-- No Results -->
      <div v-else class="text-center py-16">
        <svg class="w-24 h-24 mx-auto text-gray-400 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.172 16.172a4 4 0 015.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <h3 class="text-xl font-semibold text-gray-600 mb-2">Không tìm thấy sản phẩm</h3>
        <p class="text-gray-500">Vui lòng thử lại với bộ lọc khác</p>
      </div>

      <!-- Pagination -->
      <div v-if="totalPages > 1" class="mt-8">
        <Pagination
          :current-page="currentPage"
          :total-pages="totalPages"
          @change="currentPage = $event"
        />
      </div>
    </main>

    <Footer />

    <!-- Toast Notification -->
    <Transition name="toast">
      <div
        v-if="toast.show"
        :class="[
          'fixed bottom-4 right-4 px-6 py-4 rounded-lg shadow-lg text-white z-50',
          toast.type === 'success' ? 'bg-green-500' : 'bg-red-500'
        ]"
      >
        {{ toast.message }}
      </div>
    </Transition>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import ProductCard from './components/ProductCard.vue';
import Pagination from './components/Pagination.vue';

export default {
  name: 'HomePage',
  components: {
    Header,
    Footer,
    ProductCard,
    Pagination,
  },
  setup() {
    const products = ref([]);
    const loading = ref(false);
    const selectedPriceRange = ref(null);
    const selectedColor = ref(null);
    const selectedStorage = ref(null);
    const sortBy = ref('default');
    const currentPage = ref(1);
    const itemsPerPage = 12;
    const currentSlide = ref(0);

    const toast = ref({
      show: false,
      message: '',
      type: 'success'
    });

    const priceRanges = ref([
      { id: 'under-10', label: 'Dưới 10 triệu', min: 0, max: 10000000 },
      { id: '10-15', label: '10 - 15 triệu', min: 10000000, max: 15000000 },
      { id: '15-20', label: '15 - 20 triệu', min: 15000000, max: 20000000 },
      { id: '20-25', label: '20 - 25 triệu', min: 20000000, max: 25000000 },
      { id: 'above-25', label: 'Trên 25 triệu', min: 25000000, max: 999999999 },
    ]);

    const colors = ref([
      { id: 'all', name: 'Tất cả', hex: '#ffffff' },
      { id: 'black', name: 'Đen', hex: '#000000' },
      { id: 'white', name: 'Trắng', hex: '#F5F5F5' },
      { id: 'blue', name: 'Xanh', hex: '#0071E3' },
      { id: 'purple', name: 'Tím', hex: '#9B59B6' },
      { id: 'red', name: 'Đỏ', hex: '#E74C3C' },
    ]);

    const storages = ref([
      { id: 'all', label: 'Tất cả' },
      { id: '128', label: '128GB' },
      { id: '256', label: '256GB' },
      { id: '512', label: '512GB' },
      { id: '1024', label: '1TB' },
    ]);

    const banners = ref([
      { id: 1, title: 'iPhone 15 Pro Max', image: 'https://images.unsplash.com/photo-1696446702874-524dc1508242?w=1200&h=400&fit=crop' },
      { id: 2, title: 'iPhone 15 Series', image: 'https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=1200&h=400&fit=crop' },
      { id: 3, title: 'Ưu đãi đặc biệt', image: 'https://images.unsplash.com/photo-1556656793-08538906a9f8?w=1200&h=400&fit=crop' },
    ]);

    const fetchProducts = async () => {
      loading.value = true;
      try {
        const response = await axios.get('http://localhost:8080/api/chi-tiet-san-pham/ban-hang');
        products.value = response.data.map(product => ({
          ...product,
          isNew: Math.random() > 0.7,
        }));
      } catch (error) {
        console.error('Error fetching products:', error);
        showToast('Không thể tải sản phẩm', 'error');
      } finally {
        loading.value = false;
      }
    };

    const selectPriceRange = (rangeId) => {
      selectedPriceRange.value = selectedPriceRange.value === rangeId ? null : rangeId;
      currentPage.value = 1;
    };

    const selectColor = (colorId) => {
      selectedColor.value = selectedColor.value === colorId ? null : colorId;
      currentPage.value = 1;
    };

    const selectStorage = (storageId) => {
      selectedStorage.value = selectedStorage.value === storageId ? null : storageId;
      currentPage.value = 1;
    };

    const clearFilters = () => {
      selectedPriceRange.value = null;
      selectedColor.value = null;
      selectedStorage.value = null;
      currentPage.value = 1;
    };

    const addToCart = (product) => {
      showToast(`Đã thêm ${product.tenSanPham} vào giỏ hàng`, 'success');
    };

    const toggleWishlist = (product) => {
      showToast('Đã thêm vào yêu thích', 'success');
    };

    const showToast = (message, type = 'success') => {
      toast.value = { show: true, message, type };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    const nextSlide = () => {
      currentSlide.value = (currentSlide.value + 1) % banners.value.length;
    };

    const prevSlide = () => {
      currentSlide.value = currentSlide.value === 0 ? banners.value.length - 1 : currentSlide.value - 1;
    };

    onMounted(() => {
      fetchProducts();
      setInterval(nextSlide, 5000);
    });

    const filteredProducts = computed(() => {
      let result = products.value;

      if (selectedPriceRange.value) {
        const range = priceRanges.value.find(r => r.id === selectedPriceRange.value);
        if (range) {
          result = result.filter(p => {
            const price = p.giaSauKhiGiam || p.giaBan;
            return price >= range.min && price <= range.max;
          });
        }
      }

      if (selectedColor.value && selectedColor.value !== 'all') {
        result = result.filter(p =>
          p.mauSac && p.mauSac.toLowerCase().includes(selectedColor.value)
        );
      }

      if (selectedStorage.value && selectedStorage.value !== 'all') {
        result = result.filter(p =>
          p.boNhoTrong && p.boNhoTrong.includes(selectedStorage.value)
        );
      }

      if (sortBy.value === 'price-asc') {
        result.sort((a, b) => (a.giaSauKhiGiam || a.giaBan) - (b.giaSauKhiGiam || b.giaBan));
      } else if (sortBy.value === 'price-desc') {
        result.sort((a, b) => (b.giaSauKhiGiam || b.giaBan) - (a.giaSauKhiGiam || a.giaBan));
      } else if (sortBy.value === 'newest') {
        result.sort((a, b) => b.id - a.id);
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

    const hasActiveFilters = computed(() => {
      return selectedPriceRange.value || selectedColor.value || selectedStorage.value;
    });

    return {
      products,
      loading,
      priceRanges,
      selectedPriceRange,
      colors,
      selectedColor,
      storages,
      selectedStorage,
      sortBy,
      currentPage,
      banners,
      currentSlide,
      toast,
      filteredProducts,
      paginatedProducts,
      totalPages,
      hasActiveFilters,
      selectPriceRange,
      selectColor,
      selectStorage,
      clearFilters,
      addToCart,
      toggleWishlist,
      nextSlide,
      prevSlide,
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
  transform: translateY(20px);
}
</style>