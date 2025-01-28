<!-- <script>
import axios from 'axios'

export default {
  data() {
    return {
      tasks: [],
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/tasks')
      .then((response) => {
        this.tasks = response.data
      })
      .catch((error) => {
        console.error('Error fetching tasks:', error)
      })
  },
}
</script> -->

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useCounterStore } from '@/stores/counter'

// Типизация для задачи (например, если задача имеет свойства id и name)
interface Task {
  id: number
  title: string
  status: string
  createdAt: Date
}

// const saske = 1
const tasks = ref<Task[]>([])
const taskCounterStore = useCounterStore()

onMounted(() => {
  axios
    .get('http://localhost:8080/tasks')
    .then((response) => {
      tasks.value = response.data
    })
    .catch((error) => {
      console.error('Error fetching tasks:', error)
    })
})

// function onDeleteButtonClicked(() => {

// })
</script>

<template>
  <div class="task-manager">
    <h1>Task Manager</h1>
    <ul class="task-list">
      <li v-for="task in tasks" :key="task.id">
        {{ task.id }} {{ task.title }} {{ task.status }} {{ task.createdAt }}
        <button @click="taskCounterStore.increment">X</button>
      </li>
    </ul>
    <button @click="taskCounterStore.increment">Increment</button>
    <button @click="taskCounterStore.decrement">Decrement</button>
    <h2>{{ taskCounterStore.count }}</h2>
  </div>
</template>

<style>
.task-list {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.task-list li {
  margin: 20px 0;
}
</style>
