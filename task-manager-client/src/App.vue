<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <header>
    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/task-manager">Task Manager</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style>
body {
  display: flex;
  align-items: start;
}
#app {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  /* justify-content: center; */
}
</style>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
  display: flex;
  place-items: center;
}

header .wrapper {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

nav {
  width: 100%;
  text-align: center;
  font-size: 1rem;
  padding: 1rem 0 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}
</style>
