<template>
    <div class="container">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" placeholder="제목" v-model="commentStore.Comment.title">
            <label for="title">Title</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control form-control-sm" placeholder="작성자" :value="userName" readonly>
            <label for="writer">작성자</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" placeholder="평점" v-model="commentStore.Comment.star">
            <label for="title">평점</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" placeholder="소요시간" v-model="commentStore.Comment.turnaround">
            <label for="title">소요 시간(분)</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" placeholder="칼로리" v-model="commentStore.Comment.calorie">
            <label for="title">칼로리</label>
        </div>
        <div class="form-floating mb-3">
            <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                v-model="commentStore.Comment.content"></textarea>
            <label for="content">Content</label>
        </div>
        <div class="d-flex justify-content-end">
            <button class="btn btn-outline-success mx-3" @click="confirmupdateBoard">수정</button>
            <button class="btn btn-outline-danger" @click="backButton">뒤로</button>
        </div>
    </div>
</template>

<script setup>
import { useCommentStore } from '@/stores/commentstore';
import { useUserStore } from '@/stores/userstore';
import { useMountainStore } from '@/stores/mountainstore';
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';
import Swal from 'sweetalert2';

const commentStore = useCommentStore();
const userStore = useUserStore();
const router = useRouter();

const token = sessionStorage.getItem('access-token');
const mountainStore = useMountainStore();

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
    } catch (error) {
      console.error('Failed to decode token:', error);
    }
  } else {
  }
};

const backButton = function () {
    router.go(-1);
};

const updateBoard = function () {
    commentStore.updateComment()
        .then(() => {
            router.push({
                name: 'CommentDetailPage',
                params: { commentSerial: commentStore.Comment.serial }
            });
        });
};

const confirmupdateBoard = () => {
    Swal.fire({
        title: '리뷰 수정이 완료되었습니다',
        icon: 'success',
        confirmButtonText: '확인'
    }).then((result) => {
        if (result.isConfirmed) {
            updateBoard();
        }
    });
};

onMounted(() => {
    checkLoginStatus();
});
</script>

<style scoped>

</style>
