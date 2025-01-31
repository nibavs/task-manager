<script setup lang="ts"></script>

<template>
  <div class="task-manager__header">
    <h1>Task Manager</h1>
    <button class="signout-btn">SIGN OUT</button>
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
  padding: 0 4px;
  border: 2px solid #ff8a8a;
  color: #ff8a8a;
  background-color: #fff;
  transition: background-color 0.2s ease;
}
</style>
