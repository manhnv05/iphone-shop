<template>
  <div class="bg-gray-50 min-h-screen">
    <ClientHeader />

    <!-- Hero Section - Compact Version -->
    <div class="relative bg-gradient-to-br from-blue-600 via-indigo-700 to-purple-800 text-white overflow-hidden">
      <!-- Animated Background -->
      <div class="absolute inset-0 opacity-20">
        <div class="absolute top-0 left-0 w-96 h-96 bg-white rounded-full mix-blend-overlay filter blur-3xl animate-blob"></div>
        <div class="absolute top-0 right-0 w-96 h-96 bg-purple-300 rounded-full mix-blend-overlay filter blur-3xl animate-blob animation-delay-2000"></div>
        <div class="absolute bottom-0 left-1/2 w-96 h-96 bg-pink-300 rounded-full mix-blend-overlay filter blur-3xl animate-blob animation-delay-4000"></div>
      </div>

      <div class="container mx-auto px-4 py-12 relative z-10">
        <div class="text-center max-w-4xl mx-auto">
          <!-- Badge -->
          <div class="inline-flex items-center gap-2 bg-white/20 backdrop-blur-sm px-4 py-2 rounded-full border border-white/30 mb-6">
            <svg class="w-4 h-4 text-yellow-300" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            <span class="font-semibold text-sm">Đối tác ủy quyền Apple</span>
          </div>

          <h1 class="text-3xl lg:text-5xl font-black mb-4 leading-tight">
            Về <span class="text-yellow-300">TopZone</span>
          </h1>
          <div class="h-1 w-24 bg-gradient-to-r from-yellow-300 to-pink-400 rounded-full mx-auto mb-6"></div>

          <p class="text-base lg:text-lg text-blue-100 mb-8 leading-relaxed">
            Hệ thống bán lẻ iPhone & sản phẩm Apple chính hãng uy tín hàng đầu Việt Nam
          </p>

          <!-- Stats - Compact -->
          <div class="grid grid-cols-3 gap-3 md:gap-6 max-w-2xl mx-auto">
            <div class="bg-white/10 backdrop-blur-md rounded-xl p-4 border border-white/20 hover:bg-white/20 transition-all">
              <div class="text-2xl lg:text-3xl font-black mb-1">10+</div>
              <div class="text-xs lg:text-sm text-blue-200">Năm</div>
            </div>
            <div class="bg-white/10 backdrop-blur-md rounded-xl p-4 border border-white/20 hover:bg-white/20 transition-all">
              <div class="text-2xl lg:text-3xl font-black mb-1">50+</div>
              <div class="text-xs lg:text-sm text-blue-200">Cửa hàng</div>
            </div>
            <div class="bg-white/10 backdrop-blur-md rounded-xl p-4 border border-white/20 hover:bg-white/20 transition-all">
              <div class="text-2xl lg:text-3xl font-black mb-1">1M+</div>
              <div class="text-xs lg:text-sm text-blue-200">Khách hàng</div>
            </div>
          </div>
        </div>
      </div>

      <!-- Wave Divider -->
      <div class="absolute bottom-0 left-0 w-full">
        <svg class="w-full h-12" viewBox="0 0 1440 100" fill="none" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="none">
          <path d="M0,50 C480,100 960,0 1440,50 L1440,100 L0,100 Z" fill="#F9FAFB"/>
        </svg>
      </div>
    </div>

    <!-- Story Section - Redesigned -->
    <div class="container mx-auto px-4 py-16">
      <div class="max-w-6xl mx-auto">
        <!-- Main Story -->
        <div class="grid md:grid-cols-2 gap-12 items-center mb-20">
          <div class="order-2 md:order-1">
            <span class="text-blue-600 font-bold text-xs uppercase tracking-wider">Câu chuyện của chúng tôi</span>
            <h2 class="text-3xl lg:text-4xl font-black text-gray-900 mb-6 mt-3">
              Hành trình 10 năm<br/>
              phát triển & đổi mới
            </h2>
            <div class="space-y-4 text-gray-600 leading-relaxed text-sm lg:text-base">
              <p>
                <strong class="text-gray-800">TopZone</strong> được thành lập vào năm 2013 với tầm nhìn trở thành
                hệ thống bán lẻ iPhone và sản phẩm Apple uy tín nhất tại Việt Nam.
              </p>
              <p>
                Khởi đầu chỉ với một cửa hàng nhỏ tại TP.HCM, chúng tôi đã không ngừng nỗ lực để mang đến
                những sản phẩm công nghệ chính hãng với chất lượng dịch vụ tốt nhất.
              </p>
              <p>
                Sau hơn <strong class="text-blue-600">10 năm phát triển</strong>, TopZone hiện có mặt tại
                <strong class="text-blue-600">50+ cửa hàng</strong> trên toàn quốc và đã phục vụ hơn
                <strong class="text-blue-600">1 triệu khách hàng</strong>.
              </p>
            </div>

            <!-- Achievement badges -->
            <div class="grid grid-cols-2 gap-3 mt-6">
              <div class="bg-gradient-to-br from-blue-50 to-indigo-50 rounded-lg p-3 border border-blue-100">
                <div class="flex items-center gap-2">
                  <div class="bg-blue-500 rounded-lg p-1.5">
                    <svg class="w-4 h-4 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </div>
                  <div>
                    <div class="text-xs font-bold text-gray-800">Đối tác AAR</div>
                    <div class="text-xs text-gray-600">Apple Reseller</div>
                  </div>
                </div>
              </div>

              <div class="bg-gradient-to-br from-green-50 to-emerald-50 rounded-lg p-3 border border-green-100">
                <div class="flex items-center gap-2">
                  <div class="bg-green-500 rounded-lg p-1.5">
                    <svg class="w-4 h-4 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
                    </svg>
                  </div>
                  <div>
                    <div class="text-xs font-bold text-gray-800">Bảo hành</div>
                    <div class="text-xs text-gray-600">Chính hãng Apple</div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="order-1 md:order-2">
            <div class="relative">
              <div class="absolute -top-4 -left-4 w-full h-full bg-gradient-to-br from-blue-400 to-indigo-500 rounded-2xl transform rotate-3"></div>
              <img
                src="https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=800&h=600&fit=crop"
                alt="TopZone Store"
                class="relative rounded-2xl shadow-2xl w-full"
              />
              <!-- Floating badge -->
              <div class="absolute -bottom-4 -right-4 bg-white rounded-xl p-4 shadow-2xl">
                <div class="text-center">
                  <div class="text-2xl font-black text-blue-600">4.9/5</div>
                  <div class="text-xs text-gray-600">Đánh giá</div>
                  <div class="flex gap-1 mt-1 justify-center">
                    <svg v-for="i in 5" :key="i" class="w-4 h-4 text-yellow-400" fill="currentColor" viewBox="0 0 20 20">
                      <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                    </svg>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Values Section - Modern Cards -->
        <div class="mb-20">
          <div class="text-center mb-12">
            <span class="text-blue-600 font-bold text-xs uppercase tracking-wider">Giá trị cốt lõi</span>
            <h2 class="text-3xl lg:text-4xl font-black text-gray-900 mt-3 mb-3">
              Cam kết của chúng tôi
            </h2>
            <p class="text-gray-600 max-w-2xl mx-auto text-sm lg:text-base">
              Những giá trị mà TopZone luôn đặt lên hàng đầu trong mọi hoạt động kinh doanh
            </p>
          </div>

          <div class="grid md:grid-cols-3 gap-6">
            <div class="group bg-white rounded-2xl p-6 shadow-lg hover:shadow-xl transition-all border-2 border-transparent hover:border-blue-500 transform hover:-translate-y-1">
              <div class="bg-gradient-to-br from-blue-500 to-indigo-600 w-12 h-12 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
                <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
              <h3 class="text-xl font-black text-gray-900 mb-3">Chính hãng 100%</h3>
              <p class="text-gray-600 leading-relaxed text-sm">
                Tất cả sản phẩm tại TopZone đều là hàng chính hãng Apple, có đầy đủ tem bảo hành và hóa đơn VAT.
              </p>
              <div class="mt-4 flex items-center text-blue-600 font-semibold text-sm group-hover:gap-2 gap-1 transition-all">
                <span>Tìm hiểu thêm</span>
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                </svg>
              </div>
            </div>

            <div class="group bg-white rounded-2xl p-6 shadow-lg hover:shadow-xl transition-all border-2 border-transparent hover:border-green-500 transform hover:-translate-y-1">
              <div class="bg-gradient-to-br from-green-500 to-emerald-600 w-12 h-12 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
                <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
              <h3 class="text-xl font-black text-gray-900 mb-3">Giá cả hợp lý</h3>
              <p class="text-gray-600 leading-relaxed text-sm">
                Cam kết mức giá tốt nhất thị trường với nhiều chương trình ưu đãi, trả góp 0% lãi suất hấp dẫn.
              </p>
              <div class="mt-4 flex items-center text-green-600 font-semibold text-sm group-hover:gap-2 gap-1 transition-all">
                <span>Xem ưu đãi</span>
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                </svg>
              </div>
            </div>

            <div class="group bg-white rounded-2xl p-6 shadow-lg hover:shadow-xl transition-all border-2 border-transparent hover:border-purple-500 transform hover:-translate-y-1">
              <div class="bg-gradient-to-br from-purple-500 to-pink-600 w-12 h-12 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform">
                <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18.364 5.636l-3.536 3.536m0 5.656l3.536 3.536M9.172 9.172L5.636 5.636m3.536 9.192l-3.536 3.536M21 12a9 9 0 11-18 0 9 9 0 0118 0zm-5 0a4 4 0 11-8 0 4 4 0 018 0z" />
                </svg>
              </div>
              <h3 class="text-xl font-black text-gray-900 mb-3">Hỗ trợ tận tâm</h3>
              <p class="text-gray-600 leading-relaxed text-sm">
                Đội ngũ nhân viên chuyên nghiệp, được đào tạo bài bản, tư vấn nhiệt tình và hỗ trợ khách hàng 24/7.
              </p>
              <div class="mt-4 flex items-center text-purple-600 font-semibold text-sm group-hover:gap-2 gap-1 transition-all">
                <span>Liên hệ ngay</span>
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                </svg>
              </div>
            </div>
          </div>
        </div>

        <!-- Team Section - Modern Design -->
        <div class="mb-20">
          <div class="text-center mb-12">
            <span class="text-blue-600 font-bold text-xs uppercase tracking-wider">Đội ngũ lãnh đạo</span>
            <h2 class="text-3xl lg:text-4xl font-black text-gray-900 mt-3 mb-3">
              Những người dẫn dắt TopZone
            </h2>
            <p class="text-gray-600 max-w-2xl mx-auto text-sm lg:text-base">
              Đội ngũ lãnh đạo giàu kinh nghiệm, tâm huyết với ngành công nghệ
            </p>
          </div>

          <div class="grid md:grid-cols-4 gap-6">
            <div v-for="member in team" :key="member.id" class="group text-center">
              <div class="relative mb-4 overflow-hidden rounded-2xl">
                <div class="absolute inset-0 bg-gradient-to-t from-blue-600 to-transparent opacity-0 group-hover:opacity-90 transition-opacity z-10"></div>
                <img
                  :src="member.image"
                  :alt="member.name"
                  class="w-full aspect-square object-cover transform group-hover:scale-110 transition-transform duration-500"
                />
                <!-- Social icons on hover -->
                <div class="absolute bottom-3 left-0 right-0 flex justify-center gap-2 opacity-0 group-hover:opacity-100 transition-opacity z-20">
                  <a href="#" class="bg-white rounded-full p-2 hover:bg-blue-500 hover:text-white transition-colors">
                    <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 24 24">
                      <path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/>
                    </svg>
                  </a>
                  <a href="#" class="bg-white rounded-full p-2 hover:bg-blue-400 hover:text-white transition-colors">
                    <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 24 24">
                      <path d="M23.953 4.57a10 10 0 01-2.825.775 4.958 4.958 0 002.163-2.723c-.951.555-2.005.959-3.127 1.184a4.92 4.92 0 00-8.384 4.482C7.69 8.095 4.067 6.13 1.64 3.162a4.822 4.822 0 00-.666 2.475c0 1.71.87 3.213 2.188 4.096a4.904 4.904 0 01-2.228-.616v.06a4.923 4.923 0 003.946 4.827 4.996 4.996 0 01-2.212.085 4.936 4.936 0 004.604 3.417 9.867 9.867 0 01-6.102 2.105c-.39 0-.779-.023-1.17-.067a13.995 13.995 0 007.557 2.209c9.053 0 13.998-7.496 13.998-13.985 0-.21 0-.42-.015-.63A9.935 9.935 0 0024 4.59z"/>
                    </svg>
                  </a>
                  <a href="#" class="bg-white rounded-full p-2 hover:bg-blue-700 hover:text-white transition-colors">
                    <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 24 24">
                      <path d="M20.447 20.452h-3.554v-5.569c0-1.328-.027-3.037-1.852-3.037-1.853 0-2.136 1.445-2.136 2.939v5.667H9.351V9h3.414v1.561h.046c.477-.9 1.637-1.85 3.37-1.85 3.601 0 4.267 2.37 4.267 5.455v6.286zM5.337 7.433c-1.144 0-2.063-.926-2.063-2.065 0-1.138.92-2.063 2.063-2.063 1.14 0 2.064.925 2.064 2.063 0 1.139-.925 2.065-2.064 2.065zm1.782 13.019H3.555V9h3.564v11.452zM22.225 0H1.771C.792 0 0 .774 0 1.729v20.542C0 23.227.792 24 1.771 24h20.451C23.2 24 24 23.227 24 22.271V1.729C24 .774 23.2 0 22.222 0h.003z"/>
                    </svg>
                  </a>
                </div>
              </div>
              <h3 class="text-lg font-black text-gray-900 mb-1">{{ member.name }}</h3>
              <p class="text-blue-600 font-semibold text-sm mb-1">{{ member.position }}</p>
              <p class="text-xs text-gray-600">{{ member.description }}</p>
            </div>
          </div>
        </div>

        <!-- Certifications - New Design -->
        <div class="bg-gradient-to-br from-gray-900 to-gray-800 rounded-2xl p-8 shadow-2xl text-white">
          <div class="text-center mb-8">
            <h2 class="text-2xl lg:text-3xl font-black mb-3">Chứng nhận & Đối tác</h2>
            <p class="text-gray-300 text-sm">Được tin tưởng bởi các thương hiệu hàng đầu</p>
          </div>

          <div class="grid grid-cols-2 md:grid-cols-4 gap-4 items-center">
            <div class="flex items-center justify-center p-4 bg-white rounded-xl hover:scale-110 transition-transform">
              <img src="https://upload.wikimedia.org/wikipedia/commons/f/fa/Apple_logo_black.svg" alt="Apple" class="h-10 md:h-12">
            </div>
            <div class="flex items-center justify-center p-4 bg-white rounded-xl hover:scale-110 transition-transform">
              <img src="https://upload.wikimedia.org/wikipedia/commons/5/53/VNPT_Logo.svg" alt="VNPT" class="h-10 md:h-12">
            </div>
            <div class="flex items-center justify-center p-4 bg-white rounded-xl hover:scale-110 transition-transform">
              <img src="https://upload.wikimedia.org/wikipedia/commons/4/4e/ViettelPay_logo.svg" alt="ViettelPay" class="h-10 md:h-12">
            </div>
            <div class="flex items-center justify-center p-4 bg-white rounded-xl hover:scale-110 transition-transform">
              <img src="https://upload.wikimedia.org/wikipedia/commons/f/fe/MoMo_Logo.png" alt="MoMo" class="h-10 md:h-12">
            </div>
          </div>

          <!-- Trust badges -->
          <div class="grid md:grid-cols-3 gap-4 mt-8">
            <div class="bg-white/10 backdrop-blur-sm rounded-xl p-4 text-center border border-white/20">
              <div class="text-3xl font-black mb-1">100%</div>
              <div class="text-xs text-gray-300">Chính hãng</div>
            </div>
            <div class="bg-white/10 backdrop-blur-sm rounded-xl p-4 text-center border border-white/20">
              <div class="text-3xl font-black mb-1">12th</div>
              <div class="text-xs text-gray-300">Tháng bảo hành</div>
            </div>
            <div class="bg-white/10 backdrop-blur-sm rounded-xl p-4 text-center border border-white/20">
              <div class="text-3xl font-black mb-1">24/7</div>
              <div class="text-xs text-gray-300">Hỗ trợ</div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- CTA Section -->
    <div class="bg-gradient-to-r from-blue-600 to-indigo-700 py-12">
      <div class="container mx-auto px-4 text-center">
        <h2 class="text-2xl lg:text-3xl font-black text-white mb-4">
          Bắt đầu trải nghiệm ngay hôm nay
        </h2>
        <p class="text-base text-blue-100 mb-6 max-w-2xl mx-auto">
          Ghé thăm cửa hàng TopZone gần nhất hoặc mua sắm online để nhận ưu đãi hấp dẫn
        </p>
        <div class="flex flex-wrap gap-3 justify-center">
          <router-link to="/outlet-sale" class="bg-white text-blue-600 px-6 py-3 rounded-xl font-bold hover:bg-blue-50 transition-all shadow-xl hover:shadow-2xl transform hover:-translate-y-1 inline-flex items-center gap-2 text-sm">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z" />
            </svg>
            Mua sắm ngay
          </router-link>
          <router-link to="/contact" class="bg-white/10 backdrop-blur-sm text-white px-6 py-3 rounded-xl font-bold hover:bg-white/20 transition-all border-2 border-white/30 inline-flex items-center gap-2 text-sm">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
            </svg>
            Tìm cửa hàng
          </router-link>
        </div>
      </div>
    </div>

    <ClientFooter />
  </div>
