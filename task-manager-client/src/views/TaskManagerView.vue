<script setup lang="ts">
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { useCounterStore } from '@/stores/counter'
import ButtonGreen from '@/components/ButtonGreen.vue'
import TaskItem from '@/components/TaskItem.vue'

// Типизация для задачи (например, если задача имеет свойства id и name)
interface Task {
  id: number
  title: string
  description: string
  status: string
  createdAt: string
}

interface TaskRequest {
  title: string
  description: string
  status: string
}

const tasks = ref<Task[]>([])

onMounted(() => {
  getAllTasks();
})

const getAllTasks = () => {
  axios
    .get('http://localhost:8080/tasks')
    .then((response) => {
      tasks.value = response.data
    })
    .catch((error) => {
      console.error('Error fetching tasks:', error)
    })
}

const deleteTask = (taskId: number) => {
  axios
    .delete('http://localhost:8080/tasks/' + taskId)
    .then((response) => {
      if (response.status !== 200) {
        console.error("Error deleting task!")
      } else {
        console.log("Task has not been deleted!:", response.data)
        getAllTasks()
      }
    })
    .catch((error) => {
      console.error('Error deleting task:', error)
    })
}

const addTask = (task: TaskRequest) => {
  axios
    // THROW UP TO THE ENV VARIABLE SERVER URL/ENDPOINT
    .post('http://localhost:8080/tasks', task)
    .then((response) => {
      if (response.status !== 200) {
        console.error("Adding task error!")
      } else {
        console.log("Task has been added!:", response.data)
        getAllTasks()
      }
    })
    .catch((error) => {
      console.error('Adding task error!', error)
    })

}

const newTask: TaskRequest = {
  title: 'new Task',
  description: 'desc',
  status: 'NEW'
}

</script>

<template>
  <div class="task-manager">
    <h1>Task Manager</h1>
    <ul class="task-list">
      <TaskItem v-for="task in tasks" :key="task.id" :task="task" @delete="deleteTask" />
    </ul>

    <ButtonGreen title="Add task" @click="addTask(newTask)"/>
  </div>
</template>

<style>
.task-manager {
  width: 1000px;
  margin-top: 100px;
  background-color: #FFFFFF;
  border-radius: 20px;
  padding: 30px 60px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: black;
}

.task-list {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

</style>
