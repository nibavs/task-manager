<script setup lang="ts">
defineProps<{
  title: string
  bgColor?: string
  textColor?: string
  borderColor?: string
  borderRadius?: string
}>()
</script>

<template>
  <button
    class="button-green"
    :style="{backgroundColor: bgColor || '#006838', color: textColor || '#fff', borderColor: borderColor || '#006838', borderRadius: borderRadius || '5px'}">
    {{ title }}
  </button>
</template>


<style scoped>
.button-green {
  font-size: 18px;
  font-weight: bold;
  border-radius: 5px;
  padding: 8px 15px;
  border: 2px solid;
  transition: background-color 0.2s ease;
}

.button-green:hover {
  filter: brightness(0.9); /* Затемнение при наведении */
}

.button-green:active {
  filter: brightness(0.8);
}
</style>

<!-- /* @media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
} */ -->

<!-- /* <template #icon>
      <DocumentationIcon />
    </template> */ -->
