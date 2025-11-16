<template>
  <div class="bg-gray-50 min-h-screen">
    <Header />

    <!-- Breadcrumb -->
    <div class="bg-white border-b">
      <div class="container mx-auto px-4 py-2">
        <div class="flex items-center text-xs text-gray-600">
          <router-link to="/" class="hover:text-blue-600 transition-colors">Trang chủ</router-link>
          <svg class="w-3 h-3 mx-1.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
          </svg>
          <router-link to="/iphone" class="hover:text-blue-600 transition-colors">iPhone</router-link>
          <svg class="w-3 h-3 mx-1.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
          </svg>
          <span class="text-gray-800 font-semibold">{{ product.tenSanPham }}</span>
        </div>
      </div>
    </div>

    <div class="container mx-auto px-4 py-6">
      <div class="grid lg:grid-cols-2 gap-6 mb-8">
        <!-- Product Images -->
        <div class="space-y-3">
          <!-- Main Image -->
          <div class="bg-white rounded-xl shadow-lg p-6 sticky top-4">
            <div class="relative aspect-square mb-3">
              <img
                :src="selectedImage"
                :alt="product.tenSanPham"
                class="w-full h-full object-contain"
              />
              

              <!-- Wishlist -->
              <button
                @click="toggleWishlist"
                class="absolute top-2 right-2 bg-white hover:bg-red-50 p-2 rounded-full shadow-md transition-all"
              >
                <svg
                  class="w-5 h-5 transition-colors"
                  :class="isWishlisted ? 'text-red-500 fill-current' : 'text-gray-400'"
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                </svg>
              </button>
            </div>

            <!-- Thumbnail Images -->
            <div class="grid grid-cols-5 gap-2">
              <div
                v-for="(image, index) in product.images"
                :key="index"
                @click="selectedImage = image"
                :class="[
                  'border-2 rounded-lg cursor-pointer overflow-hidden transition-all',
                  selectedImage === image ? 'border-blue-600 ring-1 ring-blue-300' : 'border-gray-200 hover:border-blue-300'
                ]"
              >
                <img :src="image" :alt="`${product.tenSanPham} ${index + 1}`" class="w-full h-full object-cover" />
              </div>
            </div>
          </div>
        </div>

        <!-- Product Info -->
        <div class="space-y-4">
          <!-- Title & Rating -->
          <div class="bg-white rounded-xl shadow-lg p-5">
            <h1 class="text-2xl font-black text-gray-900 mb-2">{{ product.tenSanPham }}</h1>

            <!-- Rating & Reviews -->
            <div class="flex flex-wrap items-center gap-3 mb-2 text-sm">
              <div class="flex items-center gap-1 bg-yellow-50 px-2 py-1 rounded-lg">
                <div class="flex">
                  <svg v-for="i in 5" :key="i" class="w-4 h-4" :class="i <= product.rating ? 'text-yellow-400 fill-current' : 'text-gray-300'" viewBox="0 0 20 20">
                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                  </svg>
                </div>
                <span class="text-xs font-bold">{{ product.rating }}</span>
              </div>
              <a href="#reviews" class="text-xs text-blue-600 hover:text-blue-700 font-semibold">({{ product.reviewCount }} đánh giá)</a>
              <span class="text-xs font-semibold text-green-600">✓ Đã bán {{ product.soldCount }}+</span>
            </div>

            <!-- SKU & Stock -->
            <div class="flex items-center gap-3 text-xs text-gray-600">
              <span>Mã: <strong class="text-gray-900">{{ selectedVariant.maSanPham }}</strong></span>
              <span>•</span>
              <span :class="selectedVariant.soLuongTonKho > 10 ? 'text-green-600' : 'text-orange-600'" class="font-semibold">
                {{ selectedVariant.soLuongTonKho > 0 ? `Còn ${selectedVariant.soLuongTonKho} sp` : 'Hết hàng' }}
              </span>
            </div>

            <!-- Price Section -->
            <div class="bg-gradient-to-r from-red-50 to-orange-50 rounded-xl p-4 mt-4 border border-red-200">
              <div class="flex items-center justify-between mb-2">
                <div class="flex items-baseline gap-2">
                  <span class="text-3xl font-black text-red-600">
                    {{ formatPrice(selectedVariant.giaSauKhiGiam || selectedVariant.giaBan) }}
                  </span>
                  <span v-if="selectedVariant.phanTramGiam > 0" class="text-lg text-gray-500 line-through">
                    {{ formatPrice(selectedVariant.giaBan) }}
                  </span>
                </div>
                <span v-if="selectedVariant.phanTramGiam > 0" class="bg-red-600 text-white px-3 py-1.5 rounded-lg font-bold text-sm">
                  -{{ selectedVariant.phanTramGiam }}%
                </span>
              </div>

              <div v-if="selectedVariant.phanTramGiam > 0" class="flex items-center gap-2 text-xs">
                <svg class="w-4 h-4 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <span class="font-bold text-green-700">
                  Tiết kiệm: {{ formatPrice(selectedVariant.giaBan - selectedVariant.giaSauKhiGiam) }}
                </span>
              </div>
            </div>
          </div>

          <!-- Storage Options -->
          <div class="bg-white rounded-xl shadow-lg p-4">
            <h3 class="text-sm font-bold text-gray-900 mb-3 flex items-center gap-1.5">
              <svg class="w-4 h-4 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 7v10c0 2.21 3.582 4 8 4s8-1.79 8-4V7M4 7c0 2.21 3.582 4 8 4s8-1.79 8-4M4 7c0-2.21 3.582-4 8-4s8 1.79 8 4" />
              </svg>
              Dung lượng
            </h3>
            <div class="grid grid-cols-3 gap-2">
              <button
                v-for="storage in storageOptions"
                :key="storage.id"
                @click="selectStorage(storage)"
                :class="[
                  'relative p-3 rounded-lg border-2 transition-all text-left',
                  selectedStorage === storage.id
                    ? 'border-blue-600 bg-blue-50'
                    : 'border-gray-200 hover:border-blue-300 bg-white'
                ]"
              >
                <div class="font-bold text-sm text-gray-900 mb-1">{{ storage.boNhoTrong }}</div>
                <div class="text-xs font-bold text-red-600">
                  {{ formatPrice(storage.giaSauKhiGiam || storage.giaBan) }}
                </div>

                <div v-if="storage.phanTramGiam > 0" class="mt-0.5">
                  <span class="text-xs text-gray-500 line-through">
                    {{ formatPrice(storage.giaBan) }}
                  </span>
                </div>

                <div v-if="selectedStorage === storage.id" class="absolute top-1.5 right-1.5">
                  <div class="bg-blue-600 rounded-full p-0.5">
                    <svg class="w-3 h-3 text-white" fill="currentColor" viewBox="0 0 20 20">
                      <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd" />
                    </svg>
                  </div>
                </div>

                <div v-if="storage.soLuongTonKho < 10" class="mt-1">
                  <span class="text-xs bg-orange-100 text-orange-700 px-1.5 py-0.5 rounded font-semibold">
                    Còn {{ storage.soLuongTonKho }}
                  </span>
                </div>
              </button>
            </div>
          </div>

          <!-- Color Options -->
          <div class="bg-white rounded-xl shadow-lg p-4">
            <h3 class="text-sm font-bold text-gray-900 mb-3 flex items-center gap-1.5">
              <svg class="w-4 h-4 text-purple-600" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M4 2a2 2 0 00-2 2v11a3 3 0 106 0V4a2 2 0 00-2-2H4zm1 14a1 1 0 100-2 1 1 0 000 2zm5-1.757l4.9-4.9a2 2 0 000-2.828L13.485 5.1a2 2 0 00-2.828 0L10 5.757v8.486zM16 18H9.071l6-6H16a2 2 0 012 2v2a2 2 0 01-2 2z" clip-rule="evenodd" />
              </svg>
              Màu sắc
            </h3>
            <div class="grid grid-cols-2 gap-2">
              <button
                v-for="color in colorOptions"
                :key="color.id"
                @click="selectColor(color)"
                :class="[
                  'relative p-3 rounded-lg border-2 transition-all text-left flex items-center gap-2',
                  selectedColor === color.id
                    ? 'border-purple-600 bg-purple-50'
                    : 'border-gray-200 hover:border-purple-300 bg-white'
                ]"
              >
                <div class="relative">
                  <div
                    class="w-8 h-8 rounded-full border-2 border-gray-300"
                    :style="{ backgroundColor: color.colorCode }"
                  ></div>
                  <div v-if="selectedColor === color.id" class="absolute -top-1 -right-1 bg-purple-600 rounded-full p-0.5">
                    <svg class="w-2.5 h-2.5 text-white" fill="currentColor" viewBox="0 0 20 20">
                      <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd" />
                    </svg>
                  </div>
                </div>

                <div class="flex-1">
                  <div class="font-bold text-xs text-gray-900">{{ color.mauSac }}</div>
                </div>
              </button>
            </div>
          </div>

          <!-- Quantity & Actions -->
          <div class="bg-white rounded-xl shadow-lg p-4">
            <div class="flex items-center gap-3 mb-4">
              <span class="text-sm font-bold text-gray-900">Số lượng:</span>
              <div class="flex items-center bg-gray-50 border-2 border-gray-200 rounded-lg overflow-hidden">
                <button
                  @click="decreaseQuantity"
                  :disabled="quantity <= 1"
                  class="px-3 py-2 hover:bg-gray-200 disabled:opacity-50 transition-colors"
                >
                  <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="3" d="M20 12H4" />
                  </svg>
                </button>
                <input
                  v-model.number="quantity"
                  type="number"
                  min="1"
                  :max="selectedVariant.soLuongTonKho"
                  class="w-16 text-center bg-transparent py-2 font-bold text-sm text-gray-900 focus:outline-none"
                />
                <button
                  @click="increaseQuantity"
                  :disabled="quantity >= selectedVariant.soLuongTonKho"
                  class="px-3 py-2 hover:bg-gray-200 disabled:opacity-50 transition-colors"
                >
                  <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="3" d="M12 4v16m8-8H4" />
                  </svg>
                </button>
              </div>
              <span class="text-xs text-gray-600">
                Còn: <strong class="text-orange-600">{{ selectedVariant.soLuongTonKho }}</strong>
              </span>
            </div>

            <!-- Total Price -->
            <div class="bg-blue-50 rounded-lg p-3 mb-4">
              <div class="flex items-center justify-between">
                <span class="text-xs font-semibold text-gray-700">Tạm tính:</span>
                <span class="text-xl font-black text-blue-600">
                  {{ formatPrice((selectedVariant.giaSauKhiGiam || selectedVariant.giaBan) * quantity) }}
                </span>
              </div>
            </div>

            <!-- Action Buttons -->
            <div class="grid grid-cols-2 gap-2">
              <button
                @click="addToCart"
                :disabled="selectedVariant.soLuongTonKho === 0"
                class="flex items-center justify-center gap-1.5 bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 rounded-lg transition-all text-sm disabled:opacity-50"
              >
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
                </svg>
                Thêm giỏ
              </button>
              <button
                @click="buyNow"
                :disabled="selectedVariant.soLuongTonKho === 0"
                class="flex items-center justify-center gap-1.5 bg-gradient-to-r from-red-600 to-orange-600 hover:from-red-700 hover:to-orange-700 text-white font-bold py-3 rounded-lg transition-all text-sm disabled:opacity-50"
              >
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
                </svg>
                Mua ngay
              </button>
            </div>
          </div>

          <!-- Promotions -->
          <div class="bg-gradient-to-r from-orange-50 to-red-50 rounded-xl p-4 border border-orange-200">
            <h3 class="text-sm font-bold text-gray-900 mb-3 flex items-center gap-1.5">
              <svg class="w-4 h-4 text-orange-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v13m0-13V6a2 2 0 112 2h-2zm0 0V5.5A2.5 2.5 0 109.5 8H12zm-7 4h14M5 12a2 2 0 110-4h14a2 2 0 110 4M5 12v7a2 2 0 002 2h10a2 2 0 002-2v-7" />
              </svg>
              Ưu đãi đặc biệt
            </h3>
            <div class="space-y-2">
              <div class="flex items-start gap-2 bg-white rounded-lg p-2 text-xs">
                <svg class="w-3.5 h-3.5 text-green-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <p class="text-gray-700">
                  <strong>Trả góp 0%</strong> qua thẻ tín dụng
                </p>
              </div>
              <div class="flex items-start gap-2 bg-white rounded-lg p-2 text-xs">
                <svg class="w-3.5 h-3.5 text-green-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <p class="text-gray-700">
                  <strong>Miễn phí ship</strong> đơn từ 5 triệu
                </p>
              </div>
              <div class="flex items-start gap-2 bg-white rounded-lg p-2 text-xs">
                <svg class="w-3.5 h-3.5 text-green-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <p class="text-gray-700">
                  <strong>Bảo hành 12 tháng</strong> chính hãng
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Product Details Tabs -->
      <div class="bg-white rounded-xl shadow-lg mb-6">
        <!-- Tab Headers -->
        <div class="border-b">
          <div class="flex gap-1 p-2">
            <button
              v-for="tab in tabs"
              :key="tab.id"
              @click="activeTab = tab.id"
              :class="[
                'px-4 py-2 rounded-lg font-bold transition-all text-sm',
                activeTab === tab.id
                  ? 'bg-blue-600 text-white'
                  : 'text-gray-600 hover:bg-gray-100'
              ]"
            >
              {{ tab.name }}
            </button>
          </div>
        </div>


        <!-- Tab Content -->
        <div class="p-6">
          <!-- Specifications Tab -->
          <div v-if="activeTab === 'specs'" class="space-y-4">
            <h3 class="text-2xl font-black text-gray-900 mb-4">Thông số kỹ thuật</h3>

            <div class="grid md:grid-cols-2 gap-4">
              <!-- Left Column -->
              <div class="space-y-3">
                <div class="bg-gray-50 rounded-lg p-3">
                  <h4 class="font-bold text-gray-900 mb-2 flex items-center gap-1.5">
                    <svg class="w-5 h-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.75 17L9 20l-1 1h8l-1-1-.75-3M3 13h18M5 17h14a2 2 0 002-2V5a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                    </svg>
                    Màn hình
                  </h4>
                  <div class="space-y-1.5 text-xs">
                    <div class="flex justify-between">
                      <span class="text-gray-600">Kích thước:</span>
                      <span class="font-semibold text-gray-900">6.9 inch</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Công nghệ:</span>
                      <span class="font-semibold text-gray-900">Super Retina XDR OLED</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Độ phân giải:</span>
                      <span class="font-semibold text-gray-900">2868 x 1320 pixels</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Tần số quét:</span>
                      <span class="font-semibold text-gray-900">120Hz ProMotion</span>
                    </div>
                  </div>
                </div>

                <div class="bg-gray-50 rounded-lg p-3">
                  <h4 class="font-bold text-sm text-gray-900 mb-2 flex items-center gap-1.5">
                    <svg class="w-5 h-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 9a2 2 0 012-2h.93a2 2 0 001.664-.89l.812-1.22A2 2 0 0110.07 4h3.86a2 2 0 011.664.89l.812 1.22A2 2 0 0018.07 7H19a2 2 0 012 2v9a2 2 0 01-2 2H5a2 2 0 01-2-2V9z" />
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 13a3 3 0 11-6 0 3 3 0 016 0z" />
                    </svg>
                    Camera
                  </h4>
                  <div class="space-y-1.5 text-xs">
                  <div class="flex justify-between">
                      <span class="text-gray-600">Camera sau:</span>
                      <span class="font-semibold text-gray-900">48MP + 48MP + 12MP</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Camera trước:</span>
                      <span class="font-semibold text-gray-900">12MP</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Quay video:</span>
                      <span class="font-semibold text-gray-900">4K@60fps, ProRes</span>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Right Column -->
              <div class="space-y-3">
                <div class="bg-gray-50 rounded-lg p-3">
                  <h4 class="font-bold text-sm text-gray-900 mb-2 flex items-center gap-1.5">
                  <svg class="w-5 h-5 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 3v2m6-2v2M9 19v2m6-2v2M5 9H3m2 6H3m18-6h-2m2 6h-2M7 19h10a2 2 0 002-2V7a2 2 0 00-2-2H7a2 2 0 00-2 2v10a2 2 0 002 2zM9 9h6v6H9V9z" />
                    </svg>
                    Vi xử lý & Hiệu năng
                  </h4>
                  <div class="space-y-1.5 text-xs">
                  <div class="flex justify-between">
                      <span class="text-gray-600">Chip:</span>
                      <span class="font-semibold text-gray-900">Apple A18 Pro</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">RAM:</span>
                      <span class="font-semibold text-gray-900">8GB</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Bộ nhớ:</span>
                      <span class="font-semibold text-gray-900">256GB / 512GB / 1TB</span>
                    </div>
                  </div>
                </div>

                <div class="bg-gray-50 rounded-lg p-3">
                  <h4 class="font-bold text-sm text-gray-900 mb-2 flex items-center gap-1.5">
                    <svg class="w-5 h-5 text-yellow-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
                    </svg>
                    Pin & Sạc
                  </h4>
                  <div class="space-y-1.5 text-xs">
                  <div class="flex justify-between">
                      <span class="text-gray-600">Dung lượng:</span>
                      <span class="font-semibold text-gray-900">4685 mAh</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Sạc nhanh:</span>
                      <span class="font-semibold text-gray-900">27W</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Sạc không dây:</span>
                      <span class="font-semibold text-gray-900">MagSafe 25W</span>
                    </div>
                  </div>
                </div>

                <div class="bg-gray-50 rounded-lg p-3">
                  <h4 class="font-bold text-sm text-gray-900 mb-2 flex items-center gap-1.5">
                    <svg class="w-5 h-5 text-red-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
                    </svg>
                    Thiết kế & Trọng lượng
                  </h4>
                  <div class="space-y-1.5 text-xs">
                  <div class="flex justify-between">
                      <span class="text-gray-600">Chất liệu:</span>
                      <span class="font-semibold text-gray-900">Titan</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Trọng lượng:</span>
                      <span class="font-semibold text-gray-900">227g</span>
                    </div>
                    <div class="flex justify-between">
                      <span class="text-gray-600">Kháng nước:</span>
                      <span class="font-semibold text-gray-900">IP68</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- Description Tab -->
          <!-- Description Tab -->
          <div v-if="activeTab === 'description'" class="prose max-w-none">
            <h3 class="text-lg font-black text-gray-900 mb-4">Mô tả sản phẩm</h3>

            <div class="space-y-4 text-gray-700 leading-relaxed">
              <p class="text-sm">
                <strong>iPhone 16 Pro Max</strong> là chiếc smartphone cao cấp nhất trong dòng sản phẩm iPhone 16 Series của Apple,
                mang đến những cải tiến vượt trội về hiệu năng, camera và thiết kế.
              </p>

              <div class="bg-blue-50 border-l-4 border-blue-600 p-4 rounded-r-lg">
                <h4 class="font-bold text-sm text-gray-900 mb-2">✨ Điểm nổi bật</h4>
                <ul class="space-y-1.5 text-xs">
                  <li class="flex items-start gap-1.5">
                    <svg class="w-4 h-4 text-blue-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                    </svg>
                    Chip A18 Pro mạnh mẽ nhất từ trước đến nay
                  </li>
                  <li class="flex items-start gap-1.5">
                    <svg class="w-4 h-4 text-blue-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                    </svg>
                    Hệ thống camera 48MP với khả năng quay ProRes Log
                  </li>
                  <li class="flex items-start gap-1.5">
                    <svg class="w-4 h-4 text-blue-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                    </svg>
                    Màn hình Super Retina XDR 6.9 inch với ProMotion 120Hz
                  </li>
                  <li class="flex items-start gap-1.5">
                    <svg class="w-4 h-4 text-blue-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                    </svg>
                    Thiết kế Titan cao cấp, nhẹ và bền bỉ
                  </li>
                  <li class="flex items-start gap-1.5">
                    <svg class="w-4 h-4 text-blue-600 mt-0.5 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                    </svg>
                    Pin lớn với thời gian sử dụng lên đến 29 giờ
                  </li>
                </ul>
              </div>

              <img
                src="https://images.unsplash.com/photo-1696446702874-524dc1508242?w=1200"
                alt="iPhone 16 Pro Max"
                class="w-full rounded-xl shadow-lg"
              />

              <h4 class="text-sm font-bold text-gray-900 mt-5">🎨 Thiết kế Titan Đẳng Cấp</h4>
              <p class="text-xs">
                iPhone 16 Pro Max sở hữu thiết kế khung viền titan chuẩn hàng không vũ trụ,
                mang lại sự sang trọng và bền bỉ vượt trội. Với trọng lượng chỉ 227g,
                đây là chiếc iPhone Pro Max nhẹ nhất từ trước đến nay.
              </p>

              <h4 class="text-sm font-bold text-gray-900 mt-5">📸 Hệ thống Camera Chuyên Nghiệp</h4>
              <p class="text-xs">
                Được trang bị hệ thống camera sau ba ống kính với cảm biến chính 48MP,
                iPhone 16 Pro Max có khả năng chụp ảnh và quay video chất lượng điện ảnh.
                Tính năng ProRes Log và Apple Log giúp người dùng có thể chỉnh màu chuyên nghiệp
                như các nhà làm phim.
              </p>

              <h4 class="text-sm font-bold text-gray-900 mt-5">⚡ Hiệu năng Vượt Trội</h4>
              <p class="text-xs">
                Chip A18 Pro với CPU 6 nhân và GPU 6 nhân mang lại hiệu năng vượt trội,
                giúp xử lý mọi tác vụ nặng từ chơi game đến chỉnh sửa video 4K một cách mượt mà.
                Neural Engine 16 nhân hỗ trợ các tính năng AI tiên tiến.
              </p>
            </div>
          </div>

          <!-- Reviews Tab -->
          <div v-if="activeTab === 'reviews'">
            <div class="flex items-center justify-between mb-5">
              <h3 class="text-lg font-black text-gray-900">Đánh giá sản phẩm</h3>
              <button class="bg-blue-600 hover:bg-blue-700 text-white font-bold px-4 py-2 rounded-lg transition-all text-sm">
                Viết đánh giá
              </button>
            </div>

            <!-- Rating Summary -->
            <div class="bg-gradient-to-r from-yellow-50 to-orange-50 rounded-xl p-5 mb-5 border-2 border-yellow-200">
              <div class="grid md:grid-cols-2 gap-5">
                <div class="text-center">
                  <div class="text-4xl font-black text-gray-900 mb-1.5">{{ product.rating }}</div>
                  <div class="flex items-center justify-center gap-0.5 mb-1.5">
                    <svg v-for="i in 5" :key="i" class="w-6 h-6" :class="i <= Math.floor(product.rating) ? 'text-yellow-400 fill-current' : 'text-gray-300'" viewBox="0 0 20 20">
                      <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                    </svg>
                  </div>
                  <p class="text-xs text-gray-600 font-semibold">{{ product.reviewCount }} đánh giá</p>
                </div>

                <div class="space-y-1.5">
                  <div v-for="star in [5,4,3,2,1]" :key="star" class="flex items-center gap-2">
                    <span class="text-xs font-semibold text-gray-700 w-10">{{ star }} sao</span>
                    <div class="flex-1 bg-gray-200 rounded-full h-2 overflow-hidden">
                      <div
                        class="bg-yellow-400 h-full rounded-full transition-all"
                        :style="{ width: `${getStarPercentage(star)}%` }"
                      ></div>
                    </div>
                    <span class="text-xs text-gray-600 w-10 text-right font-semibold">{{ getStarPercentage(star) }}%</span>
                  </div>
                </div>
              </div>
            </div>

            <!-- Review List -->
            <div class="space-y-4">
              <div
                v-for="review in reviews"
                :key="review.id"
                class="bg-white border-2 border-gray-200 rounded-xl p-4 hover:border-blue-300 transition-all hover:shadow-md"
              >
                <div class="flex items-start gap-3 mb-3">
                  <img
                    :src="review.avatar"
                    :alt="review.name"
                    class="w-10 h-10 rounded-full border-2 border-gray-200"
                  />
                  <div class="flex-1">
                    <div class="flex items-center justify-between mb-1.5">
                      <h4 class="font-bold text-sm text-gray-900">{{ review.name }}</h4>
                      <span class="text-xs text-gray-500">{{ review.date }}</span>
                    </div>
                    <div class="flex items-center gap-1.5 mb-1.5">
                      <div class="flex">
                        <svg v-for="i in 5" :key="i" class="w-3.5 h-3.5" :class="i <= review.rating ? 'text-yellow-400 fill-current' : 'text-gray-300'" viewBox="0 0 20 20">
                          <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                        </svg>
                      </div>
                      <span v-if="review.verified" class="text-xs bg-green-100 text-green-700 px-1.5 py-0.5 rounded-full font-semibold">
              ✓ Đã mua
            </span>
                    </div>
                  </div>
                </div>

                <p class="text-xs text-gray-700 leading-relaxed mb-3">{{ review.comment }}</p>

                <!-- Review Images -->
                <div v-if="review.images" class="grid grid-cols-4 gap-1.5 mb-3">
                  <img
                    v-for="(img, idx) in review.images"
                    :key="idx"
                    :src="img"
                    alt="Review image"
                    class="w-full aspect-square object-cover rounded-lg cursor-pointer hover:opacity-75 transition-opacity border border-gray-200"
                  />
                </div>

                <!-- Helpful -->
                <div class="flex items-center gap-3 pt-3 border-t">
                  <button class="text-xs text-gray-600 hover:text-blue-600 font-semibold flex items-center gap-1.5">
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5" />
                    </svg>
                    Hữu ích ({{ review.helpful }})
                  </button>
                  <button class="text-xs text-gray-600 hover:text-blue-600 font-semibold">
                    Trả lời
                  </button>
                </div>
              </div>
            </div>

            <!-- Load More -->
            <div class="text-center mt-5">
              <button class="bg-gray-100 hover:bg-gray-200 text-gray-700 font-bold px-6 py-2.5 rounded-lg transition-all text-sm">
                Xem thêm đánh giá
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Related Products - ENHANCED -->
      <div>
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-2xl font-black text-gray-900 flex items-center gap-2">
            <svg class="w-7 h-7 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4" />
            </svg>
            Sản phẩm tương tự
            <span class="text-sm font-normal text-gray-500">({{ relatedProducts.length }} sản phẩm)</span>
          </h3>
          <router-link to="/iphone" class="text-blue-600 hover:text-blue-700 font-bold text-sm flex items-center gap-1 bg-blue-50 px-4 py-2 rounded-lg hover:bg-blue-100 transition-colors">
            Xem tất cả
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </router-link>
        </div>

        <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-5 gap-4">
          <ProductCard
            v-for="relatedProduct in relatedProducts"
            :key="relatedProduct.id"
            :product="relatedProduct"
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
          'fixed bottom-4 right-4 px-6 py-4 rounded-xl shadow-2xl text-white z-50 flex items-center gap-3',
          toast.type === 'success' ? 'bg-green-500' : 'bg-red-500'
        ]"
      >
        <svg v-if="toast.type === 'success'" class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
        </svg>
        <span class="font-semibold">{{ toast.message }}</span>
      </div>
    </Transition>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios';
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import ProductCard from './components/ProductCard.vue';

