<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import ButtonGreen from '@/components/ButtonGreen.vue'
import AddTaskModal from '@/components/AddTaskModal.vue'
import TaskList from '@/components/TaskList.vue'
import type { Task, TaskRequest } from '@/types'

const API_TASKS_ENDPOINT = import.meta.env.VITE_API_TASKS_ENPOINT

const tasks = ref<Task[]>([])
const isAddModalOpen = ref(false)

onMounted(() => {
  getAllTasks()
})

const getAllTasks = () => {
  axios
    .get(`${API_TASKS_ENDPOINT}`)
    .then((response) => {
      tasks.value = response.data
    })
    .catch((error) => {
      console.error('Error fetching tasks:', error)
    })
}

const deleteTask = (taskId: number) => {
  axios
    .delete(`${API_TASKS_ENDPOINT}/${taskId}`)
    .then((response) => {
      if (response.status !== 200) {
        console.error('Error deleting task!')
      } else {
        console.log('Task has not been deleted!:', response.data)
        getAllTasks()
      }
    })
    .catch((error) => {
      console.error('Error deleting task:', error)
    })
}

const addTask = (task: TaskRequest) => {
  axios
    .post(`${API_TASKS_ENDPOINT}`, task)
    .then((response) => {
      if (response.status !== 200) {
        console.error('Adding task error!')
      } else {
        console.log('Task has been added!:', response.data)
        getAllTasks()
      }
    })
    .catch((error) => {
      console.error('Adding task error!', error)
    })
}
</script>

<template>
  <div class="task-manager">
    <div class="task-manager__header">
      <h1>Task Manager</h1>
    </div>
    <TaskList :tasks="tasks" @delete-task="deleteTask" />

    <ButtonGreen title="Add task" @click="isAddModalOpen = true" />

    <AddTaskModal v-if="isAddModalOpen" @close="isAddModalOpen = false" @addTask="addTask" />
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
}

.task-manager__header {
  margin-bottom: 30px;
}
</style>
