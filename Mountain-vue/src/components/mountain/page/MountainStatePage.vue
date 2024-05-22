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
    justify-content: center;
    align-items: center;
  }
  
  .menu-item {
    position: relative;
    margin: 0 10px;
    font-size: 16px;
    color: #686de0;
    text-decoration: none;
    transition: color 0.3s;
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
    transition: opacity 0.3s, top 0.3s;
    pointer-events: none;
  }
  
  .menu-item:hover:before {
    opacity: 1;
    top: calc(100% + 5px);
  }
  </style>
  