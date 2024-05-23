<template>
  <div class="container">
    <div class="box">
      <p class="mytext">| 마이 페이지 |</p>
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-body">
              <div class="d-flex align-items-center flex-column flex-md-row">
                <div v-if="userStore.User.point > 1000">
                  <img src="@/assets/images/sqq.jpeg" alt="프로필" style="width: 200px; height: 120px; margin-top: 30px;"
                    class="rounded-circle">
                </div>
                <div v-else-if="1000 > userStore.User.point && userStore.User.point >= 800">
                  <img src="@/assets/images/godgod.png" alt="프로필" style="width: 130px; height: 120px; margin-left: 30px;"
                    class="rounded-circle">
                </div>
                <div v-else-if="800 > userStore.User.point && userStore.User.point >= 600">
                  <img src="@/assets/images/glass.png" alt="프로필" style="width: 180px; height: 280px;"
                    class="rounded-circle">
                </div>
                <div v-else-if="600 > userStore.User.point && userStore.User.point >= 400">
                  <img src="@/assets/images/glassonly.png" alt="프로필" style="width: 190px; height: 280px;"
                    class="rounded-circle">
                </div>
                <div v-else-if="400 > userStore.User.point && userStore.User.point >= 200">
                  <img src="@/assets/images/cap.png" alt="프로필" style="width: 190px; height: 280px;"
                    class="rounded-circle">
                </div>
                <div v-else-if="200 > userStore.User.point && userStore.User.point >= 0">
                  <img src="@/assets/images/dora2.jpg" alt="프로필" style="width: 200px; height: 280px;"
                    class="rounded-circle">
                </div>

                <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
                  <h5 class="font-weight-bold d-inline-block me-2"> </h5>{{ userStore.User.name }}님
                  <br>
                  <!-- 여기 이름 {{  }} 들어와야 해 -->
                  <!-- 팔로워 팔로워 수 떠야 해 -->
                  <a v-if="level != '3'" class="text-decoration-none d-inline-block text-primary">
                    <strong>팔로워 {{ userStore.FollowerList.length }}</strong> <span class="text-muted"></span> </a>
                  <br>
                  <a v-if="level != '3'" class="text-decoration-none d-inline-block text-primary">
                    <strong>팔로잉 {{ userStore.FollowingList.length }}</strong> <span class="text-muted"></span> </a>
                </div>
              </div>
            </div>
            <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
              <!-- <div class="progress">
                      <div class="progress-bar bg-warning" role="progressbar" :style="{ width: progressWidth + '%' }" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">
                        <strong>{{ progressWidth }}%</strong>
                      </div>
                    </div> -->

              <div class="zt-span6 last">
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <h3><strong>진행율 바</strong></h3>
                <div class="zt-skill-bar">
                  <div :style="{ width: progressWidth + '%' }">point<span>{{ progressWidth }}%</span></div>
                </div>
              </div>

            </div>
            <div class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center">
              <div class="zt-span6 last">
                <h3><strong>point</strong></h3>
                <div :class="['zt-skill-bar', progressClass]">
                  <div :style="{ width: progressWidth + '%' }">point<span>{{ progressWidth }}%</span></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- <MyInfo /> -->
      <div v-if="userId == userStore.User.id">
        <RouterLink :to="{ name: 'MyInfoMain', params: { userId: `${route.params.userId}` } }">내 정보</RouterLink> |
        <RouterLink :to="{ name: 'MyFriends', params: { userId: `${route.params.userId}` } }">친구관리</RouterLink> |
        <RouterLink :to="{ name: 'MyComplete', params: { userId: `${route.params.userId}` } }">정복 산</RouterLink>
      </div>
      <!-- <div v-else class="text-end">
        <router-link class="nav-link" :to="{ name: 'MyInfoMain', params: { userId: userId } }">
          <button>내 정보 보기</button>
        </router-link>
      </div> -->
      <RouterView />
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userstore';
import { useCommentStore } from '@/stores/commentstore';
import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const commentStore = useCommentStore();

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial
const userId = ref(''); // 사용자 serial

const checkLoginStatus = () => {
  const token = sessionStorage.getItem('access-token');
  if (token) {
    try {
      const tokenPayload = JSON.parse(decodeURIComponent(escape(atob(token.split('.')[1]))));
      userName.value = tokenPayload['name'];
      userSerial.value = tokenPayload['serial'];
      userId.value = tokenPayload['id'];
    } catch (error) {
      console.error('Failed to decode token:', error);
    }
  }
};

onMounted(async () => {
  await checkLoginStatus();
  userStore.getUserByid(`${route.params.userId}`).then(() => {
    commentStore.completeComment(userStore.User.serial);
    userStore.getfollwerList(userStore.User.serial);
    userStore.getfollwingList(userStore.User.serial);
  })
})

const user = computed(() => userStore.User);

const progressWidth = computed(() => {
  // 포인트에 따른 프로그레스 바의 퍼센티지 계산 (예시)
  const point = userStore.User.point;
  return Math.min((point / 1000) * 100, 100); // 최대 100%를 넘지 않도록 설정
});

const progressClass = computed(() => {
  const point = userStore.User.point;
  if (point >= 1) return 'zt-skill-bar-high';
  return 'zt-skill-bar-very-low';
});
</script>

<style scoped>
.container {
  display: flex;
  height: auto;
  background-color: white;
  border: 1px solid rgb(209, 209, 209);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: auto;
  padding: 20px;
}

.mytext {
  font-size: larger;
  color: blue;
  font-family: "Gowun Dodum", sans-serif;
}

.box {
  text-align: left;
  padding: 50px;
  background-color: white;
  height: 100%;
  width: 100%;
}

.infobar {
  margin-top: 20px;
}

.nav-link {
  text-decoration: none;
  color: black;
  display: inline-block;
  padding: 10px 15px;
  border-top: 2px solid transparent; /* 상단 테두리로 변경 */
  transition: border-color 0.3s ease;
}

.nav-link:hover {
  color: rgb(23, 26, 245);
  font-weight: bold;
  border-top: 3px solid #e21b1b;
}

.zt-skill-bar {
  color: #fff;
  font-size: 10px;
  line-height: 5px;
  height: 15px;
  margin-bottom: 5px;
  background-color: rgba(0, 0, 0, 0.1);
  border-radius: 2px;
}

.zt-skill-bar div {
  background-color: #ffc600;
  position: relative;
  padding-left: 25px;
  width: 0;
  border-radius: 2px;
  transition: width 0.5s ease;
}

.zt-skill-bar span {
  display: block;
  position: absolute;
  right: 0;
  top: 0;
  height: 22px;
  padding: 0 5px 0 10px;
  background-color: #e02323;
  border-radius: 0 2px 2px 0;
}

.zt-skill-bar span:before {
  content: "";
  position: absolute;
  width: 6px;
  height: 6px;
  top: 50%;
  left: -3px;
  margin-top: -3px;
  background-color: #c01a1a;
  transform: rotate(45deg);
}

.zt-skill-bar-high div {
  background-color: #ffc600;
}

.zt-skill-bar div {
  min-width: 20px;
}
</style>
