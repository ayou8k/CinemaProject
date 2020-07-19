import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CinemaComponent} from "./cinema/cinema.component";


const routes: Routes = [
  {
    //adding routers ; disabling tlsint for whitespaces and quotes issues
    path:'cinema',
    component: CinemaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
