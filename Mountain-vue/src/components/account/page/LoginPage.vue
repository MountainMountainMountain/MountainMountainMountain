<template>
    <div class="page-container">
    <div class="login-wrapper">
            <Login />
        </div>
    </div>
</template>

<script setup>
import Login from "../component/Login.vue"
</script>

<style scoped>
.page-container {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}
.login-wrapper {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f0f0f0;
}
</style>