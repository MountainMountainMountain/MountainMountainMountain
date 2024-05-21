<template>
    <div>
        <h1>commentcreate</h1>
        <!-- {{  CommentStore.createComment }} -->
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="title" placeholder="제목" v-model="comment.title">
            <label for="title">Title</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="writer" placeholder="작성자" v-model="comment.writer">
            <label for="writer">Writer</label>
        </div>
        <div class="form-floating mb-3">
            <textarea class="form-control" id="content" placeholder="내용" style="height: 200px"
                v-model="comment.content"></textarea>
            <label for="content">Content</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="calorie" placeholder="칼로리" v-model="comment.calorie">
            <label for="calorie">Calorie</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="turnaround" placeholder="시간" v-model="comment.turnaround">
            <label for="turnaround">Turnaround</label>
        </div>
        <div class="d-flex justify-content-end">
            <button class="btn btn-outline-primary" @click="createComment">등록</button>
        </div>

    </div>
</template>

<script setup>
import { useCommentStore } from '@/stores/commentstore';
import { useMountainStore } from '@/stores/mountainstore';
import { useUserStore } from '@/stores/userstore'
import { ref, onMounted } from 'vue';

const CommentStore = useCommentStore();
const mountainStore = useMountainStore();
const userStore = useUserStore();

const comment = ref({
    title: "",
    writer: "",
    user_serial: "",
    content: "",
    calorie: "",
    turnaround: "",
})

const createComment = function () {
    CommentStore.createComment(comment.value)
};



</script>

<style scoped></style>