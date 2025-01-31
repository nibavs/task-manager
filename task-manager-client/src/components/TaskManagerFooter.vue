<script setup lang="ts">
const isAddModalOpen = defineModel<boolean>()
</script>

<template>
  <div class="task-manager__footer">
    <p class="copyright">&copy; nibavs 2025</p>
    <button class="add-task-btn" @click="isAddModalOpen = true">Add task</button>
  </div>
</template>

<style scoped>
.task-manager__footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.add-task-btn {
  width: 120px;
  font-size: 18px;
  font-weight: bold;
  border-radius: 5px;
  padding: 8px 15px;
  border: 2px solid #006838;
  color: #ffffff;
  background-color: #006838;
  transition: background-color 0.2s ease;
}

.copyright {
  font-family: Arial, Helvetica, sans-serif;
  color: #bebebe;
}
</style>
