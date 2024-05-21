<template>
    <div class="container">
        <div class="box">
            <p class="text-primary">마이 페이지</p>
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex align-items-center flex-column flex-md-row">
                                <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;" class="rounded-circle">
                                <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
                                    <h5 class="font-weight-bold d-inline-block me-2"> </h5>님 
                                    <!-- 여기 이름 {{  }} 들어와야 해 -->
                                    <div class="progress">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: 55%"
                                            aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                            <strong>55%</strong>
                                        </div>
                                    </div>
                                    <!-- 팔로워 팔로워 수 떠야 해 -->
                                    <a v-if="level != '3'" class="text-decoration-none d-inline-block text-primary">
                                        <strong>팔로워</strong> <span class="text-muted">followers</span> </a>
                                    <a v-if="level != '3'"
                                        class="text-decoration-none d-inline-block text-primary ms-3">
                                        <strong>팔로잉</strong> <span class="text-muted">following</span> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- <MyInfo /> -->
            <RouterLink :to="{ name: 'MyInfo' }">내 정보</RouterLink> |
            <RouterLink :to="{ name: 'MyFriends' }">친구관리</RouterLink> |
            <RouterLink :to="{ name: 'MyComplete' }">정복 산</RouterLink>
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userstore';
import { computed, onMounted } from 'vue';
import MyInfo from './MyInfo.vue';

const userStore = useUserStore();

onMounted(() => {
    userStore.getUser();
    // userStore.getfollwingList(userStore.User);
    // userStore.getfollwerList(userStore.User);
})

// const { User, FollowingList, FollowerList } = userStore;

const user = computed(() => userStore.User);

// const UserStore = useUserStore();

// onMounted(() => {
//     UserStore.getUser();
// })
</script>

<style scoped>
.container {
    display: flex;
    height: auto;
    background-color: white;
    border: 1px solid rgb(209, 209, 209);
    width: auto;
    padding: 50px;
}

.box {
    text-align: left;
    /* border: 1px solid rgb(204, 211, 236); */
    padding: 50px;
    /* box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); */
    background-color: white;
    height: 100%;
    width: 100%;

}
</style>