<template>
    <div class="Infocontainer">
        <a class="title" style="text-decoration: none; color: black; font-size: larger" >기본 정보</a>
        <div class="Infobox">
            <!-- <MyState /> -->
            <!-- 아이디, 이메일, 여기에 팔로우 뜨게 할까? -->
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex flex-column align-items-start">
                                <div class="info-item">
                                    <i class="bi bi-person-circle fs-7"></i>
                                    <a> 아이디: </a>
                                </div>
                                <div class="info-item">
                                    <img src="@/assets/images/mountain1.jpg" alt="프로필" class="rounded-circle">
                                    <a> 이메일: </a>
                                </div>
                                <div class="info-item">
                                    <img src="@/assets/images/mountain1.jpg" alt="프로필" class="rounded-circle">
                                    <a> 몰라: </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
// import MyState from './MyState.vue';
import { useUserStore } from '@/stores/userstore';
import { computed, onMounted } from 'vue';

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
.Infocontainer {
    display: flex;
    flex-direction: column;
    background-color: rgb(244, 255, 239);
    border: 1px solid rgb(209, 209, 209);
    padding: 30px;
}

.title {
    align-self: flex-start;
    margin-bottom: 20px;
    font-weight: bold;
}

.Infobox {
    text-align: left;
    padding: 50px;
    background-color: white;
    width: 100%;
}

.info-item {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
}

.info-item img {
    width: 30px;
    height: 30px;
    margin-right: 10px;
}
</style>
