<template>
  <div>
    <hr>
    <br>
    <Photo />
    <button class="btn btn-light back-button" @click="backButton">목록으로 돌아가기</button>
    <div id="index_wrap">
      <ul id="leftToRight">
        <li>
          <RouterLink class="buttonbar" :to="{ name: 'MountainInfo' }"><strong>정보</strong></RouterLink>
        </li>
        <li>
          <RouterLink class="buttonbar" :to="{ name: 'MountainLocation' }"><strong>위치</strong></RouterLink>
        </li>
        <li>
          <RouterLink class="buttonbar" v-if="token !== null" @click="showReplyForm = true" :to="{ name: 'Book' }"><strong>예약</strong></RouterLink>
        </li>
      </ul>
    </div>
    <RouterView />
  </div>
</template>

<script setup>
import Photo from '@/components/mountain/component/Photo.vue';

import { useRouter } from 'vue-router';
const router = useRouter();
import { ref, computed, onMounted } from 'vue';
import { useUserStore } from '@/stores/userstore';
const userStore = useUserStore();
const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const token = sessionStorage.getItem('access-token');
const checkUserSerial = () => {
  if (token !== null) {
    const payload = JSON.parse(atob(token.split('.')[1]));
    userName.value = payload.name;
    userSerial.value = payload.serial;
    userStore.getUserByid(userSerial.value);
  }
}
const backButton = function () {
    router.go(-1);
}
onMounted(() => {
  checkUserSerial();
})
</script>

<style scoped>
.buttonbar {
  color: white;
  text-align: center;
  padding: 15px 20px;
  text-decoration: none;
  cursor: pointer;
  flex: 1;
}

.router-link-active:hover {
  border-bottom: none;
}

#index_wrap ul {
  display: block;
  margin-top: 40px;
  margin-bottom: 10px;
}

#index_wrap ul li {
  position: relative;
  display: inline-block;
  margin: 0 20px;
  height: 40px;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
}

#index_wrap ul p {
  font-size: 1.1rem;
  font-weight: 800;
}

#index_wrap ul li a {
  color: #000;
}

#leftToRight li::after {
  position: absolute;
  content: "";
  display: block;
  border-bottom: 3px solid #0615f0;
  transition: width 250ms ease-out;
  left: auto;
  right: 0;
  width: 0;
}

#leftToRight li:hover::after {
  width: 100%;
  left: 0;
  right: auto;
}

#fromInsideOut li::after {
  position: absolute;
  content: "";
  display: block;
  border-bottom: 3px solid #000;
  transition: all 250ms ease-out;
  left: 50%;
  width: 0;
}

#fromInsideOut li:hover::after {
  transition: all 250ms ease-out;
  left: 0%;
  width: 100%;
}

#stretch li::after {
  position: absolute;
  content: "";
  display: block;
  border-bottom: 3px solid #000;
  transition: width 250ms ease-in-out;
  left: auto;
  width: 0;
}

#stretch li:hover::after {
  width: 100%;
}

.back-button {
  float: right;
  margin-right: 20px;
  margin-bottom: 20px;
}
</style>