<template>
    <div>
        <MainNavBar/>
       <FooterBar/>
       <router-view />
    </div>
</template>

<script setup>
import MainNavBar from '@/components/common/MainNavBar.vue';
import FooterBar from '@/components/common/FooterBar.vue';

</script>

<style scoped>

</style>