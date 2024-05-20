import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

const REST_COMMENT_API = `http://localhost:8080/api-comment`

export const useCommentStore = defineStore('comment', () => {

  const Comment = ref({})
  const CommentList = ref([])

  const getCommentList = function (id) {
    axios.get(`${REST_COMMENT_API}/`)
  }


  return { Comment, CommentList }
})
