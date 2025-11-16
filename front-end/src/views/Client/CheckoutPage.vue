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
        <div class="lg:col-span-1 space-y-6">
          <!-- Order Summary -->
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

            <div class="bg-white rounded-lg shadow-sm p-6">
              <div class="flex justify-between items-center mb-4">
                <h2 class="text-lg font-bold text-gray-800">Mã giảm giá</h2>
                <button
                  @click="showVoucherModal = true"
                  class="text-blue-600 hover:text-blue-700 text-sm font-medium"
                >
                  Xem tất cả
                </button>
              </div>

              <!-- Manual Input -->
              <div class="flex gap-2 mb-4">
                <input
                  v-model="promoCode"
                  type="text"
                  placeholder="Nhập mã giảm giá"
                  class="flex-1 px-3 py-2 text-sm border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  @keyup.enter="applyPromoCode"
                />
                <button
                  @click="applyPromoCode"
                  :disabled="!promoCode || applyingPromo"
                  class="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-4 py-2 rounded-lg text-sm transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  {{ applyingPromo ? 'Đang xử lý...' : 'Áp dụng' }}
                </button>
              </div>

              <!-- Applied Voucher -->
              <div v-if="appliedVoucher" class="bg-gradient-to-r from-green-50 to-emerald-50 border-2 border-green-300 rounded-lg p-3">
                <div class="flex items-start justify-between">
                  <div class="flex items-start gap-2 flex-1">
                    <div class="bg-green-500 text-white p-1.5 rounded-lg">
                      <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v13m0-13V6a2 2 0 112 2h-2zm0 0V5.5A2.5 2.5 0 109.5 8H12zm-7 4h14M5 12a2 2 0 110-4h14a2 2 0 110 4M5 12v7a2 2 0 002 2h10a2 2 0 002-2v-7" />
                      </svg>
                    </div>
                    <div class="flex-1">
                      <div class="flex items-center gap-1 mb-0.5">
                        <span class="font-bold text-sm text-green-800">{{ appliedVoucher.code }}</span>
                        <span v-if="appliedVoucher.isBest" class="text-xs bg-yellow-400 text-yellow-900 px-1.5 py-0.5 rounded-full font-bold">
                        TỐT NHẤT
                      </span>
                      </div>
                      <p class="text-xs text-green-700 mb-1">{{ appliedVoucher.description }}</p>
                      <p class="text-base font-bold text-green-800">
                        -{{ formatPrice(appliedVoucher.discountAmount) }}
                      </p>
                    </div>
                  </div>
                  <button
                    @click="removeVoucher"
                    class="text-red-600 hover:text-red-700 p-1"
                    title="Xóa mã"
                  >
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                    </svg>
                  </button>
                </div>
              </div>

              <!-- Best Voucher Suggestion -->
              <div v-else-if="bestVoucher && !appliedVoucher" class="bg-gradient-to-r from-yellow-50 to-orange-50 border-2 border-yellow-300 rounded-lg p-3">
                <div class="flex items-start justify-between gap-2">
                  <div class="flex items-start gap-2 flex-1">
                    <div class="bg-yellow-500 text-white p-1.5 rounded-lg flex-shrink-0">
                      <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
                      </svg>
                    </div>
                    <div class="flex-1">
                      <div class="flex items-center gap-1 mb-0.5">
                        <span class="font-bold text-sm text-yellow-900">{{ bestVoucher.code }}</span>
                        <span class="text-xs bg-yellow-400 text-yellow-900 px-1.5 py-0.5 rounded-full font-bold">
                        TIẾT KIỆM NHẤT
                      </span>
                      </div>
                      <p class="text-xs text-yellow-800 mb-1">{{ bestVoucher.description }}</p>
                      <p class="text-base font-bold text-yellow-900">
                        -{{ formatPrice(bestVoucher.discountAmount) }}
                      </p>
                    </div>
                  </div>
                  <button
                    @click="applyBestVoucher"
                    class="bg-yellow-500 hover:bg-yellow-600 text-white font-bold px-3 py-1.5 rounded-lg text-xs whitespace-nowrap flex-shrink-0"
                  >
                    Áp dụng
                  </button>
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
                <span class="flex items-center gap-1">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v13m0-13V6a2 2 0 112 2h-2zm0 0V5.5A2.5 2.5 0 109.5 8H12zm-7 4h14M5 12a2 2 0 110-4h14a2 2 0 110 4M5 12v7a2 2 0 002 2h10a2 2 0 002-2v-7" />
                  </svg>
                  Giảm giá
                </span>
                <span class="font-bold">-{{ formatPrice(discount) }}</span>
              </div>
            </div>

            <div class="flex justify-between items-center py-4">
              <span class="text-lg font-bold text-gray-800">Tổng cộng</span>
              <div class="text-right">
                <div v-if="discount > 0" class="text-sm text-gray-500 line-through">
                  {{ formatPrice(subtotal + shippingFee) }}
                </div>
                <div class="text-2xl font-bold text-red-600">{{ formatPrice(total) }}</div>
              </div>
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

          <!-- Discount Code Section - MOVED HERE -->
          
        </div>
      </div>
    </div>

    <Footer />

    <!-- Voucher Modal -->
    <Transition name="modal">
      <div v-if="showVoucherModal" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
        <div class="bg-white rounded-2xl max-w-2xl w-full max-h-[80vh] overflow-hidden">
          <!-- Modal Header -->
          <div class="flex justify-between items-center p-6 border-b">
            <h3 class="text-2xl font-bold text-gray-800">Chọn mã giảm giá</h3>
            <button @click="showVoucherModal = false" class="text-gray-400 hover:text-gray-600">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>

          <!-- Modal Body -->
          <div class="p-6 overflow-y-auto max-h-[calc(80vh-140px)]">
            <!-- Best Voucher -->
            <div v-if="bestVoucher" class="mb-6">
              <h4 class="text-sm font-bold text-yellow-600 mb-3 flex items-center gap-2">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
                </svg>
                MÃ TIẾT KIỆM NHẤT
              </h4>
              <VoucherCard
                :voucher="bestVoucher"
                :is-best="true"
                :is-applied="appliedVoucher?.code === bestVoucher.code"
                @apply="selectVoucher(bestVoucher)"
              />
            </div>

            <!-- All Vouchers -->
            <div>
              <h4 class="text-sm font-bold text-gray-700 mb-3">TẤT CẢ MÃ GIẢM GIÁ</h4>
              <div class="space-y-3">
                <VoucherCard
                  v-for="voucher in availableVouchers.filter(v => v.code !== bestVoucher?.code)"
                  :key="voucher.code"
                  :voucher="voucher"
                  :is-applied="appliedVoucher?.code === voucher.code"
                  @apply="selectVoucher(voucher)"
                />
              </div>
            </div>

            <!-- Unavailable Vouchers -->
            <div v-if="unavailableVouchers.length > 0" class="mt-6">
              <h4 class="text-sm font-bold text-gray-400 mb-3">CHƯA ĐỦ ĐIỀU KIỆN</h4>
              <div class="space-y-3 opacity-60">
                <VoucherCard
                  v-for="voucher in unavailableVouchers"
                  :key="voucher.code"
                  :voucher="voucher"
                  :is-disabled="true"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </Transition>

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

