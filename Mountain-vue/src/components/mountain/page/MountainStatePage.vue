<template>
  <div class="navigation-menu">
    <router-link :to="{ name: 'MountainStatePage', params: { state: '전체' } }" class="menu-item" data-hover="전체">전체</router-link>
    <router-link :to="{ name: 'MountainStatePage', params: { state: '수도권' } }" class="menu-item" data-hover="수도권">수도권</router-link>
    <router-link :to="{ name: 'MountainStatePage', params: { state: '강원도' } }" class="menu-item" data-hover="강원도">강원도</router-link>
    <router-link :to="{ name: 'MountainStatePage', params: { state: '충청도' } }" class="menu-item" data-hover="충청도">충청도</router-link>
    <router-link :to="{ name: 'MountainStatePage', params: { state: '전라도' } }" class="menu-item" data-hover="전라도">전라도</router-link>
    <router-link :to="{ name: 'MountainStatePage', params: { state: '경상도' } }" class="menu-item" data-hover="경상도">경상도</router-link>
    <router-link :to="{ name: 'MountainStatePage', params: { state: '제주도' } }" class="menu-item" data-hover="제주도">제주도</router-link>
  </div>
  <Search/>
</template>

<script setup>
import { useRoute } from 'vue-router';
import Search from '../component/Search.vue';
const route = useRoute();
</script>

<style scoped>
.navigation-menu {
  display: flex;
  justify-content: space-between; /* 메뉴 아이템들을 균등하게 배치합니다. */
  align-items: center;
  width: 60%;
  margin: 0 auto; /* 중앙 정렬을 위해 자동 마진을 추가합니다. */
}

.menu-item {
  position: relative;
  margin: 0 10px;
  font-size: 16px;
  color: #101011;
  text-decoration: none;
  transition: color 0.3s, transform 0.3s;
  padding: 10px 0;
}

.menu-item:before {
  content: attr(data-hover);
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translateX(-50%);
  color: #fff;
  text-shadow: 0 0 1px rgba(255, 255, 255, 0.3);
  opacity: 0;
  transition: transform 0.3s, opacity 0.3s;
  pointer-events: none;
}

.menu-item:after {
  content: '';
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 2px;
  background: #dc5f06;
  opacity: 0;
  transform: translateY(5px);
  transition: transform 0.3s, opacity 0.3s;
  pointer-events: none;
}

.menu-item:hover {
  color: #1920e7;
  transform: translateY(2px);
  font: bold;
}

.menu-item:hover:before {
  opacity: 0;
  transform: translateY(-2px);
}

.menu-item:hover:after {
  opacity: 1;
  transform: translateY(0px);
}
</style>
