import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovementComponent } from './movement/movement.component';
import { FarmComponent } from './farm/farm.component';

const routes: Routes = [
  {path: '', component: MovementComponent},
  {path: 'farms', component: FarmComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
