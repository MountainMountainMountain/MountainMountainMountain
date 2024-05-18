import { createRouter, createWebHistory } from "vue-router";
import MountainView from "@/views/MountainView.vue";
import CommentView from "@/views/CommentView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'mountain',
      component: MountainView,
      // children: [
      //   {
      //     path: '',
      //     name: 'mountainInfo',
      //     component: MountainInfo,
      //   },
      //   {
      //     path: 'mountaionDetail/book?',
      //     name: 'book',
      //     component: Book,
      //   },
      //   {
      //     path: 'location',
      //     name: 'mountainLocation',
      //     component: MountainLocation,
      //   },
      // ],
    },
    // {
    //   path: '/mypage',
    //   name: 'MyPage',
    //   component: MyPageView,
    //   redirect: '/myPage/A/MyInfo',
    //   children: [
    //     {
    //       path: 'myInfo',
    //       name: 'myInfo',
    //       component: MyInfo,
    //     },
    //     {
    //       path: 'myFriends',
    //       name: 'myFriends',
    //       component: MyFriends,
    //     },
    //     {
    //       path: 'myComplete',
    //       name: 'myComplete',
    //       component: MyComplete,
    //     },
    //     {
    //       path: 'modifyInfo',
    //       name: 'modifyInfo',
    //       component: ModifyInfo,
    //     },
    //   ],
    // },
  //   {
  //     path: '/comment',
  //     name: 'Comment',
  //     component: CommentView,
  //     children: [
  //       {
  //         path: 'create',
  //         name: 'CommentCreate',
  //         component: CommentCreate,
  //       },
  //       {
  //         path: 'detail',
  //         name: "CommentDetail",
  //         component: CommentDetail,
  //       },
  //       {
  //         path: 'list',
  //         name: 'CommentList',
  //         component: CommentDetail,
  //       },
  //       {
  //         path: 'modify',
  //         name: 'CommentModify',
  //         component: CommentModify,
  //       },
        
  //     ],
  //   },
  //   {
  //     path: '/chat',
  //     name: 'Chat',
  //     component: ChatView,
  //     children: [
  //       {
  //         path: 'detailChat',
  //         name: 'DetailChat',
  //         component: DetailChat,
  //       },
  //       {
  //         path: 'joinChat',
  //         name: 'JoinChat',
  //         component: JoinChat,
  //       },
  //     ],
  //   },
  //   {
  //     path: '/account',
  //     name: 'account',
  //     component: AccountView,
  //     redirect: '/account/login',
  //     children: [
  //       {
  //         path: 'login',
  //         name: 'Login',
  //         component: Login,
  //       },
  //       {
  //         path: 'signUp',
  //         name: 'SignUp',
  //         component: SignUp,
  //       },
  //     ],
  //   },
  //   {
  //     path: '/',
  //   },
  ],
}
);

export default router;
