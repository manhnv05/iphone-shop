<template>
  <div class="bg-gray-50 min-h-screen">
    <Header />

    <!-- Breadcrumb -->
    <div class="bg-white border-b">
      <div class="container mx-auto px-4 py-3">
        <div class="flex items-center text-sm text-gray-600">
          <router-link to="/" class="hover:text-blue-600">Trang chủ</router-link>
          <svg class="w-4 h-4 mx-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
          </svg>
          <span class="text-gray-800">Giỏ hàng</span>
        </div>
      </div>
    </div>

    <div class="container mx-auto px-4 py-8">
      <!-- Empty Cart -->
      <div v-if="cartItems.length === 0" class="bg-white rounded-lg shadow-sm p-12 text-center">
        <svg class="w-32 h-32 mx-auto text-gray-300 mb-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
        </svg>
        <h2 class="text-2xl font-bold text-gray-800 mb-2">Giỏ hàng trống</h2>
        <p class="text-gray-600 mb-6">Hãy thêm sản phẩm vào giỏ hàng để tiếp tục mua sắm</p>
        <router-link to="/shop" class="inline-block bg-blue-600 hover:bg-blue-700 text-white font-semibold px-8 py-3 rounded-lg transition-colors">
          Tiếp tục mua sắm
        </router-link>
      </div>

      <!-- Cart with items -->
      <div v-else class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- Cart Items -->
        <div class="lg:col-span-2 space-y-4">
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex justify-between items-center mb-6">
              <h2 class="text-2xl font-bold text-gray-800">
                Giỏ hàng <span class="text-gray-500 text-lg">({{ cartItems.length }} sản phẩm)</span>
              </h2>
              <button @click="clearCart" class="text-red-600 hover:text-red-700 text-sm font-medium">
                Xóa tất cả
              </button>
            </div>

            <!-- Cart Items List -->
            <div class="space-y-4">
              <div
                v-for="item in cartItems"
                :key="item.id"
                class="flex gap-4 p-4 border border-gray-200 rounded-lg hover:border-blue-300 transition-colors"
              >
                <!-- Product Image -->
                <div class="flex-shrink-0">
                  <img
                    :src="item.duongDanAnh || 'https://via.placeholder.com/100x100.png?text=iPhone'"
                    :alt="item.tenSanPham"
                    class="w-24 h-24 object-cover rounded-lg cursor-pointer"
                    @click="goToProduct(item.idChiTietSanPham)"
                  />
                </div>

                <!-- Product Info -->
                <div class="flex-1">
                  <h3
                    @click="goToProduct(item.idChiTietSanPham)"
                    class="font-semibold text-gray-800 mb-2 hover:text-blue-600 cursor-pointer"
                  >
                    {{ item.tenSanPham }}
                  </h3>
                  <div class="flex flex-wrap gap-2 mb-3">
                    <span class="text-xs bg-gray-100 text-gray-600 px-2 py-1 rounded">{{ item.boNhoTrong }}</span>
                    <span class="text-xs bg-gray-100 text-gray-600 px-2 py-1 rounded">{{ item.mauSac }}</span>
                    <span class="text-xs bg-gray-100 text-gray-600 px-2 py-1 rounded">{{ item.ram }}</span>
                  </div>

                  <!-- Price and Actions -->
                  <div class="flex justify-between items-center">
                    <div>
                      <div v-if="item.phanTramGiam > 0" class="flex items-center gap-2">
                        <span class="text-lg font-bold text-red-600">
                          {{ formatPrice(item.giaSauKhiGiam) }}
                        </span>
                        <span class="text-sm text-gray-500 line-through">
                          {{ formatPrice(item.giaBan) }}
                        </span>
                        <span class="text-xs bg-red-100 text-red-600 px-2 py-1 rounded">
                          -{{ item.phanTramGiam }}%
                        </span>
                      </div>
                      <div v-else>
                        <span class="text-lg font-bold text-gray-800">
                          {{ formatPrice(item.giaBan) }}
                        </span>
                      </div>
                    </div>

                    <!-- Quantity Controls -->
                    <div class="flex items-center gap-3">
                      <div class="flex items-center border border-gray-300 rounded-lg">
                        <button
                          @click="decreaseQuantity(item)"
                          :disabled="item.quantity <= 1"
                          class="px-3 py-2 hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                        >
                          <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 12H4" />
                          </svg>
                        </button>
                        <input
                          v-model.number="item.quantity"
                          @change="updateQuantity(item)"
                          type="number"
                          min="1"
                          :max="item.soLuongTonKho"
                          class="w-16 text-center border-x border-gray-300 py-2 focus:outline-none"
                        />
                        <button
                          @click="increaseQuantity(item)"
                          :disabled="item.quantity >= item.soLuongTonKho"
                          class="px-3 py-2 hover:bg-gray-100 disabled:opacity-50 disabled:cursor-not-allowed"
                        >
                          <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
                          </svg>
                        </button>
                      </div>

                      <!-- Remove Button -->
                      <button
                        @click="removeItem(item)"
                        class="text-red-600 hover:text-red-700 p-2"
                        title="Xóa sản phẩm"
                      >
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
                        </svg>
                      </button>
                    </div>
                  </div>

                  <!-- Stock Warning -->
                  <div v-if="item.quantity >= item.soLuongTonKho" class="mt-2 text-sm text-orange-600">
                    ⚠ Chỉ còn {{ item.soLuongTonKho }} sản phẩm
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Promotion Code -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h3 class="font-semibold text-gray-800 mb-4">Mã khuyến mãi</h3>
            <div class="flex gap-3">
              <input
                v-model="promoCode"
                type="text"
                placeholder="Nhập mã khuyến mãi"
                class="flex-1 px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
              <button
                @click="applyPromoCode"
                :disabled="!promoCode || applyingPromo"
                class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-3 rounded-lg transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
              >
                {{ applyingPromo ? 'Đang áp dụng...' : 'Áp dụng' }}
              </button>
            </div>

            <!-- Applied Promo -->
            <div v-if="appliedPromo" class="mt-4 flex items-center justify-between bg-green-50 border border-green-200 rounded-lg p-3">
              <div class="flex items-center gap-2">
                <svg class="w-5 h-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span class="text-sm font-medium text-green-800">
                  Mã "{{ appliedPromo.code }}" - Giảm {{ appliedPromo.discount }}%
                </span>
              </div>
              <button @click="removePromo" class="text-red-600 hover:text-red-700">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
          </div>
        </div>

        <!-- Order Summary -->
        <div class="lg:col-span-1">
          <div class="bg-white rounded-lg shadow-sm p-6 sticky top-20">
            <h3 class="text-xl font-bold text-gray-800 mb-6">Thông tin đơn hàng</h3>

            <!-- Summary Items -->
            <div class="space-y-4 mb-6">
              <div class="flex justify-between text-gray-600">
                <span>Tạm tính ({{ totalItems }} sản phẩm)</span>
                <span class="font-semibold">{{ formatPrice(subtotal) }}</span>
              </div>

              <div v-if="appliedPromo" class="flex justify-between text-green-600">
                <span>Giảm giá ({{ appliedPromo.discount }}%)</span>
                <span class="font-semibold">-{{ formatPrice(discountAmount) }}</span>
              </div>

              <div class="flex justify-between text-gray-600">
                <span>Phí vận chuyển</span>
                <span class="font-semibold">{{ shippingFee > 0 ? formatPrice(shippingFee) : 'Miễn phí' }}</span>
              </div>

              <div class="border-t pt-4 flex justify-between items-center">
                <span class="text-lg font-bold text-gray-800">Tổng cộng</span>
                <span class="text-2xl font-bold text-red-600">{{ formatPrice(total) }}</span>
              </div>
            </div>

            <!-- VAT Note -->
            <div class="bg-blue-50 border border-blue-200 rounded-lg p-3 mb-6">
              <p class="text-sm text-blue-800">
                <svg class="w-4 h-4 inline mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                Giá đã bao gồm VAT
              </p>
            </div>

            <!-- Checkout Button -->
            <router-link
              to="/checkout"
              class="block w-full bg-red-600 hover:bg-red-700 text-white text-center font-bold py-4 rounded-lg transition-colors mb-3"
            >
              Tiến hành thanh toán
            </router-link>

            <router-link
              to="/shop"
              class="block w-full bg-white hover:bg-gray-50 text-blue-600 text-center font-semibold py-3 rounded-lg border-2 border-blue-600 transition-colors"
            >
              Tiếp tục mua sắm
            </router-link>

            <!-- Benefits -->
            <div class="mt-6 space-y-3">
              <div class="flex items-center gap-3 text-sm text-gray-600">
                <svg class="w-5 h-5 text-green-600 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span>Miễn phí vận chuyển cho đơn hàng trên 5 triệu</span>
              </div>
              <div class="flex items-center gap-3 text-sm text-gray-600">
                <svg class="w-5 h-5 text-green-600 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span>Bảo hành chính hãng Apple</span>
              </div>
              <div class="flex items-center gap-3 text-sm text-gray-600">
                <svg class="w-5 h-5 text-green-600 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span>Đổi trả trong 14 ngày</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Recommended Products -->
      <div v-if="cartItems.length > 0" class="mt-12">
        <h3 class="text-2xl font-bold text-gray-800 mb-6">Có thể bạn quan tâm</h3>
        <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
          <ProductCard
            v-for="product in recommendedProducts"
            :key="product.id"
            :product="product"
            :compact="true"
            @add-to-cart="addToCart"
          />
        </div>
      </div>
    </div>

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
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import axios from 'axios';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import ProductCard from './components/ProductCard.vue';

