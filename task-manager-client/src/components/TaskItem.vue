<script setup lang="ts">
import { defineProps } from 'vue'
import ButtonGreen from '@/components/ButtonGreen.vue'
import type { Task } from '@/types'
import { useEditModalStore } from '@/stores/editModal'

defineProps<{ task: Task }>()

// const formatDate = (dateString: string) => {
//   const dateOfCreation = new Date(dateString).getTime();
//   const dateNow = new Date().getTime();
//   const diffTime = Math.abs(dateNow - dateOfCreation);
//   const diffHours = Math.floor(diffTime / (1000 * 60 * 60));
//   return diffHours + ' h'
// }

const formatStatus = (statusString: string) => {
  return (
    statusString.charAt(0) +
    statusString.slice(1, statusString.length).toLowerCase().replace('_', ' ')
  )
}
const statusBgColor = (statusString: string) => {
  switch (statusString) {
    case 'NEW':
      return '#fff'
    case 'IN_PROGRESS':
      return '#ff8a8a'
    case 'COMPLETED':
      return '#006838'
  }
}

const statusTextColor = (statusString: string) => {
  switch (statusString) {
    case 'NEW':
      return '#000'
    case 'IN_PROGRESS':
      return '#fff'
    case 'COMPLETED':
      return '#fff'
  }
}

const openEditModal = (task: Task) => {
  const editModalStore = useEditModalStore()
  editModalStore.setTaskToEdit(task)
  editModalStore.openModal()
}
</script>

<template>
  <li class="task-item">
    <p class="task-item__title">{{ task.title }}</p>
    <p
      class="task-item__status"
      :style="{
        backgroundColor: statusBgColor(task.status),
        color: statusTextColor(task.status),
        borderColor: statusTextColor(task.status),
      }"
    >
      {{ formatStatus(task.status) }}
    </p>
    <div class="button-container">
      <ButtonGreen
        title="Edit"
        bgColor="#fff"
        text-color="#006838"
        border-color="#006838"
        border-radius="20px"
        @click="openEditModal(task)"
      />
      <ButtonGreen
        title="x"
        bgColor="#fff"
        textColor="#006838"
        borderColor="#006838"
        border-radius="50%"
        @click="$emit('delete', task.id)"
      />
    </div>
  </li>
</template>

<style scoped>
.task-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  border: 2px solid #d9d9d9;
  border-radius: 15px;
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 20px;
  text-align: center;
}

.task-item__title {
  text-align: left;
  width: 300px;
}

.task-item__status {
  text-align: center;
  min-width: 150px;
  border-radius: 30px;
  border: 1px solid;
}

.button-container {
  width: 130px;
  display: flex;
  justify-content: space-between;
}
</style>
