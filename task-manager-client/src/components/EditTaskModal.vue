<script setup lang="ts">
import { onMounted, ref } from 'vue'
import ButtonGreen from './ButtonGreen.vue'
import { useEditModalStore } from '@/stores/editModal'

const emit = defineEmits(['close', 'editTask'])
const title = ref('')
const description = ref('')
const status = ref('NEW')
const editModalStore = useEditModalStore()

const submitTask = () => {
  emit(
    'editTask',
    {
      title: title.value,
      description: description.value,
      status: status.value,
    },
    editModalStore.taskToEdit?.id,
  )
  emit('close')
}

onMounted(() => {
  title.value = editModalStore.taskToEdit?.title || ''
  description.value = editModalStore.taskToEdit?.description || ''
  status.value = editModalStore.taskToEdit?.status || 'NEW'
})
</script>

<template>
  <div class="modal__overlay">
    <div class="modal">
      <div class="modal__header">
        <h1>Edit Task</h1>
      </div>
      <form action="" class="modal__form">
        <label for="title">Title</label>
        <input type="text" name="Title" id="title" v-model="title" required />
        <label for="desc">Description</label>
        <input type="text" name="Description" id="desc" v-model="description" required />

        <label for="status">Status</label>
        <select name="Status" id="status" v-model="status" required>
          <option value="NEW">NEW</option>
          <option value="IN_PROGRESS">IN_PROGRESS</option>
          <option value="COMPLETED">COMPLETED</option>
        </select>
      </form>

      <div class="modal__buttons">
        <ButtonGreen
          title="Cancel"
          bgColor="#fff"
          textColor="#000"
          borderColor="#000"
          @click="emit('close')"
        />
        <ButtonGreen title="Edit task" @click="submitTask" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal__overlay {
  padding: 0;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.25);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  width: 400px;
  min-height: 550px;
  margin: 0 50px;
  padding: 30px 70px;
  position: relative;
  background-color: #fff;
  border-radius: 20px;
}

.modal__form {
  display: flex;
  flex-direction: column;
  font-size: 20px;
  font-weight: 600;
}

.modal__form label {
  margin: 20px 0 5px;
}

.modal__form input,
.modal__form select {
  height: 40px;
  font-size: 16px;
  padding: 0 10px;
}

.modal__buttons {
  margin-top: 80px;
  display: flex;
  justify-content: space-between;
}
</style>