export default {
  name: 'ProductDetail',
  components: {
    Header,
    Footer,
    ProductCard,
  },
  setup() {
    const router = useRouter();
    const route = useRoute();

    const product = ref({
      id: 1,
      tenSanPham: 'iPhone 16 Pro Max',
      rating: 4.8,
      reviewCount: 1248,
      soldCount: 3500,
      phanTramGiam: 7,
      isNew: true,
      isHot: true,
      images: [
        'https://images.unsplash.com/photo-1696446702874-524dc1508242?w=600',
        'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=600',
        'https://images.unsplash.com/photo-1632633173522-43f28ab4e898?w=600',
        'https://images.unsplash.com/photo-1663499482523-1c0c1baa4d7d?w=600',
        'https://images.unsplash.com/photo-1678911820864-e5c67189c221?w=600',
      ],
    });

    const storageOptions = ref([
      { id: 1, boNhoTrong: '256GB', giaBan: 34990000, giaSauKhiGiam: 32990000, phanTramGiam: 6, soLuongTonKho: 50 },
      { id: 2, boNhoTrong: '512GB', giaBan: 40990000, giaSauKhiGiam: 38990000, phanTramGiam: 5, soLuongTonKho: 30 },
      { id: 3, boNhoTrong: '1TB', giaBan: 46990000, giaSauKhiGiam: 44990000, phanTramGiam: 4, soLuongTonKho: 8 },
    ]);

    const colorOptions = ref([
      { id: 1, mauSac: 'Titan Đen', colorCode: '#2C3E50', maSanPham: 'IP16PM-256-BK' },
      { id: 2, mauSac: 'Titan Tự Nhiên', colorCode: '#A8957D', maSanPham: 'IP16PM-256-NT' },
      { id: 3, mauSac: 'Titan Trắng', colorCode: '#E8E8E8', maSanPham: 'IP16PM-256-WH' },
      { id: 4, mauSac: 'Titan Sa Mạc', colorCode: '#D4AF7A', maSanPham: 'IP16PM-256-DS' },
    ]);

    const reviews = ref([
      {
        id: 1,
        name: 'Nguyễn Văn A',
        avatar: 'https://i.pravatar.cc/150?img=1',
        rating: 5,
        date: '10/11/2024',
        comment: 'Máy đẹp, chạy mượt, camera chụp ảnh cực đỉnh. Pin trâu lắm, dùng cả ngày không cần sạc. Rất hài lòng với sản phẩm!',
        images: [
          'https://images.unsplash.com/photo-1696446702874-524dc1508242?w=200',
          'https://images.unsplash.com/photo-1678652197831-2d180705cd2c?w=200',
        ],
        verified: true,
        helpful: 145,
      },
      {
        id: 2,
        name: 'Trần Thị B',
        avatar: 'https://i.pravatar.cc/150?img=2',
        rating: 5,
        date: '08/11/2024',
        comment: 'Sản phẩm tuyệt vời, đáng đồng tiền bát gạo. Giao hàng nhanh, đóng gói cẩn thận. TopZone phục vụ rất chu đáo!',
        verified: true,
        helpful: 89,
      },
      {
        id: 3,
        name: 'Lê Văn C',
        avatar: 'https://i.pravatar.cc/150?img=3',
        rating: 4,
        date: '05/11/2024',
        comment: 'Máy ok, nhưng giá hơi cao. Nếu có khuyến mãi tốt thì đáng mua lắm.',
        verified: true,
        helpful: 56,
      },
    ]);

    const relatedProducts = ref([]);

    const selectedImage = ref('');
    const selectedStorage = ref(1);
    const selectedColor = ref(1);
    const quantity = ref(1);
    const isWishlisted = ref(false);
    const activeTab = ref('specs');

    const tabs = ref([
      { id: 'specs', name: 'Thông số kỹ thuật' },
      { id: 'description', name: 'Mô tả sản phẩm' },
      { id: 'reviews', name: 'Đánh giá' },
    ]);

    const toast = ref({
      show: false,
      message: '',
      type: 'success',
    });

    const selectedVariant = computed(() => {
      const storage = storageOptions.value.find(s => s.id === selectedStorage.value);
      const color = colorOptions.value.find(c => c.id === selectedColor.value);
      return {
        ...storage,
        ...color,
      };
    });

    const formatPrice = (price) => {
      return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
      }).format(price);
    };

    const selectStorage = (storage) => {
      selectedStorage.value = storage.id;
    };

    const selectColor = (color) => {
      selectedColor.value = color.id;
    };

    const increaseQuantity = () => {
      if (quantity.value < selectedVariant.value.soLuongTonKho) {
        quantity.value++;
      }
    };

    const decreaseQuantity = () => {
      if (quantity.value > 1) {
        quantity.value--;
      }
    };

    const toggleWishlist = () => {
      isWishlisted.value = !isWishlisted.value;
      showToast(
        isWishlisted.value ? 'Đã thêm vào yêu thích' : 'Đã xóa khỏi yêu thích',
        'success'
      );
    };

    const addToCart = () => {
      if (selectedVariant.value.soLuongTonKho === 0) {
        showToast('Sản phẩm đã hết hàng', 'error');
        return;
      }
      showToast(`Đã thêm ${quantity.value} sản phẩm vào giỏ hàng`, 'success');
    };

    const buyNow = () => {
      if (selectedVariant.value.soLuongTonKho === 0) {
        showToast('Sản phẩm đã hết hàng', 'error');
        return;
      }
      addToCart();
      router.push('/checkout');
    };

    const getStarPercentage = (star) => {
      const percentages = { 5: 75, 4: 18, 3: 5, 2: 1, 1: 1 };
      return percentages[star] || 0;
    };

    const showToast = (message, type = 'success') => {
      toast.value = { show: true, message, type };
      setTimeout(() => {
        toast.value.show = false;
      }, 3000);
    };

    const fetchRelatedProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/chi-tiet-san-pham/ban-hang?limit=5');
        relatedProducts.value = response.data.slice(0, 5);
      } catch (error) {
        console.error('Error fetching related products:', error);
      }
    };

    onMounted(() => {
      selectedImage.value = product.value.images[0];
      fetchRelatedProducts();
    });

    return {
      product,
      storageOptions,
      colorOptions,
      reviews,
      relatedProducts,
      selectedImage,
      selectedStorage,
      selectedColor,
      quantity,
      isWishlisted,
      activeTab,
      tabs,
      toast,
      selectedVariant,
      formatPrice,
      selectStorage,
      selectColor,
      increaseQuantity,
      decreaseQuantity,
      toggleWishlist,
      addToCart,
      buyNow,
      getStarPercentage,
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
  transform: translateX(100px);
}

.prose {
  @apply text-gray-700;
}

.prose h4 {
  @apply text-xl font-bold text-gray-900 mt-6 mb-3;
}

.prose p {
  @apply mb-4 leading-relaxed;
}

.prose ul {
  @apply space-y-2;
}

.prose li {
  @apply flex items-start gap-2;
}
</style>