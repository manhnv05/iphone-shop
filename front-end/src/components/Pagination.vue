<template>
  <nav class="flex items-center justify-center select-none" aria-label="Phân trang">
    <ul
      class="flex items-center gap-1 sm:gap-2 p-1 rounded-full
             bg-white/90 border border-blue-100 backdrop-blur
             shadow-sm"
    >
      <!-- Về trang đầu -->
      <li>
        <button
          :aria-label="'Trang đầu'"
          :disabled="isFirst"
          @click="goToPage(0)"
          :class="[baseBtnClass, iconBtnClass]"
          class="group"
        >
          <!-- Shine effect -->
          <span class="absolute inset-0 pointer-events-none -skew-x-12 bg-gradient-to-r from-transparent via-white/50 to-transparent opacity-0 translate-x-[-120%] group-hover:opacity-100 group-hover:translate-x-[120%] transition-all duration-500"></span>
          <svg class="h-4 w-4 sm:h-5 sm:w-5 relative z-[1]" viewBox="0 0 24 24" fill="none">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.8"
                  d="M18 19 11 12l7-7M11 19 4 12l7-7"/>
          </svg>
        </button>
      </li>

      <!-- Trang trước -->
      <li>
        <button
          :aria-label="'Trang trước'"
          :disabled="isFirst"
          @click="goToPage(currentPage - 1)"
          :class="[baseBtnClass, iconBtnClass]"
          class="group"
        >
          <span class="absolute inset-0 pointer-events-none -skew-x-12 bg-gradient-to-r from-transparent via-white/50 to-transparent opacity-0 translate-x-[-120%] group-hover:opacity-100 group-hover:translate-x-[120%] transition-all duration-500"></span>
          <svg class="h-4 w-4 sm:h-5 sm:w-5 relative z-[1]" viewBox="0 0 24 24" fill="none">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.8"
                  d="M15 19 8 12l7-7"/>
          </svg>
        </button>
      </li>

      <!-- Số trang + dấu ba chấm -->
      <li v-for="(item, idx) in pageItems" :key="idx">
        <span v-if="item === '...'" class="px-2 sm:px-3 text-blue-400 font-medium">…</span>

        <button
          v-else
          :aria-label="'Trang ' + (item + 1)"
          :aria-current="item === currentPage ? 'page' : undefined"
          @click="goToPage(item)"
          :class="[
            baseBtnClass,
            item === currentPage ? activeBtnClass : numberBtnClass
          ]"
          class="group"
        >
          <span class="absolute inset-0 pointer-events-none -skew-x-12 bg-gradient-to-r from-transparent via-white/60 to-transparent opacity-0 translate-x-[-120%] group-hover:opacity-100 group-hover:translate-x-[120%] transition-all duration-500"></span>
          <span class="relative z-[1]">{{ item + 1 }}</span>
        </button>
      </li>

      <!-- Trang sau -->
      <li>
        <button
          :aria-label="'Trang sau'"
          :disabled="isLast"
          @click="goToPage(currentPage + 1)"
          :class="[baseBtnClass, iconBtnClass]"
          class="group"
        >
          <span class="absolute inset-0 pointer-events-none -skew-x-12 bg-gradient-to-r from-transparent via-white/50 to-transparent opacity-0 translate-x-[-120%] group-hover:opacity-100 group-hover:translate-x-[120%] transition-all duration-500"></span>
          <svg class="h-4 w-4 sm:h-5 sm:w-5 relative z-[1]" viewBox="0 0 24 24" fill="none">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.8"
                  d="m9 5 7 7-7 7"/>
          </svg>
        </button>
      </li>

      <!-- Đến trang cuối -->
      <li>
        <button
          :aria-label="'Trang cuối'"
          :disabled="isLast"
          @click="goToPage(totalPages - 1)"
          :class="[baseBtnClass, iconBtnClass]"
          class="group"
        >
          <span class="absolute inset-0 pointer-events-none -skew-x-12 bg-gradient-to-r from-transparent via-white/50 to-transparent opacity-0 translate-x-[-120%] group-hover:opacity-100 group-hover:translate-x-[120%] transition-all duration-500"></span>
          <svg class="h-4 w-4 sm:h-5 sm:w-5 relative z-[1]" viewBox="0 0 24 24" fill="none">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.8"
                  d="m6 19 7-7-7-7M13 19l7-7-7-7"/>
          </svg>
        </button>
      </li>
    </ul>

    <!-- Hiển thị tổng quát -->
    <div class="ml-3 hidden sm:flex items-center text-sm text-blue-700">
      Trang
      <span class="mx-1 font-semibold text-blue-900">{{ currentPage + 1 }}</span>/<span class="mx-1">{{ totalPages }}</span>
    </div>
  </nav>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
  currentPage: { type: Number, required: true },
  totalPages: { type: Number, required: true },
  siblingCount: { type: Number, default: 1 },
  boundaryCount: { type: Number, default: 1 }
});

