<template>
  <div class="flex justify-center items-center gap-2">
    <!-- Previous Button -->
    <button
      @click="changePage(currentPage - 1)"
      :disabled="currentPage === 1"
      class="px-4 py-2 rounded-lg border border-gray-300 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-100 transition-colors"
    >
      <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
      </svg>
    </button>

    <!-- Page Numbers -->
    <template v-for="(page, index) in displayedPages" :key="index">
      <button
        v-if="page !== '...'"
        @click="changePage(page)"
        :class="[
          'px-4 py-2 rounded-lg transition-colors',
          currentPage === page
            ? 'bg-blue-600 text-white font-semibold'
            : 'border border-gray-300 hover:bg-gray-100'
        ]"
      >
        {{ page }}
      </button>
      <span v-else class="px-2 text-gray-500">...</span>
    </template>

    <!-- Next Button -->
    <button
      @click="changePage(currentPage + 1)"
      :disabled="currentPage === totalPages"
      class="px-4 py-2 rounded-lg border border-gray-300 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-100 transition-colors"
    >
      <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
      </svg>
    </button>
  </div>
</template>

<script>
import { computed } from 'vue';

export default {
  name: 'Pagination',
  props: {
    currentPage: {
      type: Number,
      required: true,
    },
    totalPages: {
      type: Number,
      required: true,
    },
    maxVisible: {
      type: Number,
      default: 7,
    },
  },
  emits: ['change'],
  setup(props, { emit }) {
    const displayedPages = computed(() => {
      const pages = [];
      const total = props.totalPages;
      const current = props.currentPage;
      const max = props.maxVisible;

      if (total <= max) {
        // Show all pages
        for (let i = 1; i <= total; i++) {
          pages.push(i);
        }
      } else {
        // Smart pagination
        if (current <= 3) {
          // Near the start
          for (let i = 1; i <= 5; i++) pages.push(i);
          pages.push('...');
          pages.push(total);
        } else if (current >= total - 2) {
          // Near the end
          pages.push(1);
          pages.push('...');
          for (let i = total - 4; i <= total; i++) pages.push(i);
        } else {
          // Middle
          pages.push(1);
          pages.push('...');
          for (let i = current - 1; i <= current + 1; i++) pages.push(i);
          pages.push('...');
          pages.push(total);
        }
      }

      return pages;
    });

    const changePage = (page) => {
      if (page >= 1 && page <= props.totalPages && page !== props.currentPage) {
        emit('change', page);
      }
    };

    return {
      displayedPages,
      changePage,
    };
  },
};
</script>