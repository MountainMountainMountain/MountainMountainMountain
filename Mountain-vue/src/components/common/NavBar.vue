<template>
  <div class="container">
    <div class="userBar">
      <div class="brand">
        <router-link class="navbar-brand" to="/">
          <img style="width: 64px;" src="" alt="이미지">
        </router-link>
      </div>
      <div class="links">
        <router-link v-if="!isLoggedIn" class="nav-link" :to="{ name: 'Login' }">로그인</router-link>
        <router-link v-if="!isLoggedIn" class="nav-link" :to="{ name: 'SignUp' }"><i
            class="fas fa-user-plus"></i></router-link>
        <router-link v-if="isLoggedIn" class="nav-link" :to="{ name: 'MyInfo' }">{{ userName }}님</router-link>
        <router-link v-if="isLoggedIn" class="nav-link" :to="{ name: 'ChatList' }"><i
            class="fas fa-comments"></i></router-link>
        <a v-if="isLoggedIn" class="nav-link" @click="logout">로그아웃</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import { useUserStore } from '@/stores/userstore';

const userStore = useUserStore();
const route = useRoute();
const isLoggedIn = ref(false);
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
  userStore.logoutUser();
  location.replace('/');
};

const checkLoginStatus = () => {
  const token = sessionStorage.getItem('access-token');
  isLoggedIn.value = !!token;
};

onMounted(() => {
  checkLoginStatus();
});


</script>

<style scoped>
.container {
  background-color: rgba(74, 75, 73, 0.33);
  display: flex;
  align-items: center;
  height: 60px;
}

.userBar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding: 0 4%;
}

.brand {
  display: flex;
  align-items: center;
}

.links {
  display: flex;
  /* 로그인과 회원가입 링크를 가로로 나열 */
}

.links a {
  text-decoration: none;
  color: black;
  margin-left: 10px;
  /* 링크 간격을 조정할 수 있습니다 */
}

.links a:hover {
  color: blue;
}
</style>
