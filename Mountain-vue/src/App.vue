<script setup>
// import { RouterLink, RouterView } from 'vue-router'
import NavBar from "./components/layout/NavBar.vue";
import Footer from "./components/layout/Footer.vue";



</script>

<template>
  <div>
    <nav-bar />
    <div style="height: 1000px;"></div>
    <router-view />
    <Footer />
  </div>
</template>

<style>

</style>