// Voucher Card Component
const VoucherCard = {
  name: 'VoucherCard',
  props: {
    voucher: Object,
    isBest: Boolean,
    isApplied: Boolean,
    isDisabled: Boolean,
  },
  emits: ['apply'],
  setup(props, { emit }) {
    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    return { formatPrice };
  },
  template: `
    <div
      :class="[
        'relative border-2 rounded-xl overflow-hidden transition-all',
        isApplied ? 'border-green-500 bg-green-50' : 'border-gray-200',
        isDisabled ? 'opacity-50 cursor-not-allowed' : 'hover:border-blue-300 cursor-pointer',
        isBest && !isApplied ? 'border-yellow-400 bg-yellow-50' : ''
      ]"
      @click="!isDisabled && $emit('apply')"
    >
      <!-- Best Badge -->
      <div v-if="isBest" class="absolute top-0 right-0 bg-yellow-400 text-yellow-900 text-xs font-bold px-3 py-1 rounded-bl-lg">
        TỐT NHẤT
      </div>

      <!-- Applied Badge -->
      <div v-if="isApplied" class="absolute top-0 right-0 bg-green-500 text-white text-xs font-bold px-3 py-1 rounded-bl-lg">
        ĐÃ CHỌN
      </div>

      <div class="flex p-4">
        <!-- Left Icon -->
        <div class="flex-shrink-0 mr-4">
          <div :class="[
            'w-16 h-16 rounded-lg flex items-center justify-center',
            isBest ? 'bg-yellow-500' : 'bg-blue-500'
          ]">
            <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v13m0-13V6a2 2 0 112 2h-2zm0 0V5.5A2.5 2.5 0 109.5 8H12zm-7 4h14M5 12a2 2 0 110-4h14a2 2 0 110 4M5 12v7a2 2 0 002 2h10a2 2 0 002-2v-7" />
            </svg>
          </div>
        </div>

        <!-- Content -->
        <div class="flex-1">
          <div class="flex items-start justify-between mb-2">
            <div>
              <h4 class="font-bold text-gray-900 text-lg">{{ voucher.code }}</h4>
              <p class="text-sm text-gray-600 mt-1">{{ voucher.description }}</p>
            </div>
          </div>

          <!-- Discount Amount -->
          <div class="mb-3">
            <span class="text-2xl font-black text-red-600">
              {{ formatPrice(voucher.discountAmount) }}
            </span>
          </div>

          <!-- Conditions -->
          <div class="space-y-1 text-xs text-gray-600">
            <div v-if="voucher.minOrder" class="flex items-center gap-1">
              <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              Đơn tối thiểu {{ formatPrice(voucher.minOrder) }}
            </div>
            <div v-if="voucher.maxDiscount" class="flex items-center gap-1">
              <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              Giảm tối đa {{ formatPrice(voucher.maxDiscount) }}
            </div>
            <div class="flex items-center gap-1">
              <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              HSD: {{ voucher.expiryDate }}
            </div>
          </div>

          <!-- Apply Button -->
          <button
            v-if="!isDisabled"
            class="mt-3 w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 rounded-lg text-sm transition-colors"
            :class="isApplied ? 'bg-green-600 hover:bg-green-700' : ''"
            @click.stop="$emit('apply')"
          >
            {{ isApplied ? '✓ Đã áp dụng' : 'Áp dụng' }}
          </button>
        </div>
      </div>
    </div>
  `,
};

