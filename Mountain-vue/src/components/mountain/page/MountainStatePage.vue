<template>
    <div>

        <router-link :to="{ name: 'MountainStatePage', params: { state: '수도권' } }">수도권</router-link> |
        <router-link :to="{ name: 'MountainStatePage', params: { state: '강원도' } }">강원도</router-link> |
        <router-link :to="{ name: 'MountainStatePage', params: { state: '충청도' } }">충청도</router-link> |
        <router-link :to="{ name: 'MountainStatePage', params: { state: '전라도' } }">전라도</router-link> |
        <router-link :to="{ name: 'MountainStatePage', params: { state: '경상도' } }">경상도</router-link> |
        <router-link :to="{ name: 'MountainStatePage', params: { state: '제주도' } }">제주도</router-link>


        <Search />
        <List />

    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import Search from "../component/Search.vue"
import List from "../component/List.vue"
import { onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';
const route = useRoute();
const mountainStore = useMountainStore();

</script>

<style scoped></style>