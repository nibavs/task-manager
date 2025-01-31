import { ref } from 'vue'
import { defineStore } from 'pinia'
import type { Task } from '@/types'

export const useEditModalStore = defineStore('editModal', () => {
  const isModalOpen = ref(false)
  const taskToEdit = ref<Task | null>(null)

  function openModal() {
    isModalOpen.value = true
  }

  function closeModal() {
    isModalOpen.value = false
  }

  function setTaskToEdit(task: Task) {
    taskToEdit.value = task
  }

  return { isModalOpen, openModal, closeModal, setTaskToEdit, taskToEdit }
})
