<script setup>
// import { RouterLink, RouterView } from 'vue-router'
import NavBar from "./components/common/NavBar.vue";
import FooterBar from "./components/common/FooterBar.vue";



</script>

<template>
  <div>
    <!-- <nav-bar /> -->
    <!-- <nav>
      <RouterLink to="/">mountain</RouterLink>
      <RouterLink to="/login">login</RouterLink>
    </nav> -->
    <router-view />
    <FooterBar />
  </div>
</template>

<style>

</style>
