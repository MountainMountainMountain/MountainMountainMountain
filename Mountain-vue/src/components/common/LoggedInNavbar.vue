<template>
    <div class="container">
        <div class="userBar">
            <div class="brand">
                <router-link class="navbar-brand" to="/">
                    <img style="width: 64px;" src="" alt="이미지">
                </router-link>
            </div>
            <router-link class="navbar-brand" :to="{ name: 'MyInfo' }">
                <img style="width: 64px;" src="" alt="프로필이미지">
            </router-link>
            &nbsp;{{ userName }}님 환영합니다.
            &nbsp;
            <router-link class="nav-link" :to="{ name: 'ChatList' }"><i class="fas fa-comments"></i></router-link>
            &nbsp;<a class="nav-link" @click="logout">로그아웃</a>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const userName = ref(''); // 사용자 이름
const router = useRouter();

// sessionStorage에서 사용자 정보를 가져옵니다.
const token = sessionStorage.getItem('access-token');
if (token) {
    const payload = JSON.parse(atob(token.split('.')[1]));
    userName.value = payload.name;
}

// 로그아웃 함수
const logout = () => {
    // 클라이언트 측에서만 토큰을 삭제하고 로그인 페이지로 이동
    sessionStorage.removeItem('access-token');
    router.push('/login');
};

</script>

<style scoped>
.container {
    background-color: rgba(47, 48, 47, 0.2);
    display: flex;
    align-items: center;
    height: 60px;
}
.userBar {
    text-align: right;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    padding: 0 4%;
}
.brand {
    display: flex;
    align-items: center;
}
</style>
