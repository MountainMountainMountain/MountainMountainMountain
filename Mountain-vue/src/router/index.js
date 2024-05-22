import { createRouter, createWebHistory } from "vue-router";

import MountainView from "@/views/MountainView.vue";
import MountainMainPage from "@/components/mountain/page/MountainMainPage.vue";
import MountainListPage from "@/components/mountain/page/MountainListPage.vue";
import MountainStatePage from "@/components/mountain/page/MountainStatePage.vue";
import MountainDetailPage from "@/components/mountain/page/MountainDetailPage.vue";

import MountainInfo from "@/components/mountain/component/MountainInfo.vue";
import MountainLocation from "@/components/mountain/component/MountainLocation.vue";
import Book from "@/components/mountain/component/Book.vue";

import MypageView from "@/views/MypageView.vue";
import MyPage from "@/components/mypage/page/MyPage.vue";
import MyInfo from "@/components/mypage/component/MyInfo.vue";
import MyComplete from "@/components/mypage/component/MyComplete.vue";
import MyFriends from "@/components/mypage/component/MyFriends.vue";

import CommentView from "@/views/CommentView.vue";
import CommentCreatePage from "@/components/comment/page/CommentCreatePage.vue";
import CommentDetailPage from "@/components/comment/page/CommentDetailPage.vue";
import CommentModifyPage from "@/components/comment/page/CommentModifyPage.vue";

import ChatView from "@/views/ChatView.vue";
import ChatList from "@/components/chat/page/ChatList.vue";
import DetailChat from "@/components/chat/page/DetailChat.vue";
import JoinChat from "@/components/chat/page/JoinChat.vue";

import AccountView from "@/views/AccountView.vue";
import LoginPage from "@/components/account/page/LoginPage.vue";
import SignUpPage from "@/components/account/page/SignUpPage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: MountainView,
      children: [
        {
          path: '',
          name: 'MountainMainView',
          component: MountainMainPage,
        },
        {
          path: ':state',
          name: 'MountainListPage',
          component: MountainListPage,
        },
        {
          path: 'state/:state',
          name: 'MountainStatePage',
          component: MountainStatePage
        },
        {
          path: 'mountain/:mountainSerial',
          name: 'MountainDetailPage',
          component: MountainDetailPage,
          children: [
            {
              path: 'mountainInfo',
              name: 'MountainInfo',
              component: MountainInfo,
            },
            {
              path: 'mountainLocation',
              name: 'MountainLocation',
              component: MountainLocation,
            },
            {
              path: 'book',
              name: 'Book',
              component: Book,
            },
          ]
        },
      ],
    },
    {
      path: '/mypage',
      name: 'MyPage',
      component: MyPage,
      children: [
        {
          path: 'myInfo',
          name: 'MyInfo',
          component: MyInfo,
        },
        {
          path: 'myFriends',
          name: 'MyFriends',
          component: MyFriends,
        },
        {
          path: 'myComplete',
          name: 'MyComplete',
          component: MyComplete,
        },
        // { 이거는 모달이나 그 자체에서 바꿀까?
        //   path: 'modifyInfo',
        //   name: 'modifyInfo',
        //   component: ModifyInfo,
        // },
      ],
    },
    {
      path: '/comment',
      name: 'Comment',
      component: CommentView,
      children: [
        {
          path: 'commentCreate/:mountainSerial',
          name: 'CommentCreatePage',
          component: CommentCreatePage,
        },
        {
          path: 'commentDetail/:commentSerial',
          name: "CommentDetailPage",
          component: CommentDetailPage,
        },
        {
          path: 'commentModify/:commentSerial',
          name: 'CommentModifyPage',
          component: CommentModifyPage,
        },
      ],
    },
    {
      path: '/chat',
      name: 'Chat',
      component: ChatView,
      children: [
        {
          path: 'chatList',
          name: 'ChatList',
          component: ChatList,
        },
        {
          path: 'detailChat',
          name: 'DetailChat',
          component: DetailChat,
        },
        {
          path: 'joinChat',
          name: 'JoinChat',
          component: JoinChat,
        },
      ],
    },
    {
      path: '/account',
      name: 'account',
      component: AccountView,
      children: [
        {
          path: 'login',
          name: 'Login',
          component: LoginPage,
        },
        {
          path: 'signUp',
          name: 'SignUp',
          component: SignUpPage,
        },
      ],
    }
  ],
}
);

export default router;
