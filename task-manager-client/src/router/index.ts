import { createRouter, createWebHistory } from 'vue-router'
import AuthView from '../views/AuthView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/auth',
      name: 'auth',
      component: AuthView,
    },
    {
      path: '/',
      name: 'task-manager',
      component: () => import('../views/TaskManagerView.vue'),
    },
  ],
})

export default router
