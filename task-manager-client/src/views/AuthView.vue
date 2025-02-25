<script setup lang="ts">
import axios from 'axios'

const API_SIGN_UP_ENDPOINT = import.meta.env.VITE_API_SIGN_UP_ENPOINT
const API_LOGIN_ENDPOINT = import.meta.env.VITE_API_LOGIN_ENPOINT

const lognIn = async () => {
  try {
    const response = await axios.get(API_LOGIN_ENDPOINT)
  } catch (error) {
    console.error('Error while log in: ', error)
  }
}

const signUp = async () => {
  try {
    const response = await axios.get(API_SIGN_UP_ENDPOINT)
  } catch (error) {
    console.error('Error while sign up: ')
  }
}
</script>

<template>
  <div>
    <h1>Auth</h1>
  </div>
</template>
