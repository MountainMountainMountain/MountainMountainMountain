<template>

    <div id="signup-container">
        <form id="signup">
            <fieldset id="signupbox">

                <h2 style="text-align: center; color: green; font-size: xx-large">산산산 가입을 환영합니다 !</h2>
                <hr>

                <input v-model="user.id" type="text" placeholder="아이디">
                <button @click.prevent="idCheck" style="width: 20px;height: 20px;"></button>
                <input v-model="user.password" type="password" placeholder="비밀번호">
                <input v-model="user.repassword" type="password" placeholder="비밀번호 재입력">
                <button @click.prevent="passwordcheck" style="width: 20px;height: 20px;"></button>
                <!-- <input v-model="userRePassword" @change="passwordcheck" id="userRePassword" type="password"> -->

                <input v-model="user.name" type="text" placeholder="이름">
                <input v-model="user.email" type="text" placeholder="email">
                <input v-model="user.birthDate" type="text" placeholder="생일">
                <input v-model="user.gender" type="text" placeholder="성별">

                <button @click.prevent="createUser" style="width: 20px;height: 20px;"></button>


            </fieldset>
        </form>
    </div>
</template>

<script setup>

import MainNavBar from '@/components/common/MainNavBar.vue';

import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router';
import { useUserStore } from '@/stores/userstore'

const user = ref({
    id: "",
    password: "",
    repassword: "",
    name: "",
    email: "",
    birthDate: "1990-01-01",
    gender: "",
})

const userStore = useUserStore();
const router = useRouter();
const route = useRoute();

const idCheck = () => {
    userStore.checkId(user.value.id)
}

const passwordcheck = () => {
    if (user.value.password.length < 8) {
        alert("비밀번호를 최소 8자 이상 작성하세요.");
    } else if (user.value.password != user.value.repassword) {
        console.log("비밀번호 다름")
        alert("비밀번호가 일치하지 않습니다.");
        // 필요에 따라 다른 작업을 수행할 수 있습니다.
    } else {
        alert("비밀번호가 일치합니다.");
    }
}

const createUser = () => {
    userStore.createUser(user.value)
        .then(() => {
            router.push({ name: 'Login' })
        })
        .catch(() => {
            
        })
}

</script>

<style scoped></style>