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
          <router-link to="/cart" class="hover:text-blue-600">Giỏ hàng</router-link>
          <svg class="w-4 h-4 mx-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
          </svg>
          <span class="text-gray-800">Thanh toán</span>
        </div>
      </div>
    </div>

    <div class="container mx-auto px-4 py-8">
      <!-- Progress Steps -->
      <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
        <div class="flex items-center justify-between max-w-3xl mx-auto">
          <div class="flex items-center flex-1">
            <div class="flex items-center justify-center w-10 h-10 bg-blue-600 text-white rounded-full font-semibold">
              1
            </div>
            <span class="ml-3 font-medium text-gray-800">Thông tin</span>
          </div>
          <div class="flex-1 h-1 bg-blue-600 mx-4"></div>
          <div class="flex items-center flex-1">
            <div :class="[
              'flex items-center justify-center w-10 h-10 rounded-full font-semibold',
              currentStep >= 2 ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-600'
            ]">
              2
            </div>
            <span class="ml-3 font-medium" :class="currentStep >= 2 ? 'text-gray-800' : 'text-gray-400'">Thanh toán</span>
          </div>
          <div class="flex-1 h-1 mx-4" :class="currentStep >= 2 ? 'bg-blue-600' : 'bg-gray-200'"></div>
          <div class="flex items-center flex-1">
            <div :class="[
              'flex items-center justify-center w-10 h-10 rounded-full font-semibold',
              currentStep >= 3 ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-600'
            ]">
              3
            </div>
            <span class="ml-3 font-medium" :class="currentStep >= 3 ? 'text-gray-800' : 'text-gray-400'">Hoàn tất</span>
          </div>
        </div>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- Left Column - Forms -->
        <div class="lg:col-span-2 space-y-6">
          <!-- Customer Information -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex justify-between items-center mb-6">
              <h2 class="text-xl font-bold text-gray-800">Thông tin khách hàng</h2>
              <router-link v-if="!isLoggedIn" to="/login" class="text-blue-600 hover:text-blue-700 text-sm font-medium">
                Đăng nhập
              </router-link>
            </div>

            <form @submit.prevent="proceedToPayment" class="space-y-4">
              <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">
                    Họ và tên <span class="text-red-600">*</span>
                  </label>
                  <input
                    v-model="customerInfo.fullName"
                    type="text"
                    required
                    class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    placeholder="Nguyễn Văn A"
                  />
                </div>
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">
                    Số điện thoại <span class="text-red-600">*</span>
                  </label>
                  <input
                    v-model="customerInfo.phone"
                    type="tel"
                    required
                    pattern="[0-9]{10}"
                    class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    placeholder="0123456789"
                  />
                </div>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Email <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="customerInfo.email"
                  type="email"
                  required
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="email@example.com"
                />
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Địa chỉ <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="customerInfo.address"
                  type="text"
                  required
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="Số nhà, tên đường"
                />
              </div>

              <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">
                    Tỉnh/Thành phố <span class="text-red-600">*</span>
                  </label>
                  <select
                    v-model="customerInfo.province"
                    required
                    @change="loadDistricts"
                    class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  >
                    <option value="">Chọn Tỉnh/TP</option>
                    <option v-for="province in provinces" :key="province.code" :value="province.code">
                      {{ province.name }}
                    </option>
                  </select>
                </div>
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">
                    Quận/Huyện <span class="text-red-600">*</span>
                  </label>
                  <select
                    v-model="customerInfo.district"
                    required
                    @change="loadWards"
                    :disabled="!customerInfo.province"
                    class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100"
                  >
                    <option value="">Chọn Quận/Huyện</option>
                    <option v-for="district in districts" :key="district.code" :value="district.code">
                      {{ district.name }}
                    </option>
                  </select>
                </div>
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">
                    Phường/Xã <span class="text-red-600">*</span>
                  </label>
                  <select
                    v-model="customerInfo.ward"
                    required
                    :disabled="!customerInfo.district"
                    class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-100"
                  >
                    <option value="">Chọn Phường/Xã</option>
                    <option v-for="ward in wards" :key="ward.code" :value="ward.code">
                      {{ ward.name }}
                    </option>
                  </select>
                </div>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">
                  Ghi chú
                </label>
                <textarea
                  v-model="customerInfo.note"
                  rows="3"
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="Ghi chú thêm (tùy chọn)"
                ></textarea>
              </div>
            </form>
          </div>

          <!-- Shipping Method -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-bold text-gray-800 mb-6">Phương thức vận chuyển</h2>
            <div class="space-y-3">
              <label
                v-for="method in shippingMethods"
                :key="method.id"
                class="flex items-center justify-between p-4 border-2 rounded-lg cursor-pointer transition-all"
                :class="shippingMethod === method.id ? 'border-blue-600 bg-blue-50' : 'border-gray-200 hover:border-blue-300'"
              >
                <div class="flex items-center">
                  <input
                    type="radio"
                    :value="method.id"
                    v-model="shippingMethod"
                    class="mr-3"
                  />
                  <div>
                    <div class="font-semibold text-gray-800">{{ method.name }}</div>
                    <div class="text-sm text-gray-600">{{ method.description }}</div>
                  </div>
                </div>
                <div class="font-bold text-gray-800">
                  {{ method.fee > 0 ? formatPrice(method.fee) : 'Miễn phí' }}
                </div>
              </label>
            </div>
          </div>

          <!-- Payment Method -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h2 class="text-xl font-bold text-gray-800 mb-6">Phương thức thanh toán</h2>
            <div class="space-y-3">
              <label
                v-for="method in paymentMethods"
                :key="method.id"
                class="flex items-start p-4 border-2 rounded-lg cursor-pointer transition-all"
                :class="paymentMethod === method.id ? 'border-blue-600 bg-blue-50' : 'border-gray-200 hover:border-blue-300'"
              >
                <input
                  type="radio"
                  :value="method.id"
                  v-model="paymentMethod"
                  class="mt-1 mr-3"
                />
                <div class="flex-1">
                  <div class="flex items-center gap-3 mb-2">
                    <img v-if="method.icon" :src="method.icon" :alt="method.name" class="h-8" />
                    <span class="font-semibold text-gray-800">{{ method.name }}</span>
                  </div>
                  <div class="text-sm text-gray-600">{{ method.description }}</div>
                </div>
              </label>
            </div>
          </div>
        </div>

        <!-- Right Column - Order Summary -->
        <div class="lg:col-span-1">
          <div class="bg-white rounded-lg shadow-sm p-6 sticky top-20">
            <h3 class="text-xl font-bold text-gray-800 mb-6">Đơn hàng ({{ cartItems.length }} sản phẩm)</h3>

            <!-- Cart Items -->
            <div class="space-y-4 mb-6 max-h-96 overflow-y-auto">
              <div
                v-for="item in cartItems"
                :key="item.id"
                class="flex gap-3 pb-4 border-b last:border-b-0"
              >
                <div class="relative flex-shrink-0">
                  <img
                    :src="item.duongDanAnh"
                    :alt="item.tenSanPham"
                    class="w-20 h-20 object-cover rounded-lg"
                  />
                  <span class="absolute -top-2 -right-2 bg-gray-800 text-white text-xs rounded-full w-6 h-6 flex items-center justify-center">
                    {{ item.quantity }}
                  </span>
                </div>
                <div class="flex-1">
                  <h4 class="font-medium text-gray-800 text-sm line-clamp-2">{{ item.tenSanPham }}</h4>
                  <div class="flex gap-1 mt-1">
                    <span class="text-xs bg-gray-100 text-gray-600 px-2 py-0.5 rounded">{{ item.boNhoTrong }}</span>
                    <span class="text-xs bg-gray-100 text-gray-600 px-2 py-0.5 rounded">{{ item.mauSac }}</span>
                  </div>
                  <div class="mt-2 font-semibold text-red-600">
                    {{ formatPrice((item.giaSauKhiGiam || item.giaBan) * item.quantity) }}
                  </div>
                </div>
              </div>
            </div>

            <!-- Price Summary -->
            <div class="space-y-3 py-4 border-t border-b">
              <div class="flex justify-between text-gray-600">
                <span>Tạm tính</span>
                <span class="font-semibold">{{ formatPrice(subtotal) }}</span>
              </div>
              <div class="flex justify-between text-gray-600">
                <span>Phí vận chuyển</span>
                <span class="font-semibold">{{ shippingFee > 0 ? formatPrice(shippingFee) : 'Miễn phí' }}</span>
              </div>
              <div v-if="discount > 0" class="flex justify-between text-green-600">
                <span>Giảm giá</span>
                <span class="font-semibold">-{{ formatPrice(discount) }}</span>
              </div>
            </div>

            <div class="flex justify-between items-center py-4">
              <span class="text-lg font-bold text-gray-800">Tổng cộng</span>
              <span class="text-2xl font-bold text-red-600">{{ formatPrice(total) }}</span>
            </div>

            <!-- Action Buttons -->
            <button
              @click="handleCheckout"
              :disabled="processing"
              class="w-full bg-red-600 hover:bg-red-700 text-white font-bold py-4 rounded-lg transition-colors mb-3 disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {{ processing ? 'Đang xử lý...' : 'Đặt hàng' }}
            </button>

            <router-link
              to="/cart"
              class="block w-full text-center text-blue-600 hover:text-blue-700 font-medium py-2"
            >
              ← Quay lại giỏ hàng
            </router-link>

            <!-- Security Notice -->
            <div class="mt-6 bg-green-50 border border-green-200 rounded-lg p-4">
              <div class="flex items-start gap-3">
                <svg class="w-6 h-6 text-green-600 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m5.618-4.016A11.955 11.955 0 0112 2.944a11.955 11.955 0 01-8.618 3.04A12.02 12.02 0 003 9c0 5.591 3.824 10.29 9 11.622 5.176-1.332 9-6.03 9-11.622 0-1.042-.133-2.052-.382-3.016z" />
                </svg>
                <div>
                  <h4 class="font-semibold text-green-800 mb-1">Thanh toán an toàn</h4>
                  <p class="text-sm text-green-700">Thông tin của bạn được bảo mật tuyệt đối</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer />

    <!-- Success Modal -->
    <Transition name="modal">
      <div v-if="showSuccessModal" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
        <div class="bg-white rounded-2xl max-w-md w-full p-8 text-center">
          <div class="w-20 h-20 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-6">
            <svg class="w-12 h-12 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
            </svg>
          </div>
          <h2 class="text-2xl font-bold text-gray-800 mb-3">Đặt hàng thành công!</h2>
          <p class="text-gray-600 mb-2">Mã đơn hàng: <strong>{{ orderCode }}</strong></p>
          <p class="text-gray-600 mb-6">Cảm ơn bạn đã mua hàng tại TopZone</p>
          <div class="space-y-3">
            <router-link
              :to="`/orders/${orderCode}`"
              class="block w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 rounded-lg transition-colors"
            >
              Xem đơn hàng
            </router-link>
            <router-link
              to="/"
              class="block w-full bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-3 rounded-lg transition-colors"
            >
              Về trang chủ
            </router-link>
          </div>
        </div>
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

