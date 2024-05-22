<template>
    <div class="review-container">
        <hr>
        <h1>{{ mountainStore.mountain.name }}</h1>
        <br>
        <div class="main-container">
            <div class="details-container">
                <div class="detail-item">
                    <strong>제목</strong>
                    <span class="detail-content">{{ commentStore.Comment.title }}</span>
                    <span class="view-count">조회 수 {{ commentStore.Comment.viewCount }}</span>
                </div>
                <div class="detail-item">
                    <strong>작성자</strong>
                    <span class="detail-content">{{ commentStore.Comment.name }}</span>
                </div>
                <div class="detail-item">
                    <strong>작성시간</strong>
                    <span class="detail-content">{{ formatDate(commentStore.Comment.regDate) }}</span>
                </div>

                <div class="detail-item content-item">
                    <strong>내용</strong>
                    <div class="content">
                        <img v-if="commentStore.Comment.image" :src="commentStore.Comment.image" alt="Comment Image"
                            class="content-image">
                        <p>{{ commentStore.Comment.content }}</p>
                    </div>
                </div>
                <div v-if="commentStore.Comment.userSerial == userSerial" class="detail-item">
                    <button id="modifybutton">
                        <RouterLink
                            :to="{ name: 'CommentModifyPage', params: { commentSerial: route.params.commentSerial } }">
                            리뷰 수정
                        </RouterLink>
                    </button>
                    <button @click="confirmDeleteComment">리뷰 삭제</button>
                </div>
            </div>
            <div class="replybox">
                <ul class="reply-list">
                    <li v-for="reply in replyStore.ReplyList" :key="reply.serial" class="reply-item">
                        <span id="replyname">{{ reply.name }}</span>
                        <br>
                        <span>{{ reply.content }}</span>
                        <button v-if="reply.userSerial == userSerial" @click="confirmDeleteReply(reply)">댓글 삭제</button>

                        <button v-if="reply.userSerial == userSerial" @click="confirmDeleteReply(reply)">등록</button>
                    </li>
                </ul>
                <button v-if="token !== null" @click="showReplyForm = true">댓글 작성하기</button>
                <div v-if="showReplyForm" class="reply-form">
                    <textarea v-model="reply.content" placeholder="댓글 내용을 입력하세요"></textarea>
                    <button @click="postReply">댓글 작성</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { useCommentStore } from "@/stores/commentstore";
import { useReplyStore } from "@/stores/replystore";
import { useMountainStore } from "@/stores/mountainstore";
import Swal from 'sweetalert2';
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useUserStore } from '@/stores/userstore';

const route = useRoute();
const router = useRouter();
const commentStore = useCommentStore();
const replyStore = useReplyStore();
const mountainStore = useMountainStore();
const userStore = useUserStore();

const userName = ref('');
const userSerial = ref('');
const token = sessionStorage.getItem('access-token');
const showReplyForm = ref(false);
const reply = ref({
    userSerial: '',
    commentSerial: '',
    content: ''
});

const checkUserSerial = () => {
    if (token !== null) {
        const payload = JSON.parse(atob(token.split('.')[1]));
        userName.value = payload.name;
        userSerial.value = payload.serial;
        userStore.getUserByid(userSerial.value);
        reply.value.userSerial = userSerial.value;
        reply.value.commentSerial = route.params.commentSerial;
    }
};

const deleteComment = function () {
    axios
        .delete(`http://localhost:8080/api-comment/comment/delete/${route.params.commentSerial}`)
        .then(() => {
            router.push({
                name: "MountainInfo",
                params: { mountainSerial: commentStore.Comment.mountainSerial }
            });
        })
        .catch(() => { });
};

const deleteReply = function (reply) {
    axios
        .delete(`http://localhost:8080/api-reply/${reply.serial}`)
        .then(() => {
            replyStore.getReplyList(route.params.commentSerial);
        })
        .catch(() => { });
};

const confirmDeleteComment = () => {
    Swal.fire({
        title: '리뷰를 삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteComment();
            Swal.fire('삭제되었습니다!', '', 'success');
        }
    });
};

const confirmDeleteReply = (reply) => {
    Swal.fire({
        title: '댓글을 삭제하시겠습니까?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteReply(reply);
            Swal.fire('삭제되었습니다!', '', 'success');
        }
    });
};

onMounted(() => {
    commentStore.getComment(route.params.commentSerial);
    replyStore.getReplyList(route.params.commentSerial);
    checkUserSerial();
});

const postReply = function () {
    replyStore.createReply(reply.value)
        .then(() => {
            replyStore.getReplyList(route.params.commentSerial);
        });
    showReplyForm.value = false;
};

const formatDate = (dateString) => {
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = (1 + date.getMonth()).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  return `${year}년 ${month}월 ${day}일`;
};

</script>

<style scoped>
.review-container {
    width: 80%;
    margin: 0 auto;
    margin-top: 20px;
    text-align: center;
}

.main-container {
    display: flex;
    justify-content: space-between;
}

.details-container {
    width: 70%;
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    margin-right: 20px;
}

.detail-item {
    width: 100%;
    margin-bottom: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.detail-content {
    flex-grow: 1;
    text-align: left;
    margin-left: 10px;
}

.title-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.title-item span {
    flex-grow: 1;
    text-align: left;
    margin-left: 10px;
}

.view-count {
    margin-left: auto;
    margin-right: 15px;
}

.content-item {
    flex-direction: column;
    width: 100%;
}

.content {
    width: 100%;
    overflow-y: auto;
    height: 400px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
}

.content-image {
    max-width: 100%;
    height: auto;
    display: block;
    margin-bottom: 10px;
}

.replybox {
    width: 30%;
    background-color: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    overflow-y: auto;
    max-height: 500px;
    /* Adjust as needed */
}

.reply-list {
    list-style-type: none;
    padding: 0;
}

.reply-item {
    margin: 10px 0;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #fff;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

#replyname {
    align-self: flex-start;
}

.reply-form {
    margin-top: 20px;
}

.reply-form textarea {
    width: 100%;
    height: 100px;
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.reply-form button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    cursor: pointer;
}

.reply-form button:hover {
    background-color: #0056b3;
}

#modifybutton {
    align-items: center;
}

.reply-item button {
    align-self: center;
    margin-top: 10px;
}
</style>