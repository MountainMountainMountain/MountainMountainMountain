<template>
    <div>
        <h1>friends</h1>


        <div class="search">
            <div>
                <label><input type="text" class="form-control" v-model="name" placeholder="이름을 입력하세요" /></label>
            </div>
            <div>
                <button class="btn btn-primary" @click="searchFriendList"><i class="fas fa-search"
                        style="color: white"></i></button>
            </div>
        </div>
        
        <div v-for="friend in userStore.UserList" :key="friend.serial">
            {{ friend.name }}
        </div>
        <h1>following</h1>

        <div v-for="friend in userStore.UserList" :key="friend.serial">
            {{ friend.name }}
            <button @click="confirmFollowFriend(friend)">친구 신청하기</button>
        </div>
        <h1>following</h1>

        <div v-for="following in userStore.FollowingList" :key="following.serial">
            <!-- {{ following.serial }} -->
            {{ following.name }}
            <button @click="confirmCancelFollowFriend(following)">친구 삭제하기</button>
            <button @click="checkInfo(following, mountainList)">정보 보기</button>
        </div>
        <h1>follower</h1>
        <div v-for="follower in userStore.FollowerList" :key="follower.serial">
            <!-- {{ follower.serial }} -->
            {{ follower.name }}
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
const name = ref('')

const mountainList = ref([
    {
        'name': '한라산',
    },
    {
        'name': '관악산',
    }
])

const name = ref('')

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

const checkInfo = (following, mountainList) => {
    Swal.fire({
        title: following.name,
        text: '등산한 산의 정보' + mountainList.name,
        showCancelButton: true,
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteFriend(following);
            Swal.fire('친구가 삭제되었습니다!', '', 'success');
        }
    });
}

onMounted(() => {
    checkLoginStatus();
    userStore.getfollwerList(userSerial.value);
    userStore.getfollwingList(userSerial.value);
    userStore.getUserByid(`${route.params.userId}`);
    userStore.UserList = null;
})

</script>

<style scoped></style>