export default {
  name: 'Checkout',
  components: {
    Header,
    Footer,
  },
  setup() {
    const router = useRouter();
    const store = useStore();

    const currentStep = ref(1);
    const processing = ref(false);
    const showSuccessModal = ref(false);
    const orderCode = ref('');

    const customerInfo = ref({
      fullName: 'Nguyễn Văn Mạnh',
      phone: '0123456789',
      email: 'manhnv05@example.com',
      address: 'Số 123, Đường ABC',
      province: '',
      district: '',
      ward: '',
      note: '',
    });

    const shippingMethod = ref('standard');
    const paymentMethod = ref('cod');

    const provinces = ref([
      { code: 'HN', name: 'Hà Nội' },
      { code: 'HCM', name: 'TP. Hồ Chí Minh' },
      { code: 'DN', name: 'Đà Nẵng' },
    ]);

    const districts = ref([]);
    const wards = ref([]);

    const shippingMethods = ref([
      {
        id: 'standard',
        name: 'Giao hàng tiêu chuẩn',
        description: '3-5 ngày làm việc',
        fee: 30000,
      },
      {
        id: 'express',
        name: 'Giao hàng nhanh',
        description: '1-2 ngày làm việc',
        fee: 50000,
      },
      {
        id: 'free',
        name: 'Miễn phí vận chuyển',
        description: 'Cho đơn hàng trên 5 triệu',
        fee: 0,
      },
    ]);

    const paymentMethods = ref([
      {
        id: 'cod',
        name: 'Thanh toán khi nhận hàng (COD)',
        description: 'Thanh toán bằng tiền mặt khi nhận hàng',
        icon: null,
      },
      {
        id: 'bank',
        name: 'Chuyển khoản ngân hàng',
        description: 'Chuyển khoản qua Vietcombank, Techcombank, VietinBank',
        icon: null,
      },
      {
        id: 'vnpay',
        name: 'Ví điện tử VNPay',
        description: 'Thanh toán qua VNPay QR',
        icon: '/images/payment/vnpay.png',
      },
      {
        id: 'momo',
        name: 'Ví MoMo',
        description: 'Thanh toán qua ví MoMo',
        icon: '/images/payment/momo.png',
      },
    ]);

    const cartItems = ref([
      {
        id: 1,
        idChiTietSanPham: 1,
        tenSanPham: 'iPhone 15 Pro Max 256GB',
        duongDanAnh: 'https://images.unsplash.com/photo-1696446702874-524dc1508242?w=300',
        mauSac: 'Titan Đen',
        boNhoTrong: '256GB',
        ram: '8GB',
        giaBan: 29990000,
        giaSauKhiGiam: 27990000,
        quantity: 1,
      },
    ]);

    const isLoggedIn = computed(() => (store && store.getters ? store.getters['auth/isLoggedIn'] : false));

    const subtotal = computed(() => {
      return cartItems.value.reduce((sum, item) => {
        const price = item.giaSauKhiGiam || item.giaBan;
        return sum + (price * item.quantity);
      }, 0);
    });

    const shippingFee = computed(() => {
      if (subtotal.value >= 5000000) return 0;
      const method = shippingMethods.value.find(m => m.id === shippingMethod.value);
      return method ? method.fee : 0;
    });

    const discount = computed(() => {
      return 0; // Calculate discount if any
    });

    const total = computed(() => {
      return subtotal.value + shippingFee.value - discount.value;
    });

    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    const loadDistricts = () => {
      // Mock data - should call API
      districts.value = [
        { code: 'D1', name: 'Quận 1' },
        { code: 'D2', name: 'Quận 2' },
        { code: 'D3', name: 'Quận 3' },
      ];
      customerInfo.value.district = '';
      customerInfo.value.ward = '';
      wards.value = [];
    };

    const loadWards = () => {
      // Mock data - should call API
      wards.value = [
        { code: 'W1', name: 'Phường 1' },
        { code: 'W2', name: 'Phường 2' },
        { code: 'W3', name: 'Phường 3' },
      ];
      customerInfo.value.ward = '';
    };

    const handleCheckout = async () => {
      // Validate form
      if (!customerInfo.value.fullName || !customerInfo.value.phone || !customerInfo.value.email) {
        alert('Vui lòng điền đầy đủ thông tin');
        return;
      }

      processing.value = true;

      try {
        // Call API to create order
        const orderData = {
          customer: customerInfo.value,
          items: cartItems.value,
          shippingMethod: shippingMethod.value,
          paymentMethod: paymentMethod.value,
          subtotal: subtotal.value,
          shippingFee: shippingFee.value,
          discount: discount.value,
          total: total.value,
        };

        // Mock API call
        await new Promise(resolve => setTimeout(resolve, 2000));

        // Generate order code
        orderCode.value = 'DH' + Date.now();

        // Show success modal
        showSuccessModal.value = true;

        // Clear cart
        // store.dispatch('cart/clearCart');
      } catch (error) {
        console.error('Checkout error:', error);
        alert('Có lỗi xảy ra. Vui lòng thử lại!');
      } finally {
        processing.value = false;
      }
    };

    onMounted(() => {
      if (cartItems.value.length === 0) {
        router.push('/cart');
      }
    });

    return {
      currentStep,
      processing,
      showSuccessModal,
      orderCode,
      customerInfo,
      shippingMethod,
      paymentMethod,
      provinces,
      districts,
      wards,
      shippingMethods,
      paymentMethods,
      cartItems,
      isLoggedIn,
      subtotal,
      shippingFee,
      discount,
      total,
      formatPrice,
      loadDistricts,
      loadWards,
      handleCheckout,
    };
  },
};
</script>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.modal-enter-active,
.modal-leave-active {
  transition: all 0.3s ease;
}

.modal-enter-from,
.modal-leave-to {
  opacity: 0;
  transform: scale(0.9);
}
</style>