<template>
    <div>
        <div class="photobar">
            <Photo />
        </div>
        <RouterLink :to="{ name: 'MountainInfo' }">MountainInfo</RouterLink> |
        <RouterLink :to="{ name: 'MountainLocation' }">MountainLocation</RouterLink> |
        이건 동적으로 바뀔 예정
        <RouterLink :to="{ name: 'Book' }">MountainBook</RouterLink>
        <RouterView />
        <CommentStar />
        <CommentList />
        <RouterLink :to="{ name: 'CommentCreatePage' }">CommentCreatePage</RouterLink>
    </div>
</template>

<script setup>
import Photo from '@/components/mountain/component/Photo.vue';
// import CommentStar from '@/components/comment/component/CommentStar.vue';
// import CommentList from '@/components/comment/component/CommentList.vue';
import MountainInfo from '@/components/mountain/component/MountainInfo.vue';
</script>

<style scoped>

</style>