export default {
  name: 'Cart',
  components: {
    Header,
    Footer,
    ProductCard,
  },
  setup() {
    const router = useRouter();
    const store = useStore();

    const cartItems = ref([
      // Example data - should come from Vuex store
      {
        id: 1,
        idChiTietSanPham: 1,
        tenSanPham: 'iPhone 15 Pro Max',
        duongDanAnh: 'https://images.unsplash.com/photo-1696446702874-524dc1508242?w=300',
        maSanPham: 'IP15PM-256-BL',
        mauSac: 'Titan Đen',
        ram: '8GB',
        boNhoTrong: '256GB',
        giaBan: 29990000,
        giaSauKhiGiam: 27990000,
        phanTramGiam: 7,
        quantity: 1,
        soLuongTonKho: 5,
      },
    ]);

    const recommendedProducts = ref([]);
    const promoCode = ref('');
    const appliedPromo = ref(null);
    const applyingPromo = ref(false);

    const toast = ref({
      show: false,
      message: '',
      type: 'success',
    });

    // Computed
    const totalItems = computed(() => {
      return cartItems.value.reduce((sum, item) => sum + item.quantity, 0);
    });

    const subtotal = computed(() => {
      return cartItems.value.reduce((sum, item) => {
        const price = item.giaSauKhiGiam || item.giaBan;
        return sum + (price * item.quantity);
      }, 0);
    });

    const discountAmount = computed(() => {
      if (!appliedPromo.value) return 0;
      return subtotal.value * appliedPromo.value.discount / 100;
    });

    const shippingFee = computed(() => {
      return subtotal.value >= 5000000 ? 0 : 50000;
    });

    const total = computed(() => {
      return subtotal.value - discountAmount.value + shippingFee.value;
    });

    // Methods
    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    const increaseQuantity = (item) => {
      if (item.quantity < item.soLuongTonKho) {
        item.quantity++;
        showToast('Đã cập nhật số lượng', 'success');
      }
    };

    const decreaseQuantity = (item) => {
      if (item.quantity > 1) {
        item.quantity--;
        showToast('Đã cập nhật số lượng', 'success');
      }
    };

    const updateQuantity = (item) => {
      if (item.quantity < 1) {
        item.quantity = 1;
      } else if (item.quantity > item.soLuongTonKho) {
        item.quantity = item.soLuongTonKho;
        showToast(`Chỉ còn ${item.soLuongTonKho} sản phẩm`, 'error');
      }
    };

    const removeItem = (item) => {
      const index = cartItems.value.findIndex(i => i.id === item.id);
      if (index > -1) {
        cartItems.value.splice(index, 1);
        showToast('Đã xóa sản phẩm khỏi giỏ hàng', 'success');
      }
    };

    const clearCart = () => {
      if (confirm('Bạn có chắc muốn xóa tất cả sản phẩm?')) {
        cartItems.value = [];
        appliedPromo.value = null;
        showToast('Đã xóa tất cả sản phẩm', 'success');
      }
    };

    const applyPromoCode = async () => {
      if (!promoCode.value) return;

      applyingPromo.value = true;
      try {
        // Call API to validate promo code
        // const response = await axios.post('/api/promo/validate', { code: promoCode.value });

        // Mock response
        await new Promise(resolve => setTimeout(resolve, 1000));

        if (promoCode.value.toUpperCase() === 'DISCOUNT10') {
          appliedPromo.value = {
            code: promoCode.value.toUpperCase(),
            discount: 10,
          };
          showToast('Áp dụng mã giảm giá thành công', 'success');
        } else {
          showToast('Mã giảm giá không hợp lệ', 'error');
        }
      } catch (error) {
        showToast('Có lỗi xảy ra', 'error');
      } finally {
        applyingPromo.value = false;
      }
    };

    const removePromo = () => {
      appliedPromo.value = null;
      promoCode.value = '';
      showToast('Đã hủy mã giảm giá', 'success');
    };

    const goToProduct = (id) => {
      router.push(`/product/${id}`);
    };

    const addToCart = (product) => {
      showToast(`Đã thêm ${product.tenSanPham} vào giỏ hàng`, 'success');
    };

    const showToast = (message, type = 'success') => {
      toast.value = { show: true, message, type };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    const fetchRecommendedProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/chi-tiet-san-pham/ban-hang?limit=6');
        recommendedProducts.value = response.data.slice(0, 6);
      } catch (error) {
        console.error('Error fetching recommended products:', error);
      }
    };

    onMounted(() => {
      fetchRecommendedProducts();
    });

    return {
      cartItems,
      recommendedProducts,
      promoCode,
      appliedPromo,
      applyingPromo,
      toast,
      totalItems,
      subtotal,
      discountAmount,
      shippingFee,
      total,
      formatPrice,
      increaseQuantity,
      decreaseQuantity,
      updateQuantity,
      removeItem,
      clearCart,
      applyPromoCode,
      removePromo,
      goToProduct,
      addToCart,
    };
  },
};
</script>

<style scoped>
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type="number"] {
  -moz-appearance: textfield;
}

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