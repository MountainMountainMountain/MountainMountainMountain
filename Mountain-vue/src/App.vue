<script setup>
// import { RouterLink, RouterView } from 'vue-router'
import NavBar from "./components/common/NavBar.vue";
import FooterBar from "./components/common/FooterBar.vue";



</script>

<template>
  <div>
    <nav-bar />
    <div style="height: 1000px;"></div>
    <router-view />
    <FooterBar />
  </div>
</template>

<style>

</style>
