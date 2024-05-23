<template>
    <div>
        <h1>회원 정보 수정</h1>
        <div class="container">
            <div class="form-floating mb-3">
                <input type="text" class="form-control form-control-sm" placeholder="Id" :value="userStore.User.id"
                    readonly>
                <label for="id">아이디</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="name" placeholder="이름" v-model="userStore.User.name">
                <label for="name">이름</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="email" placeholder="이메일" v-model="userStore.User.email">
                <label for="email">이메일</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password" placeholder="비밀번호" v-model="password">
                <label for="password">새로운 비밀번호</label>
            </div>
            <div class="d-flex justify-content-end mt-4">
                <button class="btn btn-primary" @click="updateUser">수정</button>
                <button class="btn btn-outline-danger" @click="backButton">뒤로</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userstore';
import { useRoute, useRouter } from 'vue-router';
import { ref } from 'vue';
import Swal from 'sweetalert2';

const userStore = useUserStore();
const route = useRoute();
const router = useRouter();
const backButton = function () {
    router.go(-1);
    // router.back();
};
const password = ref('');

const updateUser = () => {
    userStore.updateUser({ ...userStore.User, password: password.value })
        .then(() => {
            Swal.fire({
                title: '수정이 완료되었습니다!',
                icon: 'success',
                confirmButtonText: '확인'
            }).then(() => {
                userStore.logoutUser(); // 수정 후 로그아웃
                location.replace('/');
            });
        })
        .catch((error) => {
            console.error('수정 중 오류 발생:', error);
            Swal.fire({
                title: '수정에 실패했습니다',
                text: '다시 시도해주세요',
                icon: 'error',
                confirmButtonText: '확인'
            });
        });
    const backButton = function () {
        router.go(-1);
        // router.back();
    }
};
</script>

<style scoped>

</style>