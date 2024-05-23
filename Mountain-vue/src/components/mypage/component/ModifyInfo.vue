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
                <input type="text" class="form-control" id="email" placeholder="이메일" v-model="userStore.User.email" @change="emailCheck">
                <label for="email">이메일</label>
                <div v-if="!isEmailValid" class="error-message">* 유효한 이메일 주소를 입력하세요</div>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password" placeholder="비밀번호" v-model="password" @change="passwordCheck">
                <label for="password">새로운 비밀번호</label>
                <div v-if="!isPasswordValid" class="error-message">* 비밀번호는 8자 이상이어야 합니다</div>
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

const password = ref('');
const isPasswordValid = ref(true);
const isEmailValid = ref(true);

const emailCheck = () => {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    isEmailValid.value = emailPattern.test(userStore.User.email);
};

const passwordCheck = () => {
    isPasswordValid.value = password.value.length >= 8;
};

const updateUser = () => {
    emailCheck();
    passwordCheck();

    if (isEmailValid.value && isPasswordValid.value) {
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
    } else {
        Swal.fire({
            title: '입력 오류',
            text: '유효한 이메일과 비밀번호를 입력하세요.',
            icon: 'warning',
            confirmButtonText: '확인'
        });
    }
};

const backButton = () => {
    router.go(-1);
};
</script>

<style scoped>
.error-message {
    color: red;
    font-size: 0.875em;
    margin-top: 5px;
}
</style>
