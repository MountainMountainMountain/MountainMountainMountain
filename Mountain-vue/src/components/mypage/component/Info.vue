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
                                        <img src="@/assets/images/mountain1.jpg" alt="프로필" class="rounded-circle">
                                        <a> 이메일: {{ userStore.User.email }}</a>
                                    </div>
                                    <div class="info-item">
                                        <img src="@/assets/images/mountain1.jpg" alt="프로필" class="rounded-circle">
                                        <a> 이름: {{ userStore.User.name }}</a>
                                    </div>
                                </div>
                            </div>
                            <router-link
                                :to="{ name: 'MyInfoMainModify', params: { userId: userId.value } }"><button>123</button></router-link>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
// import MyState from './MyState.vue';
import { useUserStore } from '@/stores/userstore';
import { ref, computed, onMounted } from 'vue';

const userStore = useUserStore();

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const userId = ref(''); // 사용자 id
const userEmail = ref(''); // 사용자 email
const token = sessionStorage.getItem('access-token');

// let id = JSON.parse(atob(token[1]))['id'];


const checkUserSerial = () => {
    const payload = JSON.parse(atob(token.split('.')[1]));
    userName.value = payload.name;
    userSerial.value = payload.serial;
    userId.value = payload.id;
    userEmail.value = payload.email;
    console.log(payload)
}

onMounted(() => {
    checkUserSerial();
    userStore.getUserByid(userId.value)
    // userStore.getUserByid();
    // userStore.getfollwingList(userStore.User);
    // userStore.getfollwerList(userStore.User);
})
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

.info-item img {
    width: 30px;
    height: 30px;
    margin-right: 10px;
}
</style>