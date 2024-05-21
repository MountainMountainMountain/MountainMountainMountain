<template>
    <div>
        <!-- {{  CommentStore.createComment }} -->
        <div class="commentbox mx-auto">
            <h3>산이름</h3>
            <fieldset class="rate">
                <input type="radio" id="rating10" name="rating" value="10"><label for="rating10" title="5점"></label>
                <input type="radio" id="rating8" name="rating" value="8"><label for="rating8" title="4점"></label>
                <input type="radio" id="rating6" name="rating" value="6"><label for="rating6" title="3점"></label>
                <input type="radio" id="rating4" name="rating" value="4"><label for="rating4" title="2점"></label>
                <input type="radio" id="rating2" name="rating" value="2"><label for="rating2" title="1점"></label>
                <!-- <input type="radio" id="rating1" name="rating" value="1"><label class="half" for="rating1"
                    title="0.5점"></label> -->

            </fieldset>
            <div class="form-floating mb-3">
                <input type="text" class="form-control form-control-sm" id="title" placeholder="제목"
                    v-model="comment.title">
                <label for="title">제목</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control form-control-sm" id="writer" placeholder="작성자"
                    v-model="comment.writer">
                <label for="writer">작성자</label>
            </div>
            <div class="d-flex justify-content-between">
                <div class="form-floating mb-3" style="width: 48%;">
                    <input type="text" class="form-control form-control-sm" id="calorie" placeholder="칼로리"
                        v-model="comment.calorie">
                    <label for="calorie">칼로리</label>
                </div>
                <div class="form-floating mb-3" style="width: 48%;">
                    <input type="text" class="form-control form-control-sm" id="turnaround" placeholder="시간"
                        v-model="comment.turnaround">
                    <label for="turnaround">총 시간</label>
                </div>
            </div>
            <div class="form-floating mb-3">
                <textarea class="form-control" id="content" placeholder="내용" style="height: 500px"
                    v-model="comment.content"></textarea>
                <label for="content">내용</label>
            </div>
            <div class="d-flex justify-content-end">
                <button class="btn btn-outline-primary" @click="createComment">등록</button>
            </div>
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

<style scoped>
.commentbox {
    width: 50%;
    height: 800px;
    /* border: 1px solid black; */
    text-align: center;
    padding: 20px;
    box-shadow: 0px 0px 5px #888;
}


.rate {
    display: inline-block;
    border: 0;
    margin-right: 15px;
}

.rate>input {
    display: none;
}

.rate>label {
    float: right;
    color: #ddd
}

.rate>label:before {
    display: inline-block;
    font-size: 2rem;
    padding: .3rem .2rem;
    margin: 0;
    cursor: pointer;
    font-family: FontAwesome;
    content: "\f005 ";
}

.rate .half:before {
    content: "\f089 ";
    position: absolute;
    padding-right: 0;
}

.rate input:checked~label,
.rate label:hover,
.rate label:hover~label {
    color: #f73c32 !important;
}

.rate input:checked+.rate label:hover,
.rate input input:checked~label:hover,
.rate input:checked~.rate label:hover~label,
.rate label:hover~input:checked~label {
    color: #f73c32 !important;
}
</style>
