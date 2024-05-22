<template>
    <div class="page-container">
        <div class="signUp-wrapper">
            <SignUp />
        </div>
    </div>
</template>

<script setup>
import SignUp from "../component/SignUp.vue"
</script>

<style scoped>
.page-container {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}
.signUp-wrapper {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f0f0f0; /* 페이지 배경 색상 */
}
</style>