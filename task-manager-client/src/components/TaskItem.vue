<script setup lang="ts">
import { defineProps } from 'vue'
import ButtonGreen from '@/components/ButtonGreen.vue'

interface Task {
  id: number
  title: string
  description: string
  status: string
  createdAt: string
}

const props = defineProps<{ task: Task }>()

const formatDate = (dateString: string) => {
  const dateOfCreation = new Date(dateString).getTime();
  const dateNow = new Date().getTime();
  const diffTime = Math.abs(dateNow - dateOfCreation);
  const diffHours = Math.floor(diffTime / (1000 * 60 * 60));
  return diffHours + ' h'
}

const formatStatus = (statusString : string) => {
  return statusString.charAt(0) + statusString.slice(1, statusString.length).toLowerCase().replace('_', ' ');
}
</script>

<template>
  <li class="task-item">
    <p class="task-item__title">{{ task.title }}</p>
    <p class="task-item__status">{{ formatStatus(task.status) }}</p>
    <!-- {{ formatDate(task.createdAt) }} -->
    <ButtonGreen title="X" @click="$emit('delete', task.id)" />
  </li>
</template>

<style scoped>
.task-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  border: 2px solid #006838;
  border-radius: 15px;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 20px;
  text-align: center;
}

.task-item__title {
  text-align: left;
  min-width: 300px;

}

.task-item__status {
  text-align: center;
  min-width: 150px;
  border: 1px solid #006838;
  background-color: #006838;
  color: #FFF;
  border-radius: 30px;
}
</style>
