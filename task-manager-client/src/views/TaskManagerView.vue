<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import AddTaskModal from '@/components/AddTaskModal.vue'
import TaskList from '@/components/TaskList.vue'
import type { Task, TaskRequest } from '@/types'
import EditTaskModal from '@/components/EditTaskModal.vue'
import { useEditModalStore } from '@/stores/editModal'
import TaskManagerFooter from '@/components/TaskManagerFooter.vue'
import TaskManagerHeader from '@/components/TaskManagerHeader.vue'

const API_TASKS_ENDPOINT = import.meta.env.VITE_API_TASKS_ENPOINT

const tasks = ref<Task[]>([])
const isAddModalOpen = ref<boolean>(false)
const editModalStore = useEditModalStore()

onMounted(() => {
  getAllTasks()
})

const getAllTasks = async () => {
  try {
    const response = await axios.get(API_TASKS_ENDPOINT)
    tasks.value = response.data
  } catch (error) {
    console.error('Error fetching tasks:', error)
  }
}

const deleteTask = async (taskId: number) => {
  try {
    await axios.delete(`${API_TASKS_ENDPOINT}/${taskId}`)
    await getAllTasks()
  } catch (error) {
    console.error(`Error deleting task ${taskId}:`, error)
  }
}

const addTask = async (task: TaskRequest) => {
  try {
    await axios.post(`${API_TASKS_ENDPOINT}`, task)
    await getAllTasks()
  } catch (error) {
    console.error('Adding task error:', error)
  }
}

const editTask = async (task: TaskRequest, taskId: number) => {
  try {
    await axios.put(`${API_TASKS_ENDPOINT}/${taskId}`, task)
    await getAllTasks()
  } catch (error) {
    console.error(`Error updating task ${taskId}:`, error)
  }
}
</script>

<template>
  <div class="task-manager">
    <TaskManagerHeader />
    <TaskList :tasks="tasks" @delete-task="deleteTask" />

    <AddTaskModal v-if="isAddModalOpen" @close="isAddModalOpen = false" @addTask="addTask" />

    <EditTaskModal
      v-if="editModalStore.isModalOpen"
      @close="editModalStore.closeModal"
      @editTask="editTask"
    />

    <TaskManagerFooter v-model="isAddModalOpen" />
  </div>
</template>

<style>
.task-manager {
  width: 1000px;
  margin: 50px 20px 0;
  background-color: #ffffff;
  border-radius: 20px;
  padding: 30px 60px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: black;
  display: flex;
  flex-direction: column;
}
</style>
