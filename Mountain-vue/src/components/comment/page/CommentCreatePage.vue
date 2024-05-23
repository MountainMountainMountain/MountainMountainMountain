<template>
    <div>
        <div class="commentbox mx-auto">
            <h3>{{ mountainStore.mountain.name }}</h3>

            <div class="rate">
                <input type="radio" id="rating5" name="rating" value="5" v-model="comment.star"
                    @click="updateStar(5)"><label for="rating5" title="5점"></label>
                <input type="radio" id="rating4" name="rating" value="4" v-model="comment.star"
                    @click="updateStar(4)"><label for="rating4" title="4점"></label>
                <input type="radio" id="rating3" name="rating" value="3" v-model="comment.star"
                    @click="updateStar(3)"><label for="rating3" title="3점"></label>
                <input type="radio" id="rating2" name="rating" value="2" v-model="comment.star"
                    @click="updateStar(2)"><label for="rating2" title="2점"></label>
                <input type="radio" id="rating1" name="rating" value="1" v-model="comment.star"
                    @click="updateStar(1)"><label for="rating1" title="1점"></label>

            </div>

            <div class="form-floating mb-3">
                <input type="text" class="form-control form-control-sm" id="title" placeholder="제목"
                    v-model="comment.title">
                <label for="title">제목</label>
            </div>
            <div class="form-floating mb-3">
                <input type="text" class="form-control form-control-sm" placeholder="작성자" :value="userName" readonly>
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
                <button class="btn btn-outline-primary me-2" @click="confirmCreateComment">등록</button>
                <button class="btn btn-light back-button" @click="backButton">취소</button>
            </div>

        </div>
    </div>
</template>

<script setup>
import { useCommentStore } from '@/stores/commentstore';
import { useMountainStore } from '@/stores/mountainstore';
import { useUserStore } from '@/stores/userstore'
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from "vue-router";
import Swal from 'sweetalert2';

const CommentStore = useCommentStore();
const mountainStore = useMountainStore();
const userStore = useUserStore();
const route = useRoute();
const router = useRouter();


const comment = ref({
    title: "",
    userSerial: "",
    mountainSerial: "",
    content: "",
    calorie: "",
    turnaround: "",
    star: 0,
})

const userName = ref(''); // 사용자 이름
const userSerial = ref(''); // 사용자 serial

const checkUserSerial = () => {
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

const createComment = function () {
    comment.value.userSerial = userSerial.value;
    comment.value.writer = userSerial.value;
    comment.value.mountainSerial = route.params.mountainSerial;
    console.log(comment.value)
    CommentStore.createComment(comment.value)
        .then(() => {
            router.push({
                name: 'MountainInfo',
                params: { mountainSerial: route.params.mountainSerial }
            });
        })
};

const updateUserPoint = function () {
    userStore.updateUserPoint(userSerial.value, mountainStore.mountain.point)
}

const confirmCreateComment = () => {
    Swal.fire({
        title: '리뷰를 등록하시겠습니까?',
        text: "작성한 리뷰를 등록하려면 예를 클릭하세요.",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '예',
        cancelButtonText: '아니오'
    }).then((result) => {
        if (result.isConfirmed) {
            createComment();
            updateUserPoint();
            Swal.fire('등록 완료!', '리뷰가 성공적으로 등록되었습니다.', 'success');
        }
    });
};

const updateStar = (value) => {
    comment.value.star = value;
};

const backButton = function () {
    router.go(-1);
    // router.back();
};

onMounted(() => {
    mountainStore.getMountainSerial(`${route.params.mountainSerial}`);
    checkUserSerial();
});
</script>

<style scoped>
.commentbox {
    width: 50%;
    text-align: center;
    padding: 20px;
    box-shadow: 0px 0px 5px #888;
    margin-top: 20px;
}

.form-floating.mb-3 {
    margin-bottom: 1rem !important;
}

.content-textarea {
    height: 500px;
    width: 100%;
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
    color: #ddd;
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

.back-button {
    margin-left: 10px;
}
</style>