export default {
  name: 'Checkout',
  components: {
    Header,
    Footer,
    VoucherCard,
  },
  setup() {
    const router = useRouter();
    const store = useStore();

    const currentStep = ref(1);
    const processing = ref(false);
    const showSuccessModal = ref(false);
    const showVoucherModal = ref(false);
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

    // Voucher state
    const promoCode = ref('');
    const appliedVoucher = ref(null);
    const applyingPromo = ref(false);

    // Mock vouchers data
    const allVouchers = ref([
      {
        id: 1,
        code: 'FREESHIP50K',
        description: 'Miễn phí vận chuyển đơn từ 500k',
        type: 'percentage',
        discountPercentage: 0,
        discountAmount: 50000,
        minOrder: 500000,
        maxDiscount: 50000,
        expiryDate: '31/12/2024',
        available: true,
      },
      {
        id: 2,
        code: 'GIAMGIA10',
        description: 'Giảm 10% cho đơn hàng từ 1 triệu',
        type: 'percentage',
        discountPercentage: 10,
        discountAmount: 0,
        minOrder: 1000000,
        maxDiscount: 500000,
        expiryDate: '31/12/2024',
        available: true,
      },
      {
        id: 3,
        code: 'GIAM200K',
        description: 'Giảm ngay 200k cho đơn từ 3 triệu',
        type: 'fixed',
        discountPercentage: 0,
        discountAmount: 200000,
        minOrder: 3000000,
        maxDiscount: 200000,
        expiryDate: '31/12/2024',
        available: true,
      },
      {
        id: 4,
        code: 'BLACKFRIDAY',
        description: 'Giảm 15% tối đa 1 triệu cho đơn từ 5 triệu',
        type: 'percentage',
        discountPercentage: 15,
        discountAmount: 0,
        minOrder: 5000000,
        maxDiscount: 1000000,
        expiryDate: '30/11/2024',
        available: true,
      },
      {
        id: 5,
        code: 'MEGA500K',
        description: 'Giảm 500k cho đơn hàng từ 10 triệu',
        type: 'fixed',
        discountPercentage: 0,
        discountAmount: 500000,
        minOrder: 10000000,
        maxDiscount: 500000,
        expiryDate: '31/12/2024',
        available: false, // Not eligible
      },
    ]);

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

    const isLoggedIn = computed(() => {
      return store && store.getters ? store.getters['auth/isLoggedIn'] : false;
    });

    const subtotal = computed(() => {
      return cartItems.value.reduce((sum, item) => {
        const price = item.giaSauKhiGiam || item.giaBan;
        return sum + (price * item.quantity);
      }, 0);
    });

    // Calculate discount based on applied voucher
    const discount = computed(() => {
      if (!appliedVoucher.value) return 0;

      const voucher = appliedVoucher.value;

      // Check if eligible
      if (voucher.minOrder && subtotal.value < voucher.minOrder) {
        return 0;
      }

      if (voucher.type === 'percentage') {
        const calculated = subtotal.value * voucher.discountPercentage / 100;
        return Math.min(calculated, voucher.maxDiscount || calculated);
      } else {
        return voucher.discountAmount;
      }
    });

    // Available vouchers (eligible)
    const availableVouchers = computed(() => {
      return allVouchers.value
        .filter(v => !v.minOrder || subtotal.value >= v.minOrder)
        .map(v => {
          let discountAmount = 0;
          if (v.type === 'percentage') {
            const calculated = subtotal.value * v.discountPercentage / 100;
            discountAmount = Math.min(calculated, v.maxDiscount || calculated);
          } else {
            discountAmount = v.discountAmount;
          }
          return { ...v, discountAmount };
        })
        .sort((a, b) => b.discountAmount - a.discountAmount);
    });

    // Unavailable vouchers (not eligible)
    const unavailableVouchers = computed(() => {
      return allVouchers.value
        .filter(v => v.minOrder && subtotal.value < v.minOrder)
        .map(v => ({
          ...v,
          discountAmount: v.type === 'percentage'
            ? v.minOrder * v.discountPercentage / 100
            : v.discountAmount
        }));
    });

    // Best voucher (highest discount)
    const bestVoucher = computed(() => {
      return availableVouchers.value.length > 0 ? availableVouchers.value[0] : null;
    });

    const shippingFee = computed(() => {
      if (subtotal.value >= 5000000) return 0;
      const method = shippingMethods.value.find(m => m.id === shippingMethod.value);
      return method ? method.fee : 0;
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
      wards.value = [
        { code: 'W1', name: 'Phường 1' },
        { code: 'W2', name: 'Phường 2' },
        { code: 'W3', name: 'Phường 3' },
      ];
      customerInfo.value.ward = '';
    };

    // Apply promo code manually
    const applyPromoCode = () => {
      if (!promoCode.value) return;

      applyingPromo.value = true;

      setTimeout(() => {
        const voucher = allVouchers.value.find(
          v => v.code.toUpperCase() === promoCode.value.toUpperCase()
        );

        if (voucher) {
          if (voucher.minOrder && subtotal.value < voucher.minOrder) {
            alert(`Đơn hàng tối thiểu ${formatPrice(voucher.minOrder)} để sử dụng mã này`);
          } else {
            appliedVoucher.value = voucher;
            showVoucherModal.value = false;
          }
        } else {
          alert('Mã giảm giá không hợp lệ');
        }

        applyingPromo.value = false;
      }, 500);
    };

    // Apply best voucher automatically
    const applyBestVoucher = () => {
      if (bestVoucher.value) {
        appliedVoucher.value = bestVoucher.value;
        promoCode.value = bestVoucher.value.code;
      }
    };

    // Select voucher from modal
    const selectVoucher = (voucher) => {
      appliedVoucher.value = voucher;
      promoCode.value = voucher.code;
      showVoucherModal.value = false;
    };

    // Remove voucher
    const removeVoucher = () => {
      appliedVoucher.value = null;
      promoCode.value = '';
    };

    const handleCheckout = async () => {
      if (!customerInfo.value.fullName || !customerInfo.value.phone || !customerInfo.value.email) {
        alert('Vui lòng điền đầy đủ thông tin');
        return;
      }

      processing.value = true;

      try {
        const orderData = {
          customer: customerInfo.value,
          items: cartItems.value,
          shippingMethod: shippingMethod.value,
          paymentMethod: paymentMethod.value,
          voucher: appliedVoucher.value,
          subtotal: subtotal.value,
          shippingFee: shippingFee.value,
          discount: discount.value,
          total: total.value,
        };

        await new Promise(resolve => setTimeout(resolve, 2000));

        orderCode.value = 'DH' + Date.now();
        showSuccessModal.value = true;
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
      showVoucherModal,
      orderCode,
      customerInfo,
      shippingMethod,
      paymentMethod,
      promoCode,
      appliedVoucher,
      applyingPromo,
      allVouchers,
      availableVouchers,
      unavailableVouchers,
      bestVoucher,
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
      applyPromoCode,
      applyBestVoucher,
      selectVoucher,
      removeVoucher,
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