<template>
    <div class="container">
        <div class="box">
            <p class="text-primary">마이 페이지</p>
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex align-items-center flex-column flex-md-row">


                                <div v-if="userStore.User.point > 1000">
                                    <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                        class="rounded-circle"> 하늘다람쥐
                                </div>
                                <div v-else-if="1000 > userStore.User.point && userStore.User.point >= 800">
                                    <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                        class="rounded-circle"> 산신령
                                </div>
                                <div v-else-if="800 > userStore.User.point && userStore.User.point >= 600">
                                    <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                        class="rounded-circle"> 모자, 선글라스, 스틱
                                </div>
                                <div v-else-if="600 > userStore.User.point && userStore.User.point >= 400">
                                    <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                        class="rounded-circle"> 모자 선글라스
                                </div>
                                <div v-else-if="400 > userStore.User.point && userStore.User.point >= 200">
                                    <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                        class="rounded-circle"> 모자
                                </div>
                                <div v-else-if="200 > userStore.User.point && userStore.User.point >= 0">
                                    <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                        class="rounded-circle"> 원형
                                </div>

                                <!-- <img src="@/assets/images/daram.jpg" alt="프로필" style="width: 140px; height: 140px;"
                                    class="rounded-circle"> -->



                                <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
                                    <h5 class="font-weight-bold d-inline-block me-2"> </h5>{{ userName }}님
                                    <!-- 여기 이름 {{  }} 들어와야 해 -->
                                    <div class="progress">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: 55%"
                                            aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                                            <strong>55%</strong>
                                        </div>
                                    </div>
                                    <!-- 팔로워 팔로워 수 떠야 해 -->
                                    <a v-if="level != '3'" class="text-decoration-none d-inline-block text-primary">
                                        <strong>팔로워 {{ userStore.FollowerList.length }}</strong> <span
                                            class="text-muted"></span> </a>
                                    <br>
                                    <a v-if="level != '3'" class="text-decoration-none d-inline-block text-primary">
                                        <strong>팔로잉 {{ userStore.FollowingList.length }}</strong> <span
                                            class="text-muted"></span> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- <MyInfo /> -->
            <RouterLink :to="{ name: 'MyInfoMain', params: { userId: `${route.params.userId}` } }">내 정보</RouterLink> |
            <RouterLink :to="{ name: 'MyFriends', params: { userId: `${route.params.userId}` } }">친구관리</RouterLink> |
            <RouterLink :to="{ name: 'MyComplete', params: { userId: `${route.params.userId}` } }">정복 산</RouterLink>
            <RouterView />
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userstore';
import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import MyInfo from './MyInfo.vue';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial

// let id = JSON.parse(atob(token[1]))['id'];


const checkLoginStatus = () => {
  const token = sessionStorage.getItem('access-token');
  if (token) {
    try {
      const tokenPayload = JSON.parse(decodeURIComponent(escape(atob(token.split('.')[1]))));
      userName.value = tokenPayload['name'];
      userSerial.value = tokenPayload['serial'];
    } catch (error) {
      console.error('Failed to decode token:', error);
    }
  } else {
  }
};

onMounted(() => {
    checkLoginStatus();
    userStore.getfollwerList(userSerial.value);
    userStore.getfollwingList(userSerial.value);
    // userStore.getUserByid();
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