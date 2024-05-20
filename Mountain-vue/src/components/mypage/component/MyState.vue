<template>
    <div class="container">
        <div class="box">
            <p class="text-primary">마이 페이지</p>
            <!-- <img style="width: 50px; border-radius: 30%;" src="@/assets/images/mountain1.jpg" alt="캐릭터"> -->
            <!-- {{ UserStore. }} -->
            <!-- 
            <div>
                <p>이름: {{ userStore.name }}</p>
                <p>점수:</p>
                <p>팔로잉: </p>
                 <p>{{ userStore.FollowerList }}</p>
            <p>{{ userStore.FollowerList }}</p> 
            </div>-->
            <!-- <div class="progress">
                <div class="progress-bar bg-warning" role="progressbar" style="width: 25%" aria-valuenow="25"
                    aria-valuemin="0" aria-valuemax="100"></div>
            </div> -->


            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex align-items-center flex-column flex-md-row">
                                <img src="" alt="프로필"
                                    style="width: 140px; height: 140px;" class="rounded-circle">
                                <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
                                    <h5 class="font-weight-bold d-inline-block me-2"> </h5>님
                                    <div class="progress">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: 55%"
                                            aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"><strong>55%</strong></div>
                                    </div>
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
        </div>
    </div>

</template>

<script setup>
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

</script>

<style scoped>
.container {
    display: flex;
    /* justify-content: center; */
    /* align-items: center; */
    height: 200vh;
    background-color: #f5f5f5;
    width: auto;
    padding: 20px;
}

.box {
    text-align: left;
    border: 1px solid rgb(129, 127, 127);
    /* border-radius: 10px; */
    padding: 50px;
    /* box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); */
    background-color: white;
    height: 400px;
    width: 400px;

}
</style>