<template>
    <div id="login-container">
        <form id="login" @submit.prevent="login">
            <fieldset id="loginbox">
                <router-link to="/">
                    <h2 style="color: green; text-align: center">산산산</h2>
                </router-link>
                <label for="user-id" style="margin-right: 38%;">아이디</label>
                <input id="user-id" type="text" placeholder="아이디를 입력하세요" autofocus required v-model.trim="id">
                <label for="user-password" style="margin-right: 38%;">비밀번호</label>
                <input id="user-password" type="password" placeholder="비밀번호를 입력하세요" v-model.trim="pw">
                <button type="submit" class="btn btn-secondary"
                    style="background-color: green; color: aliceblue; border-radius: 10%;">로그인</button>
                <div>
                    <router-link :to="{ name: 'SignUp' }" class="nav-link">회원가입</router-link>
                </div>
            </fieldset>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router';
import { useUserStore } from '@/stores/userstore'

const id = ref("")
const pw = ref("")

const store = useUserStore();
const router = useRouter();
const route = useRoute();

const login = function () {
    store.userLogin(id.value, pw.value)
        .then(() => {
            location.replace('/');
        })
        .catch((error) => {
            console.error(error);
            alert('로그인에 실패했습니다. 아이디와 비밀번호를 다시 확인해주세요.');
        });
}

</script>

<style scoped>
#login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: calc(100vh - 100px);
    /* 100px for footer height */
}

#login {
    width: 100%;
    max-width: 400px;
}

#loginbox {
    border: none;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

a {
    color: #000;
    text-decoration: none;
}

a:hover {
    color: blue;
}

.signup-link {
    font-size: 0.8em;
    /* Smaller font size for signup link */
}

button {
    background-color: greenyellow;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    font-size: 1em;
}

button:hover {
    background-color: limegreen;
}
</style>
