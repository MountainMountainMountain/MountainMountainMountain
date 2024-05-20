<template>
    <div>
        <h1>mountaininfo</h1>

        <div v-if="mountainStore.mountain && Object.keys(mountainStore.mountain).length > 0" class="d-flex justify-content-center">
            <div class="card" style="width: 30rem">

                <div class="card-body">
                    <div class="mb-3 d-flex justify-content-between align-items-center">
                        <h4 class="card-title">{{ mountainStore.mountain.name }} <span class="badge bg-danger">{{ mountainStore.mountain.state }}</span></h4>
                        <div class="d-flex justify-content-end">
                            <h6 class="card-subtitle mx-3 text-body-secondary">{{ mountainStore.mountain.altitude }}</h6>
                            <h6 class="card-subtitle text-body-secondary">{{ mountainStore.mountain.town }}</h6>
                        </div>
                    </div>
                    <p class="card-text ">
                        {{ mountainStore.mountain.course }}
                    </p>
                    <div class="d-flex justify-content-center">
                        <button class="mx-3 btn btn-outline-success" @click="moveUpdate">수정</button>
                        <button class="mx-3 btn btn-outline-danger" @click="deleteMountain">삭제</button>
                    </div>
                </div>
            </div>
        </div>

        <div v-else>
            Loading...
        </div>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useMountainStore } from '@/stores/mountainstore';
import { useRoute } from "vue-router";

const route = useRoute();
const mountainStore = useMountainStore();
// const mountainSerial = route.params.mountainSerial;
const mountainSerial = 1;

onMounted(() => {
    mountainStore.getMountainSerial(mountainSerial);
});

function moveUpdate() {
    // Implement the logic for updating the mountain
}

function deleteMountain() {
    mountainStore.deleteMountain(mountainSerial).then(() => {
        // Navigate to the list page or show a confirmation message
    });
}
</script>
