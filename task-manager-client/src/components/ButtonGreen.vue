<script setup lang="ts">
defineProps<{
  title: string
}>()
</script>

<template>
  <button class="button-green">
    {{ title }}
  </button>
</template>


<style scoped>
.button-green {
  background-color: #006838;
  color: #fff;
  font-size: 18px;
  font-weight: bold;
  border-radius: 5px;
  padding: 8px 15px;
  border: 1px solid #006838;
}
.button-green:hover {
  background-color: #045630;

}
.button-green:active {
  background-color: #2e835c;
}

/* @media (min-width: 1024px) {
  .greetings h1,
  .greetings h3 {
    text-align: left;
  }
} */

/* <template #icon>
      <DocumentationIcon />
    </template> */
</style>
