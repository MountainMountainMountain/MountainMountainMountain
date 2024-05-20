<template>
    <div>
        <MainLoginNavBar />
        <MyState />
        <RouterView />
        <RouterLink :to="{ name: 'MyInfo' }">myinfo</RouterLink> | 
        <RouterLink :to="{ name: 'MyFriends' }">myfrined</RouterLink> | 
        <RouterLink :to="{ name: 'MyComplete' }">mycomplete</RouterLink>
    </div>
</template>

<script setup>
import MyState from "../component/MyState.vue"
import MainLoginNavBar from '@/components/common/MainLoginNavBar.vue';
</script>

<style scoped></style>