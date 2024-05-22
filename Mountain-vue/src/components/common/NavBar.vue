<template>
  <div class="userBar">
    <div class="brand">
      <router-link class="navbar-brand" to="/">
        <img style="width: 64px;" src="@/assets/images/Mlogo.png" alt="이미지">
      </router-link>
    </div>
    <div class="links">
      <router-link v-if="!isLoggedIn" class="nav-link" :to="{ name: 'Login' }">로그인</router-link>
      <router-link v-if="!isLoggedIn" class="nav-link" :to="{ name: 'SignUp' }"><i
          class="fas fa-user-plus"></i></router-link>
      <router-link v-if="isLoggedIn" class="nav-link" :to="{ name: 'MyInfoMain', params: { userId: userId } }">{{
        userName }}님</router-link>
      <router-link v-if="isLoggedIn" class="nav-link" :to="{ name: 'ChatList' }"><i
          class="fas fa-comments"></i></router-link>
      <a v-if="isLoggedIn" class="nav-link" @click="logout">로그아웃</a>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/userstore';

const userStore = useUserStore();
const isLoggedIn = ref(false);
const userName = ref('');
const userSerial = ref('');
const userId = ref('');

const router = useRouter();

const checkLoginStatus = () => {
  const token = sessionStorage.getItem('access-token');
  if (token) {
    try {
      const tokenPayload = JSON.parse(decodeURIComponent(escape(atob(token.split('.')[1]))));
      userName.value = tokenPayload['name'];
      userSerial.value = tokenPayload['serial'];
      userId.value = tokenPayload['id'];
      isLoggedIn.value = true;
    } catch (error) {
      console.error('Failed to decode token:', error);
      isLoggedIn.value = false;
    }
  } else {
    isLoggedIn.value = false;
  }
};

// 로그아웃 함수
const logout = () => {
  userStore.logoutUser();
  sessionStorage.removeItem('access-token');
  location.replace('/');
};

onMounted(() => {
  checkLoginStatus();
});
</script>

<style scoped>
.userBar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding: 0 4%;
  height: 100px;
  font-family: "Gowun Dodum", sans-serif;
  font-weight: 400;
  font-style: normal;
  font-size: large;
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