</template>

<script>
import { ref } from 'vue';
import ClientHeader from './components/Header.vue';
import ClientFooter from './components/Footer.vue';

export default {
  name: 'About',
  components: {
    ClientHeader,
    ClientFooter,
  },
  setup() {
    const team = ref([
      {
        id: 1,
        name: 'Nguyễn Văn Mạnh',
        position: 'CEO & Founder',
        description: 'Người sáng lập và dẫn dắt TopZone',
        image: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=400&h=400&fit=crop',
      },
      {
        id: 2,
        name: 'Trần Thị Lan',
        position: 'Chief Operating Officer',
        description: 'Quản lý vận hành toàn hệ thống',
        image: 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=400&h=400&fit=crop',
      },
      {
        id: 3,
        name: 'Lê Văn Hùng',
        position: 'Chief Technology Officer',
        description: 'Phát triển công nghệ & hệ thống',
        image: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=400&h=400&fit=crop',
      },
      {
        id: 4,
        name: 'Phạm Thị Mai',
        position: 'Chief Marketing Officer',
        description: 'Chiến lược marketing & thương hiệu',
        image: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=400&h=400&fit=crop',
      },
    ]);

    return {
      team,
    };
  },
};
</script>

<style scoped>
@keyframes blob {
  0%, 100% { transform: translate(0, 0) scale(1); }
  33% { transform: translate(30px, -50px) scale(1.1); }
  66% { transform: translate(-20px, 20px) scale(0.9); }
}

.animate-blob {
  animation: blob 7s infinite;
}

.animation-delay-2000 {
  animation-delay: 2s;
}

.animation-delay-4000 {
  animation-delay: 4s;
}
</style>