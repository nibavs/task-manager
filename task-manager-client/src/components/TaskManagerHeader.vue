<script setup lang="ts">
import router from '@/router'
import axios from 'axios'

const logOut = () => {
  localStorage.removeItem('token')
  delete axios.defaults.headers.common['Authorization']
  router.push('/auth')
}
</script>

<template>
  <div class="task-manager__header">
    <h1>Task Manager</h1>
    <button class="signout-btn" @click="logOut">SIGN OUT</button>
  </div>
</template>

<style lang="css" scoped>
.task-manager__header {
  margin-bottom: 30px;
  display: flex;
  justify-content: space-between;
}
.signout-btn {
  width: 110px;
  font-size: 14px;
  font-weight: bold;
  border-radius: 30px;
  border: 2px solid #ff8a8a;
  color: #ff8a8a;
  background-color: #fff;
  transition: background-color 0.2s ease;
}
</style>
