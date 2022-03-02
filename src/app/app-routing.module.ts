import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrazioneComponent } from './registrazione/registrazione.component';
import { HomepageComponent } from './homepage/homepage.component';
const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registrazione',
    component: RegistrazioneComponent
  },
  {
    path: 'homepage',
    component: HomepageComponent
  },
  // {
  //   path: '',
  //   redirectTo: 'login',
  //   pathMatch: 'full'
  // }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
