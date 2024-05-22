<template>
    <div>
        <div class="Infocontainer">
            <a class="title" style="text-decoration: none; color: black; font-size: larger">기본 정보</a>
            <div class="Infobox">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex flex-column align-items-start">
                                    <div class="info-item">
                                        <i class="bi bi-person-circle fs-7"></i>
                                        <a> 아이디: {{ userStore.User.id }}</a>
                                    </div>
                                    <div class="info-item">
                                        <i class="bi bi-envelope"></i>
                                        <a> 이메일: {{ userStore.User.email }}</a>
                                    </div>
                                    <div class="info-item">
                                        <i class="bi bi-person"></i>
                                        <a> 이름: {{ userStore.User.name }}</a>
                                    </div>
                                </div>
                            </div>
                            <router-link :to="{ name: 'MyInfoMainModify', params: { userId: userId.value } }">
                                <button>정보 수정</button>
                            </router-link>

                            <button @click="confirmDeleteUser">회원 탈퇴</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
const router = useRouter();
import { useUserStore } from '@/stores/userstore';
import { ref, onMounted } from 'vue';
import axios from 'axios';

const userStore = useUserStore();

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const userId = ref(''); // 사용자 id
const userEmail = ref(''); // 사용자 email
const token = sessionStorage.getItem('access-token');

const checkUserSerial = () => {
    if (token) {
        try {
            const payload = JSON.parse(atob(token.split('.')[1]));
            userName.value = payload.name;
            userSerial.value = payload.serial;
            userId.value = payload.id;
            userEmail.value = payload.email;
            console.log(payload);
        } catch (error) {
            console.error('토큰 파싱 중 오류 발생:', error);
        }
    } else {
        console.warn('토큰이 없습니다.');
    }
}

const deleteComment = function () {
    axios
        .delete(`http://localhost:8080/api-user/user/${userSerial.value}`)
        .then(() => {
            sessionStorage.removeItem('access-token')

        })
        .catch(() => { });
};

const confirmDeleteUser = () => {
    Swal.fire({
        title: '유저를 삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteComment();
            Swal.fire('삭제되었습니다!', '', 'success')
                .then(() => {
                    checkUserSerial();
                    location.replace('/');
                })
        }
    });
};

onMounted(() => {
    checkUserSerial();
    if (userId.value) {
        userStore.getUserByid(userId.value)
            .then(() => {
                console.log('사용자 정보 로드 완료');
            })
            .catch((error) => {
                console.error('사용자 정보 로드 중 오류 발생:', error);
            });
    }
});
</script>

<style scoped>
.Infocontainer {
    display: flex;
    flex-direction: column;
    background-color: rgb(244, 255, 239);
    border: 1px solid rgb(209, 209, 209);
    padding: 30px;
}

.title {
    align-self: flex-start;
    margin-bottom: 20px;
    font-weight: bold;
}

.Infobox {
    text-align: left;
    padding: 50px;
    background-color: white;
    width: 100%;
}

.info-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}

.info-item i {
    margin-right: 10px;
}

.info-item a {
    text-decoration: none;
    color: black;
}
</style>
