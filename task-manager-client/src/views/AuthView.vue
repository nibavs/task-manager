<script setup lang="ts">
import ButtonGreen from '@/components/ButtonGreen.vue'
import router from '@/router'
import type { Credentials } from '@/types'
import axios from 'axios'
import { ref } from 'vue'

const API_SIGN_UP_ENDPOINT = import.meta.env.VITE_API_SIGN_UP_ENPOINT
const API_SIGN_IN_ENDPOINT = import.meta.env.VITE_API_SIGN_IN_ENPOINT

const isSignUp = ref<boolean>(false)
const credentials = ref<Credentials>({ username: '', password: '' })
const errorOutput = ref<string>('')

const auth = async () => {
  try {
    const response = await axios.post(
      isSignUp.value ? API_SIGN_UP_ENDPOINT : API_SIGN_IN_ENDPOINT,
      credentials.value,
    )
    const token = response.data
    if (token) {
      localStorage.setItem('token', token)
      router.push('/')
    }
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      errorOutput.value = error.response.data
    } else {
      errorOutput.value = 'Unknown error!'
      console.error('Error while auth: ', error)
    }
  }
}
</script>

<template>
  <div class="auth">
    <h1>Auth</h1>
    <div class="auth__buttons">
      <button
        :class="isSignUp ? 'auth__active-mode-button' : 'auth__inactive-mode-button'"
        @click="isSignUp = false"
      >
        Sign In
      </button>
      <button
        :class="isSignUp ? 'auth__inactive-mode-button' : 'auth__active-mode-button'"
        @click="isSignUp = true"
      >
        Sign Up
      </button>
    </div>
    <form action="" class="auth__form">
      <label for="auth__username">Username</label>
      <input type="text" id="auth__username" v-model="credentials.username" />
      <label for="auth__password">Password</label>
      <input type="text" id="auth__password" v-model="credentials.password" />
      <!-- <label v-if="isSignUp" for="auth__repeat-password">Repeat password</label>
      <input v-if="isSignUp" type="text" id="auth__repeat-password" /> -->
    </form>
    <p class="error-output">{{ errorOutput }}</p>

    <ButtonGreen title="Confirm" style="align-self: flex-end" @click="auth" />
  </div>
</template>

<style>
.auth {
  width: 320px;
  margin: 50px 20px 0;
  background-color: #ffffff;
  border-radius: 20px;
  padding: 30px 60px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  color: black;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.auth__form {
  display: flex;
  flex-direction: column;
  font-size: 20px;
  font-weight: 600;
  /* margin-bottom: 40px; */
}

.auth__form label {
  margin: 20px 0 5px;
}

.auth__form input {
  height: 40px;
  width: 300px;
  font-size: 16px;
  padding: 0 10px;
}

.auth__buttons {
  margin-top: 10px;
  display: flex;
  justify-content: space-between;
}

.auth__buttons button {
  width: 100px;
  height: 30px;
  font-size: 15px;
  font-weight: bold;
  border-radius: 5px;
  border: 2px solid;
  color: #fff;
}

.auth__inactive-mode-button {
  background-color: rgba(0, 104, 56, 0.901);
  border-color: rgba(0, 104, 56, 0.901);
}

.auth__active-mode-button {
  background-color: rgba(0, 104, 56, 0.2);
  border-color: rgba(0, 104, 56, 0.2);
}

.error-output {
  color: red;
  padding: 10px 0;
}
</style>
