<template>
    <div class="friendcontainer">
      <a class="title" style="text-decoration: none; color: black; font-size: larger; font-weight: bolder;">친구 관리</a>
      <div class="searchbar">
        <div>
          <label><input type="text" class="form-control" style="width: 600px;" v-model="name" placeholder="이름을 입력하세요" /></label>
          <button class="btn btn-primary" @click="searchFriendList" style="width: 45px; height: 40px">
            <i class="fas fa-search" style="color: white; text-align: center;"></i>
          </button>
        </div>
      </div>
      <br>
      <div v-for="friend in userStore.UserList" :key="friend.serial" class="friend-item">
        {{ friend.name }}
        <i class="bi bi-person-plus-fill add-friend-icon" @click="confirmFollowFriend(friend)"></i>
      </div>
  
      <br>
      <h4 style="font-weight: bold;">following</h4>
  
      <div class="followlist" v-for="following in userStore.FollowingList" :key="following.serial">
        <i class="bi bi-person-heart">
          <br>&nbsp;&nbsp;&nbsp;{{ following.name }}
        </i>
        <div class="friendbutton">
          <button @click="checkInfo(following, mountainList)" class="btn btn-success">정보 보기</button>
          <button @click="confirmCancelFollowFriend(following)" class="btn btn-danger">친구 삭제하기</button>
          <!-- <i class="bi bi-person-fill-dash fs-5" @click="confirmCancelFollowFriend(following)"></i>/ -->
        </div>
      </div>
      <br>
      <br>
      <h4 style="font-weight: bold;">follower</h4>
      <div class="followlist" v-for="follower in userStore.FollowerList" :key="follower.serial">
        <i class="bi bi-person-heart">
          <br>&nbsp;&nbsp;&nbsp;{{ follower.name }}
        </i>
      </div>
    </div>
  </template>
  
  <script setup>
  import { useRoute, useRouter } from "vue-router";
  import { useCommentStore } from "@/stores/commentstore";
  import { useReplyStore } from "@/stores/replystore";
  import axios from "axios";
  const route = useRoute();
  const router = useRouter();
  const commentStore = useCommentStore();
  const replyStore = useReplyStore();
  import { useUserStore } from '@/stores/userstore';
  import { ref, computed, onMounted } from 'vue';
  
  const userStore = useUserStore();
  const isLoggedIn = ref(false);
  
  const name = ref('');
  
  const searchFriendList = () => {
      userStore.getUserByName(name.value, userSerial.value);
  };
  
  const userName = ref(''); // 사용자 이름
  const userSerial = ref(''); // 사용자 serial
  
  const checkLoginStatus = () => {
      const token = sessionStorage.getItem('access-token');
      if (token) {
          try {
              const tokenPayload = JSON.parse(decodeURIComponent(escape(atob(token.split('.')[1]))));
              userName.value = tokenPayload['name'];
              userSerial.value = tokenPayload['serial'];
              userId.value = tokenPayload['id'];
              isLoggedIn.value = true;
          } catch (error) {
              console.error('Failed to decode token:', error);
              isLoggedIn.value = false;
          }
      } else {
          isLoggedIn.value = false;
      }
  };
  
  const createFriend = (friend) => {
      userStore.createFollow(userStore.User.serial, friend.serial)
          .then(() => {
              userStore.getfollwingList(userSerial.value);
          })
          .catch(() => { })
  }
  
  const confirmFollowFriend = (friend) => {
      Swal.fire({
          title: '친구를 추가하시겠습니까?',
          icon: 'question',
          showCancelButton: true,
          confirmButtonText: '신청',
          cancelButtonText: '취소'
      }).then((result) => {
          if (result.isConfirmed) {
              createFriend(friend);
              Swal.fire('친구가 추가되었습니다!', '', 'success');
          }
      });
  }
  
  const deleteFriend = (following) => {
      axios
          .delete(`http://localhost:8080/api-user/follow/${userStore.User.serial}/${following.serial}`)
          .then(() => {
              userStore.getfollwingList(userSerial.value);
          })
          .catch(() => { })
  }
  
  const confirmCancelFollowFriend = (following) => {
      Swal.fire({
          title: '친구를 삭제하시겠습니까?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonText: '삭제',
          cancelButtonText: '취소'
      }).then((result) => {
          if (result.isConfirmed) {
              deleteFriend(following);
              Swal.fire('친구가 삭제되었습니다!', '', 'success');
          }
      });
  }
  
  const checkInfo = (following) => {
      Swal.fire({
          title: following.name + "의 정보를 보시겠습니까?",
          showCancelButton: true,
          cancelButtonText: '취소'
      }).then((result) => {
          if (result.isConfirmed) {
              commentStore.completeComment(following.serial);
              router.push({
                  name: 'MyComplete',
                  params: { userId: following.id }
              })
          }
      });
  }
  
  onMounted(() => {
      checkLoginStatus();
      userStore.getUserByid(route.params.userId);
      userStore.getfollwerList(userSerial.value);
      userStore.getfollwingList(userSerial.value);
      // userStore.getUserByid(`${route.params.userId}`);
      userStore.UserList = null;
  })
  
  </script>
  
  <style scoped>
  .searchbar {
      align-items: center;
      text-align: center;
  }
  
  .friendcontainer {
      display: flex;
      flex-direction: column;
      background-color: rgb(248, 249, 253);
      border: 3px solid rgba(43, 97, 247, 0.68);
      padding: 30px;
      font-family: "Gowun Dodum", sans-serif;
      font-size: larger;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .friend-item {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
  }
  
  .add-friend-icon {
      cursor: pointer;
      font-size: 1.2em;
      color: #362eced6;
      margin-left: 10px;
  }
  
  .followlist {
      margin-left: 25px;
      display: flex;
      justify-content: space-between;
      align-items: center;
  }
  
  .friendbutton {
      display: flex;
      gap: 10px;
  }
  
  .btn {
      background-color: #44af56;
      border: none;
      padding: 5px 10px;
      cursor: pointer;
      font-size: 1em;
  }
  
  .btn-danger {
      background-color: #dc3545;
      width: fit-content;
      font-size: 0.9em;
  }
  
  .btn-success {
      background-color: #44af56;
      width: fit-content;
      font-size: 0.9em;
  }
  
  .btn:hover {
      background-color: #1e8b32;
  }
  
  .btn-danger:hover {
      background-color: #c82333;
  }
  </style>
  