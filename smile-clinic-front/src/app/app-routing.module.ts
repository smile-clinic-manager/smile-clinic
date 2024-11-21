import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TestingBackComponent } from './testing-back/testing-back.component';

export const routes: Routes = [{ path: '', component: TestingBackComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
