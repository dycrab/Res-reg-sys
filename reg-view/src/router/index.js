import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
    // { path: '/', redirect: '/home' },
    {
      path: '/test',
      component:() =>
          import('../components/Template')
    },
    {
        path: '/',
        component: () =>
            import ('../components/HomeView.vue'),
        redirect: '/department',
        children: [{
                path: '/department',
                component: () =>
                    import ('../components/Department.vue'),
                    meta:{ title:'医生选择页面'}
            },
            {
                path: '/about/:doctorId/:doctorName/:partType',
                component: () =>
                    import ('../components/AboutView.vue'),
                meta:{ title:'信息填写页面'}
            },
            {
                path:'/RegisterDisplay/:name/:idCard/:doctorName/:registerDate',
                component: ()=>
                    import('../components/RegisterDisplay'),
                meta:{ title:'预约展示页面'}
            }
        ],

    },
    {
        path:'/admin',
        component: ()=>
            import('../components/AdminHome'),
        meta:{ title:'管理员登录页面'},
        redirect: '/adminLogin',
        children: [
            {
                path: '/adminLogin',
                component: ()=>
                    import('../components/AdLogin'),
                meta:{ title:'管理员登录页面'},

            },
            {
                path: '/adminPage',
                name:'adminPage',
                component: ()=>
                    import('../components/AdminPage'),
                meta:{ title:'管理员页面'},
                children:[
                    {
                        path:'/RegisterDataView',
                        component:()=>
                            import("../components/admin/RegisterDataView"),
                        meta:{title:'挂号信息展示'}
                    },
                    {
                        path:'/RegisterDataByIdCard',
                        component:()=>
                            import("../components/admin/RegisterDataByIdCard"),
                        meta:{title:'按挂号人身份证搜索挂号数据'}
                    },
                    {
                        path:'/RegisterDataByDoctor',
                        component:()=>
                            import("../components/admin/RegisterDataByDoctor"),
                        meta:{title:'按医生姓名搜索挂号数据'}
                    },
                    {
                        path:'/RegisterDataByDate',
                        component:()=>
                            import("../components/admin/RegisterDataByDate"),
                        meta:{title:'按日期搜索挂号数据'}
                    },
                    {
                        path:'/DoctorInfoView',
                        component:()=>
                            import("../components/admin/DoctorInfoView"),
                        meta:{title:'医生信息展示'}
                    },
                    {
                        path:'/DoctorInfoSearch',
                        component:()=>
                            import("../components/admin/DoctorInfoSearch"),
                        meta:{title:'医生信息管理'}
                    },
                    {
                        path:'/DoctorInfoAdd',
                        component:()=>
                            import("../components/admin/DoctorInfoAdd"),
                        meta:{title:'医生信息管理'}
                    },
                    {
                        path:'/DoctorInfoModify',
                        component:()=>
                            import("../components/admin/DoctorInfoModify"),
                        meta:{title:'医生信息管理'}
                    },
                    {
                        path:'/DoctorInfoDelete',
                        component:()=>
                            import("../components/admin/DoctorInfoDelete"),
                        meta:{title:'医生信息管理'}
                    },


                ]
            }

        ]
    },
    {
        path: '/doctor',
        component:()=>
            import("../components/DoctorHome"),
        redirect: '/doctorLogin',
        meta:{title:'医生登录'} ,
        children: [
            {
                path: '/doctorLogin',
                component:()=>
                    import("../components/DoctorLogin"),
                meta: {title: '医生登录页面'}
            },
            {
                path: '/CallPatient/:doctorId',
                component:()=>
                    import("../components/CallPatient"),
                meta: {title: '医生问诊系统'}
            }
        ]


    }
]

const router = new VueRouter({
    mode: 'history',
    routes,
})
// 挂载路由导航守卫
// router.beforeEach((to, from, next) => {
//     // to 将要访问的路径
//     // from 代表从哪个路径跳转而来
//     // next 是一个函数，表示放行
//     // next() 放行  next('/login') 强制跳转
//     // if (to.path === '/doctorLogin' || to.path === '/adminLogin') return next()
//     // // 获取token
//     // const tokenStr = window.sessionStorage.getItem('token')
//     // if (!tokenStr) return next('/login')
//     // next()
// })
export default router