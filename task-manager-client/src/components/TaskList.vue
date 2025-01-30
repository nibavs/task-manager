<script setup lang="ts">
import TaskItem from '@/components/TaskItem.vue'
import type { Task } from '@/types'

defineProps<{ tasks: Task[] }>()
const emit = defineEmits(['delete-task'])
</script>

<template>
  <ul class="task-list">
    <TaskItem
      v-for="task in tasks"
      :key="task.id"
      :task="task"
      @delete="emit('delete-task', task.id)"
    />
  </ul>
</template>

<style scoped>
.task-list {
  list-style-type: none;
  margin: 0;
  padding: 0;
}
</style>
