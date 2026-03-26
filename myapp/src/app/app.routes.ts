import { mapToCanActivate, Routes } from '@angular/router';
// import { Login } from './login/login';
import { Signup } from './main_component/signup/signup';
import { Home } from './shared_component/home/home';
import { Contact } from './main_component/contact/contact';
import { About } from './main_component/about/about';
import { Login } from './main_component/login/login';

import { Sidebar } from './shared_component/sidebar/sidebar';
import { Layout } from './main_component/layout/layout';
import { Main } from './main_component/main/main';
import { authGuardGuard } from './guards/auth-guard-guard';
import { Unauthorized } from './main_component/unauthorized/unauthorized';
import { Admin } from './main_component/admin/admin';
import { UserList } from './main_component/user-list/user-list';
import { Logout } from './main_component/logout/logout';


export const routes: Routes = [

    { path: 'contact', component: Contact, title: 'Contact Page' },
    { path: 'dashboard', component: Layout, canActivate:[authGuardGuard],title: 'Dashboard Page'  },
    { path: '', component: Home, title: 'Home Page' },
    { path: 'login', component: Login, title: 'Login Page' },
    { path: 'signup', component: Signup, title: 'Signup Page' },
    { path: 'about', component: About, title: 'About Page' },
    {path:'admin',component:Admin,title:"Admin-page"},
    {path:'user',component:UserList,title:'user-page'},
    {path:'logout',component:Logout,title:'logout-page'},

    {path:'unauthorized',component:Unauthorized, title:'Unauthorized-page'},


];