const emit = defineEmits(['page-changed']);

const isFirst = computed(() => props.currentPage <= 0);
const isLast = computed(() => props.currentPage >= props.totalPages - 1);

function goToPage(page) {
  if (page < 0 || page > props.totalPages - 1 || page === props.currentPage) return;
  emit('page-changed', page);
}

function range(start, end) {
  const out = [];
  for (let i = start; i <= end; i++) out.push(i);
  return out;
}

const pageItems = computed(() => {
  const total = props.totalPages;
  const curr = Math.max(0, Math.min(props.currentPage, total - 1));
  const bc = Math.max(0, props.boundaryCount);
  const sc = Math.max(0, props.siblingCount);

  if (total <= 0) return [];
  if (total === 1) return [0];

  const startPages = range(0, Math.min(bc - 1, total - 1));
  const endPages = range(Math.max(total - bc, 0), total - 1);

  const left = Math.max(curr - sc, 0);
  const right = Math.min(curr + sc, total - 1);
  const middle = range(left, right);

  const items = [];
  const addUnique = (arr) => arr.forEach(i => { if (!items.includes(i)) items.push(i); });

  addUnique(startPages);
  if (middle[0] > (startPages[startPages.length - 1] ?? -1) + 1) items.push('...');
  addUnique(middle);
  if ((endPages[0] ?? total) > (middle[middle.length - 1] ?? -1) + 1) items.push('...');
  addUnique(endPages);

  return items;
});

// Style classes (tông xanh dương sáng)
const baseBtnClass =
  'relative overflow-hidden h-9 w-9 sm:h-10 sm:w-10 inline-flex items-center justify-center rounded-full ' +
  'font-medium tracking-tight transition-all duration-200 ' +
  'border ring-1 ' +
  'focus:outline-none focus:ring-2 focus:ring-sky-300 focus:ring-offset-2 focus:ring-offset-white ' +
  'disabled:opacity-50 disabled:cursor-not-allowed disabled:shadow-none disabled:hover:translate-y-0 disabled:hover:scale-100';

const iconBtnClass =
  'bg-white text-blue-600 border-blue-200 ring-blue-50 ' +
  'hover:-translate-y-0.5 hover:scale-[1.03] hover:shadow-lg hover:shadow-blue-300/40';

const numberBtnClass =
  'bg-white text-blue-700 border-blue-200 ring-blue-50 ' +
  'hover:text-white hover:bg-gradient-to-br hover:from-blue-500 hover:to-sky-500 hover:border-transparent ' +
  'hover:-translate-y-0.5 hover:scale-[1.03] hover:shadow-lg hover:shadow-blue-300/50';

const activeBtnClass =
  'text-white border-transparent ring-0 ' +
  'bg-gradient-to-br from-blue-600 via-sky-500 to-cyan-500 ' +
  'shadow-lg shadow-blue-300/60';
</script>