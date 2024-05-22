<template>
    <div>
        <h1>modify</h1>
        <div class="container">
            <div class="form-floating mb-3">
                <input type="text" class="form-control form-control-sm" placeholder="Id" :value="userStore.User.id"
                    readonly>
                <label for="id">Id</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="name" placeholder="이름" v-model="userStore.User.name">
                <label for="name">Name</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="email" placeholder="이메일" v-model="userStore.User.email">
                <label for="email">Email</label>
            </div>

            <div class="d-flex justify-content-end">
                <button class="btn btn-outline-success mx-3" @click="updateUser">수정</button>
                <button class="btn btn-outline-danger" @click="backButton">뒤로</button>
            </div>

        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userstore';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';
const userStore = useUserStore()
const route = useRoute()
const router = useRouter()

const updateUser = function () {
    userStore.updateUser(userStore.User)
        .then(() => {
            userStore.logoutUser();
            location.replace('/');
        })
}
const backButton = function () {
    router.go(-1);
    // router.back();
}

onMounted(() => {
    userStore.getUserByid(`${route.params.userId}`)
});
</script>

<style scoped></style>